package solver;


import java.util.Scanner;
import java.util.Stack;

import algorithms.BFS;
import algorithms.DFS;
import algorithms.Utility;
import representaation.State;

public class Solver {

	public static void main(String[] args) {
		Utility utility = new Utility();
		
		//input
		Integer[][] array= new Integer[3][3];
		Scanner scan = new Scanner(System.in);
		System.out.println("enter initial state (numbers are separated by space)");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				array[i][j] = scan.nextInt();
			}
		}
		
		State root = new State(array, null, 0);
		
		// testing BFS
		BFS bfs = new BFS(root);
		System.out.println(bfs.solve());
		utility.backtrack(bfs.getGoalState());
		
		//testing DFS
		DFS dfs = new DFS(root);
		System.out.println(dfs.solve());
		utility.backtrack(bfs.getGoalState());
		

	}
}
