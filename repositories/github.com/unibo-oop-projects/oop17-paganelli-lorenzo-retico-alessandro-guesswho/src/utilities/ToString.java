package utilities;

import java.lang.annotation.*;

/**
 * ToString Annotation.
 * Can be used to set correct articles to the different Traits, the article will be used to compose questions about
 * the Trait.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ToString {

    /**
     * @return the article
     */
    String article() default "";
}
