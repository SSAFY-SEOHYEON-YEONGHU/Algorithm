package topological_sort;

import java.io.*;
import java.util.*;


public class BOJ_241225_선수과목 {
    static int N,M;
    static ArrayList<Integer>[] adj;
    static int[] indegree;

    public static void topologicalSort(){
        Queue<int[]> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        int[] answer = new int[N+1];

        for (int i = 1; i <= N ; i++) {
            if(indegree[i] == 0) q.add(new int[]{i,1});
        }


        while(!q.isEmpty()) {
            int[] cur = q.poll();
            answer[cur[0]] = cur[1];

            for (int nxt : adj[cur[0]]) {
                indegree[nxt]--;
                if (indegree[nxt] == 0) q.add(new int[]{nxt, cur[1]+1});
            }
        }

        for(int i=1; i<=N; i++) sb.append(answer[i]).append(" ");
        System.out.println(sb
        );
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        indegree = new int[N+1];

        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            indegree[b]++;
        }

        topologicalSort();
    }
}
