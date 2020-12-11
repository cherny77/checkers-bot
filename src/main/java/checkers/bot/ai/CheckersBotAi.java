package checkers.bot.ai;

import checkers.bot.api.ICheckersBotAi;
import checkers.bot.api.IHeuristic;
import checkers.bot.engine.CheckersEngine;
import checkers.bot.util.ConvertUtils;
import checkers.bot.util.Move;

import java.util.ArrayList;

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
}
