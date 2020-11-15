package solver;


import java.util.Scanner;
import java.util.Stack;

import algorithms.AStar;
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
		scan.close();
		State root = new State(array, null, 0);
		
//		// testing BFS
//		BFS bfs = new BFS(root);
//		System.out.println(bfs.solve());
//		utility.backtrack(bfs.getGoalState());
		
//		//testing DFS
//		DFS dfs = new DFS(root);
//		System.out.println(dfs.solve());
//		utility.backtrack(dfs.getGoalState());

		//testing A* with Euclidean cost
		AStar aStarEuclidean = new AStar(root);
		System.out.println(aStarEuclidean.solve(true));
		utility.backtrack(aStarEuclidean.getGoalState());

		//testing A* with Euclidean cost
		AStar aStarManhattan = new AStar(root);
		System.out.println(aStarManhattan.solve(false));
		utility.backtrack(aStarManhattan.getGoalState());

		// 1 2 5 3 4 0 6 7 8	cost = 12
		// 5 2 1 7 6 8 0 4 3	infinite loop
		// 1 2 5 3 4 8 6 0 7	cost = 33.06 & 35
	}
}
