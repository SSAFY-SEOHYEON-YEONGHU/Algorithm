package bfs;

import java.util.*;

public class PGMS_250609_석유시추 {
    int N,M;
    int[][] groupNum;
    int[] cnt;

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public void setGroupNum(int gNum, int x, int y, boolean[][] visited, int[][] land){
        Queue<Node> q = new ArrayDeque<>();
        int groupCnt = 0;

        q.add(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            groupNum[cur.x][cur.y] = gNum;
            groupCnt++;

            for(int k=0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx <0 || nx>= N || ny < 0 || ny >= M || visited[nx][ny]) continue;
                if(land[nx][ny] == 1){
                    q.add(new Node(nx,ny));
                    visited[nx][ny] = true;
                }
            }
        }

        cnt[gNum] = groupCnt;
    }

    public int simulation(int[][] land){
        boolean[][] visited = new boolean[N][M];
        int gNum = 1;
        // 그룹 번호 새김
        for(int i=0; i<N; i++){
            for(int j=0; j<M;j++){
                if(land[i][j] == 0 || visited[i][j]) continue;
                setGroupNum(gNum++, i,j, visited, land);
            }
        }

        // 석유 시추
        int answer = 0;
        for(int j=0; j<M; j++){
            Set<Integer> g = new HashSet<>();
            int tmp = 0;
            for(int i=0; i<N; i++){
                if(!g.contains(groupNum[i][j])){
                    tmp += cnt[groupNum[i][j]];
                    g.add(groupNum[i][j]);
                }
            }
            answer = Math.max(answer, tmp);
        }

        return answer;
    }

    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        groupNum = new int[N][M];
        cnt = new int[N*N+10];

        return simulation(land);
    }
}