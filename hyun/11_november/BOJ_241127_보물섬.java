package bfs;

import java.io.*;
import java.util.*;
public class BOJ_241127_보물섬 {
    static int N,M;
    static char[][] input;
    static int answer = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static void bfs(int x, int y){
        Queue<Node> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];

        q.add(new Node(x,y));
        visited[x][y] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();
            answer = Math.max(answer, visited[cur.x][cur.y]);

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= M ||
                        input[nx][ny] != 'L' || visited[nx][ny] != 0 ) continue;

                q.add(new Node(nx,ny));
                visited[nx][ny] = visited[cur.x][cur.y] + 1;
            }
        }
    }
    static void simulation(){


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(input[i][j] == 'L')
                    bfs(i,j);
            }
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new char[N][M];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine().toCharArray();
        }

        simulation();

        System.out.println(answer - 1);
    }
}
