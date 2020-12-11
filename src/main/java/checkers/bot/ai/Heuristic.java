package checkers.bot.ai;

import checkers.bot.engine.CheckersEngine;

import java.util.ArrayList;

import static checkers.bot.util.Constants.*;

public class Heuristic {
    private int[][] board;
    private int[][] originBoard;
    private int[] boardCheckers = new int[]{0, 0, 0, 0};
    private int[] originBoardCheckers = new int[]{0, 0, 0, 0};
    /* goodSimpleChecker, goodSimpleQueen, enemySimpleChecker, enemySimpleQueen*/

    public Heuristic(int[][] originBoard, int[][] board) {
        this.originBoard = originBoard;
        this.board = board;
        countCheckers(this.board, this.boardCheckers);
        countCheckers(this.originBoard, this.originBoardCheckers);
    }

    public int countSimpleHeuristic() {
        return boardCheckers[0] + boardCheckers[1] * QUEEN_KOEFF - boardCheckers[2] - boardCheckers[3] * QUEEN_KOEFF;
    }

    public int countDifficultHeuristics() {
        return boardCheckers[0] + boardCheckers[1] * QUEEN_KOEFF - boardCheckers[2] - boardCheckers[3] * QUEEN_KOEFF
                + isBecomeQueen (originBoardCheckers, boardCheckers, 1)
                + countHowManyWeHaveKilledEnemy(originBoardCheckers, boardCheckers, 2)
                - countHowManyEnemyWillKillOwrCheckers(board);
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



    /*
    + порівнювати кількість вбитих оригінальна дошка - ця дошка  (1 вбитий + 1/ кололева + 3)
    + чи ми змінили стан (із простої стали королевою) +3
    + порахувати скільки наших фігур після нашого ходу вб'ють
    - перевірити чи нас не б'ють (якщо блють просту - -1, якщо б'ють королеву, то -3) -> треба зробити енджін для ворога - проаналізувати кожен його крок - довго
    - подивитися чи ми в безпечній позиція
    - чи ми зайняли угл карти(кутова позиція)
     */


    private int countHowManyWeHaveKilledEnemy(int[] originBoardCheckers, int[] boardCheckers, int figurePosition) {
        return originBoardCheckers[figurePosition] - boardCheckers[figurePosition] + (originBoardCheckers[figurePosition + 1] - boardCheckers[figurePosition + 1]) * QUEEN_KOEFF;
    }
    private int isBecomeQueen(int[] originBoardCheckers, int[] boardCheckers, int queenPos){
        return originBoardCheckers[queenPos] < boardCheckers[queenPos] ? QUEEN_KOEFF : 0;
    }

    private int countHowManyEnemyWillKillOwrCheckers(int[][] board) {
        CheckersEngine engine = new CheckersEngine(board);
        ArrayList<ArrayList<int[][]>> newBoards = engine.getAllPossibleBoards(ENEMY_PLAYER);
        int maxKilledPlayers = 0;
        for (int i = 0; i < newBoards.size(); i++) {
            for (int j = 0; j < newBoards.get(i).size(); j++) {
                int[][] enemyBoard = newBoards.get(i).get(j);
                int[] enemyCheckers = new int[]{0, 0, 0, 0};
                countCheckers(enemyBoard, enemyCheckers);
//                countCheckers(board, boardCheckers);
                int killedValue = countHowManyWeHaveKilledEnemy(enemyCheckers, boardCheckers, 0);
                if (maxKilledPlayers < killedValue)
                    maxKilledPlayers = killedValue;
            }
        }
        return -maxKilledPlayers;
    }
}
