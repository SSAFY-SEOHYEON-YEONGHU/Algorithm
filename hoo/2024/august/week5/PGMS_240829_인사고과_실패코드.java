package august.week5;

import java.util.PriorityQueue;

public class PGMS_240829_인사고과_실패코드 {

    class Employee implements Comparable<Employee> {
        int aScore; // 근무 태도 점수
        int bScore; // 동료 평가 점수
        int index;  // 주어진 사원의 인덱스

        public Employee(int aScore, int bScore, int index) {
            this.aScore = aScore;
            this.bScore = bScore;
            this.index = index;
        }

        @Override
        public String toString() { return this.aScore + " " + this.bScore + " " + this.index; }

        @Override
        public int compareTo(Employee e) {
            if ((e.aScore + e.bScore) == (this.aScore + this.bScore)) return this.index - e.index;
            return (e.aScore + e.bScore) - (this.aScore + this.bScore); // 내림차순 정렬
        }
    }

    public int solution(int[][] scores) {
        int answer = calcGrade(scores);

        return answer;
    }

    int calcGrade(int[][] scores) {
        PriorityQueue<Employee> pq = new PriorityQueue<>();
        for (int i = 0; i < scores.length; i++) {
            pq.offer(new Employee(scores[i][0], scores[i][1], i));
        }

        int grade = 0;  // 등수 세알리기
        boolean canReceiveIncentive = false;    // 원호가 인센티브를 받았는 지 여부 저장
        int[] maxScore = new int[2]; // a점수와 b점수의 최댓값을 이용, 인센티브를 받지 못하는 경우 걸러낸다
        Employee now;

        while (!pq.isEmpty()) {
            now = pq.poll();

            if (now.aScore > maxScore[0] || now.bScore > maxScore[1]) {   // 현재 고용자의 점수 중 하나라도 저장된 최대 점수보다 높다면
                if (now.aScore > maxScore[0] && now.bScore > maxScore[1]) maxScore = new int[] {now.aScore, now.bScore};   // 모든 점수가 다 높으면 무조건 갱신
                else if (Math.abs(now.aScore - now.bScore) < Math.abs(maxScore[0] - maxScore[1])) maxScore = new int[] {now.aScore, now.bScore};    // 그게 아니라면 고과 비교하기 좋게 점수 차가 작은 값으로 갱신
            } else if (now.aScore < maxScore[0] && now.bScore < maxScore[1]) continue;

            grade++;
            if (now.index == 0) {   // 원호가 인센티브를 받은 경우임
                canReceiveIncentive = true;
                break;
            }
        }

        return (canReceiveIncentive)? grade:-1;
    }

}
