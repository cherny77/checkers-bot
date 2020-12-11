package checkers.bot.dto;

import checkers.bot.util.Move;

import java.util.ArrayList;
import java.util.List;

public class MakeMoveRequest {
    private List<Integer> move;

    public MakeMoveRequest(List<Integer> move) {
        this.move = move;
    }

    public MakeMoveRequest(Move step) {
        move = new ArrayList<>();
        move.add(step.getConvertedFrom());
        move.add(step.getConvertedTo());
    }

    public List<Integer> getMove() {
        return move;
    }

    public void setMove(List<Integer> move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return "MakeMoveRequest{" +
                "move=" + move +
                '}';
    }
}
