package checkers.bot.engine;

import java.util.ArrayList;

public class data_boards {

    public ArrayList<int[][]> boards = new ArrayList<>();

    data_boards() {
        init();
    }

    public int[][] getBoard(int index) {
        return boards.get(index);
    }

    private void init() {
        // 0
        boards.add(new int[][]
                {{0,-1, 0,-1, 0,-1, 0,-1},
                 {-1,0,-1, 0,-1, 0,-1, 0},
                 {0,-1, 0,-1, 0,-1, 0,-1},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 1, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0}});
        // 1
        boards.add(new int[][]
                {{0,-1, 0,-1, 0,-1, 0,-1},
                 {-1,0,-1, 0,-1, 0,-1, 0},
                 {0,-1, 0,-1, 0,-1, 0,-1},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 1, 0, 0, 0, 1},
                 {0, 0, 0, 0, 0, 0, 1, 0},
                 {0, 0, 0, 0, 0, 0, 0, 1}});
        // 2
        boards.add(new int[][]
                {{0,-1, 0,-1, 0,-1, 0, 0},
                 {-1,0,-1, 0,-1, 0, 1, 0},
                 {0,-1, 0,-1, 0,-1, 0,-1},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 1, 0, 0, 0, 1},
                 {0, 0, 0, 0, 0, 0, 1, 0},
                 {0, 0, 0, 0, 0, 0, 0, 1}});
        // 3
        boards.add(new int[][]
                {{0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, -1, 0, 0, 0, 0, 0, 0},
                 {0, 0,-1, 0,-1, 0, 0, 0},
                 {0, 0, 0, 1, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0}});
        // 4
        boards.add(new int[][]
                {{0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0,-1, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0,-1, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0,-1, 0, 0, 0, 0, 0, 1},
                 {1, 0, 0, 0, 0, 0, 1, 0},
                 {0, 0, 0, 0, 0, 0, 0, 1}});
        // 5
        boards.add(new int[][]
                {{0, 0, 0, 0, 0, 0, 0, 0},
                 {0,-1, 0,-1, 0, 0, 0, 0},
                 {0, 0, 0, 0,10, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0}});
        // 6
        boards.add(new int[][]
                {{0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0,-1, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0,-1, 0,-1, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0,-1, 0, 0, 0, 0, 0, 1},
                 {1, 0, 0, 0, 0, 0, 1, 0},
                 {0, 0, 0, 0, 0, 0, 0, 1}});
        // 7
        boards.add(new int[][]
                {{0, 0, 0, 0,10, 0, 0, 0},
                 {0, 0, 0, 0, 0,-1, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0,-1, 0,-1, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0,-1, 0, 0, 0, 0, 0, 1},
                 {1, 0, 0, 0, 0, 0, 1, 0},
                 {0, 0, 0, 0, 0, 0, 0, 1}});

        // 8
        boards.add(new int[][]
                {{0,-1, 0,-1, 0,-1, 0,-1},
                 {-1,0,-1, 0,-1, 0,-1, 0},
                 {0,-1, 0,-1, 0,-1, 0,-1},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 0, 0, 0, 0, 0},
                 {0, 0, 0, 1, 0, 0, 0, 1},
                 {0, 0, 0, 0, 0, 0, 1, 0},
                 {0, 0, 0, 0, 0, 0, 0, 1}});
        
    }


}