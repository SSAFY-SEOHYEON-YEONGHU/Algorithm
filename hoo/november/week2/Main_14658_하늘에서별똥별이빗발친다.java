package november.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14658_하늘에서별똥별이빗발친다 {

    static int N, M, L, K;
    static List<int[]> commetList;

    public static void main(String[] args) throws IOException {
        init();
        setTrampoline();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        commetList = new ArrayList<>(); // 별똥별 좌표 저장
        int x, y;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            commetList.add(new int[] {x, y});
        }
    }

    static void setTrampoline() {
        int answer = K;

        int startX, startY, endX, endY; // 트램펄린의 좌하단 꼭지점 좌표
        for (int i = 0; i < commetList.size(); i++) {   // 모든 별똥별의 좌표에 대해, 이 별의 x좌표를 트램펄린의 왼쪽 모서리 x좌표로 두고
            for (int j = 0; j < commetList.size(); j++) {   // 다른 별의 y좌표를 트램펄린의 아래 모서리 y좌표로 둠.
                startX = commetList.get(i)[0];
                startY = commetList.get(j)[1];
                endX = startX + L;
                endY = startY + L;

                int coverCount = 0; // 지금의 트램펄린이 튕겨낼 수 있는 별 개수
                int[] commet;
                for (int k = 0; k < K; k++) {   // 모든 별들에 대해서
                    commet = commetList.get(k);
                    if ( (startX <= commet[0] && commet[0] <= endX)
                            && (startY <= commet[1] && commet[1] <= endY) ) coverCount++;   // 트램펄린 범위 안이면 카운트 + 1
                }
                answer = Math.min(answer, K-coverCount);    // 정답 갱신
            }
        }

        System.out.println(answer);
    }

}
