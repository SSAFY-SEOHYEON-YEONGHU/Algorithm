import java.util.*;

class Solution {

    public int[] solution(String[] wallpaper) {
        int[] answer = new int[4];

        int N = wallpaper.length;
        int M = wallpaper[0].length();

        int sx = N;
        int sy = M;
        int ex = -1;
        int ey = -1;

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(wallpaper[i].charAt(j) == '.') continue;

                if(sx > i) sx = i;
                if(sy > j) sy = j;
                if(ex < i) ex = i;
                if(ey < j) ey = j;
            }
        }

        answer[0] = sx;
        answer[1] = sy;
        answer[2] = ex+1;
        answer[3] = ey+1;

        return answer;
    }
}