package two_pointer;

import java.io.*;
import java.util.*;

public class BOJ_241031_용액 {
    static int N;
    static long[] input;
    static long[] answer;

    public static void simulation(){
        int left = 0;
        int right = N-1;

        while(true){
            long sum = input[left] + input[right];

            if(sum < 0) left++;
            else if(sum > 0) right--;

            if(left < N && right >= 0 && left < right){
                if(Math.abs(answer[1] + answer[0]) > Math.abs(input[right] + input[left]))
                    answer = new long[]{input[left], input[right]};
                if(sum == 0) break;
            }
            else break;
        }

        System.out.println(answer[0] + " " + answer[1]);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(st.nextToken());
        }

        answer = new long[]{input[0], input[N-1]};
        simulation();
    }
}
