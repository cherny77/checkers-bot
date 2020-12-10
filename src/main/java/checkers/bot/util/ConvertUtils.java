package checkers.bot.util;

import checkers.bot.dto.GetGameInfoResponse;

import java.util.List;

public class ConvertUtils {
    public static final int ROWS_NUMBER = 8;
    public static final int COLUMN_NUMBER = 8;
    private static final int GOOD_PLAYER = 1;
    private static final int GOOD_QUEEN = 11;
    private static final int ENEMY_PLAYER = 2;
    private static final int ENEMY_QUEEN = 22;

    public static byte[][] convertBoard(List<GetGameInfoResponse.Data.BoardItem> board, String color) {
        byte[][] convertedBoard = new byte[ROWS_NUMBER][COLUMN_NUMBER];
        System.out.println(convertedBoard);
        switch (color) {
            case "RED":
                for (GetGameInfoResponse.Data.BoardItem boardItem : board) {
                    if (boardItem.getRow() % 2 == 0) {
                        convertedBoard[convertedBoard.length - 1 - boardItem.getRow()][convertedBoard.length - 1 - (boardItem.getColumn() * 2 + 1)] = getBoardItemNumber(boardItem, color);
                    } else {
                        convertedBoard[convertedBoard.length - 1 - boardItem.getRow()][convertedBoard.length - 1 - boardItem.getColumn() * 2] = getBoardItemNumber(boardItem, color);
                    }
                }
                break;
            case "BLACK":
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

    public static byte getBoardItemNumber(GetGameInfoResponse.Data.BoardItem boardItem, String color) {
        if (boardItem.getColor().equals(color)) {
            if (boardItem.isKing()) return GOOD_QUEEN;
            return GOOD_PLAYER;
        } else {
            if (boardItem.isKing()) return ENEMY_QUEEN;
            return ENEMY_PLAYER;
        }
    }

    public static void printState(byte map[][]) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }


}
