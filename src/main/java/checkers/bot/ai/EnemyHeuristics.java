package checkers.bot.ai;

import checkers.bot.api.IHeuristic;

import static checkers.bot.util.Constants.*;
import static checkers.bot.util.Constants.ENEMY_QUEEN;

public class EnemyHeuristics implements IHeuristic {

    private int[][] board;
    private int[][] originBoard;
    private int[] boardCheckers = new int[]{0, 0, 0, 0};
    private int[] originBoardCheckers = new int[]{0, 0, 0, 0};

    public EnemyHeuristics(int[][] originBoard) {
        this.originBoard = originBoard;
        countCheckers(this.originBoard, boardCheckers);
    }

    @Override
    public int estimate(int[][] original, int[][] state) {
        this.originBoard = original;
        this.board = state;
        countCheckers(this.board, this.boardCheckers);
        countCheckers(this.originBoard, this.originBoardCheckers);

        return boardCheckers[2] + boardCheckers[3] * QUEEN_KOEFF - boardCheckers[0] - boardCheckers[1] * QUEEN_KOEFF
                + isBecomeQueen(originBoardCheckers, boardCheckers, 3)
                + countHowManyWeHaveKilledEnemy(originBoardCheckers, boardCheckers, 0);
    }

    private void countCheckers(int[][] board, int[] boardCheckers) {
        for (int y = 0; y < ROWS_NUMBER; y++) {
            for (int x = 0; x < COLUMN_NUMBER; x++) {
                if (board[y][x] == GOOD_PLAYER) {
                    boardCheckers[2]++;
                } else if (board[y][x] == GOOD_QUEEN) {
                    boardCheckers[3]++;
                } else if (board[y][x] == ENEMY_PLAYER) {
                    boardCheckers[0]++;
                } else if (board[y][x] == ENEMY_QUEEN) {
                    boardCheckers[1]++;
                }
            }
        }
    }

    private int isBecomeQueen(int[] originBoardCheckers, int[] boardCheckers, int queenPos) {
        return originBoardCheckers[queenPos] < boardCheckers[queenPos] ? QUEEN_KOEFF : 0;
    }

    private int countHowManyWeHaveKilledEnemy(int[] originBoardCheckers, int[] boardCheckers, int figurePosition) {
        return originBoardCheckers[figurePosition] - boardCheckers[figurePosition] + (originBoardCheckers[figurePosition + 1] - boardCheckers[figurePosition + 1]) * QUEEN_KOEFF;
    }
}
