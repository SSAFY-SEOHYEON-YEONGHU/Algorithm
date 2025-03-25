package bfs;

import java.io.*;
import java.util.*;
public class BOJ_250325_불 {
    static int tc, w,h;
    static char[][][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static StringBuilder sb = new StringBuilder();
    static class Node{
        int x,y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static int simulation(Queue<Node> sang, Queue<Node> fire){
        int time = 0;

        while(time <= w*h){
            time++;

            // 1. 상근 이동
            int sSize = sang.size();
            for (int i = 0; i < sSize; i++) {
                Node cur = sang.poll();

                if(map[cur.x][cur.y][0] == '*') continue;

                for (int k = 0; k < 4; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w) return time;
                    if(map[nx][ny][0] == '#' || map[nx][ny][0] == '*' || map[nx][ny][1] == '@') continue;
                    map[nx][ny][1] = '@';
                    sang.add(new Node(nx,ny));
                }
            }

            // 2. 불 번짐
            int fSize = fire.size();
            for (int i = 0; i < fSize; i++) {
                Node cur = fire.poll();

                for (int k = 0; k < 4; k++) {
                    int nx = cur.x + dx[k];
                    int ny = cur.y + dy[k];
                    if(nx < 0 || nx >= h || ny < 0 || ny >= w ||
                            map[nx][ny][0] == '#' || map[nx][ny][0] == '*') continue;
                    map[nx][ny][0] = '*';
                    fire.add(new Node(nx,ny));
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        tc = Integer.parseInt(br.readLine());
        for (int t = 0; t < tc; t++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new char[h][w][2];

            Queue<Node> sang = new ArrayDeque<>();
            Queue<Node> fire = new ArrayDeque<>();

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j][0] = s.charAt(j);
                    if(map[i][j][0] == '*') fire.add(new Node(i,j));
                    else if(map[i][j][0] == '@') {
                        map[i][j][0] = '.';
                        map[i][j][1] = '@';
                        sang.add(new Node(i,j));
                    }
                }
            }

            //
            int result = simulation(sang, fire);
            if(result == -1)sb.append("IMPOSSIBLE").append("\n");
            else sb.append(result).append("\n");
        }
        System.out.println(sb);
    }
}
