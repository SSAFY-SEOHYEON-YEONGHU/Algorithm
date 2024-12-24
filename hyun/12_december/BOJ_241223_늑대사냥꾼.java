package dfs;

import java.io.*;
import java.util.*;

public class BOJ_241223_늑대사냥꾼 {
    static int N,M;
    static char[][] map;
    static int[][] distance;
    static int answer = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int hx, hy; // 현우 위치
    static ArrayList<Node> tree = new ArrayList<>(); // 나무 위치

    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void getDistance(int[][] distance){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int value = Integer.MAX_VALUE;
                for (Node cur : tree) {
                    value = Math.min(value, Math.abs(cur.x - i) + Math.abs(cur.y - j));
                }
                distance[i][j] = value;
            }
        }
    }

    public static void dfs(int x, int y, boolean[][] visited, int minValue){
        //System.out.println(x + " " + y);
        int tmp = Math.min(minValue, distance[x][y]);

        if(map[x][y] == 'J'){
            answer = Math.max(answer, tmp);
            return;
        }

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

            visited[nx][ny] = true;
            dfs(nx,ny, visited, tmp);
            visited[nx][ny] = false;
        }
    }

    public static void simulation(){
        distance = new int[N][M];
        // 나무 위치 저장 ( 최솟값만 저장 )
        getDistance(distance);

        // dfs 수행
        boolean[][] visited = new boolean[N][M];
        visited[hx][hy] = true;
        dfs(hx,hy,visited, distance[hx][hy]);

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);

                if(map[i][j] == 'V'){
                    hx = i;
                    hy = j;
                }
                else if(map[i][j] == '+') tree.add(new Node(i,j));
            }
        }

        simulation();
    }
}
