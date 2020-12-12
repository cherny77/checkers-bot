package checkers.bot.ai;

import checkers.bot.api.ICheckersBotAi;
import checkers.bot.api.IHeuristic;
import checkers.bot.engine.CheckersEngine;
import checkers.bot.util.ConvertUtils;
import checkers.bot.util.Move;

import java.util.*;

public class CheckersBotAi implements ICheckersBotAi {
    public static int DEPTH = 6;
    private IHeuristic heuristic;
    private IHeuristic helpingHeuristic;
    private String color;


    public CheckersBotAi(IHeuristic heuristic, IHeuristic helpingHeuristic) {
        this.heuristic = heuristic;
        this.helpingHeuristic = helpingHeuristic;
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public Move getNextStep(int[][] curState) {
        int[][] resMap = miniMax(curState, DEPTH, true);
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
                vals.put(board, heuristic.estimate(curState, this.miniMax(board, depth - 1, false)));
            }
            int maxVal = Collections.max(vals.values());
            for (int[][] n : possibleMoves) {
                if (vals.get(n) == maxVal) possVals.add(n);
            }
        } else {
            for (int[][] board : possibleMoves) {
                vals.put(board, heuristic.estimate(curState, this.miniMax(board, depth - 1, true)));
            }
            int minVal = Collections.min(vals.values());
            for (int[][] n : possibleMoves) {
                if (vals.get(n) == minVal) possVals.add(n);
            }

        }
        if (possVals.size() == 1)
            return possVals.get(0);
        Map<int[][], Integer> finVals = new HashMap<>();
        List<int[][]> finPossVals = new ArrayList<>();
        if (isMax) {
            for (int[][] board : possVals) {
                finVals.put(board, helpingHeuristic.estimate(curState, board));
            }

            int maxVal = Collections.max(finVals.values());
            for (int[][] n : possVals) {
                if (finVals.get(n) == maxVal) finPossVals.add(n);
            }
        } else {
            for (int[][] board : possVals) {
                finVals.put(board, helpingHeuristic.estimate(curState, board));
            }

            int minVal = Collections.min(finVals.values());
            for (int[][] n : possVals) {
                if (finVals.get(n) == minVal) finPossVals.add(n);
            }
        }

        if (finPossVals.size() == 1) finPossVals.get(0);
        return getRandom(possVals);
    }


    private int[][] getRandom(List<int[][]> possVal) {
        Random random = new Random();
        int ind = random.nextInt(possVal.size());
        return possVal.get(ind);
    }
}


