package tech.pillcloud.rpc.common;

import lombok.AllArgsConstructor;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/10 22:12
 */

@AllArgsConstructor
public class PillResult {

    private Integer errorCode;

    private Object result;

    public static PillResult successResult(Object obj) {
        return new PillResult(ErrorCodeEnum.SUCCESS.getErrorCode(), obj);
    }

    public static PillResult failResult(String failDesc) {
        return new PillResult(ErrorCodeEnum.FAIL.getErrorCode(), failDesc);
    }

}
