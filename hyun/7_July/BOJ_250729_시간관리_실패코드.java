package sort;

import java.io.*;
import java.util.*;

public class BOJ_250729_시간관리 {
    static int N;
    static int[][] input;

    static void simulation(){
        Arrays.sort(input, (a,b) -> a[1] - b[1]);

        int[][] range = new int[N][2];
        range[0][1] = input[0][1];
        range[0][0] = input[0][1] - input[0][0];
        if(range[0][0] < 0){
            System.out.println(-1);
            return;
        }

        for (int i = 1; i < N; i++) {
            if(range[i-1][1] + input[i][0] > input[i][1]) {
                range[i][0] = input[i][0];
                range[i][1] = input[i][1];
                for (int j = i; j > 0; j--) {
                    // 무조건 소요시간만큼 앞으로 당기는 것이 아니라
                    // 최소로 당겨야 할 만큼 당겨야댐
                    int diff = Math.abs(Math.abs(range[j-1][1] - range[j][1]) - input[j][0]);
                    range[j-1][0] -= diff;
                    range[j-1][1] -= diff;
                }
                range[i][0] = range[i-1][1];
            }
            else {
                range[i][0] = input[i - 1][1];
                range[i][1] = range[i][0] + input[i][0];
            }
        }
        if(range[0][0] < 0) System.out.println(-1);
        else System.out.println(range[0][0]);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        input = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        //
        simulation();
    }
}
