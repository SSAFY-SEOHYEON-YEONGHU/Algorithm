package december.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_2668_숫자고르기 {

    public static void main(String[] args) throws IOException {
        int[] numbers = init();
        findMaxSet(numbers);
    }

    static int[] init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N+1];
        for (int i = 1; i <= N; i++) numbers[i] = Integer.parseInt(br.readLine());

        return numbers;
    }

    static void findMaxSet(int[] numbers) {
        List<Integer> answer = new ArrayList<>();   // 조건을 만족하는 숫자를 모두 담고 있는 리스트
        boolean[] isChecked = new boolean[numbers.length]; // 각 숫자가 체크됐는 지 여부 저장할 배열
        boolean[] isSelected = new boolean[numbers.length]; // 숫자가 순한을 이루는 집합 속 숫자인 지 저장할 배열
        for (int i = 1; i < numbers.length; i++) {
            if (isSelected[i]) continue;    // 이미 정답에 포함됨이 확정된 숫자는 건너 뜀

            List<Integer> temp = new ArrayList<>();
            isChecked[i] = true;
            temp.add(i);
            answer = dfs(answer, temp, numbers, i, i, isChecked, isSelected);
            isChecked[i] = false;
            temp.remove(temp.size()-1);
        }

        Collections.sort(answer);   // 정답 숫자들 오름차순으로 정렬
        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (int i = 0; i < answer.size(); i++) sb.append(answer.get(i)).append("\n");
        System.out.println(sb);
    }

    static List<Integer> dfs(List<Integer> answer, List<Integer> temp, int[] numbers, int now, int target, boolean[] isChecked, boolean[] isSelected) {
        // temp : 이번 탐색에서 담았던 숫자들 리스트, now : 현재 숫자, target : 맨 처음 dfs를 시작했던 숫자(순환되는 지 여부를 파악하기 위해서 target으로 지정), isChecked : 지금 탐색에서 체크했던 숫자인 지 여부 저장, isSelected : 최종적으로 선택됐던 숫자인 지 여부 저장
        if (!isChecked[numbers[now]]) {
            isChecked[numbers[now]] = true;
            temp.add(numbers[now]);
            dfs(answer, temp, numbers, numbers[now], target, isChecked, isSelected);
            isChecked[numbers[now]] = false;
            temp.remove(temp.size()-1);
        }

        if (numbers[now] == target) {   // 만약 순환함이 파악됐으면 temp에 담았던 숫자들 모두 answer에 넣어 줌
            for (int i = 0; i < temp.size(); i++) {
                answer.add(temp.get(i));
                isSelected[temp.get(i)] = true;
            }
        }

        return answer;
    }

}
