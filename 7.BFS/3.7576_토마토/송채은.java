import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] box = new int[N+2][M+2];
        for(int i = 0; i < box.length; i++)
        	Arrays.fill(box[i], 2);
        
        boolean[][] visited = new boolean[N+2][M+2]; 

        for(int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j < M+1; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Node> q = new LinkedList<>();
        int days;
        int maxdays = 0;

        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < M+1; j++) {
                days = 0;
                if(box[i][j] == 1 && !visited[i][j]) {
                    visited[i][j] = true;
                    Node coordinate = new Node(i, j, days);
                    q.add(coordinate);                   
                }
            }
        }

        while(!q.isEmpty()) {
            Node check = new Node(q.peek().x, q.peek().y, q.peek().days);
            maxdays = Math.max(maxdays, check.days);
            q.poll();
            for(int d = 0; d < 4; d++) {
                int x = check.x + dx[d];
                int y = check.y + dy[d];
                if(box[x][y] == 0 && !visited[x][y]) {
                	visited[x][y] = true;
                    box[x][y] = 1;
                    Node next = new Node(x, y, check.days + 1);
                    q.add(next);
                }
            }
        }
        
        boolean completeFlag = true;
        for(int i = 1; i < N+1; i++) {
            for(int j = 1; j < M+1; j++) {
                if(box[i][j] == 0)
                    completeFlag = false;
            }
        }
        if(!completeFlag) System.out.println("-1");
        else System.out.println(maxdays);
    }
}

class Node{
    int x, y, days;

    Node(int x, int y, int days){
        this.x = x;
        this.y = y;
        this.days = days;
    }
}