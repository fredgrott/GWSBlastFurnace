package com.github.shareme.gwsblastfurnace.library.type;

/**
 * Created by fgrott on 11/27/2015.
 */
@SuppressWarnings("unused")
public interface Monoid<A> {
    Monoid<A> empty();

    Monoid<A> add(A a);

    A get();

    class TypeMismatchException extends Exception {
        public TypeMismatchException(String s) {
            super(s);
        }
    }
}
