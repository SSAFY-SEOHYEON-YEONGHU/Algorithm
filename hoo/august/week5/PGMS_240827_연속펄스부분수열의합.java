package august.week5;

import java.util.Arrays;

public class PGMS_240827_연속펄스부분수열의합 {

    int[] multipliedInOrder;    // 1, -1, 1, ... 을 곱한 값
    int[] multipliedReverseOrder;   // -1, 1, -1, ... 을 곱한 값
    long maxValue;   // 최댓값 저장할 변수

    public long solution(int[] sequence) {
        init(sequence);
        long answer = findMaxValue();

        return answer;
    }

    void init(int[] sequence) {
        multipliedInOrder = new int[sequence.length];
        multipliedReverseOrder = new int[sequence.length];
        int[] pulse = new int[] {1, -1};    // 펄스 수열을 곱해주기 위한 배열
        for (int i = 0; i < sequence.length; i++) { // sequnce에 1부터 시작하는 펄스 수열, -1부터 시작하는 펄스 수열을 각각 곱해줌
            multipliedInOrder[i] = sequence[i] * pulse[i%2];
            multipliedReverseOrder[i] = sequence[i] * pulse[(i+1)%2];
        }
        maxValue = Long.MIN_VALUE;
    }

    long findMaxValue() {   // 펄스 수열을 곱한 배열에 대해 원소들의 합이 최댓값인 부분 수열 찾는 함수
        int l = multipliedInOrder.length;
        long[][] dpArr = new long[2][l];  // 0: 1부터 시작한 펄스 수열 곱한 배열의 연속 부분 수열 합 최댓값 저장, 1: -1부터 시작한 펄스 수열에 대해서 동일한 동작 수행
        dpArr[0][0] = multipliedInOrder[0];
        dpArr[1][0] = multipliedReverseOrder[0];
        for (int i = 1; i < l; i++) {
            dpArr[0][i] = Math.max(0, dpArr[0][i-1]) + multipliedInOrder[i];
            dpArr[1][i] = Math.max(0, dpArr[1][i-1]) + multipliedReverseOrder[i];
        }
        Arrays.sort(dpArr[0]);
        Arrays.sort(dpArr[1]);

        return Math.max(dpArr[0][l-1], dpArr[1][l-1]);
    }

}
