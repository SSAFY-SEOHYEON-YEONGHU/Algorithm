package twentytwentyfive.february.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2406_안정적인네트워크 {

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
        public int compareTo(Edge e) { return this.cost-e.cost; } // 비용 기준 오름차순 정렬

        @Override
        public String toString() { return this.from + " " + this.to + " " + this.cost; }
    }

    static int n, m;
    static int[] parent; // 각 지사 컴퓨터들의 부모 컴퓨터를 저장할 배열
    static PriorityQueue<Edge> connectionCosts;

    public static void main(String[] args) throws IOException {
        init();
        if (connectionCosts.size() == 0) System.out.println(0 + " " + 0); // 연결해야하는 컴퓨터들이 없으면 0 0 출력 후 종료
        else System.out.println(calcMinNeedCost()); // 아니라면 연결해야하는 컴퓨터들 최소비용으로 연결해줌
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];
        for (int i = 2; i <= n; i++) parent[i] = i; // 지사 컴퓨터들 부모 컴퓨터를 자기 자신으로 초기화
        int from, to;
        for (int i = 0; i < m; i++) { // 우선 간선이 연결된 노드들은 같은 네트워크로 합쳐줌
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            union(from, to);
        }

        connectionCosts = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            if (i == 1) continue; // 1번 컴퓨터는 판단 안함
            int cost;
            for (int j = 1; j <= n; j++) { // i+1부터 하면 중복 안되지만 tokenizer의 token을 빼줘야해서 1부터 실행
                cost = Integer.parseInt(st.nextToken());
                if (j > i && find(i) != find(j)) connectionCosts.offer(new Edge(i, j, cost)); // 본인 번호 초과인 컴퓨터이거나 이미 같은 네트워크에 속한 컴퓨터가 아닌 경우에만 pq에 저장
            }
        }
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) { // 서로 부모가 다르다면 둘 중 작은 수로 부모 합쳐줌
            if (a < b) parent[parentB] = parentA;
            else parent[parentA] = parentB;
        }
    }

    static int find(int a) { // 부모를 찾는 함수
        if (parent[a] == a) return a; // 자기 자신이 부모이면 자기 자신 반환

        return parent[a] = find(parent[a]); // 그게 아니라면 재귀적으로 최종 부모 찾고, a의 부모에도 저장
    }

    static String calcMinNeedCost() {
        StringBuilder sb = new StringBuilder();
        int connectedCost = 0; // 신규로 연결한 쌍의 총 비용
        List<Edge> connectedEdge = new ArrayList<>(); // 연결한 간선(컴퓨터 쌍)

        Edge now;
        while (!connectionCosts.isEmpty()) { // 비용을 기준으로 오름차순 정렬한 모든 간선들에 대해 수행
            now = connectionCosts.poll();
//            System.out.println(now);

            if (find(now.from) == find(now.to)) continue; // 이미 연결됐다면 건너 뜀
            union(now.from, now.to);
            connectedCost += now.cost;
            connectedEdge.add(now);
        }
        sb.append(connectedCost).append(" ").append(connectedEdge.size()).append("\n");
        for (Edge e : connectedEdge) sb.append(e.from).append(" ").append(e.to).append("\n");
//        System.out.println(Arrays.toString(parent));

        return sb.toString();
    }

}
