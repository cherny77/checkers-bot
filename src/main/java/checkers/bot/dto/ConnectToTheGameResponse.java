package checkers.bot.dto;

public class ConnectToTheGameResponse {
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

    @Override
    public String toString() {
        return "ConnectToTheGameResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }

    public class Data {
        private String color;
        private String token;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "color='" + color + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}
