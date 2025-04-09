package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250409_영단어암기는괴로워 {
    static int N,M;
    static class Node{
        String str;
        int cnt,len;
        Node(String str, int cnt, int len){
            this.str = str;
            this.cnt = cnt;
            this.len = len;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hmap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String s = br.readLine();

            if(s.length() < M) continue;
            if(hmap.containsKey(s)) hmap.put(s, hmap.get(s) + 1);
            else hmap.put(s,1);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.cnt == n2.cnt && n1.len == n2.len)
                    return n1.str.compareTo(n2.str);
                else if(n1.cnt == n2.cnt)
                    return n2.len - n1.len;
                else return n2.cnt - n1.cnt;
            }
        });



        for(String s : hmap.keySet()){
            pq.add(new Node(s,hmap.get(s), s.length()));
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll().str).append("\n");
        }
        System.out.println(sb);
    }
}
