package august.week4;

import java.util.Arrays;

public class PGMS_240823_숫자변환하기_DP_실패 {

    public int solution(int x, int y, int n) {
        int answer = calcCase(x, y, n);

        return answer;
    }

    int calcCase(int x, int y, int n) {
        int[] dpArr = new int[y*3+1];   // 계산 결과로 나올 수 있는 최댓값은 곱하기 3까지 있으므로 y의 3배로 지정
        Arrays.fill(dpArr, 10_000_000);  // 횟수는 우선 최댓값으로 초기화
        dpArr[x] = 0;   // x를 만드는 데 필요한 연산은 없으므로 0으로 갱신

        for (int i = x+1; i < y+1; i++) { // y 바로 밑 수까지 반복
            if (i < n) {
                if (i % 2 == 0 && i % 3 == 0) dpArr[i] = Math.min(dpArr[i/2], dpArr[i/3]) + 1;
                else if (i % 2 == 0 && i % 3 != 0) dpArr[i] = dpArr[i/2] + 1;
                else if (i % 2 != 0 && i % 3 == 0) dpArr[i] = dpArr[i/3] + 1;
            }
            else {
                if (i % 2 == 0 && i % 3 == 0) dpArr[i] = Math.min(dpArr[i-n], Math.min(dpArr[i/2], dpArr[i/3])) + 1;
                else if (i % 2 == 0 && i % 3 != 0) dpArr[i] = Math.min(dpArr[i-n], dpArr[i/2]) + 1;
                else if (i % 2 != 0 && i % 3 == 0) dpArr[i] = Math.min(dpArr[i-n], dpArr[i/3]) + 1;
            }
        }

        return (dpArr[y] == 10_000_000)? -1:dpArr[y];
    }

}
