package _11003;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Fail1 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		int[] min = new int[N];
		for(int i = 0; i < N; i++) {
			int temp = arr[i];
			for(int l = 0; l < L; l++) {
				if(i - l >= 0) temp = Math.min(temp,  arr[i - l]);
			}
			min[i] = temp;
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++)
			sb.append(min[i] + " ");
		System.out.println(sb.toString());
	}
}
