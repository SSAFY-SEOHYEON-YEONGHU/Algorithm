package twentytwentyfive.february.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_24463_미로 {

    static class Player{
        int row;
        int col;

        public Player(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int N, M;
    static char[][] maze;

    public static void main(String[] args) throws IOException {
        int[][] entries = init();
        maze[entries[0][0]][entries[0][1]] = '.';
        boolean[][] isVisited = new boolean[N][M];
        isVisited[entries[0][0]][entries[0][1]] = true;
        findExit(new Player(entries[0][0], entries[0][1]), entries[1], isVisited);
    }

    static int[][] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];
        int[][] entries = new int[2][2]; // 미로의 두 출구 정보 저장하는 배열
        int entryIndex = 0;
        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = (row.charAt(j) == '.')? '@':row.charAt(j); // .인 곳 처음부터 @로 표시
                if ( (i == 0 || i == N-1) && maze[i][j] == '@' ) entries[entryIndex++] = new int[] {i, j}; // 맨 윗 줄이나 아랫 줄에 구멍이 있는 경우
                else if ( (j == 0 || j == M-1) && maze[i][j] == '@' ) entries[entryIndex++] = new int[] {i, j}; // 왼쪽 줄이나 오른쪽 줄에 구멍이 있는 경우
            }
        }

        return entries;
    }

    static void findExit(Player now, int[] endPoint, boolean[][] isVisited) {
        if (now.row == endPoint[0] && now.col == endPoint[1]) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) sb.append(maze[i][j]);
                sb.append("\n");
            }
            System.out.println(sb);

            return;
        }

        int[] dirRow = new int[] {-1, 0, 1, 0};
        int[] dirCol = new int[] {0, 1, 0, -1};
        int nextRow, nextCol;
        for (int d = 0; d < 4; d++) {
            nextRow = now.row + dirRow[d];
            nextCol = now.col + dirCol[d];

            if (isOuted(nextRow, nextCol) || maze[nextRow][nextCol] == '+' || isVisited[nextRow][nextCol]) continue;

            maze[nextRow][nextCol] = '.';
            isVisited[nextRow][nextCol] = true;
            findExit(new Player(nextRow, nextCol), endPoint, isVisited);
            maze[nextRow][nextCol] = '@';
            isVisited[nextRow][nextCol] = false;
        }

    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < M)) return false;
        return true;
    }

}
