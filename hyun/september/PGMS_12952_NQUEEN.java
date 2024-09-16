package backtracking;

import java.util.*;

public class PGMS_12952_NQUEEN {
    ArrayList<Node> axios = new ArrayList<>();
    int N;
    int result = 0;
    int[][] map;
    int[] dx = {-1,-1,1,1};
    int[] dy = {-1,1,-1,1};

    public class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public boolean checkOne(int x, int y){
        // 가로
        for(int i=0; i<N; i++) if(y!=i && map[x][i] == 1) return false;
        // 세로
        for(int i=0; i<N; i++) if(x!=i && map[i][y] == 1) return false;
        //대각선
        for(int k=0; k<N; k++){
            int cx = x;
            int cy = y;
            while(true){
                cx += dx[k];
                cy += dy[k];
                if(cx < 0 || cx >= N || cy < 0 || cy >= N) break;
                if(map[cx][cy] == 1) return false;
            }
        }

        return true;
    }

    public boolean checkAll(){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j] == 1)
                    if(!checkOne(i,j)) return false;
            }
        }
        return true;
    }

    public void dfs(int idx, int cnt){
        if(cnt == N){
            if(checkAll()) result++;
            return;
        }

        for(int i=idx; i<axios.size(); i++){
            Node cur = axios.get(i);
            map[cur.x][cur.y] = 1;

            dfs(i+1, cnt+1);

            map[cur.x][cur.y] = 0;
        }
    }

    public void simulation(){
        // axios 에 4x4 좌표들 채워주기
        for(int i=0; i<N; i++)
            for(int j=0; j<N; j++)
                axios.add(new Node(i,j));

        // dfs
        dfs(0,0);

    }
    public int solution(int n) {
        N = n;
        map = new int[N][N];
        simulation();
        return result;
    }
}