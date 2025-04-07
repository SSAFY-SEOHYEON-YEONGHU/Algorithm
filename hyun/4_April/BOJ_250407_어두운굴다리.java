package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250407_어두운굴다리 {
    static int N,M;
    static int[] map;

    static void simulation(){
        int distance = map[0] - 0; // 처음

        for (int i = 0; i < M-1; i++) {
            int cur = (map[i+1] - map[i]) / 2;
            if(((map[i+1] - map[i]) % 2) != 0) cur++;

            distance = Math.max(distance, cur);
        }
        distance = Math.max(distance, N - map[M-1]);
        System.out.println(distance);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        map = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        //
        simulation();
    }
}
