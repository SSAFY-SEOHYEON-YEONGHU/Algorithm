package stack;

import java.util.*;

public class PGMS_241125_햄버거만들기 {
    int answer = 0;
    public void simulation(int[] ingredient){
        Stack<Integer> s = new Stack<>();
        int[] hamburger = {1,3,2,1};

        for(int su : ingredient){
            s.add(su);

            if(s.peek()==1 && s.size() >=4){
                boolean isAnswer = true;
                int size = s.size();
                int idx = 0;
                for(int i=size-1; i >= size - 4; i--){
                    if(s.get(i) != hamburger[idx++]) {
                        isAnswer = false;
                        break;
                    }
                }

                if(isAnswer) {
                    answer++;
                    for(int i=0; i<4; i++) s.pop();
                }

            }
        }
    }
    public int solution(int[] ingredient) {

        simulation(ingredient);
        return answer;
    }
}