package com.shurikat.cooperationgame;

import com.shurikat.cooperationgame.agents.ConstBetStrategy;
import com.shurikat.cooperationgame.agents.RandomBetStrategy;
import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Bet;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
public class Utils {
    private static final Random ran = new Random(0);
    public static Agent agent(String name, int money, Bet bet) {
        return Agent.builder().strategy(new ConstBetStrategy(bet)).name(name).money(money);
    }

    public static Agent anyAgent() {
        return Agent.builder().strategy(new RandomBetStrategy()).name(Integer.toHexString(ran.nextInt()))
                .money(ran.nextInt(100));
    }

    public static Agent nullAgent() {
        return null;
    }
}
