package twentytwentyfive.january.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_4179_불 {

    static class J {
        int row;
        int col;
        int dist;

        public J(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    static int R, C;
    static char[][] map;
    static Queue<int[]> fireAxisQueue;    // 불이 나있는 칸들 가지고 있는 큐

    static int[] dirRow = new int[] {-1, 0, 1, 0};  // 상 우 하 좌
    static int[] dirCol = new int[] {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        J initJihoon = init();
        int escapeTime = doEscape(initJihoon);

        System.out.println( (escapeTime==-1)? "IMPOSSIBLE":escapeTime );
    }

    static J init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fireAxisQueue = new ArrayDeque<>();
        J initJihoon = new J(0, 0, 0);
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'F') fireAxisQueue.offer(new int[] {i, j});    // 불이난 위치 저장
                if (map[i][j] == 'J') initJihoon = new J(i, j, 0); // 지훈이 시작 위치 저장
            }
        }

        return initJihoon;
    }

    static int doEscape(J initJihoon) {
        Queue<J> q = new ArrayDeque<>();
        boolean[][] isVisited = new boolean[R][C];
        q.offer(initJihoon);
        isVisited[initJihoon.row][initJihoon.col] = true;

        J now;
        while (!q.isEmpty()) {
            int nowJihoonCount = q.size();
            for (int i = 0; i < nowJihoonCount; i++) {  // 현재 시간의 지훈이 위치에 대해서 수행
                now = q.poll();
                if (map[now.row][now.col] == 'F') continue; // 지훈이가 간 위치에 불이 덮친 경우 탈출 불가

                int nextRow, nextCol;
                for (int d = 0; d < 4; d++) {
                    nextRow = now.row + dirRow[d];
                    nextCol = now.col + dirCol[d];
                    if (isOuted(nextRow, nextCol)) return now.dist+1;   // 가장자리와 접한 곳에서 다음 이동은 탈출임

                    if (map[nextRow][nextCol] == 'F' || map[nextRow][nextCol] == '#' || isVisited[nextRow][nextCol]) continue; // 다음 이동할 곳이 불이난 곳이나 벽, 방문했던 곳이면 이동 불가
                    q.offer(new J(nextRow, nextCol, now.dist+1));
                    isVisited[nextRow][nextCol] = true;
                }
            }

            spreadFire();   // 불 번짐 처리
        }

        return -1;
    }

    static void spreadFire() {
        int fireCount = fireAxisQueue.size();
        int[] now;
        for (int i = 0; i < fireCount; i++) {
            now = fireAxisQueue.poll();
            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || map[nextRow][nextCol] == '#' || map[nextRow][nextCol] == 'F') continue;  // 범위 밖, 벽, 이미 불인 곳은 건너 뜀
                map[nextRow][nextCol] = 'F';
                fireAxisQueue.offer(new int[] {nextRow, nextCol});
            }
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < R) && (0 <= col && col < C)) return false;
        return true;
    }

}
