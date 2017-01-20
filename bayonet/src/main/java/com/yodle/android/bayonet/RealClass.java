package com.yodle.android.bayonet;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Inherited
public @interface RealClass {
}
