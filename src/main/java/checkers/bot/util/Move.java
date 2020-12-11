package checkers.bot.util;

import static checkers.bot.util.Constants.COLUMN_NUMBER;

public class Move {
    public static byte BLACK_CELLS_NUMBER_IN_ROW = 4;
    private Position from;
    private Position to;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Move(int fromX, int fromY, int toX, int toY) {
        this.from = new Position(fromX, fromY);
        this.to = new Position(toX, toY);
    }

    public Position getFrom() {
        return from;
    }


    public int getConvertedFrom() {
        return convertPos(from);
    }

    public int getConvertedTo() {
        return convertPos(to);
    }

    public static int convertPos(Position pos) {
        int res = pos.getY() * BLACK_CELLS_NUMBER_IN_ROW + (pos.getX()) / 2 + 1;
        return res;
    }

    public void setFrom(Position from) {
        this.from = from;
    }

    public Position getTo() {
        return to;
    }

    public void setTo(Position to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Step: " + getConvertedFrom() + ", " + getConvertedTo();
    }
}
