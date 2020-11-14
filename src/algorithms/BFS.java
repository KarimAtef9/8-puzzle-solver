package algorithms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import representaation.State;

public class BFS {
	private Set<Integer> explored = new HashSet<>();
	private Queue<State> frontier = new LinkedList<State>();
	private State initialState = null;
	private State goalState = null;
	private Utility utility = new Utility();
	
	public BFS(State intialState) {
		this.initialState = intialState;
	}
	
	public State getGoalState() {
		return goalState;
	}
	
	public boolean solve() {
		long startTime = System.currentTimeMillis();
		if(initialState == null) {
			return false;
		}
		goalState = null; 
		frontier.add(initialState);
		while(!frontier.isEmpty()) {
			State currentState = frontier.remove();
			explored.add(currentState.getIntRepresentation());
			if(utility.goalTest(currentState)) {
				goalState = currentState;
				System.out.println("Depth of goal node = "+goalState.getDepth());
				long endTime = System.currentTimeMillis();
				System.out.println("Algorithm Running time = "+(endTime-startTime)+" ms");
				return true;
			}
			
			ArrayList<State> neighbours = currentState.neighbours(false, false);
			for(State neighbour : neighbours) {
				Integer representation = neighbour.getIntRepresentation();
				if(!searchInFrontier(frontier.iterator(), representation) && !explored.contains(representation)) {
					frontier.add(neighbour);
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Algorithm failed with runtime = "+(endTime-startTime)+" ms");
		return false;
	}
	

	
	private boolean searchInFrontier(Iterator<State> iterator, Integer representation) {
		while(iterator.hasNext()){
			if(iterator.next().getIntRepresentation() == representation) {
				return true;
			}
		}
		return false;
	}
}
