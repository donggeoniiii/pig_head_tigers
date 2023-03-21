import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[M];
		boolean[] used = new boolean[N+1];
		func(arr, used, 0);
	}
	
	static void func(int[] arr, boolean[] used, int k) {
		
		if(k == M) {
			for(int i = 0; i < M; i++)
				System.out.print(arr[i] + " ");
			System.out.println();
			return;
		}
		
		for(int i = 1; i <= N; i++) {
			if(!used[i]) {
				arr[k] = i;
				used[i] = true;
				func(arr, used, k+1);
				used[i] = false;
			}
		}
	}
}