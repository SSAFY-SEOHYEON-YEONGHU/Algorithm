package greedy;

import java.io.*;
import java.util.*;
public class BOJ_241119_볼모으기 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        char[] balls = br.readLine().toCharArray();
        int bCnt = 0;
        int rCnt= 0;

        for(int i = 0; i<N; i++){
            if(balls[i] == 'B') bCnt ++;
            else rCnt++;
        }

        int result = N;
        int count = 0;

        //R을 왼쪽으로
        for(int i = 0; i<N; i++){
            if(balls[i] == 'R'){
                count++;
            } else break;
        }
        result = Math.min(result, rCnt-count);

        //R을 오른쪽으로
        count = 0;
        for(int i = N-1; i>=0; i--){
            if(balls[i] == 'R') count++;
            else break;
        }
        result = Math.min(result, rCnt-count);

        //B를 왼쪽으로
        count = 0;
        for(int i = 0; i<N; i++){
            if(balls[i] == 'B') count++;
            else break;
        }
        result = Math.min(result, bCnt-count);

        //B를 오른쪽으로
        count = 0;
        for(int i = N-1; i>=0; i--){
            if(balls[i] == 'B') count++;
            else break;
        }
        result = Math.min(result, bCnt-count);

        System.out.println(result);
    }
}
