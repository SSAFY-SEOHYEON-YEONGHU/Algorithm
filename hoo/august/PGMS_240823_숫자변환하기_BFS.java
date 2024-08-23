package august;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class PGMS_240823_숫자변환하기 {

    public int solution(int x, int y, int n) {
        int answer = calcCase(x, y, n);

        return answer;
    }

    int calcCase(int x, int y, int n) {
        int[] isMaked = new int[y+1];   // y까지 숫자를 만들며 들었던 최소 횟수를 저장
        Arrays.fill(isMaked, Integer.MAX_VALUE);    // 횟수는 우선 최댓값으로 초기화

        Queue<int[]> q = new ArrayDeque<>();    // 0번 인덱스에는 숫자, 1번 인덱스에는 횟수 저장
        q.offer(new int[] {x, 0});
        isMaked[x] = 0; // x를 만드는 데 필요한 연산은 없으므로 0으로 갱신

        int nowNumber;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            nowNumber = now[0];
            if (isMaked[nowNumber] < now[1]) continue;  // 이미 더 적은 횟수로 해당 숫자를 만들었으면 건너 뜀

            for (int i = 0; i < 3; i++) {
                int makedNumber = makeNextNumber(i, n, nowNumber);
                if (makedNumber > y || isMaked[makedNumber] < now[1] + 1) continue;    // 이미 더 적은 횟수로 해당 숫자를 만들었으면 건너 뜀
                q.offer(new int[] {makedNumber, now[1] + 1});
                isMaked[makedNumber] = now[1] + 1;
            }
        }

        return (isMaked[y] == Integer.MAX_VALUE)? -1:isMaked[y];    // 숫자를 만들 수 없었으면 -1, 만들 수 있으면 저장된 최솟값 출력
    }

    int makeNextNumber(int mode, int n, int nowNumber) {
        int makedNumber = nowNumber;
        switch(mode) {
            case 0:
                makedNumber += n;
                break;
            case 1:
                makedNumber *= 2;
                break;
            case 2:
                makedNumber *= 3;
                break;
        }

        return makedNumber;
    }

}
