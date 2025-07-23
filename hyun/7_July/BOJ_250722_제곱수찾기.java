package implementation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
1. 방향 우,하 만 생각함
2. 선택하는 기준을 공차를 범위 넘어갈때까지 선택할 수 있다고 생각함
2-1. 그래서 맨 처음 수도 제곱수 확인 안함
 */
public class BOJ_250722_제곱수찾기 {
    static int N,M;
    static char[][] map;
    static int[] ddx = {-1,1,1,-1};
    static int[] ddy = {1,-1,1,-1};

    static long makeNum(int x, int y){
        long result = -1;
        for (int dx = 0; dx < N; dx++) {
            for (int dy = 0; dy < M; dy++) {
                if(dx == 0 && dy == 0){
                    int a = (int)Math.sqrt(map[x][y] - '0');
                    if((map[x][y] - '0') == Math.pow(a,2)) result = Math.max(result, map[x][y] - '0');

                    continue;
                }

                // 해당 dx, dy 는 +,- 조합으로 이루어짐
                for (int k = 0; k < 4; k++) {
                    int ndx = dx * ddx[k];
                    int ndy = dy * ddy[k];

                    String madeNum = "";

                    int nx = x;
                    int ny = y;
                    while(true){
                        madeNum += map[nx][ny];

//                        System.out.println(madeNum);
                        int a = (int)Math.sqrt(Long.parseLong(madeNum));
                        if(Long.parseLong(madeNum) == Math.pow(a,2)) result = Math.max(result, Long.parseLong(madeNum));

                        nx += ndx;
                        ny += ndy;
                        if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
                    }
                }
            }
        }


        return result;
    }

    static void simulation(){
        long answer = -1;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                answer = Math.max(answer, makeNum(i,j));
            }
        }

        System.out.println(answer);
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        //
        simulation();
    }
}
