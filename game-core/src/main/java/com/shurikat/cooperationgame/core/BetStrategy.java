package com.shurikat.cooperationgame.core;

import com.shurikat.cooperationgame.core.Bet;

/**
 * @author Alex Ivchenko
 */
public interface BetStrategy {
    Bet bet();
    String name();
}
