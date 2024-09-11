package bfs;

import java.util.*;

class PGMS_159993_미로탈출 {
    int N,M;
    char[][] map;

    Node start;
    Node lever;
    Node end;

    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int BFS(Node start, Node end){
        Queue<Node> q = new ArrayDeque<>();
        int[][] visited = new int[N][M];

        q.add(start);
        visited[start.x][start.y] = 1;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.x == end.x && cur.y == end.y) return visited[cur.x][cur.y] - 1;
            for(int k=0; k<4; k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny] !=0 || map[nx][ny] == 'X') continue;
                q.add(new Node(nx,ny));
                visited[nx][ny] = visited[cur.x][cur.y] + 1;
            }
        }

        return -1;
    }

    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        map = new char[N][M];

        for(int i=0; i<N; i++) {
            map[i] = maps[i].toCharArray();
            for(int j=0; j<M; j++){
                if(map[i][j] == 'S') start = new Node(i,j);
                else if(map[i][j] == 'L') lever = new Node(i,j);
                else if(map[i][j] == 'E') end = new Node(i,j);
            }
        }

        int a = BFS(start, lever);
        int b = BFS(lever, end);

        if(a != -1 && b != -1) return a+b;
        return -1;
    }
}