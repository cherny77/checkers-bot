package checkers.bot.engine;

import java.io.*;
import java.util.ArrayList;

public class MainTEST {
    public static void main(String[] args) throws IOException {
        data_boards data = new data_boards();
        int[][] testingBoard = data.getBoard(4);

        CheckersEngine engine = new CheckersEngine(testingBoard, -1);
        ArrayList<ArrayList<int[][]>> newBoards = engine.getAllPossibleBoards(1);
        new MainTEST().writeResultToFile(testingBoard, newBoards);
    }

    private void writeResultToFile(int[][] originBoard, ArrayList<ArrayList<int[][]>> boards) throws IOException {
        String boardsStr = "ORIGIN\n";
        boardsStr += boardToString(originBoard);
        boardsStr += "TRANSFORMED\n";
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < boards.get(i).size(); j++) {
                boardsStr += boardToString(boards.get(i).get(j));
            }
        }
        String fileName = "D:\\code\\AI\\chekers\\checkers-bot\\src\\main\\java\\checkers\\bot\\engine\\boards.txt";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] strToBytes = boardsStr.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();

    }

    private String boardToString(int[][] board) {
        String b = "";
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] == 1 || board[y][x] == 2) {
                    b += " " + board[y][x] + ",";
                } else if (board[y][x] == 0) {
                    b += " *,";
                } else {
                    b += board[y][x] + ",";
                }
            }
            b += "\n";
        }
        b += "\n";
        return b;
    }


}
