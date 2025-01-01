package twentytwentyfour.october.week3;

import java.util.Arrays;

public class PGMS_241014_입국심사 {

    public long solution(int n, int[] times) {
        Arrays.sort(times); // 이분탐색을 위해 시간을 오름차순으로 정렬
        long answer = parametricSearch(n, times);

        return answer;
    }

    long parametricSearch(int n, int[] times) {
        long low = 0;
        long high = (long) n * times[times.length-1];
        long mid, personCount;    // mid 값 안에 모든 사람들이 입국 심사가 가능한 지 여부 파악
        long answer = 0;
        while (low < high) {
            mid = (low + high) / 2L;
            personCount = calcPersonCount(times, mid);
            if (personCount < n) low = mid+1;
            else {
                high = mid;
                answer = mid;   // 더 낮은 값이 나올 수도 있으므로 사람 수 충족하는 동안은 계속 정답 갱신해주어야 함
            }
        }

        return answer;
    }

    long calcPersonCount(int[] times, long time) {   // 주어진 시간 내에 몇 명의 사람을 심사할 수 있는 지 계산하는 함수
        long personCount = 0;
        for (int i = 0; i < times.length; i++) {    // 주어진 시간에 대해 각 입국심사위원이 걸리는 시간을 나누어 준 값이 각자가 맡을 수 있는 사람 수임.
            personCount += time / times[i];
        }

        return personCount;
    }

}
