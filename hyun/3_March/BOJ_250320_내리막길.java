package dfs;

import java.io.*;
import java.util.*;

/*
기초 dfs 로 풀었을때 각 지점에서 4방향으로 모두 가주어 4^500 시간초과남
길이 나있는 지점은 그 지점에 방문했을때 바로 백트랙킹 되도록 구성
하지만 여전히 시간초과 -> 질문 게시판 확인
경로가 전혀 없는 입력이 주어질 경우 즉, 길이 나있지 않은 지점에도 백트랙킹 되도록 구성해야함

따라서 각 지점에서 4방향 모두 경로가 없다면 -1 표시해주고 백트랙킹 !
 */

public class BOJ_250320_내리막길 {
    static int N,M;
    static int[][] input;
    static int[][] dp;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static int dfs(int x, int y){
        if(dp[x][y] != 0) return dp[x][y];

        int cnt = 0;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];

            if(nx < 0 || nx >= M || ny < 0 || ny >= N || input[nx][ny] >= input[x][y]) {
                cnt++;
                continue;
            }

            int su = dfs(nx, ny);
            if(su <= 0) cnt++;
            else dp[x][y] += su;

        }

        if(cnt == 4) dp[x][y] = -1;

        return dp[x][y];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        input = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //
        dp[M-1][N-1] = 1;
        int result = dfs(0,0);
        if(result <= 0) System.out.println(0);
        else System.out.println(result);
    }
}
