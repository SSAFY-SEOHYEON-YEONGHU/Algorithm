package stack;

import java.io.*;
import java.util.*;
public class BOJ_241105_주식 {
    static int T,N;
    static int[] input;

    public static long simulation(){
        long answer = 0;
        int maxValue = input[N-1];

        for (int i = N-2; i >= 0; i--) {
            if(input[i] < maxValue) answer += (maxValue - input[i]);
            else if(input[i] > maxValue) maxValue = input[i];
        }

        return answer;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            input = new int[N];
            for (int i = 0; i < N; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            sb.append(simulation()).append("\n");
        }

        System.out.println(sb);
    }
}
