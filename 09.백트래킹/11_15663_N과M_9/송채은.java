import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static int M;
	static int[] arr;
	static boolean[] used;
	static int[] sequence;
	static String temp = "";
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		used = new boolean[N];
		st = new StringTokenizer(br.readLine(), " ");
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
		
		int tmp = 0;
		for(int i = 0; i < arr.length; i++) {
			if(!used[i] && tmp != arr[i]) {
				used[i] = true;
				sequence[m] = arr[i];
				tmp = arr[i];
				seq(m + 1);
				used[i] = false;
			}
		}
	}
	
}