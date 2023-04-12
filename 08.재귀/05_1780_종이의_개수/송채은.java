import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] paper;
	static int paper_1 = 0;
	static int paper0 = 0;
	static int paper1 = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] str = br.readLine().split(" ");
			for(int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(str[j]);
			}
		}
		count(0, 0, N);
		System.out.println(paper_1);
		System.out.println(paper0);
		System.out.println(paper1);
	}
	
	static void count(int startx, int starty, int N) {
		if(N == 1) {
			if(paper[startx][starty] == -1) paper_1++;
			else if(paper[startx][starty] == 0) paper0++;
			else paper1++;
			return;
		}
		
		if(check(startx, starty, N)) {
			if(paper[startx][starty] == -1) paper_1++;
			else if(paper[startx][starty] == 0) paper0++;
			else paper1++;
			return;
		}
		else {
			count(startx, starty, N/3);
			count(startx, starty + N/3, N/3);
			count(startx, starty + N/3*2, N/3);
			count(startx + N/3, starty, N/3);
			count(startx + N/3, starty + N/3, N/3);
			count(startx + N/3, starty + N/3*2, N/3);
			count(startx + N/3*2, starty, N/3);
			count(startx + N/3*2, starty + N/3, N/3);
			count(startx + N/3*2, starty + N/3*2, N/3);
		}
	}
	
	static boolean check(int startx, int starty, int N) {
		int temp = paper[startx][starty];
		for(int i = startx; i < startx + N; i++) {
			for(int j = starty; j < starty + N; j++) {
				if(paper[i][j] != temp)
					return false;
			}
		}
		return true;
	}
}