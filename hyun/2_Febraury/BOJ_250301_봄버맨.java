package simulation;

import java.io.*;
import java.util.*;
public class BOJ_250301_봄버맨 {
    static int R,C,N;
    static char[][] input;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static void printArr(int[][] map){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
    static void explode(int time){
        ArrayList<int[]> axios = new ArrayList<>();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != time) continue;
                axios.add(new int[]{i,j});
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                    // 폭파
                    axios.add(new int[]{nx,ny});
                }

            }
        }

        for(int[] cur: axios){
            map[cur[0]][cur[1]] = 0;
        }
    }

    static void putBomb(int time){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 0) map[i][j] = time + 3;
            }
        }
    }
    static void simulation(){

        for (int t = 2; t <= N ; t++) {
            if(t % 2 == 0) putBomb(t);
            else explode(t);
//            printArr(map);
//            System.out.println();
        }

        // 정답 처리
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] == 0) sb.append(".");
                else sb.append("O");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        input = new char[R][C];
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            input[i] = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if(input[i][j] == '.') map[i][j] = 0;
                else map[i][j] = 3;
            }
        }

        simulation();
    }
}
