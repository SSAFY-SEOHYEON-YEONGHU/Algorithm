package implementation;

import java.util.*;

public class PGMS_241113_혼자놀기의달인 {

    public int simulation(int[] cards){
        ArrayList<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[cards.length];

        for(int i=0; i< cards.length; i++){
            if(visited[i]) continue;

            int cnt = 1;
            int cur = cards[i] - 1;
            visited[i] = true;

            while(true){
                if(visited[cur]) break;

                cnt++;
                visited[cur] = true;
                cur = cards[cur] - 1;
            }

            answer.add(cnt);
        }

        Collections.sort(answer, Collections.reverseOrder());
        if(answer.size() <= 1) return 0;
        return answer.get(0) * answer.get(1);
    }

    public int solution(int[] cards) {
        return simulation(cards);
    }
}