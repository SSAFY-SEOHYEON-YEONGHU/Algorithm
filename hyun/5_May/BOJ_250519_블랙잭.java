package dfs;

import java.io.*;
import java.util.*;

public class BOJ_250519_블랙잭 {
    static int N,M;
    static int[] input;
    static int answer = -1;

    static void dfs(int idx, int cnt, int sum){
        if(cnt == 3){
            if(sum <= M) answer = Math.max(answer, sum);
            return;
        }

        for (int i = idx; i < N; i++) {
            dfs(i+1, cnt+1, sum + input[i]);
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //
        dfs(0,0,0);
        System.out.println(answer);
    }
}
