import java.util.*;

class Solution {

    public int solution(int a, int b, int n) {
        int answer = countReturnCokes(a, b, n);

        return answer;
    }

    int countReturnCokes(int a, int b, int n) {
        int returnCokes = 0;
        while (true) {
            returnCokes += (n/a)*b; // 현재 가지고 있는 빈 병으로 얻을 수 있는 새 콜라
            int temp = (n/a)*b;
            n %= a; // 가진 빈 병으로 콜라 교환 후 남은 빈 병
            n += temp;  // 얻은 콜라 더해줌
            if (n < a) break;   // 가진 빈 병 개수가 a보다 작다면 중단
        }

        return returnCokes;
    }

}