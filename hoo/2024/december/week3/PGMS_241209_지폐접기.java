import java.util.*;

class Solution {

    public int solution(int[] wallet, int[] bill) {
        int answer = calcFoldCount(wallet, bill);

        return answer;
    }

    int calcFoldCount(int[] wallet, int[] bill) {
        int foldCount = 0;

        while (true) {
            if (Math.min(wallet[0], wallet[1]) >= Math.min(bill[0], bill[1]) && Math.max(wallet[0], wallet[1]) >= Math.max(bill[0], bill[1])) break;    // 조건 만족 시 반복문 종료

            if (bill[0] > bill[1]) {
                bill[0] = bill[0] / 2;
            } else bill[1] = bill[1] / 2;
            foldCount++;
        }

        return foldCount;
    }

}