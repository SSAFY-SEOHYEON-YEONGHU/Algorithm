import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static class Node{
        int a,b;
        Node(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<>(){
           @Override
            public int compare(Node n1, Node n2){
                if(n1.a == n2.a) return n1.b - n2.b;
                return n1.a - n2.a;
            }
        });
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            String s = br.readLine();
            String[] tmp = s.split("\\.");
            if(tmp.length > 1){
                pq.add(new Node(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1])));
            }else{
                pq.add(new Node(Integer.parseInt(s),-1));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.b == -1) sb.append(cur.a).append("\n");
            else sb.append(cur.a).append(".").append(cur.b).append("\n");
        }
        System.out.println(sb);
    }
}
