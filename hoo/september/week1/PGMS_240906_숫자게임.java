package september.week1;

import java.util.Collections;
import java.util.PriorityQueue;

public class PGMS_240906_숫자게임 {

    public int solution(int[] A, int[] B) {
        int answer = calcScore(A, B);

        return answer;
    }

    int calcScore(int[] A, int[] B) {
        int answer = 0;

        PriorityQueue<Integer> aPQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> bPQ = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < A.length; i++) {
            aPQ.offer(A[i]);
            bPQ.offer(B[i]);
        }

        for (int i = 0; i < A.length; i++) {    // 모든 A의 숫자에 대해
            if (aPQ.peek() < bPQ.peek()) {  // 현재 A숫자보다 B의 숫자가 더 높으면 B승리 처리, 두 숫자 모두 뽑아줌
                aPQ.poll();
                bPQ.poll();
                answer++;
            } else aPQ.poll();  // 비기거나 A숫자가 B숫자보다 높은 경우 B의 숫자는 다음 A의 높은 숫자와 비교를 위해 놔두고 A 숫자만 뽑음
        }


        return answer;
    }

}
