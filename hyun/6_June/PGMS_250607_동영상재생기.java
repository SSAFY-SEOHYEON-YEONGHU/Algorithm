package implementation;

// 10초 후로 이동했을때, 10초 미만이면 -1도 10초 미만에 해당한다...
public class PGMS_250607_동영상재생기 {
    int start,end,curTime,total;

    public void setTime(String command){
        if(command.equals("next")){
            curTime += 10;
            if(total - curTime < 10) curTime = total;
        }
        else{
            curTime -= 10;
            if(curTime < 10) curTime = 0;
        }
    }

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        total = Integer.parseInt(video_len.substring(0,2)) * 60 + Integer.parseInt(video_len.substring(3,5));
        curTime = Integer.parseInt(pos.substring(0,2)) * 60 + Integer.parseInt(pos.substring(3,5));
        start = Integer.parseInt(op_start.substring(0,2)) * 60 + Integer.parseInt(op_start.substring(3,5));
        end = Integer.parseInt(op_end.substring(0,2)) * 60 + Integer.parseInt(op_end.substring(3,5));

        if(start <= curTime && curTime <= end){
            curTime = end;
        }

        for(String c : commands){
            setTime(c);
            if(start <= curTime && curTime <= end){
                curTime = end;
            }
        }

        int quotient = curTime / 60;
        int remainder = curTime % 60;
        String answer = "";

        if(quotient == 0) answer += "00:";
        else if(quotient < 10) answer += "0" + String.valueOf(quotient)+":";
        else answer += String.valueOf(quotient)+":";

        if(remainder == 0) answer += "00";
        else if(remainder < 10) answer += "0" + String.valueOf(remainder);
        else answer += String.valueOf(remainder);
        return answer;
    }
}