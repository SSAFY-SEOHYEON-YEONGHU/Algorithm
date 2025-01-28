package twentytwentyfive.january.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2631_줄세우기 {

    static int N;
    static int[] children;

    public static void main(String[] args) throws IOException {
        init();
        lineChildren();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        children = new int[N];
        for (int i = 0; i < N; i++) children[i] = Integer.parseInt(br.readLine());
    }

    static void lineChildren() {
        int maxInOrder = 0;   // 가장 길게 오름차순 정렬이 돼있는 학생들의 수
        int[] inOrderCount = new int[N];
        for (int i = 0; i < N; i++) {
            inOrderCount[i] = 1;    // 일단 본인 포함해서 카운트할 거라 1로 초기화
            for (int j = 0; j < i; j++) {
                if (children[i] > children[j]) inOrderCount[i] = Math.max(inOrderCount[i], inOrderCount[j] + 1);    // 자신의 이전 순서 학생 발견되면, 그 학생의 값+1과 현재 자신의 값 중 큰 값으로 갱신
            }
            maxInOrder = Math.max(maxInOrder, inOrderCount[i]);
        }
        System.out.println(N-maxInOrder);   // 전체 학생 수에서 가장 길게 순서대로 배정이 된 학생들 수 빼주면 새로 정렬해줘야 하는 학생들 수가 나옴
    }

}
