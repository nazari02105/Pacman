package Sample.Controller;

import java.util.Collections;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class GeneratingMaze {
    private  int x;
    private  int y;
    private  int[][] maze;

    public GeneratingMaze(int x, int y) {
        this.x = x;
        this.y = y;
        maze = new int[this.x][this.y];
        generateMaze(0, 0);
    }

    public GeneratingMaze() {

    }

    public String[][] display() {

        String[][] xyz = new String[y*2 + 1][x*2 + 1];
        int counterx = 0;
        int countery = 0;

        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                if (j == 0 && i == 0){
                    xyz[countery][counterx] = "#";
                    counterx += 1;
                    xyz[countery][counterx] = "#";
                    counterx += 1;
                }
                else{
                    if ((maze[j][i] & 1) == 0){
                        xyz[countery][counterx] = "#";
                        counterx += 1;
                        xyz[countery][counterx] = "#";
                        counterx += 1;
                    }
                    else{
                        xyz[countery][counterx] = "#";
                        counterx += 1;
                        xyz[countery][counterx] = " ";
                        counterx += 1;
                    }
                }
            }
            xyz[countery][counterx] = "#";
            counterx += 1;
            countery += 1;
            counterx = 0;

            for (int j = 0; j < x; j++) {
                if (j == 0){
                    xyz[countery][counterx] = "#";
                    counterx += 1;
                    xyz[countery][counterx] = " ";
                    counterx += 1;
                }
                else{
                    if ((maze[j][i] & 8) == 0){
                        xyz[countery][counterx] = "#";
                        counterx += 1;
                        xyz[countery][counterx] = " ";
                        counterx += 1;
                    }
                    else{
                        xyz[countery][counterx] = " ";
                        counterx += 1;
                        xyz[countery][counterx] = " ";
                        counterx += 1;
                    }
                }
            }
            xyz[countery][counterx] = "#";
            counterx += 1;
            countery += 1;
            counterx = 0;
        }
        for (int j = 0; j < x; j++) {
            if (j == x-1){
                xyz[countery][counterx] = "#";
                counterx += 1;
                xyz[countery][counterx] = "#";
                counterx += 1;
            }
            else{
                xyz[countery][counterx] = "#";
                counterx += 1;
                xyz[countery][counterx] = "#";
                counterx += 1;
            }
        }
        xyz[countery][counterx] = "#";
        counterx += 1;
        countery += 1;
        counterx = 0;

        return xyz;
    }

    private void generateMaze(int cx, int cy) {
        DIR[] dirs = DIR.values();
        Collections.shuffle(Arrays.asList(dirs));
        for (DIR dir : dirs) {
            int nx = cx + dir.dx;
            int ny = cy + dir.dy;
            if (between(nx, x) && between(ny, y)
                    && (maze[nx][ny] == 0)) {
                maze[cx][cy] |= dir.bit;
                maze[nx][ny] |= dir.opposite.bit;
                generateMaze(nx, ny);
            }
        }
    }

    private static boolean between(int v, int upper) {
        return (v >= 0) && (v < upper);
    }

    private enum DIR {
        N(1, 0, -1), S(2, 0, 1), E(4, 1, 0), W(8, -1, 0);
        private final int bit;
        private final int dx;
        private final int dy;
        private DIR opposite;
        static {
            N.opposite = S;
            S.opposite = N;
            E.opposite = W;
            W.opposite = E;
        }

        private DIR(int bit, int dx, int dy) {
            this.bit = bit;
            this.dx = dx;
            this.dy = dy;
        }
    };

    public static String[][] returningMaze() throws IOException {
        int soton = 10;
        int satr = 8;
         GeneratingMaze maze = new GeneratingMaze(soton, satr);
         String[][] myMaze = maze.display();

        Random random = new Random();
        for (int j = 0; j<100; ++j){
            int x = random.nextInt(19) + 1;
            int y = random.nextInt(15) + 1;
            if (myMaze[y][x].equals("#")){
                myMaze[y][x] = " ";
            }
        }

         return myMaze;
    }

}
