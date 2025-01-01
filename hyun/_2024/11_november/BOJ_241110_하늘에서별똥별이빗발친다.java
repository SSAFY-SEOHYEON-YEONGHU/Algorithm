package sort;

import java.io.*;
import java.util.*;

public class BOJ_241110_하늘에서별똥별이빗발친다 {
    static int N,M,L,K;
    static ArrayList<Node> stars = new ArrayList<>();
    static int answer = 0;
    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void examineY(ArrayList<Integer> arr){
        arr.add(600000);
        Collections.sort(arr);

        for (int i = 0; i < arr.size(); i++) {
            int kijun = arr.get(i);
            for (int j = i+1; j < arr.size() ; j++) {
                if(arr.get(j) > kijun + L){
                    answer = Math.max(answer, j-i);
                    break;
                }
            }
        }
    }
    public static void getStars(){
        stars.add(new Node(N+10, M+10));
        Collections.sort(stars, (o1, o2) -> o1.x - o2.x);


        for (int i = 0; i < K; i++) {
            int kijun = stars.get(i).x;
            ArrayList<Integer> yy = new ArrayList<>();

            for (int j = i; j < K ; j++) {
                Node cur = stars.get(j);
                if(cur.x <= kijun + L) yy.add(cur.y);
                else {

                    break;
                }
            }

            examineY(yy);

        }

        System.out.println(K-answer);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            stars.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        getStars();
    }
}
