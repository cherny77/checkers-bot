package checkers.bot.dto;

//import checkers.bot.util.BordItemDeserializer;

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


    public static class Data {
        private String status;
        private String whose_turn;
        private String winner;
        private List<BoardItem> board;
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

        public List<BoardItem> getBoard() {
            return board;
        }

        public void setBoard(List<BoardItem> board) {
            this.board = board;
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

        @Override
        public String toString() {
            return "Data{" +
                    "status='" + status + '\'' +
                    ", whose_turn='" + whose_turn + '\'' +
                    ", winner='" + winner + '\'' +
                    ", board=" + board +
                    ", available_time=" + available_time +
                    ", is_started=" + is_started +
                    ", is_finished=" + is_finished +
                    '}';
        }

        public static class BoardItem {
            private String color;
            private int row;
            private int column;
            private boolean king;
            private int position;

            public BoardItem() {
            }

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
                return king;
            }

            public void setKing(boolean king) {
                this.king = king;
            }

            public int getPosition() {
                return position;
            }

            public void setPosition(int position) {
                this.position = position;
            }

            @Override
            public String toString() {
                return "BordItem{" +
                        "color='" + color + '\'' +
                        ", row=" + row +
                        ", column=" + column +
                        ", king=" + king +
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



