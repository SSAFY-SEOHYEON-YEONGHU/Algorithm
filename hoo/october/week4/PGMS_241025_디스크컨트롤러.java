package october.week4;

import java.util.*;

public Class PGMS_디스크컨트롤러 {

    public int solution(int[][] jobs) {
        int answer = discControl(jobs);
        return answer;
    }

    int discControl(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() { // 우선 주어진 작업들을 요청 시간에 따라 정렬
            @Override
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {  // 작업을 실행하는 실행큐
            @Override
            public int compare(int[] i1, int[] i2) {    // 이 안에서의 정렬은 작업 소요 시간 오름차순으로 정렬
                return i1[1] - i2[1];
            }
        });

        int nextRequest = jobs[0][0];    // 실행 큐에 넣을 다음 작업의 요청 시간, 처음 값은 맨 처음 작업으로
        int jobIndex = 0;   // 실행큐에 넣어줄 작업의 인덱스
        int jobCount = 0;
        int totalTime = 0; // 작업에 사용된 총 시간(요청부터 종료까지의 시간)
        int[] nowJob;   // 실행큐의 맨 앞에 있는 현재 처리할 작업
        while (jobCount < jobs.length) {    // 모든 작업을 처리할 때까지
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= nextRequest) pq.offer(jobs[jobIndex++]);   // 현재 실행큐에 넣을 작업의 요청시간(실행큐의 마지막 작업이 끝난 시간)보다 이전에 요청이 들어온 작업들을 넣어줌(모든 작업이 실행큐에 들어가기 전까지만)

            if (pq.isEmpty()) {    // 실행 큐가 비었다면, 다음 실행 큐에 넣어줄 작업 시간을 현재 작업의 시작 시간으로 갱신
                nextRequest = jobs[jobIndex][0];
            } else {    // 실행큐에 작업이 들어있다면
                nowJob = pq.poll();
                nextRequest += nowJob[1];   // 다음 실행큐에 넣을 지에 대해 판단할 시간은, 현재 작업의 실행시간을 더해준 값
                totalTime += (nextRequest - nowJob[0]); // 작업이 끝난 시간 - 요청이 들어온 시간을 총 실행 시간에 더해줌
                jobCount++; // 처리한 작업 수 카운트처리
            }
        }

        return totalTime / jobs.length;
    }

}