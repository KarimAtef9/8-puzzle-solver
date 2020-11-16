package solver;

import java.util.Scanner;
import algorithms.*;
import representaation.State;

public class Solver {

	public static void main(String[] args) {
		Utility utility = new Utility();
		// input
		Integer[][] array = new Integer[3][3];
		Scanner scan = new Scanner(System.in);
		int k = 0;
		while (k != 4) {
			System.out.println("Select option:\n1) BFS\n2) DFS\n3) A*\n4) All\n5) Exit");
			k = scan.nextInt();
			if (k == 5) {
				scan.close();
				System.exit(0);
			}
			System.out.println("enter initial state (numbers are separated by space)");
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					array[i][j] = scan.nextInt();
				}
			}
			State root = new State(array);
			if (k == 1) {	// apply BFS
				Parent bfs = new BFS();
				run(bfs,"BFS", root, utility);
			} else if (k == 2) {
				Parent dfs = new DFS();
				run(dfs,"DFS", root, utility);
			} else if (k == 3) {
				Parent aStarEuclidean = new AStar();
				runStar(aStarEuclidean, true,"A* (Euclidean Distance)", root, utility);
				Parent aStarManhattan = new AStar();
				runStar(aStarManhattan, false,"A* (Manhattan Distance)", root, utility);
			} else if (k == 4) {
				Parent bfs = new BFS();
				run(bfs,"BFS", root, utility);
				System.out.println("==================================");
				Parent dfs = new DFS();
				run(dfs,"DFS", root, utility);
				System.out.println("==================================");
				Parent aStarEuclidean = new AStar();
				runStar(aStarEuclidean, true,"A* (Euclidean Distance)", root, utility);
				Parent aStarManhattan = new AStar();
				runStar(aStarManhattan, false,"A* (Manhattan Distance)", root, utility);
			}
		}
		scan.close();

		// 1 2 5 3 4 0 6 7 8 cost = 12
		// 5 2 1 7 6 8 0 4 3 infinite loop
		// 1 2 5 3 4 8 6 0 7 cost = 33.06 & 35
	}

	// running BFS & DFS algorithms & printing the required outputs
	static void run(Parent parent, String name, State root, Utility utility) {
		if (parent.solve(root)) {
			System.out.println(name+" running time = "+parent.getRunningTime()+"ms");
			System.out.println(name+" depth = "+parent.getSearchDepth());
			System.out.println(name+" cost = "+parent.getSearchDepth());
			System.out.println(name+" nodes expanded = "+parent.getExplored());
			utility.backtrack(parent.getGoalState());
		} else {
			System.out.println(name+" failed with run time = "+parent.getRunningTime()+"ms");
		}
	}
	// running A* algorithm & printing the required outputs
	static void runStar(Parent parent, boolean euclidean, String name, State root, Utility utility) {
		if (parent.solveStar(root, euclidean)) {
			System.out.println(name+" running time = "+parent.getRunningTime()+"ms");
			System.out.println(name+" depth = "+parent.getSearchDepth());
			System.out.println(name+" cost = "+parent.getSearchDepth());
			System.out.println(name+" nodes expanded = "+parent.getExplored());
			utility.backtrack(parent.getGoalState());
		} else {
			System.out.println(name+" failed with run time = "+parent.getRunningTime()+"ms");
		}
	}
}
