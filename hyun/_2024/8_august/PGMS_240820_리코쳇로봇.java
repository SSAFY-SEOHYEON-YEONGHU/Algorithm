package bfs;

// 장애물, 맨끝에 부딪힐 때까지 미끄러짐 = 1번 이동
// R : 로봇, G : 목표 , D : 장애물

// 지나가다가 목표지점 발견 -> 도착 아님
// 어디 걸려서 멈췄는데, 목표지점이다 -> 도착한 것

import java.io.*;
import java.util.*;

class PGMS_240820_리코쳇로봇 {
    int N,M;

    class Node{
        int x,y,cnt;
        Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    public Node get_next(Node cur, int dir, String[] board){
        int x = cur.x;
        int y = cur.y;

        int nx,ny;
        while(true){
            nx = x + dx[dir];
            ny = y + dy[dir];

            if(nx < 0 || nx >= N || ny < 0 || ny >= M || board[nx].charAt(ny) == 'D') break;

            x = nx;
            y = ny;
        }

        return new Node(x,y,0);
    }

    public int simulation(String[] board){
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        boolean isEnd = false;
        for(int i=0; i<N;i++){
            for(int j=0; j<M;j++){
                if(board[i].charAt(j) == 'R'){
                    q.add(new Node(i,j,0));
                    visited[i][j] = true; // 여기를 다시 지나치는 것은, 시작점을 다시 온것 -> 최단 거리가 아님
                    isEnd = true;
                    break;
                }
            }
            if(isEnd) break;
        }

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(board[cur.x].charAt(cur.y) == 'G') return cur.cnt;

            for(int k =0; k<4; k++){
                Node nxt = get_next(cur, k, board);
                if(!visited[nxt.x][nxt.y]){
                    q.add(new Node(nxt.x, nxt.y, cur.cnt + 1));
                    visited[nxt.x][nxt.y] = true;
                }
            }

        }

        return -1;

    }

    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();

        return simulation(board);
    }


}