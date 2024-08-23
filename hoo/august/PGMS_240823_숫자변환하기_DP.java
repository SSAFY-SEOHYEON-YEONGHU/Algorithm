package august;

import java.util.Arrays;

public class PGMS_240823_숫자변환하기_DP {

    public int solution(int x, int y, int n) {
        int answer = calcCase(x, y, n);

        return answer;
    }

    int calcCase(int x, int y, int n) {
        int[] dpArr = new int[y*3+1];   // 계산 결과로 나올 수 있는 최댓값은 곱하기 3까지 있으므로 y의 3배로 지정
        int INF = 10_000_000;
        Arrays.fill(dpArr, INF);  // 횟수는 우선 최댓값으로 초기화
        dpArr[x] = 0;   // x를 만드는 데 필요한 연산은 없으므로 0으로 갱신

        int minCase;
        for (int i = x+1; i < y+1; i++) { // y까지 반복
            int dividedByTwo = INF;    // 현재 수를 만들기 전의 숫자들
            int dividedByThree = INF;
            int minusedByN = INF;
            if (i / 2 >= 1 && i % 2 == 0) dividedByTwo = dpArr[i/2];    // 각 숫자는 연산을 통해 구할 수 있는 지 판단 후, 구할 수 있는 숫자면 그때의 횟수를 저장해줌
            if (i / 3 >= 1 && i % 3 == 0) dividedByThree = dpArr[i/3];
            if (i >= n) minusedByN = dpArr[i-n];

            minCase = Math.min(minusedByN, Math.min(dividedByTwo, dividedByThree)) + 1;
            dpArr[i] = minCase;
        }

        return (dpArr[y] >= INF)? -1:dpArr[y];
    }

}
