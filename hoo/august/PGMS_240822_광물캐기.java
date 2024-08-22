package august;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PGMS_240822_광물캐기 {

    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int[] neededEnerge = calcNeededEnerge(picks, minerals);
        answer = findBestCase(picks, minerals, neededEnerge);

        return answer;
    }

    int[] calcNeededEnerge(int[] picks, String[] minerals) {    // 5개씩 끊어서 돌 곡괭이 기준 피로도 저장하는 함수
        int picksCount = 0; // 곡괭이 개수만큼만 광물 캐는 데 사용 가능하므로 곡괭이 개수도 구해야 함.
        for (int i = 0; i < picks.length; i++) picksCount += picks[i];
        int dividedByFive = minerals.length%5 == 0 ? minerals.length/5 : minerals.length/5 + 1; // 우선 광물을 5개씩 끊었을 때 몇 칸이 될지 구함
        dividedByFive = Math.min(dividedByFive, picksCount);    // 곡괭이 개수와 비교하여 둘 중 낮은 값으로 저장
        int[] neededEnerge = new int[dividedByFive];

        int energeIndex = 0;
        int tempSum = 0;
        for (int i = 0; i < Math.min(minerals.length, dividedByFive * 5); i++) { // 모든 광물 돌며(곡괭이 개수가 더 적으면 곡괭이 개수만큼만) 5개씩 끊은 광물에 필요한 피로도 저장하기
            if (i != 0 && i%5 == 0) {
                neededEnerge[energeIndex] = tempSum;
                energeIndex++;
                tempSum = 0;
            }

            switch (minerals[i]) {
                case "diamond":
                    tempSum += 25;
                    break;
                case "iron":
                    tempSum += 5;
                    break;
                case "stone":
                    tempSum += 1;
                    break;
            }
        }
        neededEnerge[energeIndex] = tempSum;    // 마지막 인덱스 피로도 저장

        return neededEnerge;
    }

    int findBestCase(int[] picks, String[] minerals, int[] neededEnerge) {
        int totalEnerge = 0;    // 다 캐는데 드는 피로도

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() { // 피로도 큰 순, 인덱스와 함께 pq에 저장
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        for (int i = 0; i < neededEnerge.length; i++) pq.offer(new int[] {neededEnerge[i], i});

        int pickIndex = 0;  // 곡괭이의 인덱스
        int mineralStartIndex;
        while (!pq.isEmpty()) { // 크기 순으로 저장한 피로도에 대해
            int[] nowNeededEnerge = pq.poll();
            while (picks[pickIndex] == 0) pickIndex++; // 곡괭이 다 썼으면 다음 곡괭이 사용

            mineralStartIndex = nowNeededEnerge[1] * 5;    // 캐기 시작할 광물의 인덱스
            totalEnerge += mineMineral(minerals, pickIndex, mineralStartIndex);
            picks[pickIndex]--;
        }

        return totalEnerge;
    }

    int mineMineral(String[] minerals, int pickIndex, int mineralStartIndex) {
        int energeSum = 0;

        int[][] energeGraph = new int[][] {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};

        for (int i = mineralStartIndex; i < Math.min(mineralStartIndex + 5, minerals.length); i++) { // 현재 곡괭이로 인덱스에 해당하는 광물들을 캠
            switch (minerals[i]) {
                case "diamond":
                    energeSum += energeGraph[pickIndex][0];
                    break;
                case "iron":
                    energeSum += energeGraph[pickIndex][1];
                    break;
                case "stone":
                    energeSum += energeGraph[pickIndex][2];
                    break;
            }
        }

        return energeSum;
    }

}
