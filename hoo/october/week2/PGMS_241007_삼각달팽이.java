package october.week2;

public class PGMS_241007_삼각달팽이 {

    public int[] solution(int n) {
        int[] answer = doSnail(n);

        return answer;
    }

    int[] doSnail(int n) {
        int[][] snail = new int[n][n];

        int upperFloor = 0; // 채워야할 층의 윗 층
        int underFloor = n-1; // 채워야할 층의 아랫 층
        int offset = 0; // 각 층에서 채워야 할 위치를 나타낼 오프셋
        int number = 1; // 채울 숫자
        while (upperFloor <= underFloor) {
            for (int i = upperFloor; i <= underFloor; i++) { // 삼각달팽이의 왼쪽에서 내려가면서 숫자를 채움
                snail[i][offset] = number++;
            }
            upperFloor++;

            for (int i = offset+1; i <= underFloor-offset; i++) { // 삼각달팽이의 아랫층 숫자만큼 채움
                snail[underFloor][i] = number++;
            }
            underFloor--;

            for (int i = underFloor; i >= upperFloor; i--) {    // 삼각달팽이의 오른쪽에서 올라가면서 숫자를 채움
                snail[i][i-offset] = number++;
            }
            upperFloor++;

            offset++;
        }
        int[] answer = new int[number-1];
        int answerIndex = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i+1; j++) {
                answer[answerIndex++] = snail[i][j];
            }
        }

        return answer;
    }

}
