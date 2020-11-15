package algorithms;

import representaation.State;

import java.util.*;

public class AStar {
    private State initialState;
    // if state found in map then pre-visited,
    // with state value if in queue & null if not in queue
    private Map<Integer, State> explored;
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

    // return the given state in the queue if found, for the given representation (integer state)
    private State searchInFrontier(Iterator<State> iterator, Integer representation) {
        while(iterator.hasNext()){
            if(iterator.next().getIntRepresentation().equals(representation)) {
                return iterator.next();
            }
        }
        return null;
    }

    /*
        applying A* algorithm, returning true if it found a solution & false otherwise
        h(n) depending on the passed boolean true for Euclidean distance
        & false for Manhattan distance
     */
    public boolean solve(boolean euclidean) {
        String method = euclidean ? "Euclidean Distance": "Manhattan Distance";
        long startTime = System.currentTimeMillis();
        explored = new HashMap<>();
        frontier.add(initialState);
        while(!frontier.isEmpty()) {
            State currentState = frontier.poll();
            explored.put(currentState.getIntRepresentation(), null);

            if(utility.goalTest(currentState)) {
                goalState = currentState;
                System.out.println(method+" path cost = "+getPathCost(goalState));
                System.out.println("Depth of goal node = "+goalState.getDepth());
                long endTime = System.currentTimeMillis();
                System.out.println("A* Running time = "+(endTime-startTime)+" ms");
                return true;
            }

            // true to calculate cost & euclidean to specify whether euclidean or manhattan cost
            ArrayList<State> neighbours = currentState.neighbours(true, euclidean);
            for(State neighbour : neighbours) {
                Integer representation = neighbour.getIntRepresentation();
                boolean visited = explored.containsKey(representation) ? true: false;
                State inFrontier = visited ? explored.get(representation): null;
                if(!visited) { // first time to reach
                    frontier.add(neighbour);
                    explored.put(representation, neighbour);
                } else if (inFrontier != null) {
                    // if found in the queue we update its cost to the minimum
                    inFrontier.setCost(Math.min(inFrontier.getCost(), neighbour.getCost()));
                }
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("A* ("+method+") failed with runtime = "+(endTime-startTime)+" ms");
        return false;
    }

    // return the path cost for the given final state
    private double getPathCost(State goal) {
        double sum = 0;
        while (goal != null) {
            sum += goal.getCost();
            goal = goal.getParent();
        }
        return sum;
    }
}
