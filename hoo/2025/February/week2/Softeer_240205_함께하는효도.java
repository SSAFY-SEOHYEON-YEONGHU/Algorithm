import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static int maxCropCount;

    public static void main(String[] args) throws Exception {
        int[][] initFriendAxis = init();  // 입력받기

        boolean[][] isMarked = new boolean[n][n];  // 각 시간 별로 친구들이 위치한 곳을 마크할 배열
        int croppedCount = 0;  // 수확한 열매 개수
        for (int i = 0; i < m; i++) {
            isMarked[initFriendAxis[i][0]][initFriendAxis[i][1]] = true;
            croppedCount += map[initFriendAxis[i][0]][initFriendAxis[i][1]];
        }

        doCrop(0, 0, croppedCount, initFriendAxis, isMarked);
        System.out.println(maxCropCount);
    }

    static int[][] init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }
        maxCropCount = 0;

        int[][] friendAxis = new int[m][2];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            friendAxis[i] = new int[] {Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1};
        }

        return friendAxis;
    }

    static void doCrop(int time, int friendIndex, int croppedCount, int[][] friendAxis, boolean[][] isMarked) {
        int[] dirRow = new int[] {-1, 0, 1, 0};  // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};

        if (time == 3) {  // 기저, 3초간 수확을 다 한 경우
            maxCropCount = Math.max(maxCropCount, croppedCount);

            return;
        }

        int[] nowFriend = friendAxis[friendIndex];
        int nextFriendRow, nextFriendCol;
        for (int d = 0; d < 4; d++) {
            nextFriendRow = nowFriend[0] + dirRow[d];
            nextFriendCol = nowFriend[1] + dirCol[d];
            if (isOuted(nextFriendRow, nextFriendCol)) continue;  // 범위 밖이면 건너 뜀

            friendAxis[friendIndex] = new int[] {nextFriendRow, nextFriendCol};  // 현재 움직일 친구가 위치한 좌표

            boolean isAlreadyMarked = isMarked[nextFriendRow][nextFriendCol];  // 현재 친구 말고 다른 친구에 의해 수확이 됐던 곳인 지 체크, 재귀 진입 후 원상복구할 때 이 값이 true라면 isMarked의 해당 좌표 값 false로 원상복구 안함
            int nextCropCount = croppedCount;
            if (!isMarked[nextFriendRow][nextFriendCol]) nextCropCount += map[nextFriendRow][nextFriendCol];  // 이전에 수확된 적이 없던 좌표면 수확함
            isMarked[nextFriendRow][nextFriendCol] = true;  // 수확했음을 표시

            int nextTime = time;
            int nextFriendIndex = friendIndex+1;
            if (friendIndex == m-1) {  // 현재 친구가 마지막 친구면 시간 +1초, 처음 친구로 인덱스 설정
                nextTime++;
                nextFriendIndex = 0;
            }

            doCrop(nextTime, nextFriendIndex, nextCropCount, friendAxis, isMarked);  // 재귀 들어감
            friendAxis[friendIndex] = new int[] {nowFriend[0], nowFriend[1]};  // 원상복구
            if (!isAlreadyMarked) isMarked[nextFriendRow][nextFriendCol] = false;  // 현재 학생 말고 다른 학생이 방문해서 수확했던 곳이라면 false로 되돌리지 않음
        }
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < n) && (0 <= col && col < n)) return false;
        return true;
    }

}
