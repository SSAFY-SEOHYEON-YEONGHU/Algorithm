package august.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PGMS_240824_혼자하는틱택토 {

    boolean isRight;    // 틱택토 판을 만들어 가던 중 주어진 판과 동일한 판을 발견했는 지 판단
    char[] OX = new char[] {'X', 'O'};  // 턴에 따라 놓을 말

    public int solution(String[] board) {
        int checkedCount = countChecked(board);
        if (checkedCount == 0) return 1;    // 아무 말도 안놓은 경우는 바로 맞는 보드로 처리
        isRight = false;

        char[][] boardForCheck;
        for (int i = 0; i < 3; i++) {   // 각 칸 시작하는 경우를 모두 체크
            for (int j = 0; j < 3; j++) {
                boardForCheck = initBoardForCheck(i, j);
                makeBoard(board, checkedCount, i, j, 1, boardForCheck);
                if (isRight) return 1;  // 맞는 보드임이 판명됐으면 결과 반환하고 중단
            }
        }

        return 0;
    }

    int countChecked(String[] board) {
        int checkedCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) != '.') checkedCount++;
            }
        }

        return checkedCount;
    }

    char[][] initBoardForCheck(int row, int col) {
        char[][] boardForCheck = new char[3][3];
        for (int i = 0; i < 3; i++) {   // 체크를 위한 보드의 초기 모습 세팅
            for (int j = 0; j < 3; j++) {
                boardForCheck[i][j] = '.';
            }
        }
        boardForCheck[row][col] = OX[1];  // 맨 처음 O는 시작할 위치에 놓은 걸로 처리

        return boardForCheck;
    }

    void makeBoard(String[] board, int checkedCount, int row, int col, int nowCount, char[][] nowBoard) {
        if (isRight) return;    // 이미 맞는 보드임이 판명되면 이후 진행 안함
        if (nowCount == checkedCount) { // 기저, 주어진 보드에 말이 놓인 만큼 말을 놨다면 같은 모양인 지 체크하고 종료
            if (checkIsSame(board, nowBoard)) isRight = true;
            return;
        }
        if (judgeIsTicTacToe(row, col, nowBoard)) return; // 방금 놓은 말을 통해 보드에서 틱택토가 나왔다면 게임 중단

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == row && j == col) || nowBoard[i][j] != '.') continue;  // 현재 칸이거나 놓을 칸에 이미 말이 놓여있는 경우는 건너 뜀
                nowBoard[i][j] = OX[(nowCount+1)%2];
                makeBoard(board, checkedCount, i, j, nowCount+1, nowBoard);
                nowBoard[i][j] = '.';
            }
        }

    }

    boolean checkIsSame(String[] board, char[][] nowBoard) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) != nowBoard[i][j]) return false; // 모양 다르면 거짓 반환
            }
        }

        return true;
    }

    boolean judgeIsTicTacToe(int row, int col, char[][] nowBoard) { // 현재 말을 둔 칸에 대해 틱택토 여부 판단
        boolean isTicTacToeRow = true;
        boolean isTicTacToeCol = true;
        for (int i = 1; i < 3; i++) {
            if (nowBoard[row][i-1] != nowBoard[row][i]) isTicTacToeRow = false;
            if (nowBoard[i-1][col] != nowBoard[i][col]) isTicTacToeCol = false;
        }

        boolean isTicTacToeDiagonal = false;
        if (row == col) {   // 왼쪽 대각선인 경우
            if (nowBoard[0][0] == nowBoard[1][1] && nowBoard[1][1] == nowBoard[2][2]) isTicTacToeDiagonal = true;
        }
        if (row + col == 2)  {   // 오른쪽 대각선인 경우
            if (nowBoard[0][2] == nowBoard[1][1] && nowBoard[1][1] == nowBoard[2][0]) isTicTacToeDiagonal = true;
        }

        return isTicTacToeRow || isTicTacToeCol || isTicTacToeDiagonal;
    }

}
