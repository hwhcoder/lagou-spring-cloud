package com.example.user;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@Inherited作用是，使用此注解声明出来的自定义注解，在使用此自定义注解时，如果注解在类上面时，子类会自动继承此注解，否则的话，子类不会继承此注解。这里一定要记住，使用Inherited声明出来的注解，只有在类上使用时才会有效，对方法，属性等其他无效。
@Inherited
//通过 @Import 导入自动配置类，开启自动装配工作
@Import({UserAutoConfigure.class})
public @interface EnableUserClient {

}
