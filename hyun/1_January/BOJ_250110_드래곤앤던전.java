package binary_search;

import java.io.*;
import java.util.*;
public class BOJ_250110_드래곤앤던전 {
    static int N,initAttack;
    static int[][] map;

    static boolean simulation(long maxLife){
        //System.out.println(maxLife);
        long life = maxLife;
        long attack = initAttack;
        for (int i = 0; i < N; i++) {
            if(map[i][0] == 1) { // 몬스터
                long mAttack = map[i][1];
                long mLife = map[i][2];

                long quotient = mLife / attack;
                long remainder = mLife % attack;
                if(remainder != 0) quotient++;

                life -= (quotient-1) * mAttack;
                if(life <= 0) return false;

            }
            else{ // 포션
                attack += map[i][1];
                life += map[i][2];

                if(life > maxLife) life = maxLife;
            }
        }
        return true;
    }

    static void binarySearch(){
        long low = 0;
        long high = Long.MAX_VALUE;

        while(low + 1 < high){
            long mid = (low+high) / 2; // 최대 생명력
            if(!simulation(mid)) low = mid;
            else high = mid;
        }
        System.out.println(high);

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        initAttack = Integer.parseInt(st.nextToken());
        map = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken());
            map[i][1] = Integer.parseInt(st.nextToken());
            map[i][2] = Integer.parseInt(st.nextToken());
        }

        binarySearch();
    }
}
