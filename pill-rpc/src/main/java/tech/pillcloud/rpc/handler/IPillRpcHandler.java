package tech.pillcloud.rpc.handler;

import tech.pillcloud.rpc.common.PillResult;

import java.lang.reflect.InvocationTargetException;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/10 22:11
 */
public interface IPillRpcHandler {

    PillResult execute(Object obj) throws InvocationTargetException, IllegalAccessException;

}
