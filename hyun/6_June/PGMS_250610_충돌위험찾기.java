package bfs;

import java.util.*;

public class PGMS_250610_충돌위험찾기 {
    int[][] map = new int[110][110];
    int[][][] time = new int[30000][110][110];
    int maxTime = 0;
    int answer = 0;

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void getAnswer(int a,int b, int c){
        if(time[a][b][c] == 2) answer++;
    }

    public int bfs(int sx, int sy, int ex, int ey, int startTime){
        int ddx = 0;
        int ddy = 0;
        if(sx < ex) ddx = 1;
        else if(sx > ex) ddx = -1;
        if(sy < ey) ddy = 1;
        else if(sy > ey) ddy = -1;

        int rowCnt = Math.abs(sx - ex);
        int colCnt = Math.abs(sy - ey);

        // 행
        for(int i=0; i<rowCnt; i++){
            // System.out.println(sx + "," + sy);
            sx += ddx;
            time[++startTime][sx][sy]++;
            getAnswer(startTime, sx, sy);
        }
        // 열
        for(int i=0; i<colCnt; i++){
            // System.out.println(sx + "," + sy);
            sy += ddy;
            time[++startTime][sx][sy]++;
            getAnswer(startTime, sx, sy);
        }

        // System.out.println("========" + startTime);

        return startTime;

    }

    public void simulation(int[][] points, int[][] routes){
        // 로봇 이동 시간대 저장
        for(int i=0; i<routes.length; i++){
            int[] route = routes[i];
            int startTime = 0;
            // 0초대 집어넣기
            time[0][points[route[0]-1][0]][points[route[0]-1][1]]++;
            getAnswer(0,points[route[0]-1][0],points[route[0]-1][1]);

            for(int r=0; r<route.length-1; r++){
                int startP = route[r] - 1;
                int endP = route[r+1] - 1;
                startTime = bfs(points[startP][0],points[startP][1],points[endP][0],points[endP][1], startTime);
            }
        }
        // System.out.println(maxTime);

    }
    public int solution(int[][] points, int[][] routes) {
        simulation(points, routes);

        return answer;
    }
}