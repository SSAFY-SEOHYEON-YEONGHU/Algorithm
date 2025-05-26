package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250526_왕복 {
    static int N;
    static long K;
    static long[] input;

    static void simulation(){
        long before = 0;

        // 오른쪽
        for (int i = 0; i < N; i++) {
            if(before <= K && K < (before + input[i])) {
                System.out.println(i+1);
                return;
            }
            before += input[i];
        }

        // 왼쪽
        for (int i = N-1; i >= 0 ; i--) {
            if(before <= K && K < before + input[i]) {
                System.out.println(i+1);
                return;
            }
            before += input[i];
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        input = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Long.parseLong(st.nextToken());
        }

        //
        simulation();
    }
}
