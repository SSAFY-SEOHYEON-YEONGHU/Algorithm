package september.week3;

public class PGMS_240916_NQueen {

    int answer;

    public int solution(int n) {
        answer = 0;
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++) {
            board[0][i] = 1;
            nQueen(n, board, 1);
            board[0][i] = 0;
        }

        return answer;
    }

    void nQueen(int n, int[][] board, int queenCount) {
        if (queenCount == n) answer++;  // 기저, n개의 퀸을 모두 놨을 경우

        for (int j = 0; j < n; j++) {
            if (!isPossible(n, board, queenCount, j)) continue;  // 둘 수 없는 칸이면 건너 뜀
            board[queenCount][j] = 1;
            nQueen(n, board, queenCount+1);
            board[queenCount][j] = 0;
        }
    }

    boolean isPossible(int n, int[][] board, int row, int col) {   // 퀸을 놓았을 때 조건을 만족하는 지 판별하는 함수
        int[] dirRow = new int[] {-1, -1, -1, 1, 1, 1}; // 좌상 상 우상 우하 하 좌하
        int[] dirCol = new int[] {-1, 0, 1, 1, 0, -1};
        int nextRow, nextCol;
        for (int d = 0; d < 6; d++) {   // 대각선, 상하로 퀸 있는 지여부 탐색
            nextRow = row;
            nextCol = col;
            while (true) {
                nextRow += dirRow[d];
                nextCol += dirCol[d];
                if (isOuted(n, nextRow, nextCol)) break;    // 범위 밖이면 반복 멈춤
                if (board[nextRow][nextCol] == 1) return false; // 탐색하는 칸에 퀸 있으면 놓는 게 불가능함
            }
        }

        return true;
    }

    boolean isOuted(int n, int row, int col) {
        if ((0 <= row && row < n) && (0 <= col && col < n)) return false;
        return true;
    }

}
