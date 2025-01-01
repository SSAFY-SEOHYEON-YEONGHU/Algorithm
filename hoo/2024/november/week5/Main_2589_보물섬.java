package twentytwentyfour.november.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589_보물섬 {

    static int N, M;
    static char[][] map;
    static int maxDist; // 최대 거리

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'W') continue; // 땅 아니면 건너 뜀
                bfs(i, j);
            }
        }
        System.out.println(maxDist);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            char col;
            for (int j = 0; j < M; j++) {
                col = row.charAt(j);
                map[i][j] = col;
            }
        }
        maxDist = 0;
    }

    static void bfs(int startRow, int startCol) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[N][M];
        q.offer(new int[] {startRow, startCol, 0}); // 0: 행, 1: 열, 2: 이동 시간
        isVisited[startRow][startCol] = true;

        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();
            maxDist = Math.max(maxDist, now[2]);    // 최대 거리 갱신

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if ((0 > nextRow || nextRow >= N) || (0 > nextCol || nextCol >= M)
                        || map[nextRow][nextCol] == 'W' || isVisited[nextRow][nextCol]) continue;
                q.offer(new int[] {nextRow, nextCol, now[2]+1});
                isVisited[nextRow][nextCol] = true;
            }
        }
    }

}
