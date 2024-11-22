package november.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_18428_감시피하기 {

    static int N;
    static char[][] map;    // T: 선생, S: 학생, O: 장애물
    static List<int[]> blanks;  // 빈칸의 좌표
    static List<int[]> teachers;    // 선생님들의 좌표
    static boolean isPossible;

    public static void main(String[] args) throws IOException {
        init();
        chooseThreeBlanks(0, 0, new boolean[blanks.size()], new ArrayList<>());
        System.out.println(isPossible? "YES":"NO");
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        blanks = new ArrayList<>();
        teachers = new ArrayList<>();
        String row;
        for (int i = 0; i < N; i++) {
            row = br.readLine();
            char col;
            for (int j = 0; j < N; j++) {
                col = row.charAt(j*2);
                if (col == 'X') blanks.add(new int[] {i, j});   // 빈칸이면 빈칸 리스트에 추가
                else if (col == 'T') teachers.add(new int[] {i, j});
                map[i][j] = col;
            }
        }
        isPossible = false;
    }

    static void chooseThreeBlanks(int start, int cnt, boolean[] isChosen, List<int[]> chosenBlanks) {  // start: 몇 번째 빈칸부터 볼 지, cnt: 몇 개 골랐는 지, isChosen: 이미 고른 빈칸인 지, chosenBlanks: 고른 빈칸들 좌표
        if (isPossible) return; // 이미 성공한 케이스 찾은 경우는 함수 바로 종료
        if (cnt == 3) { // 기저, 빈칸 세 곳을 고른 경우
            if (judgeIsPossible(chosenBlanks)) isPossible = true;   // 학생들 다 숨은 경우인 지 체크
//            System.out.println(Arrays.toString(isChosen));
            return;
        }

        for (int i = start; i < blanks.size(); i++) {
            if (isChosen[i]) continue;

            isChosen[i] = true;
            chosenBlanks.add(blanks.get(i));
            chooseThreeBlanks(i+1, cnt+1, isChosen, chosenBlanks);
            isChosen[i] = false;
            chosenBlanks.remove(chosenBlanks.get(chosenBlanks.size()-1));
        }
    }

    static boolean judgeIsPossible(List<int[]> chosenBlanks) {  // 빈칸 세 곳을 다 채운 경우 숨는 게 가능한 지 판단하는 함수
        for (int i = 0; i < chosenBlanks.size(); i++) map[chosenBlanks.get(i)[0]][chosenBlanks.get(i)[1]] = 'O';    // 고른 곳에 장애물 두기

        int[] dirRow = new int[] {-1, 0, 1, 0}; // 상 우 하 좌
        int[] dirCol = new int[] {0, 1, 0, -1};
        int[] nowTeacher;
        for (int i = 0; i < teachers.size(); i++) { // 모든 선생님들의 시선에서 학생 보이는 지 체크
            nowTeacher = teachers.get(i);
            for (int d = 0; d < 4; d++) {
                int nextRow = nowTeacher[0];
                int nextCol = nowTeacher[1];
                while (true) {
                    nextRow += dirRow[d];
                    nextCol += dirCol[d];
                    if (isOuted(nextRow, nextCol) || map[nextRow][nextCol] == 'O') break;   // 학생을 마주치지 않고 범위 밖으로 나가거나 장애물을 만난 경우 이번 방향 탐색 종료
                    if (map[nextRow][nextCol] == 'S') {
                        for (int j = 0; j < chosenBlanks.size(); j++) map[chosenBlanks.get(j)[0]][chosenBlanks.get(j)[1]] = 'X';    // 장애물 뒀던 곳 원상복귀

                        return false;  // 그 외의 경우에 학생을 마주쳤다면 이건 실패한 경우임
                    }
                }
            }
        }

        return true;    // 모든 선생님이 학생을 마주치지 못했다면 가능한 경우임을 반환
    }

    static boolean isOuted(int row, int col) {
        if ((0 <= row && row < N) && (0 <= col && col < N)) return false;
        return true;
    }

}
