import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_8979_올림픽 {

    static class Country implements Comparable<Country> {
        int countryNumber;  // 국가 번호
        int gold;   // 순서대로 금 은 동 개수
        int silver;
        int copper;

        public Country(int countryNumber, int gold, int silver, int copper) {
            this.countryNumber = countryNumber;
            this.gold = gold;
            this.silver = silver;
            this.copper = copper;
        }

        @Override
        public int compareTo(Country c) {
            if (this.gold == c.gold) {  // 금메달 수가 같다면
                if (this.silver == c.silver) return c.copper - this.copper;  // 그 와중에 은메달 수도 같다면 동메달 개수 내림차순
                return c.silver - this.silver;  // 은메달 개수 내림차순
            }
            return  c.gold - this.gold; // 금메달 개수 내림차순
        }

        @Override
        public String toString() { return this.countryNumber + " " + this.gold + " " + this.silver + " " + this.copper; }
    }

    static int N, K;

    public static void main(String[] args) throws IOException {
        PriorityQueue<Country> pq = init();
        System.out.println(calcRate(pq));
    }

    static PriorityQueue<Country> init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        PriorityQueue<Country> pq = new PriorityQueue<>();
        int countryNumber, gold, silver, copper;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            countryNumber = Integer.parseInt(st.nextToken());
            gold = Integer.parseInt(st.nextToken());
            silver = Integer.parseInt(st.nextToken());
            copper = Integer.parseInt(st.nextToken());
            pq.offer(new Country(countryNumber, gold, silver, copper));
        }

        return pq;
    }

    static int calcRate(PriorityQueue<Country> pq) {
        int rate = 1;
        int sameRateCount = 1;  // 공동 등수를 카운트하는 변수

        Country now, next;
        while (!pq.isEmpty()) {
            now = pq.poll();
            next = pq.peek();

            if (now.countryNumber == K) return rate;    // 등수 찾고자 하는 국가 등장 시 등수 반환
            if (now.gold == next.gold && now.silver == next.silver && now.copper == next.copper) {    // 공동 등수 처리할 국가 나오면 공동 등수 카운트 +1
                sameRateCount++;
                continue;
            }
            rate += sameRateCount;
            sameRateCount = 1;
        }

        return 0;
    }

}
