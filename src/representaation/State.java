package representaation;

import java.util.ArrayList;

public class State {

	private Integer[][] mapping = new Integer[3][3];
	private State parent = null;
	private double cost = 0;
	private int depth = 0;


	// setters and getters
	public Integer[][] getMapping() {
		return mapping;
	}

	public void setMapping(Integer[][] state) {
		this.mapping = state;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}


	// constructors
	public State() {

	}

	public State(Integer[][] mapping, State parent, double cost) {
		this.mapping = mapping;
		this.parent = parent;
		this.cost = cost;
	}

	/*
	 * computeCost: true to compute cost , false not to compute cost heuristicCost:
	 * false compute cost with Manhattan Distance and true compute cost with
	 * Euclidean Distance
	 */
	public ArrayList<State> neighbours(boolean computeCost, boolean heuristicCost) {
		// find 0 coordinates
		int row, col = 0;
		for (row = 0; row < 3; row++) {
			boolean found = false;
			for (col = 0; col < 3; col++) {
				if (this.mapping[row][col] == 0) {
					found = true;
					break;
				}
			}
			if (found)
				break;
		}
		ArrayList<State> neighbours = new ArrayList<State>();
		// check move-up state
		if (row > 0) {
			neighbours.add(nextState(row, col, row-1, col, computeCost, heuristicCost));
		}
		// check move-left state
		if (col > 0) {
			neighbours.add(nextState(row, col, row, col-1, computeCost, heuristicCost));
		}
		// check move-down state
		if (row < 2) {
			neighbours.add(nextState(row, col, row+1, col, computeCost, heuristicCost));
		}
		// check move-right state
		if (col < 2) {
			neighbours.add(nextState(row, col, row, col+1, computeCost, heuristicCost));
		}
		return neighbours;
	}

	/*
		return neighbour state of the given dimensions
	 */
	private State nextState(int row, int col, int targetRow, int targetCol,
							boolean computeCost, boolean heuristicCost) {
		State state = new State();
		state.setMapping(getMapping(row, col, targetRow, targetCol));
		state.setParent(this);
		if (computeCost)
			state.setCost(computeCost(state.getMapping(), heuristicCost));
		else
			state.setCost(this.cost+1);
		state.setDepth(this.getDepth() + 1);
		return state;
	}

	/*
	 * Integer representation is the sum of each element multiplied by 10 ^ (row * 3
	 * + col) this representation will be used to index the state in explored set
	 */
	public Integer getIntRepresentation() {
		int representation = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				representation += mapping[row][col] * Math.pow(10, 8 -(row * 3 + col));
			}
		}
		return representation;
	}

	/*
	 * A function find a new state when moving 0 from a position of (zeroRow,
	 * zeroCol) to (targetRow, targetCol)
	 */
	private Integer[][] getMapping(int zeroRow, int zeroCol, int targetRow, int targetCol) {
		Integer[][] newMapping = new Integer[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				newMapping[i][j] = this.mapping[i][j];
			}
		}
		Integer temp = newMapping[targetRow][targetCol];
		newMapping[targetRow][targetCol] = 0;
		newMapping[zeroRow][zeroCol] = temp;
		return newMapping;
	}

	/*
	 * heuristicCost: false compute cost with Manhattan Distance and true compute
	 * cost with Euclidean Distance
	 */
	private double computeCost(Integer[][] mapping, boolean heuristicCost) {
		double distanceSum = 0;
		if (!heuristicCost) {
			// computing Manhattan Distance
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					Integer number = mapping[row][col];
					distanceSum += (Math.abs(row - number / 3) + Math.abs(col - number % 3));
				}
			}
		} else {
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					Integer number = mapping[row][col];
					distanceSum += Math.sqrt(Math.pow(row - number / 3, 2) + Math.pow(col - number % 3, 2));
				}
			}
		}
		// f(n) = g(n) + h(n)
		double cost = this.depth + 1 + distanceSum;
		return cost;
	}
}