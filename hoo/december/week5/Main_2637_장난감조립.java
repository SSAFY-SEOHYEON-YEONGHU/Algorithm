package december.week5;

import com.sun.security.jgss.GSSUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2637_장난감조립 {

    static class Parts {    // 중간 부품을 표현하는 클래스
        int partsNumber;
        int prevParts;  // 해당 중간 부품 조립에 필요한 부품 번호
        int needs;

        public Parts(int partsNumber, int prevParts, int needs) {
            this.partsNumber = partsNumber;
            this.prevParts = prevParts;
            this.needs = needs;
        }

        @Override
        public String toString() { return this.partsNumber + " " + this.prevParts + " " + this.needs; }
    }

    static int N, M;    // N : 완제품 번호
    static List<List<Parts>> partsList; // 해당 부품을 필요로 하는 부품을 담은 리스트의 리스트
    static int[] needParts; // 해당 부품 조립에 선행되는 부품 개수 저장한 배열

    public static void main(String[] args) throws IOException {
        init();
        calcNeeds();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        partsList = new ArrayList<>();
        for (int i = 0; i <= N; i++) partsList.add(new ArrayList<>());  // 일단 부품 개수만큼 리스트 삽입
        needParts = new int[N+1];

        StringTokenizer st;
        int partsNumber, prevParts, needs;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            partsNumber = Integer.parseInt(st.nextToken());
            prevParts = Integer.parseInt(st.nextToken());
            needs = Integer.parseInt(st.nextToken());
            partsList.get(prevParts).add(new Parts(partsNumber, prevParts, needs));    // prevParts를 필요로 하는 부품으로 추가
            needParts[partsNumber] += needs;    // 현재 부품 만드는 데 필요한 부품 개수 더해줌
        }
    }

    static void calcNeeds() {
        int[][] usedPartsArr = new int[N+1][N+1];  // 각 중간 부품에 사용된 기본 부품 개수 저장하는 배열
        Queue<Parts> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (needParts[i] == 0) {
                q.offer(new Parts(i, -1, 0));   // 우선 기본 부품들을 큐에 삽입
                List<Parts> nextPartsList = partsList.get(i);
                for (Parts nextParts : nextPartsList) {
                    usedPartsArr[nextParts.partsNumber][i] = nextParts.needs;   // 현재 부품(기본 부품)을 필요로 하는 부품에 필요 개수 표시
                }
            }
        }

        Parts now;
        while (!q.isEmpty()) {
            now = q.poll(); // 먼저 필요한 부품들을 해결한 부품

            List<Parts> nextPartsList = partsList.get(now.partsNumber); // 현재 부품을 선재 부품으로 하는 부품들의 리스트
            for (Parts nextParts : nextPartsList) {
                needParts[nextParts.partsNumber] -= nextParts.needs;

                for (int i = 1; i <= N; i++) usedPartsArr[nextParts.partsNumber][i] += usedPartsArr[now.partsNumber][i] * (nextParts.needs);   // 현재 부품을 필요로 하는 부품에, (필요한 현재 부품 개수) * (현재 부품에 드는 기본 부품 수) 필요 개수 더해주기
                if (needParts[nextParts.partsNumber] == 0) q.offer(nextParts);    // 해당 부품 조립 완료 시, 필요했던 부품들 개수 카운트 해주기
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) if (usedPartsArr[N][i] != 0) sb.append(i + " " + usedPartsArr[N][i]).append("\n");
        System.out.println(sb);
    }

}
