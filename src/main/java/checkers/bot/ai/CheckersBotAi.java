package checkers.bot.ai;

import checkers.bot.api.ICheckersBotAi;
import checkers.bot.api.IHeuristic;
import checkers.bot.engine.CheckersEngine;
import checkers.bot.util.ConvertUtils;
import checkers.bot.util.Move;

import java.util.*;

public class CheckersBotAi implements ICheckersBotAi {
    public static int DEPTH = 3;
    private IHeuristic heuristic;
    private String color;


    public CheckersBotAi(IHeuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Move getNextStep(int[][] curState) {
        int[][] resMap = miniMax(curState, DEPTH, true);
//        CheckersEngine checkersEngine = new CheckersEngine(curState);
//        ArrayList<ArrayList<int[][]>> newBoards = checkersEngine.getAllPossibleBoards(1);
//        int maxGrade = -1;
//        int[][] resMap = null;
//        for (ArrayList<int[][]> a : newBoards) {
//            for (int[][] board : a) {
//                int currentGrade = heuristic.estimate(board);
//                if (currentGrade > maxGrade || resMap == null) {
//                    maxGrade = currentGrade;
//                    resMap = board;
//                }
//            }
//        }
        return ConvertUtils.getStepByTwoBoards(curState, resMap, color);
    }

    public int[][] miniMax(int[][] curState, int depth, boolean isMax) {
        if (depth == 0)
            return curState;

        CheckersEngine checkersEngine = new CheckersEngine(curState);
        List<int[][]> possibleMoves = new ArrayList<>();
        if (isMax)
            possibleMoves = checkersEngine.getPossibleBoards(1);
        else possibleMoves = checkersEngine.getPossibleBoards(2);

        Map<int[][], Integer> vals = new HashMap<>();
        List<int[][]> possVals = new ArrayList<>();
        if (isMax) {
            for (int[][] board : possibleMoves) {
                vals.put(board, heuristic.estimate(this.miniMax(board, depth - 1, false)));
            }
            int maxVal = Collections.max(vals.values());
            for (int[][] n : possibleMoves) {
                if (vals.get(n) == maxVal) possVals.add(n);
            }
        } else {
            for (int[][] board : possibleMoves) {
                vals.put(board, heuristic.estimate(this.miniMax(board, depth - 1, true)));
            }
            int minVal = Collections.min(vals.values());
            for (int[][] n : possibleMoves) {
                if (vals.get(n) == minVal) possVals.add(n);
            }

        }
        return possVals.get(0);
    }
}
