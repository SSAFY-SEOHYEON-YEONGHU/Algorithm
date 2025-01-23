package twentytwentyfive.january.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13911_집구하기 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int dist;

        public Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist - e.dist;
        }
    }

    static int V, E;
    static List<List<Edge>> edgeList;
    static int M, x, S, y;  // M: 맥도날드 개수, x: 맥세권 조건 / S: 스타벅스 개수, y: 스세권 조건
    static List<Integer> mcDonaldList, starbucksList;
    static boolean[] isMcDonald, isStarbucks;   // 해당 노드가 맥도날드인 지 스타벅스인 지 여부 저장
    static int[] mcDonaldDist, starbucksDist;    // 각 집에서 각 프랜차이즈까지의 거리


    public static void main(String[] args) throws IOException {
        init();
        dijkstra(mcDonaldList, x, mcDonaldDist);
        dijkstra(starbucksList, y, starbucksDist);

        int minDist = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            if ((mcDonaldDist[i] != Integer.MAX_VALUE && starbucksDist[i] != Integer.MAX_VALUE) // 오버플로우 방지
            && (mcDonaldDist[i] != 0 && starbucksDist[i] != 0)) minDist = Math.min(minDist, mcDonaldDist[i]+starbucksDist[i]); // 각 프랜차이즈가 위치한 곳은 집이 아님
        }
        System.out.println((minDist==Integer.MAX_VALUE)? -1:minDist);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) edgeList.add(new ArrayList<>());
        int from, to, dist;
        for (int i = 0; i < E; i++) {   // 간선 정보 저장
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            dist = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to, dist));
            edgeList.get(to).add(new Edge(to, from, dist));
        }

        getInputFranchise(br, 'M');
        getInputFranchise(br, 'S');

        mcDonaldDist = new int[V+1];
        int franchiseIndex = 0;
        for (int i = 1; i <= V; i++) {
            if (franchiseIndex < mcDonaldList.size() && i == mcDonaldList.get(franchiseIndex)) franchiseIndex++; // 맥도날드인 정점은 거리 0으로 그냥 두고
            else mcDonaldDist[i] = Integer.MAX_VALUE; // 나머지 정점들은 최대값으로 갱신
        }
        starbucksDist = new int[V+1];
        franchiseIndex = 0;
        Arrays.fill(starbucksDist, Integer.MAX_VALUE);
        for (int i = 1; i <= V; i++) {
            if (franchiseIndex < starbucksList.size() && i == starbucksList.get(franchiseIndex)) franchiseIndex++; // 스타벅스인 정점은 거리 0으로 그냥 두고
            else starbucksDist[i] = Integer.MAX_VALUE; // 나머지 정점들은 최대값으로 갱신
        }
    }

    static void getInputFranchise(BufferedReader br, char mode) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        if (mode == 'M') {
            M = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());

            mcDonaldList = new ArrayList<>();
            isMcDonald = new boolean[V+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                mcDonaldList.add(Integer.parseInt(st.nextToken()));
                isMcDonald[mcDonaldList.get(i)] = true;
            }
        } else {
            S = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            starbucksList = new ArrayList<>();
            isStarbucks = new boolean[V+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < S; i++) {
                starbucksList.add(Integer.parseInt(st.nextToken()));
                isStarbucks[starbucksList.get(i)] = true;
            }
        }
    }

    static void dijkstra(List<Integer> startPoints, int limit, int[] dist) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[V + 1];

        for (int start : startPoints) { // 각 프랜차이즈의 모든 시작점을 다 넣어 줌
            dist[start] = 0;
            pq.offer(new Edge(start, start, 0));
        }

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            int current = now.to;

            if (visited[current]) continue; // 이미 다음 지점까지의 거리들을 탐색해본 곳이면 건너 뜀
            visited[current] = true;    // 이 지점에서 다음 지점들을 방문했다는 의미로 여기서 방문 처리를 해줌

            for (Edge next : edgeList.get(current)) {
                if (visited[next.to]) continue;
                if (dist[current] + next.dist > limit) continue;    // 거리 제한 넘어가면 건너 뜀

                if (dist[next.to] > dist[current] + next.dist) {    // 거리 짧은 곳은 일단 pq에 넣어 줌
                    dist[next.to] = dist[current] + next.dist;
                    pq.offer(new Edge(current, next.to, dist[next.to]));
                }
            }
        }
    }

}
