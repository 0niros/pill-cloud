package tech.pillcloud.rpc.annotation;

import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;

/**
 * @description: desc here
 * @author: 南柯先森
 * @email: 1311765133@qq.com
 * @date: 2022/12/10 21:59
 */

@Retention(RUNTIME)
@Target(TYPE)
public @interface PillRpcClient {

    String value();

    String address();

}
