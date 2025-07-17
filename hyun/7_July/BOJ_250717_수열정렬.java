package sort;

import java.io.*;
import java.util.*;

public class BOJ_250717_수열정렬 {
    static int N;
    static ArrayList<Node> input = new ArrayList<>();
    static class Node{
        int num, idx;
        Node(int num, int idx){
            this.num = num;
            this.idx = idx;
        }
    }

    static void simulation(){
        int[] answer = new int[N];
        Collections.sort(input, new Comparator<Node>(){
            @Override
            public int compare(Node n1, Node n2){
                if(n1.num == n2.num) return n1.idx - n2.idx;
                return n1.num - n2.num;
            }
        });

        int lo = 0;
        for(Node cur : input){
            answer[cur.idx] = lo++;
        }

        for(int su : answer) System.out.print(su + " ");

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input.add(new Node(Integer.parseInt(st.nextToken()), i));
        }

        simulation();
    }
}
