import java.util.*;

class Solution {

    public int solution(int[] cards) {
        int answer = doCardGame(cards);

        return answer;
    }

    int doCardGame(int[] cards) {
        boolean[] isOpened = new boolean[cards.length + 1];   // 상자의 열림 여부 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());  // 각 상자를 열었을 때 그룹에 속하는 상자 수를 최댓값부터 저장하는 pq
        for (int i = 1; i <= cards.length; i++) {
            if (isOpened[i]) continue;  // 이미 열어본 상자는 건너 뜀
            pq.offer(openBoxes(cards, isOpened, i));
        }

        if (pq.size() <= 1) return 0;   // 만약 한 번만에 게임이 종료됐으면 0점 반환

        return pq.poll() * pq.poll();
    }

    int openBoxes(int[] cards, boolean[] isOpened, int startBox) {   // 한 상자를 시작으로 하여 상자 그룹을 확인하는 함수
        int groupCount = 1; // 이번 카드를 시작으로 하는 상자 그룹의 상자 개수
        isOpened[startBox] = true;
        int nextBox = cards[startBox-1];
        while (true) {
            if (isOpened[nextBox]) break;  // 이미 열린 상자면 반복문 종료
            isOpened[nextBox] = true;
            nextBox = cards[nextBox-1];
            groupCount++;
        }

        return groupCount;
    }

}