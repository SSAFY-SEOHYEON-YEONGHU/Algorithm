package september.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PGMS_240923_배달 {

    class Road implements Comparable<Road> {
        int from;
        int to;
        int hour;

        public Road(int from, int to, int hour) {
            this.from = from;
            this.to = to;
            this.hour = hour;
        }

        @Override
        public int compareTo(Road r) {  // 걸리는 시간을 기준으로 오름차순 정렬
            return this.hour - r.hour;
        }

        @Override
        public String toString() { return this.from + " " + this.to + " " + this.hour; }
    }

    public int solution(int N, int[][] road, int K) {
        int answer = calcPossibleDeliveries(N, road, K);

        return answer;
    }

    int calcPossibleDeliveries(int N, int[][] road, int K) {
        List<List<Road>> roadList = makeRoadList(N, road);
        int[] minHourArr = new int[N+1]; // 각 도시까지 가는 데 걸리는 최단거리를 저장할 배열
        int INF = 2000 * 10_000 + 1;
        for (int i = 2; i <= N; i++) minHourArr[i] = INF;  // 자기 자신으로 가는 도로를 제외하고는 최댓값으로 초기화
        PriorityQueue<Road> pq = new PriorityQueue<>();
        Road initRoad;
        for (int i = 0; i < roadList.get(1).size(); i++) { // 일단 주어진 도로를 이용해서 1번 마을과 이어진 마을까지 시간 갱신
            initRoad = roadList.get(1).get(i);
            minHourArr[initRoad.to] = Math.min(minHourArr[initRoad.to], initRoad.hour);
            pq.offer(roadList.get(1).get(i));
        }

        Road now;
        List<Road> nextRoadList;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (minHourArr[now.to] < now.hour) continue;  // 이미 더 짧은 경로가 존재하는 경우면 무시

            nextRoadList = roadList.get(now.to);
            Road next;
            for (int i = 0; i < nextRoadList.size(); i++) {
                next = nextRoadList.get(i);
                if (minHourArr[next.to] > minHourArr[now.to] + next.hour) {
                    minHourArr[next.to] = minHourArr[now.to] + next.hour;
                    pq.offer(next);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) if (minHourArr[i] <= K) answer++;

        return answer;
    }

    List<List<Road>> makeRoadList(int N, int[][] road) {
        List<List<Road>> roadList = new ArrayList<>();
        for (int i = 0; i <= N; i++) roadList.add(new ArrayList<>());

        int from, to, hour;
        for (int i = 0; i < road.length; i++) {
            from = road[i][0];
            to = road[i][1];
            hour = road[i][2];
            roadList.get(from).add(new Road(from, to, hour));
            roadList.get(to).add(new Road(to, from, hour));
        }

        return roadList;
    }

}
