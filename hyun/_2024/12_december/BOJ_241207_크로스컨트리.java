package implementation;

import java.io.*;
import java.util.*;

public class BOJ_241207_크로스컨트리 {

    static int N;
    static int total;
    static int[] input;
    static StringBuilder sb = new StringBuilder();

    static class Node{
        int sum, cnt, five;
        Node(int sum, int cnt, int five){
            this.sum = sum;
            this.cnt = cnt;
            this.five = five;
        }
    }

    public static void countTeam(int[] counting){
        for(int i=0; i<N; i++){
            counting[input[i]]++;
        }
    }

    public static void calScore(int[] counting, HashMap<Integer,Node> hmap){
        for(int i=1; i<=total; i++){
            if(counting[i] == 6) hmap.put(i, new Node(0,0,0));
        }

        int score = 1;
        for(int team : input){
            if(hmap.containsKey(team)){
                Node cur = hmap.get(team);
                if(cur.cnt < 4) cur.sum += score;
                else if(cur.cnt == 4) cur.five = score;

                cur.cnt += 1;
                score++;
                hmap.put(team,cur);
            }
        }

        int sum = Integer.MAX_VALUE;
        int team = 0;
        int five = Integer.MAX_VALUE;
        for(int t : hmap.keySet()){
            Node cur = hmap.get(t);

            if(sum >= cur.sum){
                if(sum > cur.sum) {
                    sum = cur.sum;
                    team = t;
                    five = cur.five;
                }
                else{
                    if(five > cur.five){
                        team = t;
                        five = cur.five;
                    }
                }
            }
        }

        sb.append(team).append("\n");
    }

    public static void simulation(){
        int[] counting = new int[total+10];
        HashMap<Integer,Node> hmap = new HashMap<>();

        countTeam(counting);
        calScore(counting, hmap);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            input = new int[N];
            total = 0;

            for(int i=0; i<N; i++){
                input[i] = Integer.parseInt(st.nextToken());
                total = Math.max(total, input[i]);
            }

            simulation();
        }

        System.out.println(sb);
    }

}
