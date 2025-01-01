package twentytwentyfour.november.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge implements Comparable<Edge> {
    int from;
    int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public int compareTo(Edge e) {  // 인접 정점을 오름차순으로 방문하기 위해 to를 오름차순으로 정렬
        return this.to - e.to;
    }

    @Override
    public String toString() {
        return this.from + " " + this.to;
    }
}

public class Main_24481_알고리즘수업깊이우선탐색3 {

    static int N, M, R;
    static List<List<Edge>> edgeList;
    static int[] depthArr;

    public static void main(String[] args) throws IOException {
        init();
        depthArr = new int[N+1];
        for (int i = 0; i <= N; i++) depthArr[i] = -1;
        dfs(R, 0);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) sb.append(depthArr[i]).append("\n");
        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        edgeList = new ArrayList<>();
        for (int i = 0; i <= N; i++) edgeList.add(new ArrayList<>());
        int from, to;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to));
            edgeList.get(to).add(new Edge(to, from));
        }
        for (int i = 0; i < edgeList.size(); i++) { // 인접한 정점 방문 때 오름차순 방문 위해 정렬
            Collections.sort(edgeList.get(i));
        }
    }

    static void dfs(int nowVertex, int depth) {
        depthArr[nowVertex] = depth;

        List<Edge> nextEdgeList = edgeList.get(nowVertex);
        Edge next;
        for (int i = 0; i < nextEdgeList.size(); i++) {
            next = nextEdgeList.get(i);
            if (depthArr[next.to] == -1) dfs(next.to, depth+1);
        }
    }

}
