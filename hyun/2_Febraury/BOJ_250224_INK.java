package simulation;

import java.io.*;
import java.util.*;
public class BOJ_250224_INK {
    static int I,N,K;
    static String ink;
    static char[][] map;
    static String command;

    static int[] cur = new int[2];
    static int curInk = 0;
    static int jumpIdx = 0;

    static int[] dx = {-1,1,0,0,-1,-1,1,1};
    static int[] dy = {0,0,-1,1,-1,1,-1,1};

    static boolean isRange(int nx, int ny){
        if(nx < 0 || nx >= N || ny < 0 || ny >= N) return false;
        return true;
    }

    static void move(char ch){
        int nx = cur[0];
        int ny = cur[1];

        if(ch == 'U') {
            nx = cur[0] - 1;
            ny = cur[1];

        }
        else if(ch == 'D') {
            nx = cur[0] + 1;
            ny = cur[1];
        }
        else if(ch == 'L') {
            nx = cur[0];
            ny = cur[1] - 1;
        }
        else if(ch == 'R') {
            nx = cur[0];
            ny = cur[1] + 1;
        }

        if(isRange(nx,ny) && map[nx][ny] == '.') {
            cur[0] = nx;
            cur[1] = ny;
        }
    }

    static void jumping(){
        char color = ink.charAt(jumpIdx);
        jumpIdx = (jumpIdx + 1) % I;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if((Math.abs(cur[0] - i) + Math.abs(cur[1] - j)) <= curInk && map[i][j] != '.')
                    map[i][j] = color;
            }
        }

        curInk = 0;
    }
    static void simulation(){
        for (int i = 0; i < K; i++) {
            char ch = command.charAt(i);

            if(ch == 'j') curInk ++;
            else if(ch == 'J') jumping();
            else move(ch);

        }

        map[cur[0]][cur[1]] = '@';

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        I = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        ink = br.readLine();


        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if(map[i][j] == '@') {
                    cur = new int[]{i,j};
                    map[i][j] = '.';
                }
            }
        }

        command = br.readLine();

        simulation();


    }
}
