package com.github.shareme.gwsblastfurnace.library.state;

import com.github.shareme.gwsblastfurnace.library.GWSPair;
import com.github.shareme.gwsblastfurnace.library.func.Function;

/**
 * Created by fgrott on 11/27/2015.
 */
@SuppressWarnings("unused")
public class State<S, A> {

    // get current state, then return new value and state
    final Function.F1<S, GWSPair<A, S>> runState;

    /**
     * use unit or init Factory
     * @param runState run state
     */
    private State(Function.F1<S, GWSPair<A, S>> runState) {
        this.runState = runState;
    }

    /**
     * unit factory given by first value
     * @param a a
     * @param <S> S
     * @param <A> A
     * @return tuple
     */
    public static <S, A> State<S, A> unit(final A a) {
        return State.init(new Function.F1<S, GWSPair<A, S>>() {
            @Override
            public GWSPair<A, S> invoke(S s) {
                return GWSPair.of(a, s);
            }
        });
    }

    /**
     * init factory with runState function
     * @param runState run state
     * @param <S> S
     * @param <A> A
     * @return state
     */
    public static <S, A> State<S, A> init(Function.F1<S, GWSPair<A, S>> runState) {
        return new State<>(runState);
    }

    /**
     * put new state
     * @param newState new state
     * @return tuple
     */
    public State<S, Void> put(final S newState) {
        return State.init(new Function.F1<S, GWSPair<Void, S>>() {
            @Override
            public GWSPair<Void, S> invoke(S s) {
                return GWSPair.of(null, newState);
            }
        });
    }

    public GWSPair<A, S> apply(S state) {
        return runState.invoke(state);
    }

    /**
     * get current value
     * @return tuple
     */
    public State<S, S> get() {
        return State.init(new Function.F1<S, GWSPair<S, S>>() {
            @Override
            public GWSPair<S, S> invoke(S s) {
                return GWSPair.of(s, s);
            }
        });
    }

    /**
     * monadic
     * @param f f
     * @param <B> B
     * @return tuple
     */
    @SuppressWarnings("unchecked")
    public <B> State<S, B> flatMap(final Function.F1<? super A, State<S, B>> f) {
        Function.F1<S, GWSPair<B, S>> fb = new Function.F1<S, GWSPair<B, S>>() {
            @Override
            public GWSPair<B, S> invoke(S s) {
                GWSPair<A, S> as = runState.invoke(s);
                State<S, ? extends B> sb = f.invoke(as.first);
                GWSPair<B, S> bs = (GWSPair<B, S>) sb.runState.invoke(as.second);
                return bs;
            }
        };
        return State.init(fb);
    }

    /**
     * map function to inner value
     * @param f f
     * @param <B> B
     * @return state
     */
    public <B> State<S, B> map(final Function.F1<? super A, ? extends B> f) {
        return flatMap(new Function.F1<A, State<S, B>>() {
            @Override
            public State<S, B> invoke(A a) {
                return State.unit(f.invoke(a));
            }
        });
    }

}