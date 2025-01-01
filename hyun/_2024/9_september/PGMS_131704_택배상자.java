package implementation;

// 보조 컨테이너는 스택 구조
import java.io.*;
import java.util.*;

public class PGMS_131704_택배상자 {
    public int solution(int[] order) {
        int answer = 0;

        Deque<Integer> sub = new ArrayDeque<>();
        int num = 1;

        for(int cur : order){
            boolean isStop = true;

            if(!sub.isEmpty() && sub.peekLast() == cur){
                sub.pollLast();
                isStop = false;
            }
            else if(num <= cur){
                while(num < cur){
                    sub.addLast(num++);
                }
                num++;
                isStop = false;
            }

            if(isStop) break;
            else answer++;

        }



        return answer;
    }
}
