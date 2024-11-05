package november;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_11501_주식 {

    static int N;
    static int[] prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            init(br);
            sb.append(calcGain()).append("\n");
        }
        System.out.println(sb);
    }

    static void init(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());
        prices = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) prices[i] = Integer.parseInt(st.nextToken());
    }

    static long calcGain() {
        long gain = 0;

        int[] biggerPrices = new int[N];    // 각 날짜 별 뒷날 중 가장 높은 값
        biggerPrices[N-1] = prices[N-1];    // 가장 마지막 날은 자기가 가장 비싼 값임
        for (int i = N-2; i >= 0; i--) {
            biggerPrices[i] = Math.max(biggerPrices[i+1], prices[i]);
        }
        for (int i = 0; i < N; i++) gain += ((long) biggerPrices[i] - (long) prices[i]);

        return gain;
    }

}
