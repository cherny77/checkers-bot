package checkers.bot.dto;

public class MakeMoveResponse {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MakeMoveResponse{" +
                "data='" + data + '\'' +
                '}';
    }
}
