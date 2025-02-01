package greedy;

import java.io.*;
import java.util.*;
public class BOJ_250128_줄세우기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        //
        int[] dp = new int[N];
        int lis = 0;
        for (int i = 0; i < N; i++) {
            int cur = input[i];
//            System.out.println("현재 " + cur);
            dp[i] = 1;
            for (int j = i-1; j >=0 ; j--) {
                if(input[j] < cur && dp[j] + 1 > dp[i]) {
//                    System.out.println(input[j]);
                    dp[i] = dp[j] + 1;
                }
            }
//            System.out.println(dp[i]);
            lis = Math.max(lis, dp[i]);
        }

        System.out.println(N - lis);


    }
}
