package project;

import com.shurikat.cooperationgame.core.Game;
import com.shurikat.cooperationgame.summary.GameSummary;

import static com.shurikat.cooperationgame.bots.Bots.naive;
import static com.shurikat.cooperationgame.bots.Bots.pessimistic;

/**
 * @author Alex Ivchenko
 */
public class Main {
    public static void main(final String... args) {
        Game game = Game.builder()
                .agent(pessimistic().name("first").startMoney(10))
                .agent(naive().name("naive").startMoney(10))
                .build();
        GameSummary result = game.execute(100);
        System.out.println(result.result().winner());
    }
}
