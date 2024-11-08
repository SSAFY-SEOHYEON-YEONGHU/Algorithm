import java.util.*;

class Solution {

    public int solution(int n, int[][] results) {
        int answer = judgeRanking(n, results);

        return answer;
    }

    int judgeRanking(int n, int[][] results) {
        int[][] rankingArr = new int[n+1][n+1]; // 각 선수의 부모노드까지 거리를 저장할 배열
        int[][] reverseRankingArr = new int[n+1][n+1];  // 각 선수의 자식 노드까지 거리를 저장할 배열
        int INF = 100 * 100 + 1;    // 거리의 최댓값 : n * n
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i != j) {
                    rankingArr[i][j] = INF;  // 자기 자신 제외 최댓값으로 초기화
                    reverseRankingArr[i][j] = INF;
                }
            }
        }
        int from, to;   // from: 이긴 사람, to: 진 사람
        for (int i = 0; i < results.length; i++) {  // 일단 주어진 승부에 따라 승부 저장
            from = results[i][0];
            to = results[i][1];
            rankingArr[to][from] = 1;   // 진 사람에게 결과를 저장함, 이러면 0이 아닌 값은 부모 노드까지의 거리를 표시하게 됨
            reverseRankingArr[from][to] = 1;    // 이긴 사람에게 결과를 저장함, 이러면 0이 아닌 값은 자식 노드까지의 거리를 표시하게 됨
        }

        for (int k = 1; k <= n; k++) {  // 플로이드 워셜 알고리즘 이용하여 유향 그래프의 각 노드간 거리 계산. k는 경유지
            for (int i = 1; i <= n; i++) {  // i는 출발지
                for (int j = 1; j <= n; j++) {  // j는 도착지
                    rankingArr[i][j] = Math.min(rankingArr[i][j], rankingArr[i][k] + rankingArr[k][j]);
                    reverseRankingArr[i][j] = Math.min(reverseRankingArr[i][j], reverseRankingArr[i][k] + reverseRankingArr[k][j]); // 바로 가는 경로와 경유지 거쳐서 가는 경로 중 작은 값을 저장
                }
            }
        }

        int answer = 0;
        int[] playerRank, playerReverseRank;
        for (int i = 1; i <= n; i++) {  // 1번 선수부터 시작
            playerRank = rankingArr[i];
            playerReverseRank = reverseRankingArr[i];
            int connectedCount = 0; // 부모, 자식 개수 합
            for (int j = 1; j <= n; j++) {
                if (j == i) continue;
                if (playerRank[j] != INF || playerReverseRank[j] != INF) connectedCount++;  // 부모나 자식이면 카운트 +1
            }
            if (connectedCount == n-1) answer++;    // 부모 선수 + 자식 선수 값이 n-1이면 순위를 판별할 수 있으므로 정답+1
        }


        return answer;
    }

}