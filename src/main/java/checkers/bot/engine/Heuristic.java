package checkers.bot.engine;

import static checkers.bot.engine.Constants.*;

public class Heuristic {
    private int[][] board;
    private int goodSimpleChecker = 0;
    private int goodSimpleQueen = 0;
    private int enemySimpleChecker = 0;
    private int enemySimpleQueen = 0;


    public Heuristic(int[][] board) {
        this.board = board;
        countCheckers();
    }

    public int countSimpleHeuristic(){
        return goodSimpleChecker+goodSimpleQueen*4-enemySimpleChecker-enemySimpleQueen*4;
    }
    public int countDifficultHeuristics(){
        return 0;
    }


    private void countCheckers(){
        for(int y = 0 ; y < ROW; y++){
            for(int x = 0 ; x < COL; x++){
                if (board[y][x] == GOOD_PLAYER) { goodSimpleChecker++;}
                else if (board[y][x] == GOOD_QUEEN) {goodSimpleQueen++;}
                else if (board[y][x] == ENEMY_PLAYER) {enemySimpleChecker++;}
                else if (board[y][x] == ENEMY_QUEEN) {enemySimpleQueen++;}
            }
        }
    }


}
