package algorithms;

import java.util.ArrayList;
import java.util.Stack;
import representaation.State;

public class DFS extends Parent{

	private Stack<State> frontier ;

	public DFS() {
		super();
	}
	
	public boolean solve(State initialState) {
		if(initialState == null) {
			return false;
		}
		initialize();
		frontier = new Stack<State>();
		frontier.push(initialState);
		getInFrontier().add(initialState.getIntRepresentation());
		startTimer();
		while(!frontier.isEmpty()) {
			State currentState = frontier.pop();
			explored.add(currentState.getIntRepresentation());
			setSearchDepth(Math.max(getSearchDepth(), currentState.getDepth()));
			if(utility.goalTest(currentState)) {
				stopTimer();
				setGoalState(currentState);
				return true;
			}
			ArrayList<State> neighbours = currentState.neighbours(false, false);
			for(State neighbour : neighbours) {
				Integer representation = neighbour.getIntRepresentation();
				if(!getInFrontier().contains(representation) && !explored.contains(representation)) {
					frontier.push(neighbour);
					getInFrontier().add(representation);
				}
			}
		}
		stopTimer();
		return false;
	}
	
}
