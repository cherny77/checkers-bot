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
        CheckersEngine checkersEngine = new CheckersEngine(curState);
        ArrayList<ArrayList<int[][]>> newBoards = checkersEngine.getAllPossibleBoards(1);
        int maxGrade = -1;
        int[][] resMap = null;
        for (ArrayList<int[][]> a : newBoards) {
            for (int[][] board : a) {
                int currentGrade = heuristic.estimate(board);
                if (currentGrade > maxGrade || resMap == null) {
                    maxGrade = currentGrade;
                    resMap = board;
                }
            }
        }
        return ConvertUtils.getStepByTwoBoards(curState, resMap, color);
    }

//    public int[][] MiniMax(int[][] curState, int depth, boolean isMax) {
//        if (depth == 0)
//            return curState;
//
//        CheckersEngine checkersEngine = new CheckersEngine(curState);
//        ArrayList<ArrayList<int[][]>> newBoards = checkersEngine.getAllPossibleBoards(1);
//        List<int[][]> possibleMoves = new ArrayList<>();
//        for (ArrayList<int[][]> boards : newBoards)
//            possibleMoves.addAll(boards);
//        if (isMax)
//        {
//            Map<int[][], Integer> vals = new HashMap<>();
//            for (int[][] board : possibleMoves)
//            {
//                vals.put(board, heuristic.estimate(board));
//            }
//
//            int maxVal = Collections.max(vals.values());
//
//            List<int[][]> possVals = new ArrayList<>();
//            for (int[][] n : possibleMoves)
//            {
//                if (vals.get(n) == maxVal) possVals.add(n);
//            }
//
//            return GetRandomElement(possVals);
//        }
//
//        else
//        {
//            IDictionary<(int, int), int> vals = new Dictionary<(int, int), int>();
//            foreach ((int, int) n in possibleMoves)
//            {
//                vals.Add(n, weights[Minimax(depth - 1, n, true, grid, weights, entity)]);
//            }
//
//            int minVal = vals.Values.Min();
//            IList<(int, int)> possVals = new List<(int, int)>();
//            foreach ((int, int) n in possibleMoves)
//            {
//                if (vals[n] == minVal) possVals.Add(n);
//            }
//            return GetRandomElement(possVals);
//    }
}
