package edu.nwu.anisc.hgmonitor.annotation;

import java.lang.annotation.*;

/**
 * @author zizhou
 * @version 1.0.0
 * @date 2024-10-18 15:14
 * @since JDK 17
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperLog {
    String scope() default "";

    String content() default "";

    String level() default "";
}
