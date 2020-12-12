package checkers.bot.ai;

import checkers.bot.api.IHeuristic;

import static checkers.bot.util.Constants.*;
import static checkers.bot.util.Constants.ENEMY_QUEEN;

public class SimpleHeuristic implements IHeuristic {
    private int[][] board;
    private int[][] originBoard;
    private int[] boardCheckers = new int[]{0, 0, 0, 0};
    private int[] originBoardCheckers = new int[]{0, 0, 0, 0};

    @Override
    public int estimate(int[][] original, int[][] state) {
        this.originBoard = original;
        this.board = state;
        countCheckers(this.board, this.boardCheckers);
        countCheckers(this.originBoard, this.originBoardCheckers);

        return boardCheckers[0] + boardCheckers[1] * QUEEN_KOEFF - boardCheckers[2] - boardCheckers[3] * QUEEN_KOEFF;
    }

    private void countCheckers(int[][] board, int[] boardCheckers) {
        for (int y = 0; y < ROWS_NUMBER; y++) {
            for (int x = 0; x < COLUMN_NUMBER; x++) {
                if (board[y][x] == GOOD_PLAYER) {
                    boardCheckers[0]++;
                } else if (board[y][x] == GOOD_QUEEN) {
                    boardCheckers[1]++;
                } else if (board[y][x] == ENEMY_PLAYER) {
                    boardCheckers[2]++;
                } else if (board[y][x] == ENEMY_QUEEN) {
                    boardCheckers[3]++;
                }
            }
        }
    }
}
