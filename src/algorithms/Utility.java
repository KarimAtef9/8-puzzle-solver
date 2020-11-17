package algorithms;

import representaation.State;

import java.util.Stack;

public class Utility {
	
	public void backtrack(State state) {
		Stack<State> stack = new Stack<>();
		while (state != null) {
			stack.push(state);
			state = state.getParent();
		}
		while (!stack.isEmpty()) {
			print(stack.pop());
		}
	}
	public void print(State state) {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				System.out.print(state.getMapping()[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println("-----");
	}
	
	public boolean goalTest(State currentState) {
		return currentState.getIntRepresentation() == 12345678;
	}

}
