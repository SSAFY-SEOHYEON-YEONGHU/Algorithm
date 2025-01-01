package bfs;

import java.io.*;
import java.util.*;

public class BOJ_241107_미로탈출 {
    static int N,M;
    static int sx,sy,ex,ey;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class Node{
        int x,y,cnt;
        Node(int x, int y,int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void simulation(){
        Queue<Node> q = new ArrayDeque<>();
        int[][][] visited = new int[N][M][2];

        if(map[sx][sy] == 1) {
            q.add(new Node(sx,sy,1));
            visited[sx][sy][1] = 1;
        }else{
            q.add(new Node(sx,sy,0));
            visited[sx][sy][0] = 1;
        }

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.x == ex && cur.y == ey){
                System.out.println(visited[ex][ey][cur.cnt] - 1);
                return;
            }

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if(map[nx][ny] == 1){
                    if(visited[nx][ny][1] == 0 && cur.cnt == 0){
                        q.add(new Node(nx,ny,1));
                        visited[nx][ny][1] = visited[cur.x][cur.y][cur.cnt] + 1;
                    }
                }
                else{
                    if(visited[nx][ny][cur.cnt] == 0){
                        q.add(new Node(nx,ny,cur.cnt));
                        visited[nx][ny][cur.cnt] = visited[cur.x][cur.y][cur.cnt] + 1;
                    }
                }
            }
        }

        System.out.println(-1);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        sx = Integer.parseInt(st.nextToken()) - 1;
        sy = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        ex = Integer.parseInt(st.nextToken()) - 1;
        ey = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulation();
    }
}
