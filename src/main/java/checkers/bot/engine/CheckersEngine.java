package checkers.bot.engine;

import java.util.ArrayList;
import java.util.Arrays;

import static checkers.bot.util.Constants.*;

public class CheckersEngine {


    private int[][] board;
    private int dir;
    boolean canKill = false;
    ArrayList<ArrayList<int[][]>> allPossibleBoardsFinal;

    public CheckersEngine(int[][] board) {
        this.board = board;
    }

    private int getNumOfPossibleSteps(int figureKey) {
        if (figureKey == GOOD_PLAYER) return 2;
        else if (figureKey == ENEMY_PLAYER) return 2;
        else if (figureKey == GOOD_QUEEN) return 4;
        else return 4; // when figureKey == ENEMY_QUEEN
    }

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
        return x >= 0 && x < COLUMN_NUMBER;
    }

    private boolean isPossibleY(int y) {
        return y >= 0 && y < ROWS_NUMBER;
    }

    private boolean isEven(int x) {
        return x % 2 == 0 && x > 0;
    }

    private boolean isFreePosition(int[][] board, int x, int y) {
        return board[y][x] == FREE_CELL;
    }

    private int[][] doStepForward(int[][] board, int figureKey, int posX, int posY, int x, int y) {
        int[][] tempBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        tempBoard[posY][posX] = FREE_CELL;
        tempBoard[y][x] = figureKey;
        return tempBoard;
    }

    // todo dangerous zone
    private boolean isEnemyQueenPosition(int figureKey, int y) {
        if (dir == 1 && y == (ROWS_NUMBER - 1) && figureKey < 0) return true;
        else if (dir == -1 && y == 0 && figureKey > 0) return true;
        else return false;
    }

    private int[][] transformToQueen(int[][] board, int figureKey, int posX, int posY, int x, int y) {
        int[][] tempBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        tempBoard[posY][posX] = FREE_CELL;
        tempBoard[y][x] = figureKey * 10 + figureKey;
        return tempBoard;
    }

    private int[][] transformToQueen(int[][] board, int figureKey, int posX, int posY, int enemyX, int enemyY, int x, int y) {
        int[][] tempBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        tempBoard[posY][posX] = FREE_CELL;
        tempBoard[enemyY][enemyX] = FREE_CELL;
        tempBoard[y][x] = figureKey * 10 + figureKey;
        return tempBoard;
    }

    private boolean isEnemyForward(int[][] board, int posX, int posY, int x, int y) {
        if (!isEven(board[posY][posX]) && isEven(board[y][x])) return true;
        else if (isEven(board[posY][posX]) && !isEven(board[y][x])) return true;
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
        int[][] tempBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);
        tempBoard[posY][posX] = FREE_CELL;
        tempBoard[oldY][oldX] = FREE_CELL;
        tempBoard[y][x] = figureKey;
        return tempBoard;
    }

    private boolean isUniqueBoards(int[][] a1, int[][] a2) {
        for (int y = 0; y < a1.length; y++) {
            for (int x = 0; x < a1.length; x++) {
                if (a1[y][x] != a2[y][x])
                    return true;
            }
        }
        return false;
    }

    private void continueKillEnemy(ArrayList<int[][]> bList, int[][] board, int[][] steps, int figureKey, int posX, int posY) {
        int[][] newBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);

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

                            newBoard = transformToQueen(board, figureKey, posX, posY, oldX, oldY, x, y);
                            bList.add(newBoard);
                        }
                        // when we are queen and we can`t transform to queen
                        else {
                            newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                            int[][] newBoard2 = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
                            int[][] newSteps = getPossibleSteps(getNumOfPossibleSteps(figureKey), x, y);

                            continueKillEnemy(bList, newBoard2, newSteps, figureKey, x, y);
                        }
                    } else {
                        newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);

                        int[][] newBoard2 = Arrays.stream(newBoard).map(int[]::clone).toArray(int[][]::new);
                        int[][] newSteps = getPossibleSteps(getNumOfPossibleSteps(figureKey), x, y);

                        continueKillEnemy(bList, newBoard2, newSteps, figureKey, x, y);

                    }
                }
            }
        }
        if (bList.size() > 0 && isUniqueBoards(bList.get(bList.size() - 1), newBoard)) {
            bList.add(newBoard);
        } else if (bList.size() == 0) {
            bList.add(newBoard);
        }
    }

    // todo code prosto ujas
    private void isCheckersCanKill(int figureKey) {
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++) {
                if (canKill)
                    return;
                if (this.board[y][x] == figureKey) {
                    int checkerSteps = getNumOfPossibleSteps(figureKey);
                    int[][] steps = getPossibleSteps(checkerSteps, x, y);
                    isCanKill(this.board, steps, figureKey, x, y);
                } else if (this.board[y][x] == figureKey * 10 + figureKey) {
                    int checkerSteps = getNumOfPossibleSteps(figureKey);
                    int[][] steps = getPossibleSteps(checkerSteps, x, y);
                    isCanKill(this.board, steps, figureKey, x, y);
                }

            }
        }
    }

    // todo code prosto ujas
    private void isCanKill(int[][] board, int[][] steps, int figureKey, int posX, int posY) {
        for (int i = 0; i < steps.length; i++) {
            int x = steps[i][0];
            int y = steps[i][1];
            if (isPossibleX(x) && isPossibleY(y)&&isEnemyForward(board, posX, posY, x, y)) {
                int oldX = x;
                int oldY = y;
                int[] pos = getPositionAfterFight(board, posX, posY, x, y);
                x = pos[0];
                y = pos[1];
                if (isCanKillEnemy(board, x, y)) {
                    canKill = true;
                }
            }
        }
    }

    private ArrayList<int[][]> doStep(int[][] board, int[][] steps, int figureKey, int posX, int posY) {
        ArrayList<int[][]> allPossibleBoards = new ArrayList<>();
        ArrayList<int[][]> bList = new ArrayList<>();

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
                    if (!canKill)
                        allPossibleBoards.add(newBoard);
                }
                // if position isn`t free and there stay enemy
                else if (isEnemyForward(board, posX, posY, x, y)) {
                    int oldX = x;
                    int oldY = y;
                    int[] pos = getPositionAfterFight(board, posX, posY, x, y);
                    x = pos[0];
                    y = pos[1];
                    if (isCanKillEnemy(board, x, y)) {
                        if (!canKill) {
                            allPossibleBoards = new ArrayList<>();
                        }
                        int[][] newBoard;
                        if (isEnemyQueenPosition(figureKey, y)) {
                            // when we are simple player and we can transform to queen
                            if (steps.length == 2) {
                                newBoard = transformToQueen(board, figureKey, posX, posY, oldX, oldY, x, y);
                                allPossibleBoards.add(newBoard);
                            }
                            // when we are queen and we can`t transform to queen
                            else {
                                newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                                int[][] newSteps = getPossibleSteps(getNumOfPossibleSteps(figureKey), x, y);

                                continueKillEnemy(bList, newBoard, newSteps, figureKey, x, y);
                            }
                        } else {
                            newBoard = killEnemy(board, figureKey, posX, posY, oldX, oldY, x, y);
                            int[][] newSteps = getPossibleSteps(getNumOfPossibleSteps(figureKey), x, y);
                            continueKillEnemy(bList, newBoard, newSteps, figureKey, x, y);

                        }
                    }
                }
            }
        }

        for (int i = 0; i < bList.size(); i++) {
            allPossibleBoards.add(bList.get(i));
        }
        return allPossibleBoards;
    }

    private ArrayList<int[][]> createBoardWithStep(int figureKey, int posX, int posY) {

        int checkerSteps = getNumOfPossibleSteps(figureKey);
        int[][] steps = getPossibleSteps(checkerSteps, posX, posY);
        int[][] newBoard = Arrays.stream(this.board).map(int[]::clone).toArray(int[][]::new);
        ArrayList<int[][]> boards = doStep(newBoard, steps, figureKey, posX, posY);

        return boards;
    }

    public ArrayList<ArrayList<int[][]>> getAllPossibleBoards(int figureKey) {
        this.dir = figureKey == GOOD_PLAYER ? -1 : 1;
        allPossibleBoardsFinal = new ArrayList<>();
        int queenKey = figureKey * 10 + figureKey;
        isCheckersCanKill(figureKey);
        for (int y = 0; y < this.board.length; y++) {
            for (int x = 0; x < this.board[y].length; x++) {
                if (this.board[y][x] == figureKey) {
                    allPossibleBoardsFinal.add(createBoardWithStep(figureKey, x, y));
                } else if (this.board[y][x] == queenKey) {
                    allPossibleBoardsFinal.add(createBoardWithStep(queenKey, x, y));
                }
            }
        }
        return allPossibleBoardsFinal;
    }
}
