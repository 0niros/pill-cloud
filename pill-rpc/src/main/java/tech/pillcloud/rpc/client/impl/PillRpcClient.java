package tech.pillcloud.rpc.client.impl;

import tech.pillcloud.rpc.client.IPillRpcClient;
import tech.pillcloud.rpc.common.PillResult;

import java.lang.reflect.Method;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/11 23:05
 */
public class PillRpcClient<K, V> implements IPillRpcClient<K, V> {

    private final Object target;

    private final Method method;

    private final String address;

    private final Class<?> paramClazz;

    private final Class<?> resultClazz;

    public PillRpcClient(Object target, Method method, String address, Class<?> paramClazz, Class<?> resultClazz) {
        this.target = target;
        this.method = method;
        this.address = address;
        this.paramClazz = paramClazz;
        this.resultClazz = resultClazz;
    }


    @Override
    public PillResult execute(K k) {
        return null;
    }
}
