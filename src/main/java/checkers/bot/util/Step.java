package checkers.bot.util;

public class Step {
    public static byte BLACK_CELLS_NUMBER_IN_ROW = 4;
    private Position from;
    private Position to;

    public Step(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Step(int fromX, int fromY, int toX, int toY) {
        this.from = new Position(fromX, fromY);
        this.to = new Position(toX, toY);
    }

    public Position getFrom() {
        return from;
    }


    public byte getConvertedFrom() {
        return convertPos(from);
    }

    public byte getConvertedTo() {
        return convertPos(to);
    }

    public static byte convertPos(Position pos){
        int res = pos.getY() * BLACK_CELLS_NUMBER_IN_ROW + pos.getX() / 2 + 1;
        return (byte) res;
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


}
