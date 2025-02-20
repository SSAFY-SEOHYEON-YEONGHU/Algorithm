package twentytwentyfive.february.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16932_모양만들기 {

    static int N, M;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        init();

        int maxArea = 0;
        int round = 1; // 0번 라운드부터 시작해서 끝날 때까지 해당 라운드에서 방문했음을 표시할 때 사용하는 변수
        int[][] isChecked = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (isChecked[i][j] != 0) continue; // 이미 그룹을 지어봤던 칸이면 실행 안함

                if (board[i][j] == 1) maxArea = Math.max(maxArea, findMaxAfterChange(i, j, board[i][j], isChecked, round)); // 원래 값으로 탐색
                else maxArea = Math.max(maxArea, findMaxAfterChange(i, j, Math.abs(board[i][j]-1), isChecked, round)); // 해당 칸 값 바꿔서 탐색
                round++;
            }
        }

        System.out.println(maxArea);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) board[i][j] = Integer.parseInt(st.nextToken());
        }
    }

    static int findMaxAfterChange(int startRow, int startCol,  int startValue, int[][] isChecked, int round) {
        int area = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startRow, startCol});
        isChecked[startRow][startCol] = round;

        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};
        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();
            area++;

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(nextRow, nextCol) || isChecked[nextRow][nextCol] == round) continue;
                if (board[nextRow][nextCol] != startValue) continue;

                q.offer(new int[] {nextRow, nextCol});
                isChecked[nextRow][nextCol] = round;
            }
        }

        return area;
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
