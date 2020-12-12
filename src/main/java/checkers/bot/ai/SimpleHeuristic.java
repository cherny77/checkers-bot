package checkers.bot.ai;

import checkers.bot.api.IHeuristic;

public class SimpleHeuristic implements IHeuristic {
    private int[][] board;
    private int[][] originBoard;
    private int[] boardCheckers = new int[]{0, 0, 0, 0};
    private int[] originBoardCheckers = new int[]{0, 0, 0, 0};

    @Override
    public int estimate(int[][] original, int[][] state) {
        return 0;
    }
}
