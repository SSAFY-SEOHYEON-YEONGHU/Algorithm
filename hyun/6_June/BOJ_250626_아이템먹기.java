package dp;

import java.io.*;
import java.util.*;

public class BOJ_250626_아이템먹기 {
    static int N,M;
    static int item, block;
    static int[][] map;

    static int[] dx = {1,0};
    static int[] dy = {0,1};

    static class Node{
        int x,y,star;
        Node(int x, int y, int star){
            this.x = x;
            this.y = y;
            this.star = star;
        }
    }

    static void simulation(){
        int[][][] dp = new int[N+1][M+1][item+1];
        if(map[1][1] == 1) dp[1][1][1] = 1;
        else dp[1][1][0] = 1;

        for (int i = 1; i <= N ; i++) {
            for (int j = 1; j <= M ; j++) {
                if(map[i][j] == 4) continue;
                for (int k = 0; k < 2; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if(j==M && k==1) continue;
                    if(i==N && k==0) continue;

                    if(map[nx][ny] == 0){
                        for (int a = 0; a <= item ; a++) {
                            dp[nx][ny][a] += dp[i][j][a];
                        }
                    }
                    else{
                        for (int a = 0; a < item ; a++) {
                            dp[nx][ny][a+1] += dp[i][j][a];
                        }
                    }

                }
            }
        }

        System.out.println(dp[N][M][item]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        item = Integer.parseInt(st.nextToken());
        block = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for (int i = 0; i < item; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        for (int i = 0; i < block; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 4;
        }

        //
        simulation();
    }
}
