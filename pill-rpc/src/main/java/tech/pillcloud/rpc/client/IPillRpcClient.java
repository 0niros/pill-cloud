package tech.pillcloud.rpc.client;

import tech.pillcloud.rpc.common.PillResult;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/11 23:03
 */
public interface IPillRpcClient<K, V> {

    PillResult execute(K k);

}
