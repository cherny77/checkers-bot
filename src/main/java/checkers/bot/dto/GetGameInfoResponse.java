package checkers.bot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetGameInfoResponse {
    private String status;
    private String whoseTurn;
    private String winner;
    private List<BordItem> board;
    private double availableTime;
    private boolean isStarted;
    private boolean isFinished;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWhoseTurn() {
        return whoseTurn;
    }

    public void setWhoseTurn(String whoseTurn) {
        this.whoseTurn = whoseTurn;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public double getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(double availableTime) {
        this.availableTime = availableTime;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public List<BordItem> getBoard() {
        return board;
    }

    public void setBoard(List<BordItem> board) {
        this.board = board;
    }

    @Override
    public String toString() {
        return "GetGameInfoResponse{" +
                "status='" + status + '\'' +
                ", whoseTurn='" + whoseTurn + '\'' +
                ", winner='" + winner + '\'' +
                ", board=" + board +
                ", availableTime=" + availableTime +
                ", isStarted=" + isStarted +
                ", isFinished=" + isFinished +
                '}';
    }

    public class BordItem {
        private String color;
        private int row;
        private int column;
        private boolean isKing;
        private int position;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getColumn() {
            return column;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public boolean isKing() {
            return isKing;
        }

        public void setKing(boolean king) {
            isKing = king;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "Checker{" +
                    "color='" + color + '\'' +
                    ", row=" + row +
                    ", column=" + column +
                    ", isKing=" + isKing +
                    ", position=" + position +
                    '}';
        }
    }
}


