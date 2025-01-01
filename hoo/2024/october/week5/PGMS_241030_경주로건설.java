import java.util.*;

class Solution {

    class Road {
        int row;
        int col;
        int dir;
        int length;  // 도로 길이
        int turn;   // 도로 건설에 쓰인 코너 개수

        public Road(int row, int col, int dir, int length, int turn) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.length = length;
            this.turn = turn;
        }
    }

    int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
    int[] dirCol = new int[] {0, 1, 0, -1};
    int minCost;

    public int solution(int[][] board) {
        minCost = Integer.MAX_VALUE;
        for (int d = 1; d <= 2; d++) {  // 시작점에서 1. 우 / 2. 하로 이동한 것을 가정하고 시작
            if (board[dirRow[d]][dirCol[d]] == 1) continue; // 그 와중에 바로 옆칸이 벽이면 해당 경로는 탐색 안함
            int[][] isVisited = new int[board.length][board[0].length]; // 이미 더 적은 비용으로 연결한 도로가 있는 지 여부 판별하기 위해 boolean[][]이 아닌 int[][]로 선언
            for (int i = 0; i < isVisited.length; i++) Arrays.fill(isVisited[i], Integer.MAX_VALUE);
            isVisited[0][0] = 0;
            isVisited[dirRow[d]][dirCol[d]] = 100;
            makeRoad(board, new Road(dirRow[d], dirCol[d], d, 1, 0), isVisited);
        }

        return minCost;
    }

    void makeRoad(int[][] board, Road now, int[][] isVisited) {
        if (now.row == board.length-1 && now.col == board[0].length-1) {  // 기저, 도로를 도착점까지 만든 경우
            minCost = Math.min(minCost, now.length*100 + now.turn*500);
            return;
        }

        int nextRow, nextCol, nextCost;
        Road next;
        for (int d = 0; d < 4; d++) {
            nextRow = now.row + dirRow[d];
            nextCol = now.col + dirCol[d];
            if (isOuted(board, nextRow, nextCol) || board[nextRow][nextCol] == 1) continue; // 범위 밖이거나 벽으로 막힌 곳, 이미 도로를 놓은 곳이면 건너 뜀
            next = new Road(nextRow, nextCol, d, now.length+1, (now.dir==d)?now.turn:now.turn+1);
            nextCost = calcCost(next);
            if (isVisited[nextRow][nextCol] < nextCost) continue; // 이미 더 적은 비용으로 연결한 도로가 있으면 건너 뜀

            isVisited[nextRow][nextCol] = nextCost;
            makeRoad(board, next, isVisited);
        }
    }

    boolean isOuted(int[][] board, int row, int col) {
        if ((0 <= row && row < board.length) && (0 <= col && col < board[0].length)) return false;
        return true;
    }

    int calcCost(Road road) {
        return road.length*100 + road.turn*500;
    }

}