package twentytwentyfive.january.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1863_스카이라인쉬운거 {

    static int n;
    static int[][] skyLine;

    public static void main(String[] args) throws IOException {
        init();
        calcMinBuilding();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        skyLine = new int[n][2];

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            skyLine[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }
    }

    static void calcMinBuilding() {
        int buildingCount = 0;
        Stack<Integer> stack = new Stack<>();   // 현재 빌딩보다 낮은 높이의 빌딩들 저장해둘 스택
        int x, y;
        for (int[] building : skyLine) {
            x = building[0];
            y = building[1];
            if (!stack.isEmpty()) while (!stack.isEmpty() && stack.peek() > y) stack.pop();    // 스택에 저장된 빌딩이 있다면, 가장 위의 빌딩들을 비교해가며 현재 빌딩보다 높은 빌딩들 빼주기
            if (y != 0) {   // 현재 입력받은 높이가 0이 아니면 빌딩 개수 +, 스택에 현재 빌딩 삽입
                if (!stack.isEmpty() && stack.peek() == y) continue;    // 스택에 남은 빌딩이 같은 층 빌딩이면 건너 뜀
                buildingCount++;
                stack.push(y);
            }
//            System.out.println(x);
//            System.out.println(stack);
//            System.out.println(buildingCount);
//            System.out.println("-------------");
        }

        System.out.println(buildingCount);
    }

}
