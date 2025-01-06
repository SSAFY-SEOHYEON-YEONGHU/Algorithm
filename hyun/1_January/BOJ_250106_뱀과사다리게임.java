package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250106_뱀과사다리게임 {
    static int N,M;
    static int[] move;
    static class Node{
        int num,cnt;
        Node(int num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visited = new boolean[110];

        q.add(new Node(1,0));
        visited[1] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.num == 100){
                System.out.println(cur.cnt);
                return;
            }

            for (int k = 1; k <= 6 ; k++) {
                int nxt = cur.num + k;
                if(nxt > 100 || visited[nxt]) continue;

                if(move[nxt] == 0){
                    q.add(new Node(nxt, cur.cnt+1));
                    visited[nxt] = true;
                }
                else{
                    if(!visited[move[nxt]]){
                        q.add(new Node(move[nxt], cur.cnt+1));
                        visited[move[nxt]] = true;
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        move = new int[101];
        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            move[a] = b;
        }

        bfs();
    }
}
