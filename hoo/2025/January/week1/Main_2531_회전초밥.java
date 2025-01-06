package twentytwentyfive.january.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2531_회전초밥 {

    static int N, d, k, c;
    static int[] sushiRail;
    static int[] ateSushi;

    public static void main(String[] args) throws IOException {
        int sushiVaries = init();
        countMaxVaries(sushiVaries);
    }

    static int init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        sushiRail = new int[N];
        ateSushi = new int[3_001];    // 손님이 연속해서 먹은 스시들의 개수를 저장하는 배열
        ateSushi[c] = 1;    // 쿠폰으로 받은 스시는 무조건 먹음 처리
        int sushiVaries = 1;    // 먹은 스시의 종류 카운트, 쿠폰으로 받은 스시는 무조건 먹음 처리
        for (int i = 0; i < N; i++) {
            sushiRail[i] = Integer.parseInt(br.readLine());
            if (i < k) {   // 연속한 k개의 스시까지는 슬라이딩 윈도우 초기값 설정해줌
                ateSushi[sushiRail[i]]++; // 먹은 초밥의 번호에 개수 카운트
                if (ateSushi[sushiRail[i]] == 1) sushiVaries++; // 원래 먹지 않았던 종류를 먹었다면, 먹은 스시 종류 +1
            }
        }

        return sushiVaries;
    }

    static void countMaxVaries(int sushiVaries) {
        int maxVaries = sushiVaries;

        int outSushi, inSushi;
        for (int i = 1; i < N; i++) {   // 두 번째 초밥부터 진행하는 슬라이딩 윈도우 적용
            outSushi = sushiRail[i-1];
            inSushi = sushiRail[(i+k-1) % N];

            ateSushi[outSushi]--;
            if (ateSushi[outSushi] == 0) sushiVaries--;
            ateSushi[inSushi]++;
            if (ateSushi[inSushi] == 1) sushiVaries++;
            maxVaries = Math.max(maxVaries, sushiVaries);
        }
        System.out.println(maxVaries);
    }

}
