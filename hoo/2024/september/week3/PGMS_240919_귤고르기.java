package september.week3;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class PGMS_240919_귤고르기 {

    class TangerineObject implements Comparable<TangerineObject> {
        int number;
        int count;

        public TangerineObject(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(TangerineObject t) {   // 개수 순 내림차순 정렬
            return t.count - this.count;
        }
    }

    public int solution(int k, int[] tangerine) {
        int answer = findMinDiff(k, tangerine);

        return answer;
    }

    int findMinDiff(int k, int[] tangerine) {
        int maxTangerineNumber = 0; // 마지막 귤의 번호
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < tangerine.length; i++) {
            if (countMap.containsKey(tangerine[i])) countMap.put(tangerine[i], countMap.get(tangerine[i]) + 1);
            else countMap.put(tangerine[i], 1);
            maxTangerineNumber = Math.max(maxTangerineNumber, tangerine[i]);
        }

        PriorityQueue<TangerineObject> pq = new PriorityQueue<>();
        for (int i = 1; i <= maxTangerineNumber; i++) {
            if (countMap.containsKey(i)) pq.offer(new TangerineObject(i, countMap.get(i)));
        }

        int answer = 0;
        TangerineObject now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            answer++;
            k -= now.count;
            if (k <= 0) break;  // 귤 할당량 다 채웠다면 바로 종료
        }

        return answer;
    }

}
