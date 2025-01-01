package twentytwentyfour.october.week4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PGMS_241023_디펜스게임 {

    public int solution(int n, int k, int[] enemy) {
        int answer = doDefenceGame(n, k, enemy);

        return answer;
    }

    int doDefenceGame(int n, int k, int[] enemy) { // n: 최초 병사 수, k: 무적권 횟수, enemy[i]: 매 라운드의 적
        PriorityQueue<Integer> kPq = new PriorityQueue<>(Comparator.reverseOrder());  // 무적권을 어느 라운드에 사용할 지 모름. 그러므로 모든 라운드를 무적권을 사용할 후보로 두기 위해 pq에 저장, 적 수 많은 곳에 무적권 쓰기 위해 내림차순으로 정렬함.
        int answer = enemy.length;  // 모든 라운드를 방어해낼 경우를 대비해 answer의 초기값은 적의 수만큼
        for (int i = 0; i < enemy.length; i++) {    // 모든 적에 대해
            kPq.offer(enemy[i]); // 무적권 사용할 후보로 두기 위해 pq에 넣어두기
            if (n < enemy[i]) {   // 병사 수 부족한 경우
                if (k == 0) {   // 근데 무적권도 없음
                    answer = i;
                    break;
                }
                n += kPq.poll(); // 무적권을 사용할 라운드의 적 수만큼 병사 수 충족
                k--;    // 무적권 사용
            }
            n -= enemy[i];  // 병사 수가 충분할 때는 병사 사용하여 막음
        }

        return answer;
    }

}
