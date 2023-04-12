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
		seq(1, 0);
		System.out.print(sb.toString());
	}
	
	public static void seq(int n, int m) {
		if(m == M) {
			for(int i = 0; i < arr.length; i++)
				sb.append(arr[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = n; i <= N; i++) {
			arr[m] = i;
			seq(i, m + 1);
		}
	}
}