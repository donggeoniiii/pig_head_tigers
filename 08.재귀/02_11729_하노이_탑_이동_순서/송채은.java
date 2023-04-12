import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int K = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println((int) Math.pow(2, N) - 1);
		recur(1, 3, N);
		System.out.println(sb.toString());
	}
	
	static void recur(int from, int to, int N) {
		if(N == 1) {
			sb.append(from + " " + to + "\n");
			return;
		}
		
		int notuse = 6 - from - to;
		recur(from, notuse, N-1);
		sb.append(from + " " + to + "\n");
		recur(notuse, to, N-1);
	}
}