package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250605_빙고 {
    static int[][] axios = new int[30][2];
    static int[] row = new int[5];
    static int[] col = new int[5];
    static int[] cross = new int[2];
    static int bingo = 0;

    static void checkBingo(int num){
        int x = axios[num][0];
        int y = axios[num][1];

        row[x]++;
        if(row[x] == 5) bingo++;

        col[y]++;
        if(col[y] == 5) bingo++;

        // 대각선
        if(x+y == 4){
            cross[0]++;
            if(cross[0] == 5) bingo++;
        }

        if(x == y){
            cross[1]++;
            if(cross[1] == 5) bingo++;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int cur = Integer.parseInt(st.nextToken());
                axios[cur][0] = i;
                axios[cur][1] = j;
            }
        }

        int turn = 0;
        for (int i = 0; i < 5; i++) { // 사회자
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                turn++;
                int cur = Integer.parseInt(st.nextToken());
                checkBingo(cur);

                if(bingo >= 3){
                    System.out.println(turn);
                    return;
                }
            }
        }

    }
}
