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

		// input
		Integer[][] array = new Integer[3][3];
		Scanner scan = new Scanner(System.in);
		System.out.println("enter initial state (numbers are separated by space)");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				array[i][j] = scan.nextInt();
			}
		}
		scan.close();
		State root = new State(array);

		System.out.println("testing BFS");
		System.out.println("_____________");

//		// testing BFS
//		BFS bfs = new BFS();
//		if (bfs.solve(root)) {
//			System.out.println("Solved");
//			System.out.println("path to goal");
//			utility.backtrack(bfs.getGoalState());
//			System.out.println();
//			System.out.print("cost of path: ");
//			System.out.println(bfs.getGoalState().getCost());
//			System.out.println();
//			System.out.print("search depth: ");
//			System.out.println(bfs.getSearchDepth());
//			System.out.println();
//			System.out.print("No of expanded states = ");
//			System.out.println(bfs.getExplored().size());
//			// should print explored state
//		}

//		//testing DFS
//		DFS dfs = new DFS();
//		if (dfs.solve(root))
//			utility.backtrack(dfs.getGoalState());

		// testing A* with Euclidean cost
		AStar aStarEuclidean = new AStar();
		if (aStarEuclidean.solve(root, true))
			utility.backtrack(aStarEuclidean.getGoalState());

		// testing A* with Euclidean cost
		AStar aStarManhattan = new AStar();
		if (aStarManhattan.solve(root, true))
			utility.backtrack(aStarManhattan.getGoalState());

		// 1 2 5 3 4 0 6 7 8 cost = 12
		// 5 2 1 7 6 8 0 4 3 infinite loop
		// 1 2 5 3 4 8 6 0 7 cost = 33.06 & 35
	}

	static void printResult(State state) {

	}
}
