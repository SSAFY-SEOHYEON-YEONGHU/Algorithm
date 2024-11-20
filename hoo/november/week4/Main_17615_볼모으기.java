package november.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_17615_볼모으기 {

    static int N;
    static String balls;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(Math.min(calcMoveCount('R'), calcMoveCount('B')));
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = br.readLine();
    }

    static int calcMoveCount(char mode) {
        int moveCount = 0;
        if (balls.charAt(0) == mode && balls.charAt(balls.length()-1) == mode) {    // 양쪽 다 같은 움직일 공과 같은 색깔인 경우
            int leftCount = 0, rightCount = 0, totalCount = 0;
            boolean leftStop = false, rightStop = false;
            for (int i = 0; i < balls.length(); i++) {
                if (balls.charAt(i) == mode) {  // 왼쪽에서부터 카운트하는 조건문
                    if (!leftStop) leftCount++;
                    totalCount++;
                }
                if (!rightStop && balls.charAt((balls.length()-1)-i) == mode) rightCount++;

                if (balls.charAt(i) != mode) leftStop = true;
                if (balls.charAt((balls.length()-1)-i) != mode) rightStop = true;
            }
            moveCount = totalCount - Math.max(leftCount, rightCount);
        } else if (balls.charAt(0) == mode && balls.charAt(balls.length()-1) != mode) {    // 왼쪽 끝만 움직일 공과 같은 색깔인 경우
            boolean countStart = false; // 끝에 있는 공의 뭉치가 끝났음을 나타낼 변수
            for (int i = 0; i < balls.length(); i++) {
                if (countStart && balls.charAt(i) == mode) moveCount++;
                else if (!countStart && balls.charAt(i) != mode) countStart = true;   // 뭉치가 끝난 경우
            }
        } else if (balls.charAt(0) != mode && balls.charAt(balls.length()-1) == mode) { // 오른쪽 끝만 움직일 공과 같은 색깔인 경우
            boolean countStart = false;
            for (int i = balls.length()-1; i >= 0; i--) {
                if (countStart && balls.charAt(i) == mode) moveCount++;
                else if (!countStart && balls.charAt(i) != mode) countStart = true;   // 뭉치가 끝난 경우
            }
        } else {    // 양쪽 모두 끝이 아닌 경우, 그냥 공 개수만큼 끝으로 움직여줘야 함
            for (int i = 0; i < balls.length(); i++) if (balls.charAt(i) == mode) moveCount++;
        }

        return moveCount;
    }

}
