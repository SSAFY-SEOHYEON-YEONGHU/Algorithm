package twentytwentyfour.december.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main_1417_국회의원선거 {

    static int N;
    static int dasomVotes;
    static PriorityQueue<Integer> otherVotes;

    public static void main(String[] args) throws IOException {
        init();
        calcMinMaesu();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dasomVotes = Integer.parseInt(br.readLine());
        otherVotes = new PriorityQueue<>(Collections.reverseOrder());   // 투표 수 내림차순 정렬하는 우선순위 큐
        for (int i = 1; i < N; i++) otherVotes.offer(Integer.parseInt(br.readLine()));
    }

    static void calcMinMaesu() {
        int maesuCount = 0;
        while (true) {
            if (otherVotes.isEmpty() || otherVotes.peek() < dasomVotes) break;  // 다솜이의 득표수가 가장 많아지면 종료
            int now = otherVotes.poll();
            now--;
            dasomVotes++;
            maesuCount++;
            otherVotes.offer(now);
        }

        System.out.println(maesuCount);
    }

}
