package floyd_washall;

import java.io.*;
import java.util.*;
public class BOJ_241224_여행가자 {
    static int N,M;
    static int[][] input;

    public static void floydWashall(){

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(input[i][j] == 1) continue;
                    if(input[i][k] * input[k][j] == 1) {
                        input[i][j] = 1;
                        input[j][i] = 1;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            input[i][i] = 1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        input = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWashall();

        st = new StringTokenizer(br.readLine());
        int[] city = new int[M];
        for (int i = 0; i < M; i++) {
            city[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        boolean isAnswer = true;
        for (int i = 0; i < M-1; i++) {
            if(input[city[i]][city[i+1]] == 0) {
                isAnswer = false;
                break;
            }
        }

        if(isAnswer) System.out.println("YES");
        else System.out.println("NO");
    }
}
