
import java.io.*;
import java.util.*;

public class PGMS_241011_무인도여행 {
    String[] map;
    int N,M;
    int[] dx = {-1,1,0,0};
    int[] dy = {0,0,-1,1};

    ArrayList<Integer> result = new ArrayList<>();
    class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public int bfs(boolean[][] visited, int x, int y){
        Queue<Node> q = new ArrayDeque<>();
        int sum = 0;

        q.add(new Node(x,y));
        visited[x][y] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            sum += map[cur.x].charAt(cur.y)-'0';

            for(int k=0; k<4;k++){
                int nx = cur.x + dx[k];
                int ny = cur.y + dy[k];
                if(nx <0 || nx>=N || ny <0 || ny >= M || visited[nx][ny] || map[nx].charAt(ny) =='X') continue;
                q.add(new Node(nx,ny));
                visited[nx][ny] = true;
            }
        }

        return sum;
    }

    public int[] simulation(){
        boolean[][] visited = new boolean[N][M];
        for(int i=0; i<N; i++){
            for(int j=0; j<M;j++){
                if(map[i].charAt(j) == 'X' || visited[i][j]) continue;
                result.add(bfs(visited,i,j));
            }
        }

        if(result.size() == 0) return new int[]{-1};

        Collections.sort(result);

        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++) answer[i] = result.get(i);
        return answer;
    }
    public int[] solution(String[] maps) {
        map = maps;
        N = maps.length;
        M = maps[0].length();

        return simulation();

    }
}