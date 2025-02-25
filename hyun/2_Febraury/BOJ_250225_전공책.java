package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250225_전공책 {
    static String kijun;
    static int N;
    static ArrayList<Node> input = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    static class Node{
        int price;
        String name;
        Node(int price, String name){
            this.price = price;
            this.name = name;
        }
    }
    static ArrayList<Integer> findIndex(boolean[] visited, int idx){
        boolean[] check = new boolean[60];
        ArrayList<Integer> result = new ArrayList<>();
        String s = input.get(idx).name;

        for (int i = 0; i < kijun.length(); i++) {
            if(visited[i]) continue;
            char c = kijun.charAt(i);
            for (int j = 0; j < s.length(); j++) {
                if(!check[j] && s.charAt(j) == c){
                    check[j] = true;
                    result.add(i);
                    break;
                }
            }
        }

        return result;
    }


    static void dfs(boolean[] visited, int idx, int price){
        if(idx == N){
            boolean isAnswer = true;
            for (int i = 0; i < kijun.length(); i++) {
                if(!visited[i]){
                    isAnswer = false;
                    break;
                }
            }
            if(isAnswer) answer = Math.min(answer, price);
            return;
        }

        Node cur = input.get(idx);

        ArrayList<Integer> location = findIndex(visited, idx); // 선택할 수 있는 위치
        ArrayList<Integer> realLocation = new ArrayList<>();
        for(int su : location) {
            if(!visited[su]) {
                realLocation.add(su);
                visited[su] = true;
            }
        }

        int adding = realLocation.size() > 0 ? cur.price : 0;
        dfs(visited, idx+1, price + adding);

        for(int su : realLocation) visited[su] = false;
        dfs(visited, idx+1, price);
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        kijun = br.readLine();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input.add(new Node(Integer.parseInt(st.nextToken()),st.nextToken()));
        }

        dfs(new boolean[kijun.length()], 0, 0);
        if(answer == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }
}
