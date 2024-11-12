package implementation;

import java.io.*;
import java.sql.SQLOutput;
import java.util.*;
public class BOJ_241112_고층건물 {
    static int N;
    static double[] input;

    public static void simulation(){
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if(i==j) continue;

                double giulgi = (input[j] - input[i])/(j-i);
                boolean isAnswer = true;
                int start = Math.min(i,j);
                int end = Math.max(i, j);
                for (int k = start+1; k <= end-1 ; k++) {
                    if(input[k] >= (giulgi * (k - i) + input[i])) {
                        isAnswer =false;
                        break;
                    }

                }
                if(isAnswer) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new double[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        simulation();

    }
}
