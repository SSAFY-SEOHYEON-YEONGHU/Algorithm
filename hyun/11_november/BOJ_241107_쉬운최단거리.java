package bfs;

import java.io.*;
import java.util.*;

public class BOJ_241107_쉬운최단거리 {
    static int N,M;
    static int ex,ey;
    static int[][] input;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static StringBuilder sb = new StringBuilder();
    static class Node{
        int x,y;
        Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void simulation(){
        Queue<Node> q = new ArrayDeque<>();
        //boolean[][] visited = new boolean[N][M];

        q.add(new Node(ex,ey));
        //visited[ex][ey] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for(int k =0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(nx<0 || nx >= N || ny < 0 || ny >= M || map[nx][ny] != -1 || input[nx][ny] != 1) continue;

                q.add(new Node(nx, ny));
                map[nx][ny] = map[cur.x][cur.y] + 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N][M];
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
                if(input[i][j] == 2){
                    ex = i;
                    ey = j;
                    map[i][j] = 0;
                }
                else if(input[i][j] == 0) map[i][j] = 0;
                else map[i][j] = -1;
            }
        }

        simulation();
    }
}
