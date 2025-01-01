package august.week5;

public class PGMS_240830_등굣길 {

    public int solution(int m, int n, int[][] puddles) {
        int answer = calcRoutes(m, n, puddles);

        return answer;
    }

    int calcRoutes(int m, int n, int[][] puddles) {
        int MAX_DIST = 10_001;
        int[][][] routeDist = new int[n+1][m+1][2]; // 3차원의 0번 인덱스에는 최소 거리 저장, 1번 인덱스에는 횟수 저

        for (int i = 1; i <= n; i++) {   // 우선 각 칸까지 걸리는 거리 최대 거리로 초기화
            for (int j = 1; j <= m; j++) routeDist[i][j][0] = MAX_DIST;
        }
        for (int i = 0; i < puddles.length; i++) routeDist[puddles[i][1]][puddles[i][0]][0] = -1;   // 물 웅덩이는 -1로 표시
        if (routeDist[1][2][0] == -1 && routeDist[2][1][0] == -1) return 0; // 집 주변이 다 웅덩이인 경우 학교를 못 감

        routeDist[1][1][0] = 0; // 출발지 거리는 0
        routeDist[1][1][1] = 1; // 출발지의 경우는 1
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (routeDist[i][j][0] != MAX_DIST || routeDist[i][j][0] == -1) continue;   // 이미 거리를 파악한 칸, 물 웅덩이 칸은 건너 뜀
                int nowDist = MAX_DIST;
                int nowRouteCount = 0;  // 현재 칸에 왼쪽, 윗쪽에서 올 수 있는 경우의 수
                int leftDist = MAX_DIST, upperDist = MAX_DIST;    // 왼쪽칸까지 오는 데 걸린 거리, 윗쪽칸까지 오는 데 걸린 거리
                if (!isOutedOrPuddle(m, n, routeDist, i, j-1)) leftDist = routeDist[i][j-1][0];
                if (!isOutedOrPuddle(m, n, routeDist, i-1, j)) upperDist = routeDist[i-1][j][0];

                if (leftDist == upperDist && leftDist != MAX_DIST) {   // 두 방향 모두에서 올 수 있는 경우, 근데 두 방향 모두 최대거리인 경우는 도달하지 못하는 경우임
                    nowDist = routeDist[i][j-1][0] + 1;
                    nowRouteCount = (routeDist[i][j-1][1] + routeDist[i-1][j][1]) % 1_000_000_007;
                }
                else if (leftDist < upperDist) {    // 왼쪽에서 오는 게 더 빠른 경우
                    nowDist = routeDist[i][j-1][0] + 1;
                    nowRouteCount = routeDist[i][j-1][1];
                } else if (leftDist > upperDist) {
                    nowDist = routeDist[i-1][j][0] + 1;
                    nowRouteCount = routeDist[i-1][j][1];
                }

                routeDist[i][j][0] = nowDist;
                routeDist[i][j][1] = nowRouteCount;
            }
        }

        return routeDist[n][m][1];
    }

    boolean isOutedOrPuddle(int m, int n, int[][][] routeDist, int row, int col) {
        if ((1 <= row && row <= n) && (1 <= col && col <= m) && routeDist[row][col][0] != -1) return false;
        return true;
    }

}
