package checkers.bot.ai;

import checkers.bot.api.ICheckersBotAi;
import checkers.bot.api.IHeuristic;
import checkers.bot.engine.CheckersEngine;

import java.util.ArrayList;
import java.util.List;

public class CheckersBotAi implements ICheckersBotAi {
    public static int DEPTH = 3;
    private IHeuristic heuristic;


    public CheckersBotAi(IHeuristic heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public void getNextStep(int[][] curState) {
        CheckersEngine checkersEngine = new CheckersEngine(curState, 1);
        ArrayList<ArrayList<int[][]>> newBoards = checkersEngine.getAllPossibleBoards(2);
        int maxGrade = 0;
        int[][] resMap;
        for (ArrayList<int[][]> a : newBoards) {
            for (int[][] board : a) {
                int currentGrade = heuristic.estimate(board);
                if (currentGrade > maxGrade) {
                    maxGrade = currentGrade;
                    resMap = board;
                }
            }
        }
    }
}
