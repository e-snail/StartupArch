package roff.startuparch.core.di.annotation;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by wuyongbo on 16-6-8.
 /**
 * A scoping annotation to permit objects whose lifetime should
 * conform to the life of the BroadcastReceiver to be memorized in the
 * correct component.
 */
@Scope
@Retention(RUNTIME)
public @interface PerBroadcastReceiver {
}
