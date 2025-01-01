package twentytwentyfour.november.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_14940_쉬운최단거리 {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        int[] startPoint = init();
        bfs(startPoint);
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int[] startPoint = new int[2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int info = Integer.parseInt(st.nextToken());
                if (info == 2) startPoint = new int[] {i, j};   // 시작점 저장
                map[i][j] = info;
            }
        }

        return startPoint;
    }

    static void bfs(int[] startPoint) {
        Queue<int[]> q = new ArrayDeque<>();
        int[][] visitedMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) visitedMap[i][j] = -1;
        }
        q.offer(new int[] {startPoint[0], startPoint[1], 0});
        visitedMap[startPoint[0]][startPoint[1]] = 0;

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || map[nextRow][nextCol] == 0 || visitedMap[nextRow][nextCol] != -1) continue;
                q.offer(new int[] {nextRow, nextCol, now[2]+1});
                visitedMap[nextRow][nextCol] = now[2]+1;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visitedMap[i][j] == -1) {   // 도달할 수 없는 위치 중에서
                    if (map[i][j] == 0) sb.append("0"); // 원래 도달할 수 없는 곳인 위치는 0
                    else sb.append("-1");   // 원래 도달할 수 있는 땅인데 도달 못한 곳이면 -1
                }
                else sb.append(visitedMap[i][j]);   // 도달한 곳은 그냥 값으로
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
