package cn.luim.boot.starter.security.annotation;

import java.lang.annotation.*;

/**
 * 标记允许匿名访问
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Anonymous {
}
