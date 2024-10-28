import java.util.*;

class Solution {

    public int[] solution(int[] sequence, int k) {
        int[] answer = findShortest(sequence, k);

        return answer;
    }

    int[] findShortest(int[] sequence, int k) {
        int left = 0, right = 0;    // 가변 슬라이딩 윈도우의 왼쪽, 오른쪽 포인터
        int sum = sequence[0];  // 가변 슬라이딩 윈도우 내부 원소들의 합
        int[] answer = new int[] {0, sequence.length-1};
        while (left < sequence.length) {
            if (sum == k) { // 합이 K인 경우
                if (right - left < answer[1] - answer[0]) { // 길이가 더 짧은 경우에 정답 갱신
                    answer[0] = left;
                    answer[1] = right;
                }
                if (right == sequence.length-1) break;  // 이미 우측 포인터가 끝까지 갔는데 합이 k이면 더이상 k만들 수 없음
                right++;    // 우측 포인터 이동
                sum += sequence[right];
            } else if (sum > k) {   // 합이 k초과인 경우, 왼쪽 포인터를 움직임
                sum -= sequence[left];
                left++;
            } else if (sum < k) {   // 합이 k미만인 경우, 오른쪽 포인터를 움직임
                if (right == sequence.length-1) break;  // 이미 우측 포인터가 끝까지 갔는데 합이 k미만이면 더이상 k만들 수 없음
                right++;
                sum += sequence[right];
            }
        }

        return answer;
    }

}