package algorithms;

import representaation.State;
import java.util.*;

public class AStar extends Parent{

    private PriorityQueue<State> frontier;
    private Comparator<State> costSorter;

    public AStar() {
    	super();
    }

    /*
        applying A* algorithm, returning true if it found a solution & false otherwise
        h(n) depending on the passed boolean true for Euclidean distance
        & false for Manhattan distance
     */
    @Override
    public boolean solveStar(State initialState, boolean euclidean) {
		if(initialState == null) {
			return false;
		}
		initialize();
        costSorter = Comparator.comparing(State::getCost);
        // priority queue according to state cost
        frontier = new PriorityQueue<>(costSorter);
        frontier.add(initialState);
        startTimer();
        while(!frontier.isEmpty()) {
            State currentState = frontier.poll();
            explored.add(currentState.getIntRepresentation());
            setSearchDepth(Math.max(getSearchDepth(), currentState.getDepth()));
            if(utility.goalTest(currentState)) {
            	stopTimer();
				setGoalState(currentState);
				return true;
            }
            // true to calculate cost & euclidean to specify whether euclidean or manhattan cost
            ArrayList<State> neighbours = currentState.neighbours(true, euclidean);
            for(State neighbour : neighbours) {
                Integer representation = neighbour.getIntRepresentation();
                boolean visited = explored.contains(representation);
                if(visited) { 
                	//already visited
                	continue;
                }
                State frontierState = searchInFrontier(frontier.iterator(), representation);
                if(frontierState == null) { 
                	// first time to be visited
                	frontier.add(neighbour);
                	explored.add(neighbour.getIntRepresentation());
                }else {
                	// visited but check if the new path's cost is lower than the current path's cost 
                	if(neighbour.getCost() < frontierState.getCost()) {
                		frontier.remove(frontierState);
                		frontier.add(neighbour);
                	}	
                }
            }
        }
        stopTimer();
        return false;
    }
}
