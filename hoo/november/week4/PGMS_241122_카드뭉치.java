import java.util.*;

class Solution {

    public String solution(String[] cards1, String[] cards2, String[] goal) {
        return makeGoal(cards1, cards2, goal)? "Yes":"No";
    }

    boolean makeGoal(String[] cards1, String[] cards2, String[] goal) {
        int start1 = 0; // cards1과 2에서 찾기를 시작할 인덱스
        int start2 = 0;
        String now;
        for (int i = 0; i < goal.length; i++) {
            now = goal[i];
            if (start1 < cards1.length && cards1[start1].equals(now)) start1++;   // 카드뭉치1의 제일 앞에서 현재 단어를 찾을 수 있다면 카드뭉치1 시작 인덱스 증가
            else if (start2 < cards2.length && cards2[start2].equals(now)) start2++;  // 상동
            else return false;  // 둘 다 제일 앞에서 못찾으면 그건 만들 수 없는 단어이므로 거짓 반환
        }

        return true;
    }

}