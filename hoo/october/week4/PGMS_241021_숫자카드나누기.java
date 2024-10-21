package october.week4;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PGMS_241021_숫자카드나누기 {

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = findAnswer(arrayA, arrayB);
        return answer;
    }

    int findAnswer(int[] arrayA, int[] arrayB) {
        int gcdA = findGCD(arrayA);  // arrayA와 arrayB의 GCD를 각각 계산
        int gcdB = findGCD(arrayB);

        int answer = 0;  // arrayA의 GCD로 arrayB의 숫자를 나눌 수 있는지 확인
        if (isValidDivisor(gcdA, arrayB)) {
            answer = Math.max(answer, gcdA);
        }

        if (isValidDivisor(gcdB, arrayA)) {  // arrayB의 GCD로 arrayA의 숫자를 나눌 수 있는지 확인
            answer = Math.max(answer, gcdB);
        }

        return answer;
    }

    int findGCD(int[] array) {  // 배열의 최대 공약수(GCD) 구하는 함수
        int gcd = array[0];
        for (int i = 1; i < array.length; i++) {
            gcd = gcd(gcd, array[i]);
            if (gcd == 1) break;  // GCD가 1이면 더 이상 계산할 필요 없음
        }
        return gcd;
    }

    int gcd(int a, int b) {  // 두 숫자의 최대 공약수(GCD) 계산
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    boolean isValidDivisor(int gcd, int[] array) {  // 특정 GCD로 다른 배열의 숫자를 나눌 수 없는지 확인
        for (int num : array) {
            if (num % gcd == 0) {
                return false;  // 하나라도 나누어 떨어지면 false
            }
        }
        return true;
    }


}
