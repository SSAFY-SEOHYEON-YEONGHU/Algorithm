package twentytwentyfive.february.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_30036_INK {

    static Map<Character, Integer> UDLRMap = new HashMap<>(); // U: 0, D: 2, L: 3, R: 1
    static int[] dirRow = new int[] {-1, 0, 1, 0};
    static int[] dirCol = new int[] {0, 1, 0, -1};

    static class Player {
        int row;
        int col;
        int jumpCount;
        int ink;

        public Player(int row, int col, int jumpCount, int ink) {
            this.row = row;
            this.col = col;
            this.jumpCount = jumpCount;
            this.ink = ink;
        }

    }

    static int I, N, K;
    static String inkString;
    static char[][] map;
    static String command;

    public static void main(String[] args) throws IOException {
        Player initPlayer = init();
        doCommand(initPlayer);
    }

    static Player init() throws IOException {
        UDLRMap.put('U', 0);
        UDLRMap.put('D', 2);
        UDLRMap.put('L', 3);
        UDLRMap.put('R', 1);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        I = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        inkString = br.readLine();

        map = new char[N][N];
        Player initPlayer = new Player(-1, -1, 0, 0);
        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j);
                if (map[i][j] == '@') initPlayer = new Player(i, j, 0, 0);
            }
        }

        command = br.readLine();

        return initPlayer;
    }

    static void doCommand(Player player) {
        int[][] isChecked = new int[N][N];

        for (int c = 0; c < command.length(); c++) { // 주어진 커맨드만큼 실행
            char nowCommand = command.charAt(c);

            if (nowCommand == 'j') player.ink++; // 잉크 충전 커맨드일 경우
            else if (nowCommand == 'J') { // 점프 커맨드일 경우
                doInk(player, isChecked, c);
            } else { // 이동 커맨드일 경우
                int commandInteger = UDLRMap.get(nowCommand);
                int nextRow = player.row + dirRow[commandInteger];
                int nextCol = player.col + dirCol[commandInteger];

                if (isOuted(nextRow, nextCol) || map[nextRow][nextCol] != '.') continue; // 범위 밖이거나 빈칸이 아니면 명령 무시

                map[player.row][player.col] = '.';
                player = new Player(nextRow, nextCol, player.jumpCount, player.ink);
                map[player.row][player.col] = '@';
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) sb.append(map[i][j]);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void doInk(Player player, int[][] isChecked, int round) { // bfs를 이용해 잉크 뿌리기
        player.jumpCount++;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {player.row, player.col});
        isChecked[player.row][player.col] = round;

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];

                if (isOuted(nextRow, nextCol) || Math.abs(nextRow-player.row) + Math.abs(nextCol-player.col) > player.ink) continue; // 맵 범위 밖이거나 잉크뿌릴 수 있는 범위 밖이면 건너 뜀
                if (isChecked[nextRow][nextCol] == round) continue; // 이번 라운드에 해당 칸 체크했으면 건너 뜀

                int nowInkIndex = player.jumpCount % I - 1;
                if (nowInkIndex == -1) nowInkIndex = I-1;
                char nowInk = inkString.charAt(nowInkIndex);
                if (map[nextRow][nextCol] != '.') map[nextRow][nextCol] = nowInk; // 해당 칸이 장애물일 경우 색칠
                q.offer(new int[] {nextRow, nextCol});
                isChecked[nextRow][nextCol] = round;
            }
        }
        player.ink = 0;
    }


    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;
        return true;
    }

}
