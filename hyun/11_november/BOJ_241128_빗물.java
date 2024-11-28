package implementation;

import java.io.*;
import java.util.*;
public class BOJ_241128_빗물 {
    static int H,W;
    static int[][] input;

    static void simulation(){
        int answer = 0;

        for (int i = 0; i < H; i++) {
            int key = 0;
            int sum = 0;
            for (int j = 0; j < W; j++) {
                if(input[i][j] == 1){
                    key++;
                    if(key == 2) {
                        answer += sum;
                        key = 1;
                        sum = 0;
                    }
                }
                else{
                    if(key==1) sum++;
                }
            }
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        input = new int[H][W];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            int num = Integer.parseInt(st.nextToken());

            for (int j = 0; j < num; j++) {
                input[j][i] = 1;
            }
        }

        simulation();
    }
}

