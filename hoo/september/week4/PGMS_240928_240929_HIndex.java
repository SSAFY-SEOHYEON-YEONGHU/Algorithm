package september.week4;

import java.util.Arrays;

public class PGMS_240928_240929_HIndex {

    public int solution(int[] citations) {
        int answer = calcHIndex(citations);

        return answer;
    }

    int calcHIndex(int[] citations) {
        Arrays.sort(citations);

        int answer = 0;
        int overHLength;
        for (int i = 0; i < citations.length; i++) {
            overHLength = citations.length - i; // 현재 체크할 인덱스 이상인 수들의 개수
            if (citations[i] > overHLength) {   // overHLength가 더 작은 수인 경우
                answer = Math.max(answer, overHLength);
            } else answer = Math.max(answer, citations[i]); // citation이 더 작은 수인 경우
        }


        return answer;
    }

}
