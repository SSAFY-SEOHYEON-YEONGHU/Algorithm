package bfs;

import java.awt.*;
import java.io.*;
import java.util.*;

public class BOJ_250721_친구 {
    static int N;
    static ArrayList<Integer>[] adj;
    static int answer = 0;

    static int bfs(int cur,  boolean[] visited){
        Queue<Integer> q = new ArrayDeque<>();

        q.add(cur);
        while(true) {
            int size = q.size();
            boolean isExit = true;
            for (int i = 0; i < size; i++) {
                cur = q.poll();
                for (int nxt : adj[cur]) {
                    if (visited[nxt]) continue;

                    visited[nxt] = true;
                    isExit = false;
                    q.add(nxt);
                }
            }
            if(isExit) break;
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            if(visited[i]) result++;
        }

        return result -1;
    }

    static void simulation(){
        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            visited[i] = true;

            System.out.println(i);
            answer = Math.max(answer, bfs(i,visited));
            System.out.println(answer);
        }

        System.out.println(answer);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N];
        for (int i = 0; i < N; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = i+1; j < N; j++) {
                if(s.charAt(j) == 'Y') {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }

        //

        simulation();
    }
}
