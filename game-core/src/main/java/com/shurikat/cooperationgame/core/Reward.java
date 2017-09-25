package com.shurikat.cooperationgame.core;

/**
 * @author Alex Ivchenko
 */
public enum Reward {
    MINUS_ONE, PLUS_TWO, PLUS_THREE;

    int affect(int oldValue) {
        switch (this) {
            case MINUS_ONE: return oldValue - 1;
            case PLUS_TWO: return oldValue + 2;
            case PLUS_THREE: return oldValue + 3;
            default: throw new UnsupportedOperationException("method affect() is not supported for " + this.name());
        }
    }
}
