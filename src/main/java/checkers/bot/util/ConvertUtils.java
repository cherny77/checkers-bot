package checkers.bot.util;

import checkers.bot.dto.GetGameInfoResponse;

import java.util.List;

public class ConvertUtils {
    public static final int ROWS_NUMBER = 8;
    public static final int COLUMN_NUMBER = 8;
//    public static final int OUR_CHECKER = 1;
//    public static final int OUR_CHECKER = 1;

    public static byte[][] convertBoard(List<GetGameInfoResponse.Data.BoardItem> board, String color){
        byte[][] convertedBoard = new byte[COLUMN_NUMBER][ROWS_NUMBER];
        System.out.println(convertedBoard);
        for (GetGameInfoResponse.Data.BoardItem boardItem : board) {

        }
        return null;
    }

}
