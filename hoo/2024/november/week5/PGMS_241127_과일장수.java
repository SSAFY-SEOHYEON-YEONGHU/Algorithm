import java.util.*;

class Solution {

    public int solution(int k, int m, int[] score) {
        int answer = calcProfit(k, m, score);

        return answer;
    }

    int calcProfit(int k, int m, int[] score) {
        Arrays.sort(score); // 점수 순 정렬

        int profit = 0;
        for (int i = score.length%m; i < score.length; i += m) {    // m개로 나누었을 때 나머지 개수 만큼의 사과들은 쓰지 않는 것이 최대 점수를 얻는 방법, 오름차순 정렬돼있기 때문에 m씩 건너뛰면 한 상자에서의 사과 최저 점수를 할당 가능
            profit += score[i] * m;
        }

        return profit;
    }

}