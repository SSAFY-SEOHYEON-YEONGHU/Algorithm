package implementation;

import java.io.*;
import java.util.*;
public class BOJ_250218_Gazzzua {
    static int N;
    static int[] price;

    static void simulation(){
        int answer = 0;
        int soldPrice = price[N-1];
        for (int i = N-1; i >= 0; i--) {
            if(price[i] < soldPrice) answer += (soldPrice - price[i]);
            else if(price[i] > soldPrice) soldPrice = price[i];

        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        price = new int[N];

        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        simulation();
    }
}
