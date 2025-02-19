package topological_sort;

import java.io.*;
import java.util.*;
public class BOJ_250218_문제집 {
    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] indegree;

    static void simulation(){
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1,i2) -> Integer.compare(i1,i2));
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N ; i++) {
            if(indegree[i] == 0) pq.add(i);
        }

        while(!pq.isEmpty()){
            int cur = pq.poll();
            sb.append(cur).append(" ");

            for(int nxt : adj[cur]){
                indegree[nxt]--;
                if(indegree[nxt] == 0) pq.add(nxt);
            }
        }

        System.out.println(sb);
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
        indegree = new int[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            adj[a].add(b);
        }

        simulation();
    }
}
