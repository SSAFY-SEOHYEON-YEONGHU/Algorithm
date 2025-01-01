package september.week2;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_240911_미로탈출 {

    class Player {
        int row;
        int col;
        int count;
        int leverPulled;    // 레버를 당겼는 지 여부 저장

        public Player(int row, int col, int count, int leverPulled) {
            this.row = row;
            this.col = col;
            this.count = count;
            this.leverPulled = leverPulled;
        }

        @Override
        public String toString() { return this.row + " " + this.col + " " + this.count + " " + this.leverPulled; }
    }

    public int solution(String[] maps) {
        Player initPlayer = init(maps);
        int answer = bfs(maps, initPlayer);

        return answer;
    }

    Player init(String[] maps) {
        Player initPlayer = null;
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length(); j++) {
                if (maps[i].charAt(j) == 'S') initPlayer = new Player(i, j, 0, 0);
            }
        }

        return initPlayer;
    }

    int bfs(String[] maps, Player initPlayer) {
        boolean[][][] isVisited = new boolean[maps.length][maps[0].length()][2];   // 3차원의 인덱스 0에는 레버를 안당긴 플레이어의 방문을, 인덱스 1에는 레버를 당긴 플레이어의 방문을 표시
        Queue<Player> q = new ArrayDeque<>();
        q.offer(initPlayer);
        isVisited[initPlayer.row][initPlayer.col][initPlayer.leverPulled] = true;

        int[] dirRow = new int[] {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dirCol = new int[] {0, 0, -1, 1};
        Player now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now.leverPulled == 1 && maps[now.row].charAt(now.col) == 'E') return now.count;  // 레버 당기고 출구 찾은 경우 움직인 횟수 반환

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                Player next = null;
                nextRow = now.row + dirRow[d];
                nextCol = now.col + dirCol[d];
                if (isOutedOrBlocked(maps, nextRow, nextCol) || isVisited[nextRow][nextCol][now.leverPulled]) continue;

                if (maps[nextRow].charAt(nextCol) == 'L') next = new Player(nextRow, nextCol, now.count+1, 1);
                else next = new Player(nextRow, nextCol, now.count+1, now.leverPulled);
                q.offer(next);
                isVisited[nextRow][nextCol][next.leverPulled] = true;
            }
        }

        return -1;
    }

    boolean isOutedOrBlocked(String[] maps, int row, int col) {
        if ((0 <= row && row < maps.length) && (0 <= col && col < maps[0].length())
                && maps[row].charAt(col) != 'X') return false;

        return true;
    }

}
