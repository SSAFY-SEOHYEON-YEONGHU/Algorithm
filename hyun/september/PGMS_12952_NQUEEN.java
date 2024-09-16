package backtracking;

import java.util.*;

public class PGMS_12952_NQUEEN {
    int N;
    int result = 0;
    int[][] map;
    int[] dx = {-1,-1,1,1};
    int[] dy = {-1,1,-1,1};


    public void putQueen(int x, int y){
        // 가로
        for(int i=0; i<N; i++) map[x][i] = 1;
        // 세로
        for(int i=0; i<N; i++) map[i][y] = 1;
        //대각선
        for(int k=0; k<4; k++){
            int cx = x;
            int cy = y;
            while(true){
                cx += dx[k];
                cy += dy[k];
                if(cx < 0 || cx >= N || cy < 0 || cy >= N) break;
                map[cx][cy] = 1;
            }
        }
    }

    public void dfs(int x, int y, int cnt){
        if(cnt == N){
            result++;
            return;
        }

        // map 을 저장해놓음
        int[][] tmp = new int[N][N];
        for(int i=0; i<N; i++) tmp[i] = map[i].clone();

        for(int i=x; i<N; i++){
            for(int j=y; j<N; j++){
                if(map[i][j] == 0){
                    putQueen(i,j); // 배열에 표시
                    dfs(x,y+1,cnt + 1);
                    for(int z=0; z<N; z++) map[z] = tmp[z].clone();// 원복
                }
            }
        }
    }

    public void simulation(){

        // dfs
        dfs(0,0,0);

    }
    public int solution(int n) {
        N = n;
        map = new int[N][N];
        simulation();
        return result;
    }
}