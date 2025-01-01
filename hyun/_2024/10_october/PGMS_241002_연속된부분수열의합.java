package two_pointer;

public class PGMS_241002_연속된부분수열의합 {
    int[] answer = {0,1000010};
    public int[] solution(int[] sequence, int k) {
        int sum = 0;
        int left = 0;

        for(int right = 0; right < sequence.length; right++){
            sum += sequence[right];

            if(sum == k){
                if(answer[1] - answer[0] > right - left) answer = new int[]{left,right};
            }
            else if(sum > k){
                while(true){
                    sum -= sequence[left++];

                    if(sum <= k){
                        if(sum == k) if(answer[1] - answer[0] > right - left) answer = new int[]{left,right};
                        break;
                    }
                }
            }
        }

        return answer;
    }
}