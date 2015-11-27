package com.github.shareme.gwsblastfurnace.library;

import com.github.shareme.gwsblastfurnace.library.func.Function;

/**
 * Created by fgrott on 11/27/2015.
 */
@SuppressWarnings("unused")
public interface Functor<A, F extends Functor<?, ?>> {
    <B> F map(Function.F1<? super A, ? extends B> func);
}