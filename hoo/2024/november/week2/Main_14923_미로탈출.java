package twentytwentyfour.november.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14923_미로탈출 {

    static class Hongik {
        int row;
        int col;
        int dist;
        int isMagicUsed;    // 홍익이가 마법의 지팡이를 사용했는 지 여부 / 0: 사용 x, 1: 사용 o

        public Hongik(int row, int col, int dist, int isMagicUsed) {
            this.row = row;
            this.col = col;
            this.dist = dist;
            this.isMagicUsed = isMagicUsed;
        }
    }

    static int N, M;
    static int Hx, Hy, Ex, Ey;  // 출발 위치, 탈출 위치
    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        escape();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Hx = Integer.parseInt(st.nextToken());
        Hy = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static void escape() {
        boolean[][][] isVisited = new boolean[N+1][M+1][2]; // 0: 마법 지팡이 사용 안하고 방문한 곳, 1: 마법 지팡이 사용하고 방문한 곳
        Queue<Hongik> q = new ArrayDeque<>();
        q.offer(new Hongik(Hx, Hy, 0, 0));
        isVisited[Hx][Hy][0] = true;

        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};
//        int minDist = Integer.MAX_VALUE;
        Hongik now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now.row == Ex && now.col == Ey) {
                System.out.println(now.dist);
                return;
            }

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now.row + dirRow[d];
                nextCol = now.col + dirCol[d];
                if (isOuted(nextRow, nextCol)) continue;    // 범위 밖이면 건너 뜀
                if (isVisited[nextRow][nextCol][now.isMagicUsed]) continue; // 이미 방문한 곳이라면 건너 뜀

                if (map[nextRow][nextCol] == 1) {   // 다음 칸이 벽이고
                    if (now.isMagicUsed == 0) { // 마법 지팡이를 사용 안한 경우
                        q.offer(new Hongik(nextRow, nextCol, now.dist+1, 1));
                        isVisited[nextRow][nextCol][1] = true;
                    }
                } else {
                    q.offer(new Hongik(nextRow, nextCol, now.dist+1, now.isMagicUsed));
                    isVisited[nextRow][nextCol][now.isMagicUsed] = true;
                }
            }
        }

        System.out.println(-1);
    }

    static boolean isOuted(int row, int col) {
        if ((1 <= row && row <= N) && (1 <= col && col <= M)) return false;
        return true;
    }

}
