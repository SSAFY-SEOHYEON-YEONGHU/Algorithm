package september.week4;

import java.util.HashSet;
import java.util.Set;

public class PGMS_240924_연속부분수열합의개수 {

    public int solution(int[] elements) {
        int answer = calcAnswer(elements);


        return answer;
    }

    int calcAnswer(int[] elements) {
        Set<Integer> sumSet = new HashSet<>();
        int sum;
        for (int i = 1; i <= elements.length; i++) { // elements의 길이만큼의 원소를 선택
            for (int j = 0; j < elements.length; j++) { // elements의 처음부터 끝 원소까지
                sum = 0;
                for (int k = 0; k < i; k++) {  // 연속으로 i개를 선택해서 더해줌
                    sum += elements[(j+k) % elements.length];
                }
                sumSet.add(sum);
            }
        }

        return sumSet.size();
    }

}
