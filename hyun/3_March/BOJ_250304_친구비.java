package bfs;

import java.io.*;
import java.util.*;

public class BOJ_250304_친구비 {
    static int N,M,K;
    static int[] cost;
    static ArrayList<Integer>[] adj;

    static int bfs(int start, boolean[] visited){
        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        visited[start] = true;
        int minCost = cost[start];

        while(!q.isEmpty()){
            int cur = q.poll();

            for(int nxt : adj[cur]){
                if(visited[nxt]) continue;

                q.add(nxt);
                visited[nxt] = true;
                minCost = Math.min(minCost, cost[nxt]);
            }
        }
        return minCost;
    }

    static void simulation(){
        int sum = 0;
        boolean[] visited = new boolean[N+1];

        for (int i = 1; i <= N ; i++) {
            if(visited[i]) continue;
            sum += bfs(i, visited);
        }

        // 비교
        for (int i = 1; i <= N ; i++) {
            if(!visited[i]) sum += cost[i];
        }

        if(sum <= K) System.out.println(sum);
        else System.out.println("Oh no");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cost = new int[N+1];
        adj = new ArrayList[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i<= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            adj[i] = new ArrayList<>();
        }

        boolean[][] visited = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == b || visited[a][b] || visited[b][a]) continue;
            adj[a].add(b);
            adj[b].add(a);
        }

        //
        simulation();
    }
}
