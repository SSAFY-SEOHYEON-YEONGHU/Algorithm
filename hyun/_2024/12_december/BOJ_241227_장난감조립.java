package topological_sort;

import java.io.*;
import java.util.*;
public class BOJ_241227_장난감조립 {
    static int N,M;
    static ArrayList<Node>adj[];
    static int[] indegree;
    static class Node{
        int cnt, made;
        Node(int cnt, int made){
            this.cnt = cnt;
            this.made = made;
        }
    }

    static void topologicalSort(){
        Queue<Integer> q = new ArrayDeque<>();
        int[][] map = new int[N+1][N+1];

        for (int i = 1; i <= N ; i++) {
            if(indegree[i] == 0) {
                q.add(i);
                for(Node nxt : adj[i]){
                    map[i][nxt.made] = nxt.cnt;
                }
            }
        }

        while(!q.isEmpty()){
            int cur = q.poll();

            for(Node nxt : adj[cur]){
                // cur 이 nxt.cnt 개가 있으면 nxt.made 가 만들어짐
                // 갯수 채워넣고
                for (int i = 1; i <= N ; i++) {
                    map[i][nxt.made] += (map[i][cur] * nxt.cnt);
                }

                // -1
                indegree[nxt.made]--;
                if(indegree[nxt.made] == 0) q.add(nxt.made);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N ; i++) {
            if(map[i][N] == 0) continue;
            sb.append(i).append(" ").append(map[i][N]).append("\n");
        }
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adj = new ArrayList[N+1];
        indegree = new int[N+1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            adj[y].add(new Node(k,x));
            indegree[x]++;
        }

        topologicalSort();


    }
}
