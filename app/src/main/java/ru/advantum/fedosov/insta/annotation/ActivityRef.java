package ru.advantum.fedosov.insta.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Collect common reference information about base features, usually implemented in every activity
 * <pre>
 *     <code>
 *      {@literal @}ActivityRef(resource = R.layout.activity_main,
 *              toolbarResId = R.id.toolbar, busEnabled = true, knifeEnabled = true,
 *              navigation = true, isHomeAsUp = true)
 *     </code>
 * </pre>
 *
 * @see FragmentRef
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface ActivityRef {

    /**
     * res id for contentView
     */
    int resource() default 0;

    /**
     * res id for toolbar
     */
    int toolbarResId() default 0;

    /**
     * if true, activity will be listening for events
     */
    boolean busEnabled() default false;

    /**
     * if true, butterKnife will inject Activity
     */
    boolean knifeEnabled() default false;

    /**
     * if true, navigation panel will appear to the left of the screen
     */
    boolean navigation() default false;


    /**
     * if true, home button will be shown as up
     */
    boolean isHomeAsUp() default false;
}
