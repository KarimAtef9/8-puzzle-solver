package algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import representaation.State;

public class BFS extends Parent{

	private Queue<State> frontier;

	public BFS() {
		super();
	}

	@Override
	public boolean solve(State initialState) {
		if(initialState == null) {
			return false;
		}
		initialize();
		frontier = new LinkedList<State>();
		frontier.add(initialState);
		startTimer();
		while(!frontier.isEmpty()) {
			State currentState = frontier.remove();
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
				if(searchInFrontier(frontier.iterator(), representation) == null && !explored.contains(representation)) {
					frontier.add(neighbour);
				}
			}
		}
		stopTimer();
		return false;
	}
	
}
