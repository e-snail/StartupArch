package roff.startuparch.core.di.annotation;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wuyongbo on 16-3-22.
 */
/**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the Activity to be memorized in the
 * correct component.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {}
