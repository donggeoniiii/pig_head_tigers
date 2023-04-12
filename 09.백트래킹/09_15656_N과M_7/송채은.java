import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int M;
	static int[] arr;
	static int[] sequence;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		sequence = new int[M];
		seq(0);
		
		System.out.print(sb.toString());
	}
	
	public static void seq(int m) {
		if(m == M) {
			for(int i = 0; i < M; i++)
				sb.append(sequence[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i = 0; i < arr.length; i++) {
			sequence[m] = arr[i];
			seq(m + 1);
		}
	}
	
}