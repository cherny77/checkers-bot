package checkers.bot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetGameInfoResponse {
    private String status;
    private Data data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    class Data {
        private String status;
        private String whose_turn;
        private String winner;
        private List<BordItem> board;
        private double available_time;
        private boolean is_started;
        private boolean is_finished;


        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getWhose_turn() {
            return whose_turn;
        }

        public void setWhose_turn(String whose_turn) {
            this.whose_turn = whose_turn;
        }

        public String getWinner() {
            return winner;
        }

        public void setWinner(String winner) {
            this.winner = winner;
        }

        public double getAvailable_time() {
            return available_time;
        }

        public void setAvailable_time(double available_time) {
            this.available_time = available_time;
        }

        public boolean isIs_started() {
            return is_started;
        }

        public void setIs_started(boolean is_started) {
            this.is_started = is_started;
        }

        public boolean isIs_finished() {
            return is_finished;
        }

        public void setIs_finished(boolean is_finished) {
            this.is_finished = is_finished;
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
                    ", whoseTurn='" + whose_turn + '\'' +
                    ", winner='" + winner + '\'' +
                    ", board=" + board +
                    ", availableTime=" + available_time +
                    ", isStarted=" + is_started +
                    ", isFinished=" + is_finished +
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

    @Override
    public String toString() {
        return "GetGameInfoResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}


