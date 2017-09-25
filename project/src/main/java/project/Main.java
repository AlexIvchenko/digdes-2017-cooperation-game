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
                .addAgent(pessimistic().name("first").money(10))
                .addAgent(naive().name("naive").money(10))
                .build();
        GameSummary result = game.execute(100);
        System.out.println(result.result().winner());
    }
}
