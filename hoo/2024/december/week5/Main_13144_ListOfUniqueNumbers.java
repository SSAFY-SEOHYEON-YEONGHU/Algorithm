package twentytwentyfour.december.week5;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13144_ListOfUniqueNumbers {

    static int[] numbers;

    public static void main(String[] args) throws IOException {
        init();
        calcCases();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void calcCases() {
        boolean[] isUsed = new boolean[100_001];    // 지금 선택된 수들에 해당 숫자가 포함됐는 지를 저장하는 배열

        int left = 0;
        int right = 0;
        long caseCount = 0;
        while (left < numbers.length) {
            while (right < numbers.length) {  // 중복되는 숫자 사용 전까지 right 늘려가기
                if (isUsed[numbers[right]]) break;   // 중복되는 수 마주치면 우측 포인터 늘려가는 것 중단
                isUsed[numbers[right]] = true;  // 사용한 숫자임을 체크
                right++;
            }

            caseCount += ((long) right - (long) left);
            isUsed[numbers[left++]] = false;
        }
        System.out.println(caseCount);
    }

}
