package august;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_240820_리코쳇로봇 {

    class Mover {
        int row;
        int col;
        int moves;  // 움직인 횟수

        public Mover(int row, int col, int moves) {
            this.row = row;
            this.col = col;
            this.moves = moves;
        }

        @Override
        public String toString() { return this.row + " " + this.col + " " + this.moves; }
    }

    int[] dirRow = new int[] {-1, 1, 0, 0}; // 상 하 좌 우
    int[] dirCol = new int[] {0, 0, -1, 1};
    int R, C;  // 보드의 높이, 너비
    int[] goal; // 목표 지점의 좌표

    public int solution(String[] board) {
        Mover initMover = init(board);
        int answer = bfs(board, initMover);

        return answer;
    }

    Mover init(String[] board) {
        Mover initMover = null;

        R = board.length;
        C = board[0].length();
        goal = new int[2];
        String nowPoint;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                nowPoint = String.valueOf(board[i].charAt(j));
                if (nowPoint.equals("R")) initMover = new Mover(i, j, 0);
                else if (nowPoint.equals("G")) goal = new int[] {i, j};
            }
        }

        return initMover;
    }

    int bfs(String[] board, Mover initMover) {
        int answer = -1;

        boolean[][] isVisited = new boolean[R][C];
        Queue<Mover> q = new ArrayDeque<>();
        q.offer(initMover);
        isVisited[initMover.row][initMover.col] = true;

        while (!q.isEmpty()) {
            Mover now = q.poll();
            if (now.row == goal[0] && now.col == goal[1]) return now.moves; // 목표에 도착 시 움직인 횟수 리턴 후 종료

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {   // 4방향 모두에 대해
                nextRow = now.row;
                nextCol = now.col;
                while (true) {  // 벽에 부딪히거나 장애물에 부딪힐 때까지 이동
                    nextRow += dirRow[d];
                    nextCol += dirCol[d];
                    if (isOuted(nextRow, nextCol) || String.valueOf(board[nextRow].charAt(nextCol)).equals("D")) break;
                }
                nextRow -= dirRow[d];   // 미끄러짐을 중단한 시점은 벽이거나 장애물에 도달한 경우이므로 한 칸 롤백
                nextCol -= dirCol[d];

                if (isVisited[nextRow][nextCol]) continue;  // 이미 방문한 곳이면 건너 뜀
                q.offer(new Mover(nextRow, nextCol, now.moves+1));
                isVisited[nextRow][nextCol] = true;
            }
        }

        return answer;
    }

    boolean isOuted(int row, int col) {
        if ((0 <= row && row < R) && (0 <= col && col < C)) return false;
        return true;
    }

}
