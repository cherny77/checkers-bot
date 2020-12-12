package checkers.bot.engine;

import checkers.bot.ai.Heuristic;

import java.io.*;
import java.util.ArrayList;

public class MainTEST {
    public static void main(String[] args) throws IOException {
        long timeStart = System.nanoTime();
        data_boards data = new data_boards();
        int[][] testingBoard = data.getBoard(0);

        CheckersEngineTest engine = new CheckersEngineTest(testingBoard);
        ArrayList<ArrayList<int[][]>> newBoards = engine.getAllPossibleBoards(1);
        new MainTEST().writeResultToFile(testingBoard, newBoards);
        for (int i = 0; i < newBoards.size(); i++) {
            for (int j = 0; j < newBoards.get(i).size(); j++) {
                Heuristic heuristic = new Heuristic(testingBoard, newBoards.get(i).get(j));
                int value = heuristic.countDifficultHeuristics();
                System.out.println(value);
            }
        }
        long timeEnd = System.nanoTime();
        System.out.println((timeEnd - timeStart) / 1_000_000 + " - milliseconds");
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
        String fileName = "src\\test\\java\\checkers\\bot\\engine\\boards.txt";
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
