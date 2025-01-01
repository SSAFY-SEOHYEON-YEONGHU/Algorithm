package dfs;

import java.util.*;

public class PGMS_241108_순위 {
    int N;
    ArrayList<Integer>[][] adj;
    int answer = 0;

    public int dfs(boolean[] visited, int cnt, int cur, int flag){

        int tmp = cnt;
        for(int nxt : adj[cur][flag]){
            if(visited[nxt]) continue;
            visited[nxt] = true;
            tmp++;
            tmp += dfs(visited, cnt, nxt, flag);
        }
        return tmp;
    }

    public void simulation(){
        int win,lose;
        for(int i=1; i<= N; i++){
            boolean[] visited = new boolean[N+1];
            visited[i] = true;
            win = dfs(visited, 0,i,0);
            lose = dfs(visited,0,i,1);

            if(win + lose == N-1) answer++;
        }
    }
    public int solution(int n, int[][] results) {
        N = n;
        adj = new ArrayList[N+1][2];
        for(int i=0; i<= N; i++)
            for(int j=0; j<2; j++)
                adj[i][j] = new ArrayList<>();

        for(int i=0; i<results.length; i++){
            int win = results[i][0];
            int lose = results[i][1];
            adj[win][0].add(lose);
            adj[lose][1].add(win);
        }

        simulation();

        return answer;
    }
}