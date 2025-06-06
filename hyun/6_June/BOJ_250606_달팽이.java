package implementation;

import java.io.*;

public class BOJ_250606_달팽이 {
    static int N,stopNum;
    static int[][] map;
    static int rx, ry;

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    static void simulation(){
        int x = N/2;
        int y = N/2;
        rx = x;
        ry = y;
        int cnt = 1;
        int num = 1;

        map[x][y] = num;

        while(true){
            for (int k = 0; k < 4; k++) {
                for (int i = 0; i < cnt; i++) {
                    x += dx[k];
                    y += dy[k];
                    num++;
                    if(num > N*N) return;

                    map[x][y] = num;
                    if(map[x][y] == stopNum){
                        rx = x;
                        ry = y;
                    }
                }

                if(k == 1 || k == 3) cnt++;
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        stopNum = Integer.parseInt(br.readLine());

        map = new int[N][N];

        simulation();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(rx+1).append(" ").append(ry+1);
        System.out.println(sb);
    }
}
