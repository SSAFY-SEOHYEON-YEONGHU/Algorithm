package implementation;

// dp
// 점화식 dp[x + (3가지 연산)] = Math.min(dp[x] + 1, dp[x + (3가지 연산)])

import java.io.*;
import java.util.*;

public class PGMS_154538_숫자변환하기 {
    public int solution(int x, int y, int n) {
        int[] num = new int[y+1];
        Arrays.fill(num,Integer.MAX_VALUE);

        num[x] = 0;
        for(int i=x; i<=y; i++){
            if(num[i] == Integer.MAX_VALUE) continue;

            if(i + n <= y) num[i+n] = Math.min(num[i+n], num[i] + 1);
            if(i * 2 <= y) num[i * 2] = Math.min(num[i * 2], num[i] + 1);
            if(i * 3 <= y) num[i * 3] = Math.min(num[i * 3], num[i] + 1);
        }

        // 입력 x,y 가 같을 수 있음
        if(x==y) return 0;
        else if(num[y] == Integer.MAX_VALUE) return -1;
        return num[y];
    }
}