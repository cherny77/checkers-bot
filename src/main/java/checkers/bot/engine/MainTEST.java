package checkers.bot.engine;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainTEST {
    public static void main(String[] args) throws IOException {
        data_boards data = new data_boards();
        int[][] testingBoard = data.getBoard(0);
        CheckersEngine engine = new CheckersEngine(testingBoard, 1);
        ArrayList<ArrayList<int[][]>> newBoards = engine.getAllPossibleBoards(testingBoard, 1);
        new MainTEST().writeResultToFile(testingBoard, newBoards);
    }

    private void writeResultToFile(int[][] originBoard, ArrayList<ArrayList<int[][]>> boards) throws IOException {
        String boardsStr = boardToString(originBoard);
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < boards.get(i).size(); j++) {
                boardsStr += boardToString(boards.get(i).get(j));
            }
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("board.txt", true));
        writer.append(' ');
        writer.append(boardsStr);

        writer.close();
    }

    private String boardToString(int[][] board) {
        String b = "";
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (board[x][y] == 0 || board[x][y] == 1 || board[x][y] == 6) {
                    b += " " + board[x][y] + ",";
                } else {
                    b += board[x][y] + ",";
                }
            }
            b += "\n";
        }
        b += "\n";
        return b;
    }


}
