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
            sum = 0;
            for (int j = 0; j < i; j++) sum += elements[j];   // 우선 맨 첫수부터 연속한 i개를 모두 선택해서 만들 수 있는 합을 만들어 줌
            sumSet.add(sum);

            for (int j = 0; j < elements.length; j++) { // 이후 슬라이딩 윈도우 형식으로 옮겨가며 바뀐 연속 숫자들의 합을 계산
                sum -= elements[j];
                sum += elements[(j+i) % elements.length];
                sumSet.add(sum);
            }
        }

        return sumSet.size();
    }

}
