package _1021;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		int[] target = new int[M];
		for(int i = 0; i < M; i++) {
			target[i] = Integer.parseInt(st.nextToken());
		}
		
		//*
		LinkedList<Integer> d = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			d.add(i);
		}
		
		int count = 0;
		for(int i = 0; i < M; i++) {
			int idx = d.indexOf(target[i]);
			if(idx > (d.size() - 1) / 2) {
				while(d.peekFirst() != target[i]) {
					int temp = d.pollLast();
					d.addFirst(temp);
					count++;
				}
				d.pollFirst();
			}
			else {
				while(d.peekLast() != target[i]) {
					int temp = d.pollFirst();
					d.addLast(temp);
					count++;
				}
				d.pollLast();
				count--;
			}
		}
		System.out.println(count);
	}
}
