package com.github.shareme.gwsblastfurnace.library.type;

/**
 * Created by fgrott on 11/27/2015.
 */
@SuppressWarnings("unused")
public class StringM implements Monoid<String> {

    private final String mString;

    private StringM(String mString){
        this.mString = mString;
    }

    public static StringM of(String i){
        return  new StringM(i);
    }

    @Override
    public Monoid<String> empty() {
        return StringM.of("");
    }

    @Override
    public Monoid<String> add(String s) {
        return StringM.of(mString + s);
    }

    @Override
    public String get() {
        return mString;
    }
}
