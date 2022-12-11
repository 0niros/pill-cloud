package tech.pillcloud.rpc.context.scanner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.core.MethodIntrospector;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AnnotatedElementUtils;
import tech.pillcloud.rpc.annotation.PillRpcServer;
import tech.pillcloud.rpc.context.PillRpcContext;
import tech.pillcloud.rpc.handler.impl.PillRpcHandler;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/10 22:23
 */

@Slf4j
public class PillRpcServerBeanScanner implements SmartInitializingSingleton {

    @Override
    public void afterSingletonsInstantiated() {
        initRpcServerBean(PillRpcContext.applicationContext);
    }

    private void initRpcServerBean(ApplicationContext applicationContext) {
        if (applicationContext == null) {
            return ;
        }

        String[] beanNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanName : beanNames) {
            Object bean = null;
            Lazy lazyBean = applicationContext.findAnnotationOnBean(beanName, Lazy.class);
            if (lazyBean != null) {
                log.info("[Scanner] => Ignore Lazy Bean:{} ", beanName);
                continue;
            }
            bean = applicationContext.getBean(beanName);

            Map<Method, PillRpcServer> annotatedMethods = null;
            try {
                annotatedMethods = MethodIntrospector.selectMethods(bean.getClass(),
                        (MethodIntrospector.MetadataLookup<PillRpcServer>) method -> AnnotatedElementUtils.findMergedAnnotation(method, PillRpcServer.class));
            } catch (Throwable ex) {
                log.error("[Scanner] => Scan bean:{} error!", beanName);
            }

            if (annotatedMethods == null || annotatedMethods.isEmpty()) {
                continue;
            }

            for (Map.Entry<Method, PillRpcServer> serverEntry : annotatedMethods.entrySet()) {
                Method executeMethod = serverEntry.getKey();
                PillRpcServer pillRpcServer = serverEntry.getValue();
                registryServerMethod(pillRpcServer, bean, executeMethod);
            }
        }
    }

    public void registryServerMethod(PillRpcServer pillRpcServer, Object bean, Method executeMethod) {
        if (pillRpcServer == null) {
            return;
        }

        if (executeMethod.getParameterCount() > 1) {
            log.error("[Registry] => Method param > 1");
            return ;
        }

        String serviceName = pillRpcServer.value();
        String methodName = executeMethod.getName();
        Class<?> paramClazz = executeMethod.getParameterTypes()[0];
        Class<?> resultClazz = executeMethod.getReturnType();
        if (serviceName == null || serviceName.trim().length() == 0) {
            log.info("[Registry] => Empty ServerName, Use method name:{}", methodName);
            serviceName = methodName;
        }
        if (PillRpcContext.pillRpcServerHandlers.containsKey(serviceName)) {
            log.warn("[Registry] => ServiceName:{} exists!", serviceName);
            return ;
        }
        executeMethod.setAccessible(true);
        PillRpcContext.pillRpcServerHandlers.put(serviceName, new PillRpcHandler(bean, executeMethod, paramClazz, resultClazz));
    }
}
