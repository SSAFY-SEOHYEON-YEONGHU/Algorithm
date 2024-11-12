package november.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1027_고층건물 {

    static int N;
    static int[] buildings;

    public static void main(String[] args) throws IOException {
        init();
        countCanSee();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        buildings = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) buildings[i] = Integer.parseInt(st.nextToken());
    }

    static void countCanSee() {
        int maxCanSee = 0;
        int INF = 1_000_000_000;    // 빌딩 높이 최댓값

        double leftGradient, rightGradient, tempGradient;    // 현재 건물 기준 왼쪽, 오른쪽의 절대값이 가장 큰 기울기 저장하는 변수
        int canSeeCount;
        for (int i = 0; i < N; i++) {
            canSeeCount = 0;
            leftGradient = INF * 1.0; // 왼쪽 기울기 초기값은 기울기 차이 최댓값의 양수값으로
            int xDiff, yDiff;
            for (int j = i-1; j >= 0; j--) {   // 현재 건물 왼쪽에 대해 수행
                xDiff = i-j;
                yDiff = buildings[i]-buildings[j];
                tempGradient = (yDiff*1.0)/(xDiff*1.0);
                if (leftGradient > tempGradient) {    // 왼쪽에 있는 빌딩들은 기울기가 같거나 증가하면 볼 수 없게 되는 것임
                    leftGradient = tempGradient;
                    canSeeCount++;
                }
            }
//            System.out.println(canSeeCount);
            rightGradient = INF * -1.0; // 오른쪽 기울기 초기값은 기울기 차이 최댓값의 음수 값으로
            for (int j = i+1; j < N; j++) { // 현재 건물 오른쪽에 대해 수행
                xDiff = j-i;
                yDiff = buildings[j]-buildings[i];
                tempGradient = (yDiff*1.0)/(xDiff*1.0);
                if (rightGradient < tempGradient) {    // 오른쪽에 있는 빌딩들은 기울기가 같거나 감소하면 볼 수 없게 되는 것임
                    rightGradient = tempGradient;
                    canSeeCount++;
                }
            }
//            System.out.println(i + " " + canSeeCount);
//            System.out.println("==================");
            maxCanSee = Math.max(maxCanSee, canSeeCount);
        }
        System.out.println(maxCanSee);
    }

}
