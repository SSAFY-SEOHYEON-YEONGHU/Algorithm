package september.week1;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class PGMS_240906_숫자게임_실패코드 {

    public int solution(int[] A, int[] B) {
        int answer = calcScore(A, B);

        return answer;
    }

    int calcScore(int[] A, int[] B) {
        int answer = 0;

        Stack<Integer> tempStack = new Stack<>();  // 최대 승점을 얻기 위해서는 A의 숫자보다 높은 B 숫자 중 가장 낮은 숫자를 사용해야 함.
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());  // B의 숫자를 크기 순으로 정렬한 PQ에서 뽑되, 위에서 말한 가장 낮은 수가 나오기 전까지 뽑고 나머지는 임시로 스택에 저장해뒀다가 다시 PQ에 삽입
        for (int i = 0; i < B.length; i++) pq.offer(B[i]);

        int aIndex = 0;
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek() > A[aIndex]) tempStack.push(pq.poll());
            if (tempStack.isEmpty()) {  // A보다 높은 수가 없었던 경우
                pq.poll();
            } else {    // A보다 높은 수 중 가장 낮은 수는 스택의 맨 위에 있음
                answer++;
                tempStack.pop();
                while (!tempStack.isEmpty()) pq.offer(tempStack.pop());
            }
            aIndex++;
        }

        return answer;
    }

}
