package implementation;

import java.util.*;
public class PGMS_240923_배달 {
    ArrayList<Node>[] adj;

    int answer = 0;

    class Node{
        int v, cost;
        Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }
    }

    public void dijkstra(int N, int K){
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost - o2.cost);
        int[] distance = new int[N+1];
        Arrays.fill(distance, (int)Math.pow(10,8));
        boolean[] visited = new boolean[N+1];

        distance[1] = 0;
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();

            if(!visited[cur.v]){
                visited[cur.v] = true;
            }

            for(Node nxt : adj[cur.v]){
                if(!visited[nxt.v] && distance[nxt.v] > cur.cost + nxt.cost){
                    distance[nxt.v] = cur.cost + nxt.cost;
                    pq.add(new Node(nxt.v, distance[nxt.v]));
                }
            }
        }

        for(int su : distance)
            if(su <= K) answer++;

    }

    public int solution(int N, int[][] road, int K) {
        // 초기화
        adj = new ArrayList[N+1];
        for(int i=0; i<= N; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<road.length; i++){
            int[] cur = road[i];
            adj[cur[0]].add(new Node(cur[1], cur[2]));
            adj[cur[1]].add(new Node(cur[0], cur[2]));
        }

        // 다익스트라
        dijkstra(N,K);


        return answer;
    }
}
