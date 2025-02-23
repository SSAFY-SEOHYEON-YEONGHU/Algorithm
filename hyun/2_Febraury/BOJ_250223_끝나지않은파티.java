package floyd_washall;

import java.io.*;
import java.util.*;
public class BOJ_250223_끝나지않은파티 {
    static int N,M;
    static int[][] input;
    static int[][] time;
    static StringBuilder sb = new StringBuilder();


    public static void getTime(){

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    input[i][j] = Math.min(input[i][j], input[i][k] + input[k][j]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new int[N][N];
        time = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getTime();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            if(input[a][b] <= c) sb.append("Enjoy other party").append("\n");
            else sb.append("Stay here").append("\n");
        }

        System.out.println(sb);
    }
}
