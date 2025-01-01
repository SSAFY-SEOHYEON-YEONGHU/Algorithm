package september.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PGMS_240912_시소짝꿍 {

    public long solution(int[] weights) {
        Map<Integer, Integer> sameNumberMap = findSameNumbers(weights);  // 우선 같은 수가 몇 번 나오는 지 숫자마다 모두 체크
        long answer = countPair(weights, sameNumberMap);  // 그 후 짝지을 수 있는 경우를 게산

        return answer;
    }

    Map<Integer, Integer> findSameNumbers(int[] weights) {  // 숫자들의 등장 횟수를 파악
        Map<Integer, Integer> sameNumberMap = new HashMap<>();
        for (int i = 0; i < weights.length; i++) {
            if (sameNumberMap.containsKey(weights[i])) sameNumberMap.put(weights[i], sameNumberMap.get(weights[i])+1);
            else sameNumberMap.put(weights[i], 1);
        }

        return sameNumberMap;
    }

    long countPair(int[] weights, Map<Integer, Integer> sameNumberMap) {
        long answer = 0;
        List<Integer> noDuplicateNumbers = makeNoDuplicateNumbers(weights, sameNumberMap);  // 같은 숫자 중복을 제거한 숫자들만 남긴 리스트 생성
        int[] multipliedWeights = new int[4_001];   // 각 무게에 2, 3, 4를 곱한 값을 저장할 배열
        for (int i = 0; i < noDuplicateNumbers.size(); i++) {
            for (int j = 2; j <= 4; j++) {
                multipliedWeights[noDuplicateNumbers.get(i) * j] += sameNumberMap.get(noDuplicateNumbers.get(i));
            }
            if (sameNumberMap.get(noDuplicateNumbers.get(i)) >= 2) answer -= calcCombination(sameNumberMap.get(noDuplicateNumbers.get(i))) * 2L; // 중복되는 수가 있는 경우는 만들 수 있는 조합 개수 * 2(결국 2, 3, 4 곱한 세 가지 중 한 경우만 카운트 돼야하므로)를 미리 빼줌
        }

        for (int i = 0; i < multipliedWeights.length; i++) {    // 각 무게를 만들 수 있는 경우가 2 이상이면 짝이루는 게 가능함. 그 개수에서 2개를 고르는 조합의 수를 정답에 더해줌
            if (multipliedWeights[i] >= 2) answer += calcCombination(multipliedWeights[i]);
        }

        return answer;
    }

    long calcCombination(int n) {  // n개 중 2개를 골라 쌍을 만드는 경우를 계산
        return (long) n * ((long) n - 1L) / 2L;
    }

    List<Integer> makeNoDuplicateNumbers(int[] weights, Map<Integer, Integer> sameNumberMap) {  // 중복이 없는 수로 이루어진 리스트를 생성하는 함수
        boolean[] isChecked = new boolean[1_001];
        List<Integer> noDuplicateNumbers = new ArrayList<>();
        for (int i = 0; i < weights.length; i++) {
            if (isChecked[weights[i]]) continue;   // 이미 앞에서 계산한 여러개인 수이므로 건너 뜀
            else if (sameNumberMap.get(weights[i]) > 1) isChecked[weights[i]] = true;
            noDuplicateNumbers.add(weights[i]);
        }

        return noDuplicateNumbers;
    }

}
