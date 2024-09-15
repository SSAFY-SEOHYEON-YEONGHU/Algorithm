package september.week2;

import java.util.Stack;

public class PGMS_240914_240915_큰수만들기 {

    public String solution(String number, int k) {
        String answer = makeBiggestNumber(number, k);

        return answer;
    }

    String makeBiggestNumber(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < number.length(); i++) {
            while (true) {  // 제거해야하는 개수가 남았다면 스택의 맨 윗수와 현재 number의 수를 비교해서, 더 작은 수들은 다 뽑아주기
                if (k <= 0) break;  // 제거할 개수만큼 다 제거했으면 중단
                if (!stack.isEmpty() && stack.peek() < number.charAt(i)) {
                    stack.pop();
                    k--;
                } else break;   // 스택이 비었거나 스택 맨 윗수가 현재 number의 수보다 크거나 같으면 중단
            }
            stack.push(number.charAt(i));   // 현재 number의 수 stack에 삽입
        }
        while (k > 0) { // 같은 숫자들만 입력으로 주어진 경우를 고려해서, number의 모든 숫자에 대해 수행하고도 제거해야할 개수가 남았다면 뒤의 수를 빼준다(예를 들어 11인데 k가 1인 경우, 위의 조회를 통해서는 1개를 제거 못함. 그래서 여기서 제거해줘야 함)
            stack.pop();
            k--;
        }

        while (!stack.isEmpty()) {
            answer = String.valueOf(stack.pop()) + answer;
        }

        return answer;
    }

}
