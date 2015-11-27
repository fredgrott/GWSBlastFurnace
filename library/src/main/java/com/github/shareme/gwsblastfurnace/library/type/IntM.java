package com.github.shareme.gwsblastfurnace.library.type;

/**
 * Created by fgrott on 11/27/2015.
 */
@SuppressWarnings("unused")
public class IntM implements Monoid<Integer> {

    private final Integer mValue;

    private IntM(Integer mValue) {
        this.mValue = mValue;
    }

    public static IntM of(int i) {
        return new IntM(i);
    }

    @Override
    public Monoid<Integer> empty() {
        return IntM.of(0);
    }

    @Override
    public Monoid<Integer> add(Integer integer) {
        return IntM.of(mValue + integer);
    }

    @Override
    public Integer get() {
        return mValue;
    }
}