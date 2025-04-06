package bfs;

import java.io.*;
import java.util.*;

/*
나는 3차원 배열로 했지만, 나보다 빠른 시간대로 나온 코드 보니깐
2차원 배열로 bfs 돌리되 검을 만나면 바로 그 검에서 공주까지의 좌표를 한번에 계산후
정답 갱신해 줌
 */

public class BOJ_250406_공주님을구하라 {
    static int N,M,T;
    static int[][] map;

    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node{
        int x,y,have;
        Node(int x, int y, int have){
            this.x = x;
            this.y = y;
            this.have = have;
        }
    }

    static void simulation(){
        Queue<Node> q = new ArrayDeque<>();
        int[][][] visited = new int[N][M][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 2; k++) {
                    visited[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        q.add(new Node(0,0,0));
        visited[0][0][0] = 0;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;

                if(cur.have == 0){
                    if(map[nx][ny] == 2 && visited[nx][ny][1] == Integer.MAX_VALUE){
                        q.add(new Node(nx,ny,1));
                        visited[nx][ny][0] = visited[cur.x][cur.y][0] + 1;
                        visited[nx][ny][1] = visited[cur.x][cur.y][0] + 1;
                    }
                    else if(map[nx][ny] == 0 && visited[nx][ny][0] == Integer.MAX_VALUE){
                        q.add(new Node(nx,ny,0));
                        visited[nx][ny][0] = visited[cur.x][cur.y][0] + 1;
                    }
                }

                else if(cur.have == 1){
                    if(visited[nx][ny][1] == Integer.MAX_VALUE){
                        q.add(new Node(nx,ny,1));
                        visited[nx][ny][1] = visited[cur.x][cur.y][1] + 1;
                    }
                }
            }
        }

        int minValue = Math.min(visited[N-1][M-1][0], visited[N-1][M-1][1]);
        if(minValue <= T) System.out.println(minValue);
        else System.out.println("Fail");
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //
        simulation();
    }
}
