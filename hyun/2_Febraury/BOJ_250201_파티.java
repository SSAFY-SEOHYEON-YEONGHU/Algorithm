package dijkstra;

import java.io.*;
import java.nio.Buffer;
import java.util.*;
public class BOJ_250201_파티 {
    static int N,M,X;
    static ArrayList<Node>[] adj;
    static int[] Xdistance;
    static class Node{
        int v,w;
        Node(int v, int w){
            this.v = v;
            this.w = w;
        }
    }

    static int dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>((n1,n2)->n1.w - n2.w);
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(!visited[cur.v]) visited[cur.v] = true;

            for(Node nxt : adj[cur.v]){
                if(visited[nxt.v]) continue;
                if(distance[nxt.v] > cur.w + nxt.w){
                    pq.add(new Node(nxt.v, cur.w + nxt.w));
                    distance[nxt.v] = cur.w + nxt.w;
                }
            }
        }

        if(start == X){
            Xdistance = new int[N+1];
            for(int i=0; i<= N; i++) Xdistance[i] = distance[i];
        }

        return distance[X];
    }

    static void simulation(){
        int answer = 0;

        dijkstra(X);

        for (int i = 1; i <= N ; i++) {
            if(i == X) continue;
            int result = dijkstra(i);

            answer = Math.max(answer, result + Xdistance[i]);
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b,c));
        }

        //
        simulation();
    }
}
