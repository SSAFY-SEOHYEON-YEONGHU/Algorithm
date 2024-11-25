import java.util.*;

class Solution {

    public int solution(int[] ingredient) {
        int answer = countHamburger(ingredient);

        return answer;
    }

    int countHamburger(int[] ingredient) {
        // 1: 빵, 2: 야채, 3: 고기
        if (ingredient.length < 4) return 0;    // 재료가 4개 미만이면 햄버거 만들 수 없음

        int hamburgerCount = 0;
        Stack<Integer> stack = new Stack<>();   // 주어진 재료를 순서대로 쌓기 위해 사용할 스택
        int i = 0;
        while (i < ingredient.length) {
            if (stack.size() >= 3 && stack.get(stack.size()-3) == 1 && stack.get(stack.size()-2) == 2 && stack.get(stack.size()-1) == 3 && ingredient[i] == 1) {  // 스택에 재료가 세 개 이상 쌓여있고, 연속된 네 개의 재료가 1, 2, 3, 1 이라면
                for (int j = 0; j < 3; j++) stack.pop();
                hamburgerCount++;
                i++;
            } else stack.push(ingredient[i++]);
        }

        return hamburgerCount;
    }

}