package october.week2;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_241009_행렬테두리회전하기 {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = calcAnswer(rows, columns, queries);

        return answer;
    }

    int[] calcAnswer(int rows, int columns, int[][] queries) {
        int[][] matrix = new int[rows][columns];
        int number = 1;
        for (int i = 0; i < rows; i++) {    // 초기의 행렬 생성
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = number++;
            }
        }

        int[] answer = new int[queries.length];
        int answerIndex = 0;
        int[] query;
        for (int i = 0; i < queries.length; i++) {
            query = queries[i];
            answer[answerIndex++] = rotateMatrixAndFindMinNumber(matrix, query);
        }

        return answer;
    }

    int rotateMatrixAndFindMinNumber(int[][] matrix, int[] query) {
        Queue<Integer> q = new ArrayDeque<>();
        int startRow = query[0] - 1;
        int startCol = query[1] - 1;
        int endRow = query[2] - 1;
        int endCol = query[3] - 1;

        for (int i = startRow; i < endRow; i++) q.offer(matrix[i][startCol]); // 왼쪽변 숫자들 삽입
        for (int i = startCol; i < endCol; i++) q.offer(matrix[endRow][i]);   // 아랫변 숫자들 삽입
        for (int i = endRow; i > startRow; i--) q.offer(matrix[i][endCol]); // 오른쪽변 숫자들 삽입
        for (int i = endCol; i > startCol; i--) q.offer(matrix[startRow][i]);   // 윗변 숫자들 삽입
        q.offer(q.poll());  // 맨 뒤 숫자 맨 앞으로

        int minNumber = Integer.MAX_VALUE;
        for (int i = startRow; i < endRow; i++) {
            matrix[i][startCol] = q.poll(); // 왼쪽변에 숫자들 할당
            minNumber = Math.min(minNumber, matrix[i][startCol]);
        }
        for (int i = startCol; i < endCol; i++) {
            matrix[endRow][i] = q.poll();   // 아랫변에 숫자들 할당
            minNumber = Math.min(minNumber, matrix[endRow][i]);
        }
        for (int i = endRow; i > startRow; i--) {
            matrix[i][endCol] = q.poll(); // 오른쪽변에 숫자들 할당
            minNumber = Math.min(minNumber, matrix[i][endCol]);
        }
        for (int i = endCol; i > startCol; i--) {
            matrix[startRow][i] = q.poll();   // 윗변에 숫자들 할당
            minNumber = Math.min(minNumber, matrix[startRow][i]);
        }

        return minNumber;
    }

}
