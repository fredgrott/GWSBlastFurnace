package com.github.shareme.gwsblastfurnace.library.maybe;

import android.support.annotation.NonNull;

import com.github.shareme.gwsblastfurnace.library.func.Function;

/**
 * Created by fgrott on 11/27/2015.
 */
class Just<A> extends Maybe<A> {
    private final A mTarget;

    Just(@NonNull A target) {
        mTarget = target;
    }

    @Override
    public String toString() {
        return "Just{" + mTarget + "}";
    }

    @Override
    public A get() {
        return mTarget;
    }

    @Override
    public A getOrElse(A defaultValue) {
        return get();
    }

    @Override
    public void foreach(Function.F<? super A> func) {
        func.invoke(mTarget);
    }

    @Override
    public <B> Maybe<B> flatMap(Function.F1<? super A, ? extends Maybe<B>> func) {
        return func.invoke(mTarget);
    }

    @Override
    public Maybe<A> filter(Function.F1<? super A, Boolean> func) {
        if (func.invoke(mTarget)) {
            return this;
        } else {
            return new None<>();
        }
    }
}