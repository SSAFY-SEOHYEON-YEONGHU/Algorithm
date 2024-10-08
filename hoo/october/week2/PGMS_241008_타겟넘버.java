package october.week2;

import java.util.ArrayDeque;
import java.util.Queue;

public class PGMS_241008_타겟넘버 {

    public int solution(int[] numbers, int target) {
        int answer = findSolution(numbers, target);

        return answer;
    }

    int findSolution(int[] numbers, int target) {
        int answer = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {numbers[0], 0});
        q.offer(new int[] {-1 * numbers[0], 0});

        int[] now;
        while (!q.isEmpty()) {
            now = q.poll();
            if (now[1] == numbers.length-1) {    // 모든 숫자를 다 썼다면
                if (now[0] == target) answer++; // 그리고 그 숫자가 타겟넘버라면
                continue;
            }

            q.offer(new int[] {now[0] + numbers[now[1]+1], now[1]+1});
            q.offer(new int[] {now[0] + (-1*numbers[now[1]+1]), now[1]+1});
        }

        return answer;
    }

}
