package silver._1074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int count = 0;
    public static int r;
    public static int c;
    public static void z(int n, int x, int y) {

        int m = (int) Math.pow(2, n);
        
        // 재귀 종료
        if (n == 0 ) return ;
        
        // 1사분면
        if (0 <= r && r < m / 2 && 0 <= c && c < m / 2) {
            z(n - 1, x, y);
        }
        // 2사분면
        else if(0 <= r && r < m/2 && m/2 <= c  && c < m){
            count += Math.pow(m / 2, 2);
            z(n - 1, x, y + m / 2);
        }
        
        // 3사분면
        else if(m/2 <= r && r < m && 0 <= c  && c < m/ 2) {
            count += Math.pow(m / 2, 2)*2;
            z(n - 1, x + m/2, y);
        }
        
        // 4사분면
        else{
            count += Math.pow(m / 2, 2)*3;
            z(n - 1, x + m/2, y + m/2);
            }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        z(n, 0, 0);
        System.out.println(count);
    }
}
