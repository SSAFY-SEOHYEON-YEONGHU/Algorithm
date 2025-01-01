package december.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_5972_택배배송 {

    static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {  // 오름차순 정렬
            return this.cost - e.cost;
        }
    }

    static int N, M;
    static List<List<Edge>> edgeList;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i <= N; i++) edgeList.add(new ArrayList<>());
        int from, to, cost;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to, cost));   // 양방향이므로 양쪽에 다 넣어 줌
            edgeList.get(to).add(new Edge(to, from, cost));
        }
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] costs = new int[N+1];
        Arrays.fill(costs, 1_000 * 50_000 + 1); // 최초 비용 초기화
        costs[1] = 0;
        Edge initEdge;
        for (int i = 0; i < edgeList.get(1).size(); i++) {  // 1과 연결된 도로 탐색
            initEdge = edgeList.get(1).get(i);
            if (costs[initEdge.to] > initEdge.cost) {   // 지금까지 비용보다 더 저렴한 비용으로 갈 수 있는 곳이면 비용 갱신 후 pq에 삽입
                costs[initEdge.to] = initEdge.cost;
                pq.offer(initEdge);
            }
        }

        Edge now;
        List<Edge> nextEdgeList;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (now.from == N) break;   // N에 도착하면 반복문 종료

            nextEdgeList = edgeList.get(now.to);
            for (Edge next : nextEdgeList) {
                if (costs[next.to] > costs[next.from] + next.cost) {
                    costs[next.to] = costs[next.from] + next.cost;
                    pq.offer(next);
                }
            }
        }

        System.out.println(costs[N]);
    }

}
