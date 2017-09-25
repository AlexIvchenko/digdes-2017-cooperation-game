package project;

import com.shurikat.cooperationgame.bots.Bots;
import com.shurikat.cooperationgame.core.Agent;
import com.shurikat.cooperationgame.core.Game;
import com.shurikat.cooperationgame.summary.GameSummary;

import java.util.Random;

/**
 * @author Alex Ivchenko
 */
public final class Tournament {
    private final Random random = new Random(System.currentTimeMillis());
    private final Settings settings;

    public Tournament(Settings settings) {
        this.settings = settings;
    }

    public static class Settings {
        private final int maxRound;
        private final int countBots;
        private final int startMoney;

        public Settings(int maxRound, int countBots, int startMoney) {
            this.maxRound = maxRound;
            this.countBots = countBots;
            this.startMoney = startMoney;
        }
    }

    public GameSummary run() {
        Game.Builder builder = Game.builder();
        for (int i = 0; i < settings.countBots; ++i) {
            Agent bot = Bots
                    .probability(random.nextInt(101))
                    .name("bot #" + i)
                    .money(settings.startMoney);
            builder.addAgent(bot);
        }
        Game game = builder.build();
        return game.proceed(settings.maxRound);
    }
}
