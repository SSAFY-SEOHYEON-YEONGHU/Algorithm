package twentytwentyfive.february.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_22866_탑보기 {

    static int N;
    static int[] towers;

    public static void main(String[] args) throws IOException {
        init();
        calcCanSee();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        towers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) towers[i] = Integer.parseInt(st.nextToken());
    }

    static void calcCanSee() {
        Stack<int[]> leftStack = new Stack<>(); // 각 빌딩 기준 왼쪽으로 볼 수 있는 빌딩을 저장하는 스택
        Stack<int[]> rightStack = new Stack<>(); // 오른쪽
        int[] canSeeCount = new int[N+1]; // 볼 수 있는 빌딩 개수 저장하는 배열
        int[] nearestBuilding = new int[N+1]; // 볼 수 있는 빌딩 중 가장 가까운 빌딩 저장하는 배열
        Arrays.fill(nearestBuilding, Integer.MAX_VALUE);

        for (int i = 0; i < N; i++) leftStack = countCanSee(leftStack, i, canSeeCount, nearestBuilding);
        for (int i = N-1; i >= 0; i--) rightStack = countCanSee(rightStack, i, canSeeCount, nearestBuilding);

        StringBuilder sb = new StringBuilder(); // 정답 출력
        for (int i = 0; i < N; i++) sb.append(canSeeCount[i]).append(" ").append( (canSeeCount[i] == 0)? "":nearestBuilding[i]+1 ).append("\n"); // 번호로 출력해야 하므로 +1해서 출력
        System.out.println(sb);
    }

    static Stack<int[]> countCanSee(Stack<int[]> canSeeStack, int nowTowerIndex, int[] canSeeCount, int[] nearestBuilding) {
        while (!canSeeStack.isEmpty() && canSeeStack.peek()[1] <= towers[nowTowerIndex]) canSeeStack.pop(); // 현재 빌딩보다 높이가 낮거나 같은 빌딩들 스택에서 제거

        canSeeCount[nowTowerIndex] += canSeeStack.size(); // 남은 빌딩들 개수만큼 볼 수 있음을 더해줌
        if (!canSeeStack.isEmpty()) {
            if (Math.abs(nearestBuilding[nowTowerIndex]-nowTowerIndex) > Math.abs(canSeeStack.peek()[0]-nowTowerIndex)) nearestBuilding[nowTowerIndex] = canSeeStack.peek()[0]; // 볼 수 있는 빌딩 중 더 인덱스 차이가 낮은 빌딩의 인덱스로 갱신
        }

        canSeeStack.push(new int[] {nowTowerIndex, towers[nowTowerIndex]});

        return canSeeStack;
    }

}
