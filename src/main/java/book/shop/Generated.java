package book.shop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/*
 * References : 
 * 1. https://www.baeldung.com/jacoco-report-exclude
 * 2. https://github.com/eugenp/tutorials/tree/master/gradle-modules/gradle/gradle-jacoco/src/main/java/com/baeldung/generated
*/
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Generated {
}