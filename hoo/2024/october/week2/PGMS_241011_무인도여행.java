package twentytwentyfour.october.week2;

import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class PGMS_241011_무인도여행 {

    public int[] solution(String[] maps) {
        int[] answer = calcAnswer(maps);
        return answer;
    }

    int[] calcAnswer(String[] maps) {
        boolean[][] isVisited = new boolean[maps.length][maps[0].length()];
        PriorityQueue<Integer> answerPq = new PriorityQueue<>();
        for (int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[0].length(); j++) {
                if (maps[i].charAt(j) == 'X' || isVisited[i][j]) continue;  // 바다이거나 이미 확인한 곳은 패스
                answerPq.offer(bfs(maps, i, j, isVisited));
            }
        }

        if (answerPq.isEmpty()) return new int[] {-1};
        int[] answer = new int[answerPq.size()];
        int answerIndex = 0;
        while (!answerPq.isEmpty()) answer[answerIndex++] = answerPq.poll();

        return answer;
    }

    int bfs(String[] maps, int startRow, int startCol, boolean[][] isVisited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {startRow, startCol});
        isVisited[startRow][startCol] = true;

        int[] dirRow = new int[] {-1, 1, 0, 0}; // 상 하 좌 우
        int[] dirCol = new int[] {0, 0, -1, 1};
        int[] now;
        int totalCount = Integer.parseInt(String.valueOf(maps[startRow].charAt(startCol))); // 섬의 제일 처음 확인한 부분 식량 값 저장
        while (!q.isEmpty()) {
            now = q.poll();

            int nextRow, nextCol;
            for (int d = 0; d < 4; d++) {
                nextRow = now[0] + dirRow[d];
                nextCol = now[1] + dirCol[d];
                if (isOuted(maps, nextRow, nextCol) || maps[nextRow].charAt(nextCol) == 'X' || isVisited[nextRow][nextCol]) continue; // 범위 밖이거나, 바다이거나, 이미 방문한 곳은 패스
                q.offer(new int[] {nextRow, nextCol});
                isVisited[nextRow][nextCol] = true;
                totalCount += Integer.parseInt(String.valueOf(maps[nextRow].charAt(nextCol)));
            }
        }

        return totalCount;
    }

    boolean isOuted(String[] maps, int row, int col) {
        if ((0 <= row && row < maps.length) && (0 <= col && col < maps[0].length())) return false;
        return true;
    }

}
