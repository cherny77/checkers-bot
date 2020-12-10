package checkers.bot.util;

import org.junit.jupiter.api.Test;

public class StepTests {
    @Test
    void ConvertTest() {
        Step step = new Step( new Position(7, 2), new Position(6, 3));
        System.out.println("From: " + step.getConvertedFrom());
        System.out.println("To: " + step.getConvertedTo());
    }
}
