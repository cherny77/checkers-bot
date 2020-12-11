package checkers.bot.api;

import checkers.bot.util.Move;

public interface ICheckersBotAi {
    public Move getNextStep(int curState[][]);

    void setColor(String color);
}
