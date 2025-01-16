package simulation;

import java.io.*;
import java.util.*;
public class BOJ_250116_IF문좀대신써줘 {
    static int N,M;
    static Node[] input;
    static int[][] numbers;
    static class Node{
        String s;
        int kijun;
        Node(String s, int kijun){
            this.s = s;
            this.kijun = kijun;
        }
    }

    static void simulation(){
        int[] answer = new int[M];
        int idx = 0;
        int point = input[0].kijun;
        Arrays.sort(numbers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for(int i=0; i<M; i++){
            int curNum = numbers[i][0];
            int curIdx = numbers[i][1];

            if(curNum > point) {
                for (int j = idx+1; j < N ; j++) {
                    if(curNum <= input[j].kijun){
                        idx = j;
                        point = input[j].kijun;
                        break;
                    }
                }
            }
            answer[curIdx] = idx;
        }

        // answer 출력
        StringBuilder sb = new StringBuilder();
        for(int a : answer) sb.append(input[a].s).append("\n");
        System.out.println(sb);
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        input = new Node[N];
        numbers = new int[M][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input[i] = new Node(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            numbers[i][0] = Integer.parseInt(br.readLine());
            numbers[i][1] = i;
        }

        simulation();

    }
}
