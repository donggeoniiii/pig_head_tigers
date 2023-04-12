import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] xy;
	static int cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int input = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        
        recur((int)Math.pow(2, input), r, c);
        bw.write(cnt + "");
        
        br.close();
        bw.flush();
        bw.close();
	}
	
	public static void recur(int N, int x, int y) {
		if(N < 2) return;
		else if(x < N / 2 && y < N / 2) {
			recur(N / 2, x % (N/2), y % (N/2));
		}
        else if(x < N / 2 && y >= N / 2) {
			cnt += (N/2) * (N/2);
			recur(N / 2, x % (N/2), y % (N/2));
		}
		else if(x >= N / 2 && y < N / 2) {
			cnt += (N/2) * (N/2) * 2;
			recur(N / 2, x % (N/2), y % (N/2));
		}
		else {
			cnt += (N/2) * (N/2) * 3;
			recur(N / 2, x % (N/2) , y % (N/2));
		}
	}
}
