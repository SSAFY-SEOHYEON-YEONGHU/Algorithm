package mst;

import java.io.*;
import java.util.*;
public class BOJ_250212_안정적인네트워크 {
    static int N,M;
    static int[][] cost;
    static boolean[][] checkCost;


    static int[] parent;
    static PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2) -> n1.cost - n2.cost);
    static int answer = 0;
    static ArrayList<Node> answerList = new ArrayList<>();
    static class Node{
        int a,b,cost;

        Node(int a, int b){
            this.a = a;
            this.b = b;
        }
        Node(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }
    }

    static int find(int x){
        if(parent[x] == x) return x;
        return find(parent[x]);
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        if(pa <= pb) parent[pb] = pa;
        else parent[pa] = pb;
    }
    static void simulation(){

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(find(cur.a) != find(cur.b)){
                union(cur.a, cur.b);

                if(!checkCost[cur.a][cur.b]){
                    answer += cost[cur.a][cur.b];
                    checkCost[cur.a][cur.b] = true;
                    checkCost[cur.b][cur.a] = true;
                    answerList.add(new Node(cur.a, cur.b));
                }
            }
        }

        System.out.println(answer + " " + answerList.size());
        for(Node cur : answerList) System.out.println(cur.a + " " + cur.b);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        cost = new int[N+1][N+1];
        checkCost = new boolean[N+1][N+1];

        for(int i=1; i<=N; i++) parent[i] = i;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a,b);

            checkCost[a][b] = true;
            checkCost[b][a] = true;
        }

        for (int i = 1; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N ; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
                if(i == j) continue;
                if(i!=1 && j!=1) pq.add(new Node(i,j,cost[i][j]));
            }
        }

        //
        simulation();
    }
}
