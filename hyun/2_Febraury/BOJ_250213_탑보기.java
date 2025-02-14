package stack;

import java.io.*;
import java.util.*;
public class BOJ_250213_탑보기 {
    static int N;
    static int[] input;
    static int[][] answer;

    static void getAnswer(int start, int end, int offset){
        Stack<int[]> st = new Stack<>();
        int i = start;

        while(true){
            if(st.isEmpty()){
                st.add(new int[]{ input[i], i});
                continue;
            }

            while(!st.isEmpty()){
                if(st.peek()[0] <= input[i]) st.pop();
                else break;
            }

            answer[i][0] += st.size();
            if(!st.isEmpty()) {
                if(Math.abs(i-answer[i][1]) > Math.abs(i-st.peek()[1])) answer[i][1] = st.peek()[1];
                if(Math.abs(i-answer[i][1]) == Math.abs(i-st.peek()[1])) answer[i][1] = Math.min(answer[i][1], st.peek()[1]);
            }
            st.add(new int[]{ input[i], i});

            if(i == end) break;
            i += offset;
        }
    }

    static void simulation(){

        getAnswer(1,N,1);
        getAnswer(N,1,-1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N ; i++) {
            if(answer[i][0] == 0) sb.append(0).append("\n");
            else sb.append(answer[i][0]).append(" ").append(answer[i][1]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        input = new int[N+1];
        answer = new int[N+1][2]; // ( 갯수, 가장 적은 번호 )
        for (int i = 1; i <= N ; i++) {
            input[i] = Integer.parseInt(st.nextToken());
            answer[i][1] = Integer.MAX_VALUE;
        }

        simulation();
    }
}
