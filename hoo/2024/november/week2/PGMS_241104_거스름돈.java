import java.util.*;

class Solution {

    public int solution(int n, int[] money) {
        int answer = calcAnswer(n, money);

        return answer;
    }

    int calcAnswer(int n, int[] money) {
        int[] dp = new int[n+1];
        dp[0] = 1;

        for (int i = 0; i < money.length; i++) {    // 1원 이후의 단위로 만들 수 있는 경우의 수를 카운트하기
            for (int j = money[i]; j <= n; j++) {
                dp[j] += dp[j-money[i]];
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[n];
    }

}