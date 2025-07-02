package de.shurablack.jwsa.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)
/**
 * Indicates that the annotated API is unstable and may change in future releases.
 */
public @interface Unstable {
}
