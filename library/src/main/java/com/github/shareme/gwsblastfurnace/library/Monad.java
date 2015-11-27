package com.github.shareme.gwsblastfurnace.library;

import com.github.shareme.gwsblastfurnace.library.func.Function;

/**
 * Created by fgrott on 11/27/2015.
 */
@SuppressWarnings("unused")
public interface Monad<A, M extends Monad<?, ?>> extends Functor<A, M> {
    <B> M flatMap(Function.F1<? super A, ? extends M> f);
}
