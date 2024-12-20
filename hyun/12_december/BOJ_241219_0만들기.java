package dfs;

import java.io.*;
import java.util.*;
public class BOJ_241219_0만들기 {
    static int N;
    static String[] order;
    static ArrayList<String>answer;
    static StringBuilder sb = new StringBuilder();
    public static void getAnswer(){
        Stack<String> exp = new Stack<>();

        for (int i = 0; i < 2*N-1; i++) {
            String cur = order[i];

            if(cur.equals(" ")){
                exp.add(exp.pop() + order[i+1]);
                i++;
            }
            else {
                exp.add(cur);
            }
        }

        int sum = Integer.parseInt(exp.get(0));
        int size = exp.size();
        for (int i = 1; i < size; i+=2) {
            if(exp.get(i).equals("+")) sum += Integer.parseInt(exp.get(i+1));
            else sum -= Integer.parseInt(exp.get(i+1));
        }
        if(sum == 0) {
            String sss = "";
            for(String su : order) sss += su;
            answer.add(sss);
        }

    }

    public static void dfs(int idx, int num){
        if(num > N){
            getAnswer();
            return;
        }

        order[idx] = "+";
        order[idx+1] = String.valueOf(num);
        dfs(idx+2, num+1);

        order[idx] = "-";
        order[idx+1] = String.valueOf(num);
        dfs(idx+2, num+1);

        order[idx] = " ";
        order[idx+1] = String.valueOf(num);
        dfs(idx+2, num+1);

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());

            order = new String[2*N-1];
            order[0] = "1";
            answer = new ArrayList<>();
            dfs(1,2);

            Collections.sort(answer);
            for(String su : answer) sb.append(su).append("\n");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
