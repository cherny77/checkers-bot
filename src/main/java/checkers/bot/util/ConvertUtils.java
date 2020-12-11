package checkers.bot.util;

import checkers.bot.dto.GetGameInfoResponse;
//import sun.jvm.hotspot.runtime.StaticBaseConstructor;
import java.util.List;

import static checkers.bot.util.Constants.*;

public class ConvertUtils {

    public static int[][] convertBoard(List<GetGameInfoResponse.Data.BoardItem> board, String color) {
        int[][] convertedBoard = new int[ROWS_NUMBER][COLUMN_NUMBER];
        switch (color) {
            case RED:
                for (GetGameInfoResponse.Data.BoardItem boardItem : board) {
                    if (boardItem.getRow() % 2 == 0) {
                        convertedBoard[convertedBoard.length - 1 - boardItem.getRow()][convertedBoard.length - 1 - (boardItem.getColumn() * 2 + 1)] = getBoardItemNumber(boardItem, color);
                    } else {
                        convertedBoard[convertedBoard.length - 1 - boardItem.getRow()][convertedBoard.length - 1 - boardItem.getColumn() * 2] = getBoardItemNumber(boardItem, color);
                    }
                }
                break;
            case BLACK:
                for (GetGameInfoResponse.Data.BoardItem boardItem : board) {
                    if (boardItem.getRow() % 2 == 0) {
                        convertedBoard[boardItem.getRow()][boardItem.getColumn() * 2 + 1] = getBoardItemNumber(boardItem, color);
                    } else {
                        convertedBoard[boardItem.getRow()][boardItem.getColumn() * 2] = getBoardItemNumber(boardItem, color);
                    }
                }
                break;
        }
        return convertedBoard;
    }

    public static int getBoardItemNumber(GetGameInfoResponse.Data.BoardItem boardItem, String color) {
        if (boardItem.getColor().equals(color)) {
            if (boardItem.isKing()) return GOOD_QUEEN;
            return GOOD_PLAYER;
        } else {
            if (boardItem.isKing()) return ENEMY_QUEEN;
            return ENEMY_PLAYER;
        }
    }

    public static void printState(int map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int[][] turnBord(int original[][]) {
        int turned[][] = new int[ROWS_NUMBER][COLUMN_NUMBER];
        for (int i = 0; i < original.length; i++) {
            for (int j = 0; j < original.length; j++) {
                turned[ROWS_NUMBER - i - 1][COLUMN_NUMBER - j - 1] = original[i][j];
            }
        }
        return turned;
    }


    public static Move getStepByTwoBoards(int original[][], int changed[][], String color) {
        if (color.equals(RED)) {
            int originalTemp[][] = turnBord(original);
            int changedTemp[][] = turnBord(changed);
            original = originalTemp;
            changed = changedTemp;
        }
        int diff[][] = new int[ROWS_NUMBER][COLUMN_NUMBER];
        for (int i = 0; i < ROWS_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                diff[i][j] = changed[i][j] - original[i][j];
            }
        }

        Position from = null;
        Position to = null;
        for (int i = 0; i < ROWS_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                if (diff[i][j] == -1 * GOOD_PLAYER || diff[i][j] == -1 * GOOD_QUEEN)
                    from = new Position(j, i);
                if (diff[i][j] == GOOD_PLAYER || diff[i][j] == GOOD_QUEEN) to = new Position(j, i);
            }
        }
        return new Move(from, to);
    }


}
