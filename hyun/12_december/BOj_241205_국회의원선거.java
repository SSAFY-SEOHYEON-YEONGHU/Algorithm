package implementation;

import java.io.*;
import java.util.*;
public class BOj_241205_국회의원선거 {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        int num = 0;
        int vote = input[0];
        int answer=0;

        while(true){

            for (int i = 0; i < N; i++) {
                if(input[i] >= vote){
                    num = i;
                    vote = input[i];
                }
            }

            if(num == 0) break;

            input[0]++;
            input[num]--;
            answer++;

            num = input[0];
            vote = -1;
        }

        System.out.println(answer);
    }
}
