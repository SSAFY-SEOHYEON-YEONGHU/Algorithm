package dfs;

import java.io.*;
import java.util.*;

public class BOJ_250528_홀수홀릭호석 {
    static int N;
    static int minAnswer = Integer.MAX_VALUE;
    static int maxAnswer = Integer.MIN_VALUE;

    static int getOdd(String num){
        int cnt = 0;
        for (int i = 0; i < num.length(); i++) {
            if((num.charAt(i) - '0') % 2 != 0) cnt++;
        }

        return cnt;
    }
    static void dfs(int num, int sum){
        String curNum = String.valueOf(num);
        int cnt = getOdd(curNum);

        if(curNum.length() == 1){
            minAnswer = Math.min(minAnswer, sum + cnt);
            maxAnswer = Math.max(maxAnswer, sum + cnt);
            return;
        }

        else if(curNum.length() == 2){
            int a = curNum.charAt(0) - '0';
            int b = curNum.charAt(1) - '0';
            dfs(a+b, sum + cnt);
        }

        else{
            int i,j = 0;
            for (i = 0; i < curNum.length()-1; i++) {
                int a = Integer.parseInt(curNum.substring(0,i+1));
                for (j = i+1; j < curNum.length()-1; j++) {
                    int b = Integer.parseInt(curNum.substring(i+1,j+1));
                    int c = Integer.parseInt(curNum.substring(j+1,curNum.length()));
                    dfs(a+b+c, sum + cnt);
                }
            }
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dfs(N,0);
        System.out.println(minAnswer + " " + maxAnswer);
    }
}
