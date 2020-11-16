package algorithms;

import representaation.State;

public class Utility {
	
	public void backtrack(State state) {
		do {
			print(state);
			state = state.getParent();
		} while (state != null);
		return;
	}
	public void print(State state) {
		for(int row = 0; row < 3; row++) {
			for(int col = 0; col < 3; col++) {
				System.out.print(state.getMapping()[row][col] + " ");
			}
			System.out.println("");
		}
		System.out.println("------");
	}
	
	public boolean goalTest(State currentState) {
		if(currentState.getIntRepresentation() == 12345678) {
			return true;
		}
		return false;
	}

}
