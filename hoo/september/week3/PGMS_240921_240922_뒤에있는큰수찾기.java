package september.week3;

import java.util.Arrays;
import java.util.Stack;

public class PGMS_240921_240922_뒤에있는큰수찾기 {

    public int[] solution(int[] numbers) {
        int[] answer = findBehindBigNumber(numbers);

        return answer;
    }

    int[] findBehindBigNumber(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);    // 뒷 큰수가 없을 경우를 대비하여 원소들 -1로 초기화

        Stack<Integer> stack = new Stack<>();   // 숫자들의 "인덱스"를 담을 스택
        for (int i = 0; i < numbers.length; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {    // 스택에 인덱스가 들어 있고, 스택 가장 위의 인덱스에 해당하는 숫자가 현재(i) 숫자보다 작을 동안 반복(다른 말로 하면, 이전의 숫자들을 하나씩 뒤돌아보며 현재(i) 숫자가 뒷 큰 수일 동안만 반복)
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);  // 스택에 인덱스 삽입
        }

        return answer;
    }

}
