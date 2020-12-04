package checkers.bot.engine;

import java.util.ArrayList;

public class CheckersEngine {


    /**
     * 1 - ми
     * 10 - наша королева
     * 0 - нічого
     * -1 - ворог
     * -10 - ворожа корорлева
     * 6 - killed
     * [[0,-1,0,-1,0,-1,0,-1]
     * <p>
     * <p>
     * <p>
     * ]
     */

    private static final int ROW = 8;
    private static final int COL = 8;
    private static final int FREE_CELL = 0;
    private static final int CHECKER_STEPS_SIMPLE = 2;
    private static final int CHECKER_STEPS_QUEEN = 4;
    private static final int GOOD_PLAYER = 1;
    private static final int GOOD_QUEEN = 10;
    private static final int ENEMY_PLAYER = -1;
    private static final int ENEMY_QUEEN = -10;

    private ArrayList<int[][]> futureBoards;
    private int[][] board;
    private int dir;

    public CheckersEngine(int[][] board, int dir) {
        this.futureBoards = new ArrayList<>();
        this.board = board;
        this.dir = dir;
    }

    private int getNumOfPossibleSteps(int figureKey) {
        if (figureKey == GOOD_PLAYER) return 2;
        else if (figureKey == ENEMY_PLAYER) return 2;
        else if (figureKey == GOOD_QUEEN) return 4;
        else return 4; // when figureKey == ENEMY_QUEEN
    }

    // 2
    private int[][] getPossibleSteps(int checkerSteps, int x, int y) {
        int[][] steps;
        if (checkerSteps == CHECKER_STEPS_SIMPLE) {
            if (dir == 1) {
                steps = new int[][]{{x - 1, y + 1}, {x + 1, y + 1}};
            } else {
                steps = new int[][]{{x - 1, y - 1}, {x + 1, y - 1}};
            }
        } else {
            steps = new int[][]{{x - 1, y + 1}, {x + 1, y + 1}, {x - 1, y - 1}, {x + 1, y - 1}};
        }
        return steps;
    }

    private boolean isPossibleX(int x) {
        return x >= 0 && x < COL;
    }

    private boolean isPossibleY(int y) {
        return y >= 0 && y < ROW;
    }

    private boolean isFreePosition(int[][] board, int x, int y) {
        return board[x][y] == FREE_CELL;
    }

    private int[][] doStepForward(int[][] board, int figureKey, int posX, int posY, int x, int y) {
        board[posX][posY] = FREE_CELL;
        board[x][y] = figureKey;
        return board;
    }

    private boolean isPositiveNum(int num) {
        return num > 0;
    }

    private boolean isNegativeNumber(int num) {
        return num < 0;
    }

    private boolean isEnemyQueenPosition(int figureKey, int y) {
        if (dir == 1 && y == (ROW - 1) && figureKey > 0) return true;
        else if (dir == -1 && y == 0 && figureKey < 0) return true;
        else return false;
    }

    private int[][] transformToQueen(int[][] board, int figureKey, int posX, int posY, int x, int y) {
        board[posX][posY] = FREE_CELL;
        board[x][y] = figureKey * 10;
        return board;
    }


    private boolean isEnemyForward(int[][] board, int posX, int posY, int x, int y) {
        if (isPositiveNum(board[posX][posY]) && isNegativeNumber(board[x][y])) return true;
        else if (isNegativeNumber(board[posX][posY]) && isPositiveNum(board[x][y])) return true;
        else return false;
    }

    /* 4 сторіни описати
  [1] - - - [2]     where : X - [posX,posY]
    - o - o -               o - x,y
    - - X - -               [] - next ouw position
    - o - o -
  [4] - - - [3]
    */
    private int[] getPositionAfterFight(int[][] board, int posX, int posY, int x, int y) {
        int[] pos = new int[2];
        if (x < posX) {
            //  [4]
            if (y < posY) {
                pos[0] = x - 1;
                pos[1] = y - 1;
            }
            // [1]
            else {
                pos[0] = x - 1;
                pos[1] = y + 1;
            }
        } else {
            // [3]
            if (y < posY) {
                pos[0] = x + 1;
                pos[1] = y - 1;
            }
            // [2]
            else {
                pos[0] = x + 1;
                pos[1] = y + 1;
                ;
            }
        }
        return pos;
    }

    private boolean isCanKillEnemy(int[][] board, int x, int y) {
        return isPossibleX(x) && isPossibleY(y) && isFreePosition(board, x, y);
    }

    private int[][] killEnemy(int[][] board, int figureKey, int posX, int posY, int oldX, int oldY, int x, int y) {
        board[posX][posY] = FREE_CELL;
        board[x][y] = FREE_CELL;
        board[oldX][oldY] = FREE_CELL; // todo may change to killed enemy number - 6 for example ?
        board[x][y] = figureKey;
        return board;
    }

