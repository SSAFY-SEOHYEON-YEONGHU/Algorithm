package implementation;

import java.io.*;
import java.util.*;
public class BOJ_241217_숫자고르기 {
    static int N;
    static int[] input;
    static int[][] visited;
    static Set<Integer> arr = new TreeSet<>();

    public static void copyArr(int[][] from, int[][] to){
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j <= N; j++) {
                from[i][j] = to[i][j];
            }
        }
    }

    public static void simulation(){

        for (int i = 1; i <= N ; i++) {
            int[][] tmp = new int[2][N+1];
            copyArr(tmp, visited);
            int idx = i;

            while(true){
                tmp[0][idx] = 1;
                int nxt = input[idx];
                tmp[1][nxt] = 1;

                if(tmp[0][nxt] == 1) break;

                idx = nxt;
            }

            boolean isAnswer = true;
            ArrayList<Integer> tmpArr = new ArrayList<>();
            for (int j = 1; j <= N ; j++) {
                if(tmp[0][j] + tmp[1][j] == 2) {
                    tmpArr.add(j);
                }
                else if(tmp[0][j] + tmp[1][j] == 1 ){
                    isAnswer = false;
                    break;
                }
            }

            if(isAnswer) {
                for(int su : tmpArr) arr.add(su);
            }
        }

        System.out.println(arr.size());
        if(arr.size() > 0) {
            for (int su : arr) System.out.println(su);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new int[N+1];
        visited = new int[2][N+1];

        for (int i = 0; i < N; i++) {
            input[i+1] = Integer.parseInt(br.readLine());

            if(input[i+1] == i+1) {
                visited[0][i+1] = 1;
                visited[1][i+1] = 1;
            }
        }

        simulation();

    }
}
