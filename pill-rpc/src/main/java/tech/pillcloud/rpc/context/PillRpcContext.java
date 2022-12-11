package tech.pillcloud.rpc.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import tech.pillcloud.rpc.handler.IPillRpcHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/10 22:05
 */

@Component
@Data
public class PillRpcContext implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    public static ConcurrentMap<String, IPillRpcHandler> pillRpcServerHandlers = new ConcurrentHashMap<>();

    public static Map<String, IPillRpcHandler> pillRpcClientHandlers = new ConcurrentHashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        PillRpcContext.applicationContext = applicationContext;
    }
}
