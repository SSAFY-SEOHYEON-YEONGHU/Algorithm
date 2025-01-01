package september.week4;

import java.util.HashMap;
import java.util.Map;

public class PGMS_240925_보석쇼핑 {

    public int[] solution(String[] gems) {
        int[] answer = findAnswer(gems);

        return answer;
    }

    int[] findAnswer(String[] gems) {
        Map<String, Integer> gemsMap = new HashMap<>();
        int gemVaries = 0;  // 보석 종류의 수
        String gem;
        for (int i = 0; i < gems.length; i++) { // map에 보석의 이름을 key로하는 값들 저장
            gem = gems[i];
            if (!gemsMap.containsKey(gem)) {
                gemsMap.put(gem, 0);
                gemVaries++;
            }
        }

        int[] answer = new int[2]; // 정답 배열
        int minAnswerLength = Integer.MAX_VALUE;    // 모든 보석을 다 가지는 경우 중 가장 짧은 구간의 길이를 저장할 변수

        int right = 0, left = 0;
        String rightGem, leftGem;
        rightGem = gems[right];
        leftGem = gems[left];
        gemsMap.put(rightGem, 1);
        int tempGemVaries = 1;  // 가변 슬라이딩 윈도우 내에 있는 보석 종류의 수
        while (left <= gems.length-1) { // 보석 수를 충족한 경우 최소 구간 비교를 위해 왼쪽 포인터가 마지막에 도달했을 때까지 반복문 수행
            if (tempGemVaries == gemVaries) {   // 모든 보석 종류를 다 가졌을 때
                if (minAnswerLength > right - left) {
                    answer = new int[] {left+1, right+1};
                    minAnswerLength = right - left;
                }
            }
            if (left == gems.length - 1) break; // 왼쪽 포인터가 마지막에 도달한 경우 반복 종료

            if (left == right || (tempGemVaries < gemVaries && right < gems.length - 1)) {    // 왼쪽 포인터와 오른쪽 포인터 위치가 같거나 / 보석 종류수가 부족하고, 오른쪽 포인터가 끝까지 가지 않은 경우 우측 포인터 이동
                right++;
                rightGem = gems[right];
                gemsMap.put(rightGem, gemsMap.get(rightGem) + 1);
                if (gemsMap.get(rightGem) == 1) tempGemVaries++;
            } else if (tempGemVaries == gemVaries || right == gems.length - 1) { // 보석 종류수가 채워져있거나 / 오른쪽 포인터가 끝까지 도달한 경우
                gemsMap.put(leftGem, gemsMap.get(leftGem) - 1); // 왼쪽 포인터가 가르키던 보석의 개수 -1
                if (gemsMap.get(leftGem) == 0) tempGemVaries--; // 그때 그 보석을 가진 개수가 0이면 보석 종류 수 -1
                left++; // 왼쪽 포인터 이동
                leftGem = gems[left];
            }
        }

        return answer;
    }

}
