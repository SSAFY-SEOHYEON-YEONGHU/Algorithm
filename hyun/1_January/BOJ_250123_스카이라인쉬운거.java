package stack;

import java.io.*;
import java.util.*;
public class BOJ_250123_스카이라인쉬운거 {
    static int N;
    static int[] y;
    static int answer = 0;

    static void popStack(Stack<Integer> st, int top){
        while(!st.isEmpty()){
            if(st.peek() == top) st.pop();
            else break;
        }
    }
    static void simulation(){
        Stack<Integer> st = new Stack<>();

        for(int cur : y){

            if(st.isEmpty()) {
                if(cur!=0) st.add(cur);
                continue;
            }

            if(st.peek() <= cur) st.add(cur);
            else{
                while(!st.isEmpty()) {
                    if(st.peek() > cur) {
                        popStack(st, st.peek());
                        answer++;
                    }
                    else break;
                }
                if(cur != 0) st.add(cur);
            }
        }

        System.out.println(answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        y = new int[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            y[i] = Integer.parseInt(st.nextToken());
        }
        y[N] = 0;
        simulation();
    }
}
