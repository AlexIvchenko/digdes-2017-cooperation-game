package com.shurikat.cooperationgame.core;

/**
 * @author Alex Ivchenko
 */
public interface Agent {
    String name();
    AppliedAgent applyGame(Game game);
}
