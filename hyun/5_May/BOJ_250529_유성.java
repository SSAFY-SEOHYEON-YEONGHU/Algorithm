package implementation;

import java.io.*;
import java.util.*;

public class BOJ_250529_유성 {
    static int R,S;
    static char[][] map;
    static int airLen = Integer.MAX_VALUE;
    static ArrayList<int[]> planet = new ArrayList<>();

    static void simulation(){
        for (int j = 0; j < S; j++) {
            int cnt = 0;
            for (int i = R-1; i >=0 ; i--) {
                if(map[i][j] == '#') {
                    cnt = 0; // 공기 세다가 다시 만날 수도 있음
                }
                else if(map[i][j] == '.') cnt++;
                else{ // 유성 발견
                    planet.add(new int[]{i,j});
                    airLen = Math.min(airLen, cnt);
                    break;
                }
            }
        }

        for(int[] cur : planet){ // 유성 airLen 만큼 이동
            int x = cur[0];
            int y = cur[1];

            for (int i = x; i >= 0 ; i--) {
                map[i+airLen][y] = map[i][y];
                map[i][y] = '.';
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < S; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        map = new char[R][S];

        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        //
        simulation();

    }
}
