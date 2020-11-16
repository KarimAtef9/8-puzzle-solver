package representaation;

import java.util.ArrayList;

public class State {

	private Integer[][] mapping;
	private State parent;
	private double cost;
	private double estimatedCostToGoal;
	private int depth;

	// constructors
	public State() {
		mapping = new Integer[3][3];
		cost = 0;
		estimatedCostToGoal = 0;
		depth = 0;
	}

	public State(Integer[][] mapping) {
		this.mapping = mapping;
	}

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

	public double getEstimatedCostToGoal() {
		return estimatedCostToGoal;
	}

	public void setEstimatedCostToGoal(double estimatedCostToGoal) {
		this.estimatedCostToGoal = estimatedCostToGoal;
	}

	/*
	 * computeEstimatedCost: true to compute cost , false not to compute cost
	 * heuristicCostFunction: false compute cost with Manhattan Distance and true
	 * compute cost with Euclidean Distance
	 */
	public ArrayList<State> neighbours(boolean computeEstimatedCost, boolean heuristicCostFunction) {
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

		// check move-right state
		if (col < 2) {
			neighbours.add(nextState(row, col, row, col + 1, computeEstimatedCost, heuristicCostFunction));
		}
		// check move-down state
		if (row < 2) {
			neighbours.add(nextState(row, col, row + 1, col, computeEstimatedCost, heuristicCostFunction));
		}
		// check move-left state
		if (col > 0) {
			neighbours.add(nextState(row, col, row, col - 1, computeEstimatedCost, heuristicCostFunction));
		}
		// check move-up state
		if (row > 0) {
			neighbours.add(nextState(row, col, row - 1, col, computeEstimatedCost, heuristicCostFunction));
		}
		return neighbours;
	}

	/*
	 * return neighbour state of the given dimensions
	 */
	private State nextState(int row, int col, int targetRow, int targetCol, boolean computeEstimatedCost,
			boolean heuristicCostFunction) {
		State state = new State();
		state.setMapping(getNewMapping(row, col, targetRow, targetCol));
		state.setParent(this);
		state.setCost(this.getCost() + 1);
		if (computeEstimatedCost)
			state.setEstimatedCostToGoal(computeEsitimatedCost(state, heuristicCostFunction));
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
				representation += mapping[row][col] * Math.pow(10, 8 - (row * 3 + col));
			}
		}
		return representation;
	}

	/*
	 * A function find a new state when moving 0 from a position of (zeroRow,
	 * zeroCol) to (targetRow, targetCol)
	 */
	private Integer[][] getNewMapping(int zeroRow, int zeroCol, int targetRow, int targetCol) {
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
	private double computeEsitimatedCost(State state, boolean heuristicCostFunction) {
		Integer[][] mapping = state.getMapping();
		double heuristicCost = 0;
		if (!heuristicCostFunction) {
			// computing Manhattan Distance
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					Integer number = mapping[row][col];
					heuristicCost += (Math.abs(row - number / 3) + Math.abs(col - number % 3));
				}
			}
		} else {
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					Integer number = mapping[row][col];
					heuristicCost += Math.sqrt(Math.pow(row - number / 3, 2) + Math.pow(col - number % 3, 2));
				}
			}
		}
		// f(n) = g(n) + h(n)
		double costFunction = state.getCost() + heuristicCost;
		return costFunction;
	}
}