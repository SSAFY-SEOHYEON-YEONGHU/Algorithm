package bfs;

import java.io.*;
import java.util.*;
public class BOj_241114_A와B2 {
    static String S,T;

    public static boolean isAnswer(String S){
        if(S.equals(S)) return true;
        return false;
    }
    public static int simulation(){
        Queue<String> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        q.add(T);
        visited.add(T);

        while(!q.isEmpty()){
            String cur = q.poll();

            if(cur.length() == S.length()){
                // 정답 확인
                if(isAnswer(cur)) return 1;
                continue;
            }

            int len = cur.length();
            if(cur.charAt(len-1) == 'A'){
                q.add(cur.substring(0,len-1));
            }

            if(cur.charAt(0) == 'B'){
                StringBuffer sb = new StringBuffer(cur);
                String tmp = sb.reverse().toString();
                q.add(tmp.substring(0,len-1));
            }
        }

        return 0;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        System.out.println(simulation());
    }
}
