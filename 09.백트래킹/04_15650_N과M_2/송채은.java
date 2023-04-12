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
		
		cur(arr, 0, 1);
		
	}
	
	static void cur(int[] arr, int idx, int start) {
		if(idx == M) {
			for(int i = 0; i < M; i++)
				System.out.print(arr[i] + " ");
			System.out.println();
			return;
		}
		if(start > N) return;
		
		arr[idx] = start;
		cur(arr, idx+1, start+1);
		
		cur(arr, idx, start+1);
	}
}