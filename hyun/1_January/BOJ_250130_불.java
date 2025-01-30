package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250130_불 {
    static int R,C;
    static char[][] input;
    static Node jihun;
    static int[][] fireMap;
    static PriorityQueue<Node> fire = new PriorityQueue<>((n1,n2) -> n1.time - n2.time);
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class Node{
        int x,y;
        int time;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        Node(int x, int y, int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static void spreadFire(int[][] fireMap){

        while(!fire.isEmpty()){
            Node cur = fire.poll();

//            for (int i = 0; i < R; i++) {
//                for (int j = 0; j < C; j++) {
//                    System.out.print(fireMap[i][j]);
//                }
//                System.out.println();
//            }
//            System.out.println("======");

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C || input[nx][ny] == '#') continue;
                if(fireMap[nx][ny] != 0) continue;

                fire.add(new Node(nx,ny, cur.time+1));
                fireMap[nx][ny] = cur.time + 1;
            }
        }
    }

    static int moveJihun(int[][] fireMap){
        Queue<Node> q = new ArrayDeque<>();
        int[][] visited = new int[R][C];

        q.add(jihun);
        visited[jihun.x][jihun.y] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= R || ny < 0 || ny >= C){ // 범위 넘어감
                    return visited[cur.x][cur.y];
                }

                if(input[nx][ny] == '#' || visited[nx][ny] != 0) continue;
                if(fireMap[nx][ny] == 0 || (fireMap[nx][ny] > 0 && fireMap[nx][ny] > visited[cur.x][cur.y] + 1)){
                    q.add(new Node(nx,ny));
                    visited[nx][ny] = visited[cur.x][cur.y] + 1;
                }

                if(fireMap[nx][ny] != 0) continue;
            }
        }

        return -1;
    }

    static void simulation(){

        // 1. 불 시간대 표시
        spreadFire(fireMap);

//        for (int i = 0; i < R; i++) {
//            for (int j = 0; j < C; j++) {
//                System.out.print(fireMap[i][j]);
//            }
//            System.out.println();
//        }

        // 2. jihun 가능한지 보기
        int result = moveJihun(fireMap);

        if(result == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(result);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        input = new char[R][C];
        fireMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            input[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(input[i][j] == 'J') jihun = new Node(i,j);
                else if(input[i][j] == 'F') {
                    fire.add(new Node(i,j,1));
                    fireMap[i][j] = 1;
                }
            }
        }

        simulation();
    }
}
