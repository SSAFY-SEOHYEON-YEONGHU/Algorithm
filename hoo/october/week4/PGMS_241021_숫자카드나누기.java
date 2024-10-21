package october.week4;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PGMS_241021_숫자카드나누기 {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = findAnswer(arrayA, arrayB);

        return answer;
    }

    int findAnswer(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);    // 철수, 영희가 가진 카드를 우선 오름차순으로 정렬
        Arrays.sort(arrayB);

        int answer = 0;
        answer = Math.max(answer, calcMaxNumber(arrayA, arrayB));
        answer = Math.max(answer, calcMaxNumber(arrayB, arrayA));

        return answer;
    }

    int calcMaxNumber(int[] firstArray, int[] secondArray) {    // firstArray: 모든 숫자를 나눌 수 있는 지 판단할 배열, secondArray: 모든 숫자를 나눌 수 없는 지 판단할 배열
        PriorityQueue<Integer> divisorPq = makeDivisorPq(firstArray);  // firstArray의 첫 번째 숫자의 약수를 저장할 pq

        int answer = 0;
        boolean isPossible;
        int divisor;
        while (!divisorPq.isEmpty()) {  // 구한 약수들에 대해 수행
            isPossible = true;
            divisor = divisorPq.poll();
            for (int i = 1; i < firstArray.length; i++) {   // 모든 숫자를 나눌 수 있는 지 판단
                if (firstArray[i] % divisor != 0) {
                    isPossible = false;
                    break;
                }
            }
            if (!isPossible) continue;  // 조건에 맞는 숫자가 아니라면 다음 약수로

            for (int i = 0; i < secondArray.length; i++) {  // 모든 숫자가 나누어 떨어지지 않는 지 판단
                if (secondArray[i] % divisor == 0) {
                    isPossible = false;
                    break;
                }
            }
            if (!isPossible) continue;  // 조건에 맞는 숫자가 아니라면 다음 약수로
            answer = divisor;   // 조건에 맞는 숫자라면 그 숫자로 갱신
        }

        return answer;
    }

    PriorityQueue<Integer> makeDivisorPq(int[] array) {
        PriorityQueue<Integer> divisorPq = new PriorityQueue<>();  // firstArray의 첫 번째 숫자의 약수를 저장할 pq
        for (int i = 1; i <= Math.sqrt(array[0]); i++) {  // 약수 구하는 반복문
            if (array[0] % i == 0) {
                if (i == Math.sqrt(array[0])) divisorPq.offer(i);
                else {
                    divisorPq.offer(i);
                    divisorPq.offer(array[0] / i);
                }
            }
        }

        return divisorPq;
    }

}
