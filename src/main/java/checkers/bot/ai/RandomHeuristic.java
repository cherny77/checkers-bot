package checkers.bot.ai;

import checkers.bot.api.IHeuristic;

import java.util.Random;

public class RandomHeuristic implements IHeuristic {
    public static int MAX_GRADE = 100;
    public static int MIN_GRADE = 0;
    private Random random;

    public RandomHeuristic() {
        random = new Random();
    }

    @Override
    public int estimate(int[][] state) {
        return random.nextInt(MAX_GRADE - MIN_GRADE + 1) + MIN_GRADE;
    }
}
