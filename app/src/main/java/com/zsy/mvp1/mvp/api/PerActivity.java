package com.zsy.mvp1.mvp.api;

/**
 * Created by 24275 on 2016/10/8.
 */

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
