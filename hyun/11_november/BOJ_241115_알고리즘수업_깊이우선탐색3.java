package dfs;

import java.io.*;
import java.util.*;
public class BOJ_241115_알고리즘수업_깊이우선탐색3 {
    static int N,M,R;
    static ArrayList<Integer>[] adj;
    static boolean[] visited;
    static int[] answer;
    static StringBuilder sb = new StringBuilder();

    public static void dfs(int cur, int cnt){

        for(int nxt : adj[cur]){
            if(visited[nxt]) continue;

            visited[nxt] = true;
            answer[nxt] = cnt+1;
            dfs(nxt, cnt+1);
        }
    }

    public static void simulation(){
        for (int i = 0; i <= N; i++) {
            Collections.sort(adj[i]);
        }
        visited = new boolean[N+1];
        answer = new int[N+1];
        Arrays.fill(answer,-1);

        visited[R] = true;
        answer[R] = 0;
        dfs(R,0);

        for (int i = 1; i <= N ; i++) sb.append(answer[i]).append("\n");
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        simulation();
    }
}
