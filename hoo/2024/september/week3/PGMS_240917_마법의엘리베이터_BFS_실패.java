package september.week3;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class PGMS_240917_마법의엘리베이터_BFS_실패 {

    // -1, 1, -10, 10, -100, 100 ... (10의 c승, c >= 0)
    // 이걸 dp로 하려면 0층부터 올라간다고 쳐도 최악의 경우에 1억 * 약 8~9 * 2(버튼 개수)라서 초과 날 듯?
    class Minsu {
        int store;  // 민수가 있는 층
        int count;  // 그 층까지 움직인 횟수

        public Minsu(int store, int count) {
            this.store = store;
            this.count = count;
        }
    }

    public int solution(int storey) {
        int answer = calcMinCount(storey);

        return answer;
    }

    int calcMinCount(int storey) {
        int[] isVisited = new int[200_000_001]; // 층마다 최소 횟수 저장
        Arrays.fill(isVisited, Integer.MAX_VALUE);
        Queue<Minsu> q = new ArrayDeque<>();
        q.offer(new Minsu(storey, 0));
        isVisited[storey] = 0;

        Minsu now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now.store == 0) return now.count;   // 0층에 도착한 경우이므로 바로 리턴

            int c = 0;  // 버튼에 할당해 줄 지수
            int nextLow, nextHigh;
            while (true) {
                nextLow = now.store - (int) Math.pow(10, c);
                nextHigh = now.store + (int) Math.pow(10, c);
                if (nextLow >= 0 && isVisited[nextLow] > now.count + 1) {
                    q.offer(new Minsu(nextLow, now.count + 1));
                    isVisited[nextLow] = now.count + 1;
                }
                if (isVisited[nextHigh] > now.count + 1) {
                    q.offer(new Minsu(nextHigh, now.count + 1));
                    isVisited[nextHigh] = now.count + 1;
                }
                c++;
                if (Math.pow(10, c) > storey) break; // 현재 층수보다 많은 층을 움직이는 버튼부터는 누르지 않음
            }
        }

        return 0;
    }

}
