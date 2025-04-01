package mst;

import java.io.*;
import java.util.*;
public class BOj_250401_도시건설 {
    static int N,M;
    static int[] parent;
    static PriorityQueue<Node> pq;
    static long answer = 0;
    static class Node{
        int a,b,cost;
        Node(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa < pb) parent[pb] = pa;
        else parent[pa] = pb;
    }
    static int find(int v){
        if(parent[v] == v) return v;
        return find(parent[v]);
    }

    static void kruskal(){

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(find(cur.a) != find(cur.b)){
                union(cur.a, cur.b);
                answer -= cur.cost;
            }
        }

        // 부모 같은지 확인
        int p = find(parent[1]);
        for (int i = 2; i <= N ; i++) {
            if(find(i) != p) {
                answer = -1;
                break;
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        for (int i = 0; i <= N; i++) parent[i] = i;
        pq = new PriorityQueue<>((n1,n2)->n1.cost - n2.cost);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Node(a,b,cost));
            answer += cost;
        }

        kruskal();


    }
}
