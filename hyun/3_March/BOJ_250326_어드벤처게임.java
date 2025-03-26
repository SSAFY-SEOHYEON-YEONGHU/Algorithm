package dfs;

import java.io.*;
import java.util.*;
public class BOJ_250326_어드벤처게임 {
    static int N;
    static ArrayList<Integer>[] adj;
    static Node[] roomInfo;
    static boolean[] visited;
    static boolean isAnswer = false;
    static StringBuilder sb = new StringBuilder();
    static class Node{
        char ch;
        int gold;
        Node(char ch, int gold){
            this.ch = ch;
            this.gold = gold;
        }
    }

    static void dfs(int cur, int gold){
        Node room = roomInfo[cur];

        // 현재 방에 들어갈 수 있는지
        int tmpGold = gold;
        if(room.ch == 'L') tmpGold = Math.max(gold, room.gold);
        else if(room.ch == 'T') {
            if(gold - room.gold < 0) return;
            tmpGold -= room.gold;
        }

        visited[cur] = true;

        if(cur == N){
            isAnswer = true;
            return;
        }


        // 다음 방 탐색
        for(int nxt : adj[cur]){
             if(visited[nxt]) continue;
             dfs(nxt, tmpGold);
            visited[nxt] = false;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            adj = new ArrayList[N+1];
            for (int i = 0; i <= N; i++) {
                adj[i] = new ArrayList<>();
            }
            roomInfo = new Node[N+1];
            visited = new boolean[N+1];
            isAnswer = false;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int gold = Integer.parseInt(st.nextToken());
                roomInfo[i+1] = new Node(c, gold);

                while(true) {
                    int su = Integer.parseInt(st.nextToken());
                    if(su == 0) break;
                    adj[i+1].add(su);
                }
            }

            //
            dfs(1,0);
            if(isAnswer) sb.append("Yes").append("\n");
            else sb.append("No").append("\n");
        }
        System.out.println(sb);
    }
}
