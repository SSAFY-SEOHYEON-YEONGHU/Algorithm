package twentytwentyfive.february.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20546_기적의매매법 {

    static class Trader { // 준현, 성민 모두를 나타낼 클래스
        int accountAmount; // 계좌 잔액
        int haveStockCount; // 보유한 주식의 수량

        public Trader(int accountAmount, int haveStockCount) {
            this.accountAmount = accountAmount;
            this.haveStockCount = haveStockCount;
        }

        public void buy(int stockPrice) { // 가지고 있는 재산으로 주식 전량 매수
            int canBuyStockCount = accountAmount / stockPrice; // 살 수 있는 주식 개수

            haveStockCount += canBuyStockCount;
            accountAmount -= stockPrice * canBuyStockCount;
        }

        public void sell(int stockPrice) { // 보유 중인 주식 전량 매도
            accountAmount += haveStockCount * stockPrice;
            haveStockCount = 0;
        }
    }

    static int[] stockPrices; // 주어진 주가를 저장할 배열

    public static void main(String[] args) throws IOException {
        int initAccountAmount = init();
        doInvestment(initAccountAmount);
    }

    static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int initAccountAmount = Integer.parseInt(br.readLine());

        stockPrices = new int[14];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 14; i++) stockPrices[i] = Integer.parseInt(st.nextToken());

        return initAccountAmount;
    }

    static void doInvestment(int initAccountAmount) {
        Trader j = new Trader(initAccountAmount, 0);
        Trader s = new Trader(initAccountAmount, 0);

        int continuousIncreasedDay = 0; // 연속으로 주가가 오른 일수 카운트
        int continuousDecreasedDay = 0; // 연속으로 주가가 내린 일수 카운트
        for (int i = 0; i < 14; i++) {
            if (i != 0) { // 우선 전날대비 가격 변동 체크
                if (stockPrices[i] > stockPrices[i-1]) { // 주가 하락한 경우
                    continuousIncreasedDay++;
                    continuousDecreasedDay = 0;
                } else if (stockPrices[i] < stockPrices[i-1]) { // 주가 상승한 경우
                    continuousIncreasedDay = 0;
                    continuousDecreasedDay++;
                } else { // 가격 동결인 경우 두 카운트 모두 초기화
                    continuousIncreasedDay = 0;
                    continuousDecreasedDay = 0;
                }
            }

            // 준헌이가 하는 거래
            if (j.accountAmount >= stockPrices[i]) j.buy(stockPrices[i]);

            // 성민이가 하는 거래
            if (continuousIncreasedDay >= 3 && s.haveStockCount > 0) s.sell(stockPrices[i]); // 3일 연속 가격 상승한 경우
            else if (continuousDecreasedDay >= 3 && s.accountAmount >= stockPrices[i]) s.buy(stockPrices[i]); // 3일 연속 가격 하락한 경우
        }

        if ( (j.accountAmount + j.haveStockCount*stockPrices[13]) > (s.accountAmount + s.haveStockCount*stockPrices[13]) )
            System.out.println("BNP");
        else if ( (j.accountAmount + j.haveStockCount*stockPrices[13]) < (s.accountAmount + s.haveStockCount*stockPrices[13]) ) System.out.println("TIMING");
        else System.out.println("SAMESAME");
    }

}
