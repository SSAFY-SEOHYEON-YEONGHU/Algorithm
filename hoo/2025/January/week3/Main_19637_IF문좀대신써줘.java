package twentytwentyfive.january.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_19637_IF문좀대신써줘 {

    static int N, M;
    static List<String[]> titleList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        init(br);
        printTitle(br);
    }

    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        titleList = new ArrayList<>();
        titleList.add(new String[] {"NO", "0"});    // 맨 처음 값인 0 저장

        int titleCount = 0; // 이때까지 저장한 칭호들 개수
        String title, value;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            title = st.nextToken();
            value = st.nextToken();
            if (i != 0 && value.equals(titleList.get(titleCount)[1])) continue;   // 이미 같은 상한선이 저장된 경우는 건너 뜀

            titleList.add(new String[] {title, value});
            titleCount++;
        }
    }

    static void printTitle(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();

        int attackAbility;
        for (int i = 0; i < M; i++) {
            attackAbility = Integer.parseInt(br.readLine());
            sb.append(calcTitle(attackAbility)).append("\n");
        }

        System.out.println(sb);
    }

    static String calcTitle(int attackAbility) {    // 상한선을 이용한 이분탐색으로 타이틀 값 찾음
        int left = 0;
        int right = titleList.size();
        int mid;
        while (left+1 < right) {
            mid = (left + right) / 2;

            if (attackAbility > Integer.parseInt(titleList.get(mid)[1])) left = mid;
            else right = mid;
        }
        String title = (left == 0)? titleList.get(1)[0]:titleList.get(right)[0];

        return title;
    }

}
