package twentytwentyfive.february.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_250217_Gazzzua {

    static int N;
    static int[] prices;

    public static void main(String[] args) throws IOException {
        init();
        calcMaxProfit();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        prices = new int[N];
        for (int i = 0; i < N; i++) prices[N-1-i] = Integer.parseInt(st.nextToken());
    }

    static void calcMaxProfit() {
        int profit = 0; // 얻은 이익
        int maxPrice = prices[0];
        for (int i = 0; i < N; i++) {
            if (prices[i] > maxPrice) maxPrice = prices[i]; // 더 높은 값 나오면 갱신
            else profit += (maxPrice) - prices[i]; // 가장 높은 값과의 차이만큼 이윤 발생
        }

        System.out.println(profit);
    }

}
