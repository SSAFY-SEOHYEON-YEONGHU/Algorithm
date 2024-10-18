package october.week3;

import java.util.Arrays;
import java.util.Comparator;

public class PGMS_241018_단속카메라 {

    public int solution(int[][] routes) {
        int answer = findSpot(routes);

        return answer;
    }

    int findSpot(int[][] routes) {
        Arrays.sort(routes, new Comparator<int[]>() {   // 나간 지점 오름차순, 같을 경우 진입 지점 오름차순 정렬
            @Override
            public int compare(int[] i1, int[] i2) {
                if (i1[1] == i2[1]) return i1[0] - i2[0];

                return i1[1] - i2[1];
            }
        });

        int spot = routes[0][1];    // 첫 지점은 첫 차의 나가는 지점으로 두고 시작
        int spotCount = 1;
        for (int i = 1; i < routes.length; i++) {
            if (spot >= routes[i][0]) {  // 이전에 카메라를 설치한 곳이 이번 차의 진입 지점에 걸친다면
                continue;
            } else {    // 이번 차의 진입 지점에 걸치지 않는다면
                spot = routes[i][1];
                spotCount++;
            }
        }

        return spotCount;
    }

}
