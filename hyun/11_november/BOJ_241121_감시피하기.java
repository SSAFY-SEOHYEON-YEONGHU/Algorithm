package dfs;

import java.io.*;
import java.util.*;

public class BOJ_241121_감시피하기 {
    static int N;
    static char[][] map;
    static ArrayList<Node> teacher = new ArrayList<>();
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static boolean meetStudent(){

        for(Node cur : teacher){
            for (int k = 0; k < 4; k++) {
                int x = cur.x;
                int y = cur.y;
                boolean isStudent = false;
                while(true){
                    x += dx[k];
                    y += dy[k];
                    if(x < 0 || x >= N || y < 0 || y >= N || map[x][y] == 'O' || map[x][y] == 'T') break;
                    if(map[x][y] == 'S'){
                        isStudent = true;
                        break;
                    }
                }

                if(isStudent) return true;
            }
        }

        return false;
    }

    public static boolean dfs(int cnt, int x, int y){
        if(cnt == 3){
            if(!meetStudent()) return true;
            return false;
        }

        boolean result = false;
        for (int i = x; i < N; i++) {
            for (int j = y; j < N; j++) {
                if(map[i][j] == 'X'){
                    map[i][j] = 'O';
                    result = dfs(cnt+1, x,y+1);
                    if(result) return true;
                    map[i][j] = 'X';
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp[j].charAt(0);
                if(map[i][j] == 'T') teacher.add(new Node(i,j));
            }
        }

        if(dfs(0,0,0)) System.out.println("YES");
        else System.out.println("NO");
    }
}
