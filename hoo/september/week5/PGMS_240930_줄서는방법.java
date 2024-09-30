package september.week5;

import java.util.ArrayList;
import java.util.List;

public class PGMS_240930_줄서는방법 {

    long[] factorial;

    public int[] solution(int n, long k) {
        int[] answer = calcAnswer(n, k);


        return answer;
    }

    int[] calcAnswer(int n, long k) {
        factorial(n);   // n까지의 팩토리얼을 계산하고 저장해둠
        int[] answer = new int[n];

        List<Integer> remainNumbers = new ArrayList<>();    // 남은 숫자들을 저장하고 있는 리스트
        for (int i = 1; i <= n; i++) remainNumbers.add(i);

        int numberIndex;    // 고른 숫자의 인덱스, k값 감소시킬 때 사용
        int tempN = n;
        for (int i = 0; i < n; i++) {   // 매 인덱스마다
            numberIndex = (int) calcNumber(tempN, k);   // 남은 숫자의 개수와 계산해둔 팩토리얼을 이용해서 현재 올 숫자의 인덱스 찾기
            answer[i] = remainNumbers.get(numberIndex);
            remainNumbers.remove(numberIndex);  // 쓴 숫자는 제거 처리(Object를 파라미터로 주지 않아서 index에 있는 수가 삭제됨)

            k -= (numberIndex * factorial[tempN-1]); // 고른 수의 인덱스를 이용하여 몇 번째 숫자까지 체크했는 지 갱신
            tempN--;    // 남은 숫자 개수 -1
        }

        return answer;
    }

    long calcNumber(int n , long k) { // 지금 고를 숫자 다음 n-1개 수들 나열하는 경우의 수 반환하는 함수
        return k % factorial[n-1]==0? k/factorial[n-1]-1:k / factorial[n-1];   // k를 n-1개의 수 팩토리얼로 나눈 몫, 그런데 여기서 나머지가 0인 경우는 몫에서 -1을 해주어야 함. 안그럼 숫자가 넘어가버림.
    }

    void factorial(int n) {
        factorial = new long[n+1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = i * factorial[i-1];
        }
    }

}
