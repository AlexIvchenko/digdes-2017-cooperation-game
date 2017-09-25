package com.shurikat.cooperationgame.core;

/**
 * @author Alex Ivchenko
 */
public enum Bet {
    ZERO,
    ONE;

    public int affect(int money) {
        switch (this) {
            case ZERO: return money;
            case ONE: return money - 1;
            default: throw new UnsupportedOperationException("method affect not supported for " + this.name());
        }
    }
}
