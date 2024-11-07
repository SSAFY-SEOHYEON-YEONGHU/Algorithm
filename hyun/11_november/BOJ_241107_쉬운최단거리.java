package bfs;

import java.io.*;
import java.util.*;

public class BOJ_241107_쉬운최단거리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splits = br.readLine().split(" ");
        int n = Integer.parseInt(splits[0]);
        int m = Integer.parseInt(splits[1]);
        int[][] distance = new int[n][m];
        boolean[][] cantGo = new boolean[n][m];

        int[] yQ = new int[n * m];
        int[] xQ = new int[n * m];
        int front = 1;
        int back = 0;

        StringBuilder sb = new StringBuilder((n * m) << 3);

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                switch (line.charAt(j << 1)) {
                    case '0':
                        cantGo[i][j] = true;
                        break;
                    case '2':
                        distance[i][j] = 1;
                        yQ[0] = i;
                        xQ[0] = j;
                        break;
                }
            }
        }

        while (front != back) {
            int y = yQ[back];
            int x = xQ[back++];

            if (x > 0 && cantGo[y][x - 1] == false && distance[y][x - 1] == 0) {
                yQ[front] = y;
                xQ[front++] = x - 1;
                distance[y][x - 1] = distance[y][x] + 1;
            }

            if (x < m - 1 && cantGo[y][x + 1] == false && distance[y][x + 1] == 0) {
                yQ[front] = y;
                xQ[front++] = x + 1;
                distance[y][x + 1] = distance[y][x] + 1;
            }

            if (y > 0 && cantGo[y - 1][x] == false && distance[y - 1][x] == 0) {
                yQ[front] = y - 1;
                xQ[front++] = x;
                distance[y - 1][x] = distance[y][x] + 1;
            }

            if (y < n - 1 && cantGo[y + 1][x] == false && distance[y + 1][x] == 0) {
                yQ[front] = y + 1;
                xQ[front++] = x;
                distance[y + 1][x] = distance[y][x] + 1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (distance[i][j] != 0)
                    sb.append(distance[i][j] - 1).append(' ');
                else if (cantGo[i][j])
                    sb.append("0 ");
                else
                    sb.append("-1 ");
            }
            sb.append('\n');
        }

        System.out.print(sb);
    }
}
