package sort;

import java.io.*;
import java.util.*;

public class BOJ_241024_올림픽 {
    static int N,K;
    static ArrayList<Node> input = new ArrayList<>();
    static class Node{
        int num;
        int a,b,c;
        Node(int num, int a, int b, int c){
            this.num = num;
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public static boolean isSame(Node n1, Node n2){
        if(n1.a == n2.a && n1.b == n2.b && n1.c == n2.c) return true;
        return false;
    }

    public static int simulation() {
        // 정렬
        Collections.sort(input, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.a == o2.a && o1.b == o2.b) return o2.c - o1.c;
                else if (o1.a == o2.a) return o2.b - o1.b;
                return o2.a - o1.a;
            }
        });

        if(K == input.get(0).num) return 1;

        int rank = 1;

        for(int i=1; i<input.size();i++){
            if(!isSame(input.get(i-1), input.get(i))){
                rank = i+1;
            }

            if(K == input.get(i).num) break;
        }

        return rank;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            input.add(new Node(num,a,b,c));
        }

        System.out.println(simulation());
    }
}
