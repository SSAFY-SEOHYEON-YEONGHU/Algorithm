package twentytwentyfive.january.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_24954_물약구매 {

    static int N;
    static int[] prices;    // 물약들의 가격, 재귀 들어가기 전 원래 가격 저장하기 위해 2차원으로 선언
    static List<List<int[]>> discountList;  // 각 물약 별 할인 정보
    static int minNeedCoins;

    public static void main(String[] args) throws IOException {
        init();
        calcMinNeedCoin(0, 0, new boolean[N]);
        System.out.println(minNeedCoins);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        prices = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) prices[i] = Integer.parseInt(st.nextToken());

        discountList = new ArrayList<>();
        int discount;   // 각 물약의 할인 정보
        for (int i = 0; i < N; i++) {
            discountList.add(new ArrayList<>());
            discount = Integer.parseInt(br.readLine());
            if (discount == 0) continue;

            for (int d = 0; d < discount; d++) {
                st = new StringTokenizer(br.readLine());
                discountList.get(i).add(new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())});
            }
        }
        minNeedCoins = Integer.MAX_VALUE;
    }

    static void calcMinNeedCoin(int boughtCount, int usedCoins, boolean[] isBought) {
        if (boughtCount == N) { // 기저, 모든 물약 다 샀을 경우
            minNeedCoins = Math.min(minNeedCoins, usedCoins);

            return;
        }

        for (int i = 0; i < N; i++) {
            if (isBought[i]) continue;

            adjustPortionPrices(discountList.get(i), 0);
            isBought[i] = true;
            calcMinNeedCoin(boughtCount+1, usedCoins+Math.max(1, prices[i]), isBought);
            isBought[i] = false;
            adjustPortionPrices(discountList.get(i), 1);
        }
    }

    static void adjustPortionPrices(List<int[]> nowPortionDiscountList, int mode) {
        for (int[] d : nowPortionDiscountList) {
            if (mode == 0) prices[d[0]] -= d[1];  // 최소 가격이 1원이므로 둘 중 높은 값 저장    // mode가 0이면 할인, 1이면 원상복구
            else prices[d[0]] += d[1];  // 인덱스 1에 저장돼있던 원래 가격 가져 옴
        }
    }

}
