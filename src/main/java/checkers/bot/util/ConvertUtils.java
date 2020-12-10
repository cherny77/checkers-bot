package checkers.bot.util;

import checkers.bot.dto.GetGameInfoResponse;
import sun.jvm.hotspot.runtime.StaticBaseConstructor;

import java.util.List;

public class ConvertUtils {
    public static final int ROWS_NUMBER = 8;
    public static final int COLUMN_NUMBER = 8;
    public static final String BLACK = "BLACK";
    public static final String RED = "RED";
    private static final int GOOD_PLAYER = 1;
    private static final int GOOD_QUEEN = 11;
    private static final int ENEMY_PLAYER = 2;
    private static final int ENEMY_QUEEN = 22;

    public static int[][] convertBoard(List<GetGameInfoResponse.Data.BoardItem> board, String color) {
        int[][] convertedBoard = new int[ROWS_NUMBER][COLUMN_NUMBER];
        System.out.println(convertedBoard);
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


    public static Step getStepByTwoBoards(int original[][], int changed[][]) {
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
                if (diff[i][j] == -1) from = new Position(j, i);
                if (diff[i][j] == 1) to = new Position(j, i);
            }
        }

        return new Step(from, to);

    }


}
