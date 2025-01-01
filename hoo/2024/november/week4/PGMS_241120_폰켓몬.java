import java.util.*;

class Solution {

    public int solution(int[] nums) {
        int answer = choosePonkemon(nums);

        return answer;
    }

    int choosePonkemon(int[] nums) {
        int ponkemonCount = countPonkemonType(nums);    // 맵에 저장된 키, 밸류 쌍 수 = 폰켓몬 종류 수

        return (ponkemonCount < nums.length/2)? ponkemonCount:nums.length/2;    // 폰켓몬 종류 수가 최대로 고를 폰켓몬 수보다 작다면 그 종류 수 반환, 크다면 최대로 고를 폰켓몬 수만큼만 반환
    }

    int countPonkemonType(int[] nums) { // 각 폰켓몬 종류가 몇이나 되는지 알아보는 함수
        Map<Integer, Integer> ponkemons = new HashMap<>();
        int now;
        for (int i = 0; i < nums.length; i++) {
            now = nums[i];
            if (!ponkemons.containsKey(now)) ponkemons.put(now, 1); // 맵에 없던 폰켓몬이면 새로운 폰켓몬이니까 맵에 저장
        }

        return ponkemons.size();
    }

}