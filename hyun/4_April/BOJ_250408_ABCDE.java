package dfs;

import java.io.*;
import java.util.*;

/*
N=5, M=2000 개는 안됨
N 이 5개라서 간선갯수가 저만큼 나올 수 없음
그럼 각정점이 가질 수 있는 간선의 갯수를 최대로 잡았을때는 N=50 ~ 60 하면 nC2 대충 계산 때리면 3~40 나옴
근데 깊이가 5밖에 안되서 O(40^5) 해도 2초 이내가 가능함
그래서 아래 풀이 가능
 */
public class BOJ_250408_ABCDE {
    static int N,M;
    static ArrayList<Integer>[] adj;

    static boolean dfs(int depth, int cur, boolean[] visited){
        if(depth == 5) return true;

        for(int nxt : adj[cur]){
            if(visited[nxt]) continue;
            visited[nxt] = true;
            if(dfs(depth + 1, nxt, visited)) return true;
            visited[nxt] = false;
        }

        return false;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }

        //
        boolean isAnswer = false;
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;
            if(dfs(1,i,visited)) {
                isAnswer = true;
                break;
            }
        }

        if(isAnswer) System.out.println(1);
        else System.out.println(0);

    }
}