    private int[][] continueKillEnemy(int[][] board, int[][] steps, int figureKey, int posX, int posY) {
        int[][] newBoard = board.clone();
        for (int i = 0; i < steps.length; i++) {
            int x = steps[i][0];
            int y = steps[i][1];
            if (isPossibleX(x) && isPossibleY(y) && isEnemyForward(board, posX, posY, x, y)) {
                int oldX = x;
                int oldY = y;
                int[] pos = getPositionAfterFight(board, posX, posY, x, y);
                x = pos[0];
                y = pos[1];
                if (isCanKillEnemy(board, x, y)) {
                    if (isEnemyQueenPosition(figureKey, y)) {
                        // when we are simple player and we can transform to queen
                        if (steps.length == 2) {
                            newBoard = transformToQueen(board, figureKey * 10, posX, posY, x, y);
                        }
                        // when we are queen and we can`t transform to queen
                        else {
                            newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                            posX = x;
                            posY = y;
                            steps = getPossibleSteps(getNumOfPossibleSteps(figureKey), posX, posY);
                            newBoard = continueKillEnemy(newBoard, steps, figureKey, posX, posY);
                        }
                    } else {
                        newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                        posX = x;
                        posY = y;
                        steps = getPossibleSteps(getNumOfPossibleSteps(figureKey), posX, posY);
                        newBoard = continueKillEnemy(newBoard, steps, figureKey, posX, posY);
                    }
                }
            }
        }
        return newBoard;
    }

    //
    private ArrayList<int[][]> doStep(int[][] board, int[][] steps, int figureKey, int posX, int posY) {

        ArrayList<int[][]> boards = new ArrayList<>();

        for (int i = 0; i < steps.length; i++) {
            int x = steps[i][0];
            int y = steps[i][1];
            if (isPossibleX(x) && isPossibleY(y)) {
                // when next step is free cell
                if (isFreePosition(board, x, y)) {
                    int[][] newBoard;
                    // when free position is enemy queen position
                    if (isEnemyQueenPosition(figureKey, y)) {
                        // when we are simple player and we can transform to queen
                        if (steps.length == 2) {
                            newBoard = transformToQueen(board, figureKey, posX, posY, x, y);
                        }
                        // when we are queen and we can`t transform to queen
                        else {
                            newBoard = doStepForward(board, figureKey, posX, posY, x, y);
                        }
                    }
                    // when only simple free position
                    else {
                        newBoard = doStepForward(board, figureKey, posX, posY, x, y);
                    }
                    boards.add(newBoard);
                }
                // if position isn`t free and there stay enemy
                else if (isEnemyForward(board, posX, posY, x, y)) {
                    int oldX = x;
                    int oldY = y;
                    int[] pos = getPositionAfterFight(board, posX, posY, x, y);
                    x = pos[0];
                    y = pos[1];
                    if (isCanKillEnemy(board, x, y)) {
                        int[][] newBoard;
                        if (isEnemyQueenPosition(figureKey, y)) {
                            // when we are simple player and we can transform to queen
                            if (steps.length == 2) {
                                newBoard = transformToQueen(board, figureKey * 10, posX, posY, x, y);
                            }
                            // when we are queen and we can`t transform to queen
                            else {
                                newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                                posX = x;
                                posY = y;
                                steps = getPossibleSteps(getNumOfPossibleSteps(figureKey), posX, posY);
                                newBoard = continueKillEnemy(newBoard, steps, figureKey, posX, posY);
                            }
                        } else {
                            newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                            posX = x;
                            posY = y;
                            steps = getPossibleSteps(getNumOfPossibleSteps(figureKey), posX, posY);
                            newBoard = continueKillEnemy(newBoard, steps, figureKey, posX, posY);
                        }
                        boards.add(newBoard);
                    }
                }
            }
        }
        return boards;
    }


    private int[][] createBoardWithStep(int[][] board, int figureKey, int posX, int posY) {
//        int checkerSteps = getNumOfPossibleSteps(figureKey);
//        int stepsCoor = createCoordinationForFutureSpteps()
//        int[][] newBoard = board.clone();
//        for (int i = 0; i < posMoving; i++) {
//
//        }
        return new int[][]{};
    }

    public Object[] sendVariableMap(int figureKey) {
        for (int x = 0; x < COL; x++) {
            for (int y = 0; y < ROW; y++) {
                if (board[x][y] == figureKey) {
                    futureBoards.add(createBoardWithStep(board, figureKey, x, y));
                }
            }
        }
        return null;
    }


}
