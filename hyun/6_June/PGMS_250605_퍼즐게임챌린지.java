package binary_search;

class PGMS_250605_퍼즐게임챌린지 {

    public long func(int[] diffs, int[] times, int level){
        long sum = 0;
        int N = diffs.length;

        for(int i=0; i<N; i++){
            if(diffs[i] <= level) sum += times[i];
            else{
                sum += ((long)(times[i-1] + times[i]) * (long)(diffs[i] - level) + times[i]);
            }
        }

        return sum;

    }

    public int solution(int[] diffs, int[] times, long limit) {
        int low = 0;
        int high = 300001;

        while(low + 1< high){
            int mid = (low + high) / 2;

            if(func(diffs, times, mid) <= limit) high = mid;
            else low = mid;
        }

        return high;


    }
}