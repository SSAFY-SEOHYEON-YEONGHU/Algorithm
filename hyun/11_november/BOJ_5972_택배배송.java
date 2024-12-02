package dijkstra;

import java.io.*;
import java.util.*;
public class BOJ_5972_택배배송 {
    static int N,M;
    static ArrayList<Node>[] adj;
    static class Node{
        int x;
        int cost;
        Node(int x, int cost){
            this.x = x;
            this.cost = cost;
        }
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.cost - o2.cost);
        boolean[] visited = new boolean[N+1];
        int[] distance = new int[N+1];
        Arrays.fill(distance,60000000);

        pq.add(new Node(1,0));
        distance[1] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(!visited[cur.x]) visited[cur.x] = true;

            for(Node nxt : adj[cur.x]){
                if(!visited[nxt.x] && distance[nxt.x] > (cur.cost + nxt.cost)){
                    distance[nxt.x] = cur.cost + nxt.cost;
                    pq.add(new Node(nxt.x, distance[nxt.x]));
                }
            }
        }

        System.out.println(distance[N]);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, cost));
            adj[b].add(new Node(a, cost));
        }

        dijkstra();

    }
}
