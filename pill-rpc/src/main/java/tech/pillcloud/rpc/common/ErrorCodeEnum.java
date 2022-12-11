package tech.pillcloud.rpc.common;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/11 22:45
 */
public enum ErrorCodeEnum {

    SUCCESS(200, "Operation successfully!"),

    FAIL(500, "Operation failed!");

    private Integer errorCode;

    private String desc;


    ErrorCodeEnum(Integer i, String s) {
        this.errorCode = i;
        this.desc = s;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getDesc() {
        return desc;
    }
}
