import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static boolean[][] paper;
    static int white = 0;
    static int blue = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        paper = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                if(str[j].equals("0"))
                    paper[i][j] = false;
                else paper[i][j] = true;
            }
        }

        count(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    static void count(int startx, int starty, int N) {
        if(N == 1) {
            if(paper[startx][starty]) blue++;
            else white++;
            return;
        }

        if(check(startx, starty, N)) {
            if(paper[startx][starty]) blue++;
            else white++;
            return;
        }else {
            count(startx, starty, N/2);
            count(startx, starty + N/2, N/2);
            count(startx + N/2, starty, N/2);
            count(startx + N/2, starty + N/2, N/2);
        }
    }

    static boolean check(int startx, int starty, int N) {
        boolean temp = paper[startx][starty];
        for(int i = startx; i < startx + N; i++)
            for(int j = starty; j < starty + N; j++)
                if(paper[i][j] == !temp) return false;
        return true;
    }
}