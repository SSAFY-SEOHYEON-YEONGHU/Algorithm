package twentytwentyfive.february.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1238_파티 {

    static class Road implements Comparable<Road> {
        int from;
        int to;
        int time;

        public Road(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Road r) { return this.time - r.time; }
    }

    static int N, M, X;
    static List<List<Road>> roadList;
    static List<List<Road>> reverseRoadList;    // 도로 방향을 뒤집어 저장하고 있는 리스트

    public static void main(String[] args) throws IOException {
        init();

//        int[] destinationToSource = dijkstra(X);    // 파티 장소부터 각 집까지의 거리를 구하는 다익스트라

//        int maxDist = 0;
//        for (int i = 1; i <= N; i++) {
//            int sourceToDestination = dijkstra(i)[X];
//            maxDist = Math.max(maxDist, sourceToDestination+destinationToSource[i]);    // 각 학생들의 왕복 시간 중 최댓값 갱신
//        }
        int[] sourceToDestination = dijkstra(reverseRoadList);
        int[] destinationToSource = dijkstra(roadList);

        int maxDist = 0;
        for (int i = 1; i <= N; i++) maxDist = Math.max(maxDist, sourceToDestination[i] + destinationToSource[i]);

        System.out.println(maxDist);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        roadList = new ArrayList<>();
        reverseRoadList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            roadList.add(new ArrayList<>());
            reverseRoadList.add(new ArrayList<>());
        }
        int from, to, time;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            time = Integer.parseInt(st.nextToken());
            roadList.get(from).add(new Road(from, to, time));
            reverseRoadList.get(to).add(new Road(to, from, time));
        }
    }

//    static int[] dijkstra(int source) {
//        PriorityQueue<Road> pq = new PriorityQueue<>();
//        int[] needTimes = new int[N+1];
//        for (int i = 1; i <= N; i++) if (i != source) needTimes[i] = Integer.MAX_VALUE; // 각 마을까지 가는 데 필요한 시간 초기화
//
//        List<Road> sourceRoadList = roadList.get(source);
//        for (Road r : sourceRoadList) {     // 출발 도시와 이어진 도로 pq에 삽입
//            if (needTimes[r.to] <= r.time) continue;
//
//            pq.offer(r);
//            needTimes[r.to] = r.time;
//        }
//
//        Road now;
//        while (!pq.isEmpty()) {
//            now = pq.poll();
//
//            List<Road> nextRoadList = roadList.get(now.to);
//            for (Road r : nextRoadList) {
//                if (needTimes[r.to] <= needTimes[now.to] + r.time) continue;    // 이미 더 적은 시간으로 갈 수 있는 곳이면 건너 뜀
//
//                pq.offer(r);
//                needTimes[r.to] = needTimes[now.to] + r.time;
//            }
//        }
//
//        return needTimes;
//    }
    static int[] dijkstra(List<List<Road>> inFunctionRoadList) {
        PriorityQueue<Road> pq = new PriorityQueue<>();

        int[] needTimes = new int[N+1];
        for (int i = 1; i <= N; i++) if (i != X) needTimes[i] = Integer.MAX_VALUE; // 각 마을까지 가는 데 필요한 시간 초기화

        List<Road> sourceRoadList = inFunctionRoadList.get(X);
        for (Road r : sourceRoadList) {     // 출발 도시(X)와 이어진 도로 pq에 삽입
            if (needTimes[r.to] <= r.time) continue;

            pq.offer(r);
            needTimes[r.to] = r.time;
        }

        Road now;
        while (!pq.isEmpty()) {
            now = pq.poll();

            List<Road> nextRoadList = inFunctionRoadList.get(now.to);
            for (Road r : nextRoadList) {
                if (needTimes[r.to] <= needTimes[now.to] + r.time) continue;    // 이미 더 적은 시간으로 갈 수 있는 곳이면 건너 뜀

                pq.offer(r);
                needTimes[r.to] = needTimes[now.to] + r.time;
            }
        }

        return needTimes;
    }

}
