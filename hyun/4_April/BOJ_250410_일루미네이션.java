package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250410_일루미네이션 {
    static int W,H;
    static int[][] map;
    static int[] dy = {-1,0,1,1,0,-1};
    static int[] dx = {0,1,0,-1,-1,-1};


    static int[] dx2 = {1,1,1,0,-1,0};
    static int[] dy2 = {-1,0,1,1,0,-1};

    static class Node{
        int y,x;
        Node(int y, int x){
            this.x = x;
            this.y = y;
        }
    }
    static void printArr(boolean[][] visited){
        System.out.println("=============================");
        for (int i = 0; i < H+2; i++) {
            for (int j = 0; j < W+2; j++) {
                if(visited[j][i]) System.out.print(1 + " ");
                else System.out.print(0 + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void bfs(){
        int total = 0;
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[W+2][H+2];

        q.add(new Node(0,0));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
//            System.out.println(cur.x + " " + cur.y);

            int cnt = 0;
            for (int k = 0; k < 6; k++) {
                int ny = cur.y + dx[k];
                int nx = cur.x + dy[k];

                if(cur.x % 2 != 0) {
                    nx = cur.x + dy2[k];
                    ny = cur.y + dx2[k];
                }

                //범위 넘음
                if(nx < 0 || ny < 0 || nx >= H+2 || ny >= W+2 || visited[ny][nx]) continue;

                if(map[ny][nx] == 1) {
                    total++;
                    cnt++;
                }
                else {
                    q.add(new Node(ny,nx));
                    visited[ny][nx] = true;
                }
            }
//            System.out.println(cnt + " 더함");
            //printArr(visited);
        }

        System.out.println(total);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[W+2][H+2];

        for (int i = 1; i <= H ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= W; j++) {
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        //
        bfs();
    }
}
