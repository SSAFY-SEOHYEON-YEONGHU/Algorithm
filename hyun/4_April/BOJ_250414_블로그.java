package sliding_window;

import java.io.*;
import java.util.*;

public class BOJ_250414_블로그 {
    static int N,X;
    static int[] input;

    static void slidingWindow(){
        int kijun = 0;
        int cnt = 1;
        for (int i = 0; i < X; i++) {
            kijun += input[i];
        }

        int sum = kijun;
        for (int i = X; i < N; i++) {
            sum += input[i];
            sum -= input[i-X];

            if(sum > kijun){
                kijun = sum;
                cnt = 1;
            }
            else if(sum == kijun) cnt++;
        }

        if(kijun == 0) System.out.println("SAD");
        else{
            System.out.println(kijun);
            System.out.println(cnt);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        input = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        //
        slidingWindow();
    }
}
