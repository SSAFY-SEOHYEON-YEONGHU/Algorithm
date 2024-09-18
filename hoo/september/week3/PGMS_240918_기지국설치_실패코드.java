package september.week3;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_240918_기지국설치_실패코드 {

    public int solution(int n, int[] stations, int w) {
        int answer = construct5G(n, stations, w);

        return answer;
    }

    int construct5G(int n, int[] stations, int w) {
        int constructCount = 0;

        int markCount = 0;
        boolean[] mark = makeMark(n, stations, w);
        for (int i = 1; i <= n; i++) if (mark[i]) markCount++;
        Queue<Integer> q = makeInitStationsQueue(n, stations, w);

        int nowStation;
        int stationLeft, stationRight;
        while (!q.isEmpty() && markCount < n) {
            nowStation = q.poll();
            stationLeft = nowStation - (2*w+1);
            stationRight = nowStation + (2*w+1);
            constructCount++;
            for (int i = Math.max(1, nowStation - w); i <= Math.min(n, nowStation + w); i++) {
                if (!mark[i]) { // 커버되는 범위가 아니면 커버되게끔 표시
                    mark[i] = true;
                    markCount++;
                }
            }
            if (stationLeft >= 1 && !mark[stationLeft]) q.offer(stationLeft);
            if (stationRight <= n && !mark[stationRight]) q.offer(stationRight);
        }
        constructCount += ((n - markCount)%w == 0)? (n-markCount)/w:(n-markCount)/w+1;
        // System.out.println(Arrays.toString(mark));
        // System.out.println(markCount);
        // System.out.println(constructCount);

        return constructCount;
    }

    boolean[] makeMark(int n, int[] stations, int w) {
        boolean[] mark = new boolean[n+1];  // 전파가 커버하는 영역을 표시하는 배열
        for (int i = 0; i < stations.length; i++) {
            for (int j = Math.max(1, stations[i] - w); j <= Math.min(n, stations[i] + w); j++) mark[j] = true;
        }


        return mark;
    }

    Queue<Integer> makeInitStationsQueue(int n, int[] stations, int w) {
        Queue<Integer> q = new ArrayDeque<>();  // 설치된 기지국의 범위를 벗어나는 왼쪽, 오른쪽에 기지국을 설치하는 것을 형상화하기 위해 큐를 이용
        int stationLeft, stationRight;
        for (int i = 0; i < stations.length; i++) {
            stationLeft = stations[i] - (2*w+1);
            stationRight = stations[i] + (2*w+1);
            if (stationLeft >= 1) q.offer(stationLeft);
            if (stationRight <= n) q.offer(stationRight);
        }

        return q;
    }

}
