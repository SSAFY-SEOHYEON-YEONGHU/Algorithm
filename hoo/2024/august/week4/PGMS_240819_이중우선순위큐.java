package august.week4;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class PGMS_240819_이중우선순위큐 {

    public int[] solution(String[] operations) {
        int[] answer = doQueue(operations);

        return answer;
    }

    int[] doQueue(String[] operations) {
        int[] answer = new int[2];

        PriorityQueue<Integer> pq = new PriorityQueue<>();  // 숫자 오름차순 우선순위 큐
        String operationString;
        char operation; // I or D
        int operationNumber;    // 연산 명령어 뒤에 주어지는 숫자
        for (int i = 0; i < operations.length; i++) {
            operationString = operations[i];
            operation = operationString.charAt(0);
            operationNumber = Integer.parseInt(operationString.substring(2));

            switch (operation) {    // I나 D 명령에 대해
                case 'I':  // I면 바로 삽입
                    pq.offer(operationNumber);
                    break;
                case 'D':   // D면 1이냐 -1이냐에 따라 연산 다르게, 큐가 비어있으면 실행 건너 뜀
                    if (pq.isEmpty()) continue;
                    if (operationNumber == -1) pq.poll();   // -1이면 최소값 뽑는 것이므로 바로 뽑음
                    else {  // 1이면 최댓값이므로 맨 마지막 수 뽑아야 함
                        List<Integer> temp = new ArrayList<>();
                        int pqSize = pq.size();
                        for (int j = 0; j < pqSize-1; j++) temp.add(pq.poll()); // 맨 마지막 수까지 임시 리스트에 저장
                        pq.poll();  // 맨 마지막 수 뽑음
                        for (int j = 0; j < temp.size(); j++) pq.offer(temp.get(j));    // 리스트에 저장해뒀던 숫자 다시 큐에 삽입
                    }
                    break;
            }
        }

        if (!pq.isEmpty()) {    // 큐가 비어있지 않으면 최댓값을 0번 인덱스에, 최솟값을 1번 인덱스에
            answer[1] = pq.poll();  // 최솟값은 바로 큐에서 뽑아서 저장
            if (pq.isEmpty()) answer[0] = answer[1];    // 최솟값 뽑고 큐가 비면 최댓값도 그 값으로 저장
            else {
                int pqSize = pq.size();
                for (int j = 0; j < pqSize-1; j++) pq.poll();   // 맨 마지막 수만 남기고 다 뽑음
                answer[0] = pq.poll();  // 맨 마지막 수 0번 인덱스에 저장
            }
        }

        return answer;
    }

}
