package stack;

import java.io.*;
import java.util.*;
public class BOJ_250331_병사배치하기 {
    static int N;
    static int[] input;

//    static void stack(){
//        Stack<Integer> s = new Stack<>();
//        int answer = 0;
//
//        for (int i = 0; i < N; i++) {
//            int cur = input[i];
//            if(s.isEmpty()) s.add(cur);
//            else {
//                if(s.peek() > cur) s.add(cur);
//                else if(s.peek() <= cur){
//                    while(!s.isEmpty()){
//                        if(s.peek() <= cur) s.pop();
//                        else break;
//                    }
//                    s.add(cur);
//                }
//            }
//            answer = Math.max(answer, s.size());
//        }
//        System.out.println(N - answer);
//    }

    static void lis(){
        int[] dp = new int[N];
        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i-1; j >= 0 ; j--) {
                if(input[j] <= input[i]) continue;
                dp[i] = Math.max(dp[i], dp[j]);
            }
            dp[i]++;
            answer = Math.max(answer, dp[i]);
        }
        System.out.println(N - answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        input = new int[N];

        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //
        lis();
    }
}
