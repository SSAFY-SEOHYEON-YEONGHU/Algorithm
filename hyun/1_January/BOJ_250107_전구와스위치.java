package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250107_전구와스위치 {
    static int N;
    static String input;
    static String target;
    static int[] dx = {-1,0,1};
    static class Node{
        String num;
        int cnt;
        Node(String num, int cnt){
            this.num = num;
            this.cnt = cnt;
        }
    }

    static String changeNum(String su, int i){
        String changePart = "";
        for (int k = 0; k < 3; k++) {
            int nxtI = i + dx[k];
            if(nxtI < 0 || nxtI >= N) continue;
            if(su.charAt(nxtI)  == '0') changePart += "1";
            else changePart += "0";
        }

        String result = "";
        result += su.substring(0,i);
        result += changePart;
        if(i+2 < N) result += su.substring(i+2,N);
        return result;
    }

    static void bfs(){
        Queue<Node> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
//        boolean[] visited = new boolean[200010];

        q.add(new Node(input,0));
        visited.add(input);

        while(!q.isEmpty()){
            Node cur = q.poll();

            if(cur.num.equals(target)){
                System.out.println(cur.cnt);
                return;
            }

            for (int i = 0; i < N; i++) {
                String made = changeNum(cur.num, i);
                if(visited.contains(made)) continue;

                q.add(new Node(made, cur.cnt+1));
                visited.add(made);
            }
        }

        System.out.println(-1);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        input = br.readLine();
        target = br.readLine();

        bfs();


    }
}