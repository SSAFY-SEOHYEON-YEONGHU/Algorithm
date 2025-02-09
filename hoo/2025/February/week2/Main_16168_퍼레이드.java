package twentytwentyfive.february.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16168_퍼레이드 {

    static class Edge {
        int from;
        int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }
    }

    static int V, E;
    static List<List<Edge>> edgeList;
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        init();

        int[][]  isUsedEdge = new int[V+1][V+1];
        for (int i = 1; i <= V; i++) {
            findRoute(i, i,0, isUsedEdge);
            if (isPossible) break;
        }

        System.out.println(isPossible? "YES":"NO");
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList<>();
        for (int i = 0; i <= V; i++) edgeList.add(new ArrayList<>());
        int from, to;
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to));
            edgeList.get(to).add(new Edge(to, from));
        }
        isPossible = false;
    }

    static void findRoute(int nowNodeNumber, int startNode, int usedEdgeCount, int[][] isUsedEdge) {  // 재귀를 통해 모든 지점, 모든 경로 방문 가능한 지 여부 체크
        // isUsedEdge : 해당 간선을 사용했는 지 여부 저장. 적힌 숫자는 시작 노드의 숫자(매 노드별로 isUsedEdge 만들면 시간 초과 때문에 이렇게 함)
        if (isPossible) return; // 이미 가능한 경우임을 판단했다면 바로 함수 종료
        if (usedEdgeCount == E) { // 기저, 모든 간선 다 사용했을 때
            isPossible = true; // 그때 모든 노드도 방문했다면 가능한 경우임

            return;
        }

        List<Edge> nextEdgeList = edgeList.get(nowNodeNumber);
        for (Edge nextEdge : nextEdgeList) {
            if (isUsedEdge[nextEdge.from][nextEdge.to] == startNode || isUsedEdge[nextEdge.to][nextEdge.from] == startNode) continue; // 이미 사용한 간선이면 건너 뜀

            isUsedEdge[nextEdge.from][nextEdge.to] = startNode;
            isUsedEdge[nextEdge.to][nextEdge.from] = startNode;
            findRoute(nextEdge.to, startNode, usedEdgeCount+1, isUsedEdge);
        }
    }

}
