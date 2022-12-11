package tech.pillcloud.rpc.handler.impl;

import tech.pillcloud.rpc.common.PillResult;
import tech.pillcloud.rpc.handler.IPillRpcHandler;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/11 20:54
 */
public class PillRpcHandler implements IPillRpcHandler {

    private final Object target;
    private final Method method;
    private final Class<?> paramClazz;
    private final Class<?> resultClazz;


    public PillRpcHandler(Object target, Method method, Class<?> paramClazz, Class<?> resultClazz) {
        this.target = target;
        this.method = method;
        this.paramClazz = paramClazz;
        this.resultClazz = resultClazz;
    }

    @Override
    public PillResult execute(Object param) throws InvocationTargetException, IllegalAccessException {
        if (!paramClazz.isInstance(param)) {
            return PillResult.failResult("[Execute] => Execute error case of wrong param!");
        }

        Object result = method.invoke(target, param);

        if (!resultClazz.isInstance(result)) {
            return PillResult.failResult("[Execute] => Execute error case of wrong result!");
        }

        return PillResult.successResult(result);
    }
}
