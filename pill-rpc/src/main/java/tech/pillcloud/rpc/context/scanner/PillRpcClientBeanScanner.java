package tech.pillcloud.rpc.context.scanner;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import tech.pillcloud.rpc.annotation.PillRpcClient;
import tech.pillcloud.rpc.context.PillRpcContext;

import java.util.Map;


/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/11 23:07
 */

@Slf4j
public class PillRpcClientBeanScanner implements SmartInitializingSingleton {
    @Override
    public void afterSingletonsInstantiated() {
        initRpcClientBean(PillRpcContext.applicationContext);
    }

    private void initRpcClientBean(ApplicationContext applicationContext) {
        if (applicationContext == null) {
            return ;
        }

        Map<String, Object> pillRpcClientBeans = applicationContext.getBeansWithAnnotation(PillRpcClient.class);
        for (var obj : pillRpcClientBeans.entrySet()) {
            Class<?> clientClazz = obj.getValue().getClass();
            PillRpcClient clientAnnotation = clientClazz.getAnnotation(PillRpcClient.class);
            String serviceName = clientAnnotation.value();
            if (serviceName == null || serviceName.trim().length() == 0) {
                serviceName = clientClazz.getSimpleName();
            }



        }
    }

}
