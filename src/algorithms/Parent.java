package algorithms;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;

import representaation.State;

public class Parent {
	protected Set<Integer> explored;
	protected State goalState;
	protected Utility utility;
	protected int searchDepth;
	protected long runningTime;
	
	protected Parent() {
		utility = new Utility();
	}

	// setters and getters
	public Set<Integer> getExplored() {
		return explored;
	}

	public int getSearchDepth() {
		return searchDepth;
	}

	protected void setSearchDepth(int searchDepth) {
		this.searchDepth = searchDepth;
	}

	public long getRunningTime() {
		return runningTime;
	}

	private void setRunningTime(long runningTime) {
		this.runningTime = runningTime;
	}

	public State getGoalState() {
		return goalState;
	}

	protected void setGoalState(State goalState) {
		this.goalState = goalState;
	}
	
	// initialize containers and variables to start solving 
	protected void initialize() {
		explored = new HashSet<Integer>();
		searchDepth  = 0;
		runningTime = 0;
		goalState = null;
	}
	
	// timer start and stop functions
	protected void startTimer() {
		setRunningTime(System.currentTimeMillis());
	}
	
	protected void stopTimer() {
		setRunningTime(System.currentTimeMillis() - getRunningTime());
	}
	
    // searching in frontier for a state using the integer representation
	protected State searchInFrontier(Iterator<State> iterator, Integer representation) {
		while(iterator.hasNext()){
			State state = iterator.next();
			if(state.getIntRepresentation().equals(representation)) {
				return state;
			}
		}
		return null;
	}

	// to be override in child classes
	public boolean solve(State root) {
		return false;
	}

	// to be override in child classes
	public boolean solveStar(State root, boolean euclidean) {
		return false;
	}
}
