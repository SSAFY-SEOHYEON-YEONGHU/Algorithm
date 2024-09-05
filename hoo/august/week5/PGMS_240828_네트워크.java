package august.week5;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_240828_네트워크 {

    int networkNumber;  // 몇 번 네트워크까지 확인했는 지 저장할 변수
    int[] networkNumbers;   // 각 노드가 몇 번 네트워크에 속하는 지 저장할 변수

    public int solution(int n, int[][] computers) {
        networkNumber = 0;
        networkNumbers = new int[n];
        for (int i = 0; i < n; i++) {
            if (networkNumbers[i] != 0) continue;    // 이미 네트워크가 확인된 노드는 건너 뜀
            networkNumber++;
            bfs(n, computers, i);
        }

        return networkNumber;
    }

    void bfs(int n, int[][] computers, int start) {
        boolean[] isVisited = new boolean[n];   // 현재 탐색에서 방문했던 노드에 대한 체크를 담당하는 배열
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        isVisited[start] = true;

        int now;
        while (!q.isEmpty()) {
            now = q.poll();
            networkNumbers[now] = networkNumber;

            for (int i = 0; i < n; i++) {   // 현재 노드의 연결 정보에 대해
                if (computers[now][i] == 0 || isVisited[i]) continue;   // 연결되지 않았거나 이번 탐색에서 이미 방문한 노드이면 건너 뜀
                q.offer(i);
                isVisited[i] = true;
            }
        }
    }

}
