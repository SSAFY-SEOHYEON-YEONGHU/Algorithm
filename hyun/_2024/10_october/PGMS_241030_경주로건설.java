package bfs;

import java.util.*;

public class PGMS_241030_경주로건설 {
    int N;
    int[][] map;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int x,y,d;
        Node(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    public boolean calCost(int d1, int d2){
        if(d1 == 0 && (d2 == 2 || d2 == 3)) return false;
        else if(d1 == 1 && (d2 == 2 || d2 == 3)) return false;
        else if(d1 == 2 && (d2 == 0 || d2 == 1)) return false;
        else if(d1 == 3 && (d2 == 0 || d2 == 1)) return false;

        return true;
    }

    public int simulation(){
        Queue<Node> q = new ArrayDeque<>();
        int[][] visited = new int[N][N];
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                visited[i][j] = Integer.MAX_VALUE;

        if(map[0][1] == 0) {
            q.add(new Node(0,1,3));
            visited[0][1] = 100;
        }
        if(map[1][0] == 0) {
            q.add(new Node(1,0,1));
            visited[1][0] = 100;
        }

        while(!q.isEmpty()){
            Node cur = q.poll();


            for(int k=0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];

                if(nx < 0 || nx >= N || ny <0 || ny >= N || map[nx][ny] == 1) continue;

                int cost = visited[cur.x][cur.y];
                boolean isFlag = false;
                if(calCost(cur.d, k)) {cost += 100; isFlag = true;}
                else cost += 600;

                if(visited[nx][ny] >= cost){
                    q.add(new Node(nx,ny,k));
                    visited[nx][ny] = cost;
                }
            }
        }

        return visited[N-1][N-1];

    }
    public int solution(int[][] board) {
        N = board.length;
        map = board;

        return simulation();
    }
}