package bfs;

import java.util.*;

public class PGMS_250610_충돌위험찾기 {
    int[][] map = new int[110][110];
    int[][][] time = new int[250][110][110];
    int maxTime = 0;

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void bfs(int sx, int sy, int ex, int ey){ // 경로 하나만 구해야함 ...
        Queue<Node> q = new ArrayDeque<>();
        int[][] visited = new int[110][110];
        int cnt = Math.abs(sx-ex) + Math.abs(sy-ey) + 1;
        maxTime = Math.max(maxTime, cnt);

        q.add(new Node(sx,sy));
        visited[sx][sy] = cnt;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x == ex && cur.y == ey){
                // maxTime = Math.min(maxTime, t);
                break;
            }

            for(int k=0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(nx < 0 || nx >= 110 || ny < 0 || ny >= 110 || visited[nx][ny] > 0) continue;
                if(Math.abs(nx-ex) + Math.abs(ny-ey) + 1 > visited[cur.x][cur.y]) continue;

                q.add(new Node(nx,ny));
                visited[nx][ny] = visited[cur.x][cur.y] - 1;
            }

        }

        // for(int i=0; i<8; i++){
        //     for(int j=0; j<8; j++){
        //         System.out.print(visited[i][j]);
        //     }
        //     System.out.println();
        // }
        // System.out.println();

        // 경로 고정하기
        for(int t=0; t<cnt; t++){
            time[t][sx][sy]++;
            // System.out.println(t+": " + sx + "," + sy);

            for(int k=0; k<4; k++){
                int nx = sx + dx[k];
                int ny = sy + dy[k];
                if(nx < 0 || nx >= 110 || ny < 0 || ny >= 110) continue;
                if(visited[nx][ny] == cnt-t-1){
                    sx = nx;
                    sy = ny;
                    break;
                }
            }
        }
        // System.out.println();
    }

    public int simulation(int[][] points, int[][] routes){
        // 로봇 이동 시간대 저장
        for(int i=0; i<routes.length; i++){
            int startP = routes[i][0] - 1;
            int endP = routes[i][1] - 1;

            bfs(points[startP][0],points[startP][1],points[endP][0],points[endP][1]);
        }
        // System.out.println(maxTime);

        // maxTime 만큼 봐주기
        int answer = 0;
        for(int x = 0; x < 110 ; x++){
            for(int y = 0; y < 110 ; y++){
                for(int t=0; t < maxTime; t++){
                    if(time[t][x][y] > 1) answer++;
                }
            }
        }

        return answer;

    }
    public int solution(int[][] points, int[][] routes) {
        return simulation(points, routes);
    }
}