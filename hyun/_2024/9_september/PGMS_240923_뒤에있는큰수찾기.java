package implementation;

import java.util.Stack;

public class PGMS_240923_뒤에있는큰수찾기 {
    class Node{
        int num;
        int idx;
        Node(int num, int idx){
            this.num = num;
            this.idx = idx;
        }
    }

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        Stack<Node> stack = new Stack<>();

        for(int i=0; i<numbers.length; i++){
            int kijun = numbers[i];

            while(stack.size() !=0 && stack.peek().num < kijun){
                Node cur = stack.pop();
                answer[cur.idx] = kijun;
            }


            stack.push(new Node(kijun,i));
        }

        for(int i=0; i<answer.length; i++){
            if(answer[i] == 0) answer[i] = -1;
        }


        return answer;
    }
}