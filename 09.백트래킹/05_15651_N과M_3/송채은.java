import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[M];
		seq(0);
		
		System.out.print(sb.toString());
	}
	
	public static void seq(int m) {
		if(m == M) {
			for(int i = 0; i < M; i++)
				sb.append(arr[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int num = 1; num <= N; num++) {
			arr[m] = num;
			seq(m + 1);
		}
	}
}