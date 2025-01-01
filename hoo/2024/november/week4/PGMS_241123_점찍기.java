import java.util.*;

class Solution {

    public long solution(int k, int d) {
        long answer = countPoints((long) k, (long) d);

        return answer;
    }

    public long countPoints(long k, long d) {
        // (ak)^2 + (bk)^2 <= d^2 인 원방정식
        // => (ak) = root(d^2 - (bk)^2)
        // bk = i * k
        long count = 0;
        long temp;
        for (long bk = 0; bk <= d; bk += k) {  // bk만 움직여주면 ak 범위 파악됨. 그 범위를 count에 더해줌
            temp = (long) Math.sqrt(d*d - bk*bk);
            count += (temp/k + 1);  // a가 0인 경우도 고려하기 위해서 1 더해줌
        }

        return count;
    }

}