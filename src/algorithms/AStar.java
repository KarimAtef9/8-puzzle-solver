package algorithms;

import representaation.State;

import java.util.*;

public class AStar {
    private State initialState;
    private Set<Integer> explored;
    private PriorityQueue<State> frontier;
    Comparator<State> costSorter;
    private Utility utility;
    private State goalState;

    public AStar(State initialState) {
        this.initialState = initialState;
        costSorter = Comparator.comparing(State::getCost);
        // priority queue according to state cost
        frontier = new PriorityQueue<>(costSorter);
        utility = new Utility();
    }

    public State getGoalState() {
        return goalState;
    }

    private State searchInFrontier(Iterator<State> iterator, Integer representation) {
        while(iterator.hasNext()){
            if(iterator.next().getIntRepresentation().equals(representation)) {
                return iterator.next();
            }
        }
        return null;
    }

    public boolean solve(boolean euclidean) {
        long startTime = System.currentTimeMillis();
        explored = new HashSet<>();
        frontier.add(initialState);
        while(!frontier.isEmpty()) {
            State currentState = frontier.poll();
            explored.add(currentState.getIntRepresentation());

            if(utility.goalTest(currentState)) {
                goalState = currentState;
                String method = euclidean ? "Euclidean Distance": "Manhattan Distance";
                System.out.println(method+" path cost = "+goalState.getCost());
                System.out.println("Depth of goal node = "+goalState.getDepth());
                long endTime = System.currentTimeMillis();
                System.out.println("Algorithm Running time = "+(endTime-startTime)+" ms");
                return true;
            }

            // true to calculate cost & euclidean to specify whether euclidean or manhattan cost
            ArrayList<State> neighbours = currentState.neighbours(true, euclidean);
            for(State neighbour : neighbours) {
                Integer representation = neighbour.getIntRepresentation();
                State inFrontier = searchInFrontier(frontier.iterator(), representation);
                // first time to reach
                if(inFrontier == null && !explored.contains(representation)) {
                    frontier.add(neighbour);
                } else if (inFrontier != null) {
                    inFrontier.setCost(Math.min(inFrontier.getCost(), neighbour.getCost()));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Algorithm failed with runtime = "+(endTime-startTime)+" ms");
        return false;
    }

}
