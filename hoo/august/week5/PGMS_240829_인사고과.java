package august.week5;

import java.util.PriorityQueue;

public class PGMS_240829_인사고과 {

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
            if (this.aScore == e.aScore) {
                return this.bScore - e.bScore; // aScore 동점 시 bScore(동료 평가 점수) 오름차순 정렬
            }
            return e.aScore - this.aScore;  // aScore(근무 태도 점수) 기준 내림차순 정렬
        }
    }

    public int solution(int[][] scores) {
        int answer = calcGrade(scores);

        return answer;
    }

    int calcGrade(int[][] scores) {
        PriorityQueue<Employee> pq = new PriorityQueue<>();
        for (int i = 0; i < scores.length; i++) pq.offer(new Employee(scores[i][0], scores[i][1], i));

        int grade = 1;  // 1등부터 등수 세알리기
        boolean canReceiveIncentive = false;    // 원호가 인센티브를 받았는 지 여부 저장
        int maxBscore = 0;  // 현재 aScore를 기준으로 내림차순 정렬임. 이때 사원의 bScore가 이전까지 최대 bScore보다 낮다면 인센티브를 못 받는 것임.
        Employee now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (now.bScore < maxBscore) continue;   // 인센티브를 받지 못하는 사원은 인센티브 로직에서 생각을 안하게끔 건너 뜀
            if (now.index == 0) canReceiveIncentive = true;   // 원호가 인센티브를 받은 경우, 받았다고 체크

            maxBscore = Math.max(maxBscore, now.bScore);    // 최대 bScore 갱신
            if (now.aScore + now.bScore > scores[0][0] + scores[0][1]) grade++;   // 원호보다 합산 점수 높은 애들에 대해서 등수를 매겨 줌
        }

        return (canReceiveIncentive)? grade:-1; // 모든 직원에 대해 검사를 했지만 원호가 인센티브를 받지 못한 경우는 -1 반환, 아니라면 원호의 등수 반환
    }

}
