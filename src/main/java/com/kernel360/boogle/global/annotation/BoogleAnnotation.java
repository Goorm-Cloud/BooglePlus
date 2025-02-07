package com.kernel360.boogle.global.annotation;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
//@EnableScheduling
@EnableAspectJAutoProxy
//@EnableBatchProcessing
@EnableJpaRepositories(basePackages = "com.kernel360.boogle")
public @interface BoogleAnnotation {
}

