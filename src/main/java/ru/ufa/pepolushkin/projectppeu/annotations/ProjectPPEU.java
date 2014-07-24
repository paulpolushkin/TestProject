/**
 * Аннотация без свойств
 * используется для всех пакетов библиотеки ProjectPPEU
 * используйте Reflection для проверки является ли данный класс классом библиотеки ProjectPPEU
 */

package ru.ufa.pepolushkin.projectppeu.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Pavel Polushkin
 * @since 23.07.2014 (1.0)
 * @version 23.07.2014
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PACKAGE)
public @interface ProjectPPEU {

}
