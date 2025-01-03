package greedy;

import java.io.*;
import java.util.*;
public class BOJ_241228_주유소 {
    static int N;
    static int[] road;
    static int[] price;

    public static void simulation(){
        long answer = 0;
        long curPrice = price[0];

        for (int i = 0; i < N-1; i++) {
            if(price[i] < curPrice) curPrice = price[i];

            answer += (curPrice * road[i]);
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        road = new int[N-1];
        price = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N-1; i++) {
            road[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }

        simulation();
    }
}
