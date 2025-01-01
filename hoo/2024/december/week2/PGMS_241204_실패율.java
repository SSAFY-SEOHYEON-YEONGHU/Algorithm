import java.util.*;

class Solution {

    public int[] solution(int N, int[] stages) {
        int[] answer = calcFailures(N, stages);

        return answer;
    }

    int[] calcFailures(int N, int[] stages) {
        // 1, 3, 2, 1, 0 + 1

        // 1 + 0, 1, 2, 3, 1    5~1 역순
        // [1, 0, 1, 2, 3, 1]
        // [1, 1, 2, 4, 7, 8]
        // 1 + 1, 2, 4, 7, 8
        Arrays.sort(stages);    // stages 오름차순으로 정렬
        int[] sumArr = new int[N+2];    // 1~N까지 스테이지 클리어 유저 수의 누적합 저장하기 위한 배열
        int[] clearArr = new int[N+2];  // 해당 스테이지를 클리어한 유저 수 저장하는 배열
        for (int i = stages.length-1; i >= 0; i--) clearArr[(N+1)-stages[i]]++;
        sumArr[0] = clearArr[0];
        for (int i = 1; i < clearArr.length; i++) sumArr[i] = sumArr[i-1] + clearArr[i];

        PriorityQueue<float[]> pq = new PriorityQueue<>(new Comparator<float[]>() {
            @Override
            public int compare(float[] f1, float[] f2) {
                if (f2[0] == f1[0]) return Float.compare(f1[1], f2[1]);   // 실패율 같은 것들끼리는 스테이지 번호 기준 오름차순 정렬
                return Float.compare(f2[0], f1[0]);   // 실패율 기준 내림차순 정렬
            }
        });
        for (int i = 1; i < clearArr.length-1; i++) {
            if (sumArr[i] == 0) pq.offer(new float[] {0, N+1 - i}); // 누적합이 0인 경우 고려
            else pq.offer(new float[] {(clearArr[i]*1.0f / sumArr[i]*1.0f), N+1 - i});
        }
        int[] answer = new int[N];
        int index = 0;
        while (!pq.isEmpty()) {
            float[] arr = pq.poll();
            // System.out.println(Arrays.toString(arr));
            answer[index++] = (int) arr[1];
        }


        return answer;
    }

}