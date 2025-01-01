package twentytwentyfour.december.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13305_주유소 {

    static int N;
    static int[] roads; // 도시를 잇는 도로의 길이
    static int[] prices;    // 해당 도시 주유소의 가격
    static int totalDistance; // 총 이동 거리

    public static void main(String[] args) throws IOException {
        init();
        calcMinPrice();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        roads = new int[N-1];
        prices = new int[N];
        totalDistance = 0;
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (i != N-1) {
                roads[i] = Integer.parseInt(st1.nextToken());
                totalDistance += roads[i];
            }
            prices[i] = Integer.parseInt(st2.nextToken());
        }
    }

    static void calcMinPrice() {
        // 더 싼 주유소 나오기 전까지는 현재 저장한 가장 싼 주유소 가격으로 가는 게 맞음
        int nowMinPrice = prices[0];    // 현재 들린 주유소 중 가장 싼 가격의 주유소, 처음 값은 무조건 첫 도시의 주유소 가격으로
        long totalPrice = 0; // 현재까지 쓴 비용 총합
        for (int i = 0; i < N-1; i++) { // 도로 개수만큼 반복
            totalPrice += (long) nowMinPrice * (long) roads[i];
            nowMinPrice = Math.min(nowMinPrice, prices[i+1]); // 주유소 가격 갱신
        }

        System.out.println(totalPrice);
    }

}
