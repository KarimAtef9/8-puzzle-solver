package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import representaation.State;

public class DFS {
	private Set<Integer> explored = new HashSet<>();
	private Stack<State> frontier = new Stack<State>();
	private State initialState = null;
	private State goalState = null;
	private Utility utility = new Utility();
	
	public DFS(State initialState) {
		this.initialState = initialState;
	}
	public State getGoalState() {
		return goalState;
	}
	
	public boolean solve() {
		if(initialState == null) {
			return false;
		}
		goalState = null;
		frontier.push(initialState);
		while(!frontier.isEmpty()) {
			State currentState = frontier.pop();
			explored.add(currentState.getIntRepresentation());
			if(utility.goalTest(currentState)) {
				goalState = currentState;
				return true;
			}
			ArrayList<State> neighbours = currentState.neighbours(false, false);
			for(State neighbour : neighbours) {
				Integer representation = neighbour.getIntRepresentation();
				if(frontier.search(neighbour.getIntRepresentation()) == -1 && !explored.contains(representation)) {
					frontier.push(neighbour);
				}
			}
		}
		
		return false;
	}
	
}
