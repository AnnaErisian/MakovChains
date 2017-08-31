package lib.jbinkrot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MarkovChain<E> {
	private Set<String> states;
	private Set<String> terminals;
	private Map<String, E> stateObjects;
	private Map<List<String>, TransitionSet> transitions;
	private int order;
	
	private List<String> previousStates;
	
	/**
	 * Constructor for arbitrary order
	 * @param order: number of previous states to use when determining next state
	 * @throws: {@link IllegalArgumentException}: If order < 1
	 */
	public MarkovChain(int order) {
		if(order < 1) {
			throw new IllegalArgumentException("Order must be greater than 0");
		}
		states = new HashSet<>();
		terminals = new HashSet<>();
		stateObjects = new HashMap<>();
		transitions = new HashMap<>();
		previousStates = new LinkedList<>();
		this.order = order;
	}
	
	/**
	 * Default constructor, creates a first order markov chain
	 */
	public MarkovChain() {
		this(1);
	}
	
	/**
	 * Adds a new state
	 * @param stateName: Name for the state
	 * @param stateData: Data object for the state
	 * @throws IllegalArgumentException if the state already exists
	 */
	public void addState(String stateName, E stateData) {
		if(states.contains(stateName)) {
			throw new IllegalArgumentException("State names must be unique");
		} else {
			states.add(stateName);
			stateObjects.put(stateName, stateData);
		}
	}

	/**
	 * Adds a new terminal state
	 * @param stateName: Name for the state
	 * @param stateData: Data object for the state
	 * @throws IllegalArgumentException if the state already exists
	 */
	public void addTerminalState(String stateName, E stateData) {
		addState(stateName, stateData);
		terminals.add(stateName);
	}
	
	/**
	 * Changes an extant state
	 * @param stateName: Name for the state
	 * @param stateData: New state data object
	 * @throws IllegalArgumentException if the state doesn't already exist
	 */
	public void changeState(String stateName, E stateData) {
		if(! states.contains(stateName)) {
			throw new IllegalArgumentException("Use addState when adding a new state");
		} else {
			stateObjects.put(stateName, stateData);
		}
	}

	/**
	 * Marks a state as terminal
	 * @param stateName: Name of state to mark as a terminal state
	 * @throws IllegalArgumentException if the state doesn't exist
	 */
	public void makeStateTerminal(String stateName) {
		if(! states.contains(stateName)) {
			throw new IllegalArgumentException("Named state does not exist");
		} else {
			terminals.add(stateName);
		}
	}


	/**
	 * Marks a state as nonterminal
	 * @param stateName: Name of state to unmark as a terminal state
	 * @throws IllegalArgumentException if the state is not a terminal state
	 */
	public void makeStateNonterminal(String stateName) {
		if(! terminals.contains(stateName)) {
			throw new IllegalArgumentException("Named state is not a terminal state");
		} else {
			terminals.remove(stateName);
		}
	}
	
	/**
	 * Adds a weighted transition
	 * @param previousStates: List of previous states in which the last item is the most recent and first item is the most distant
	 * 							{"B", "A", "C"} would make a transition from state C when preceded by A, which was preceded by B.
	 * @param nextState: The state to transition to.
	 * @param weight: The weight of this transition.  Normalized internally.
	 */
	public void addTransition(List<String> previousStates, String nextState, double weight) {
		if(previousStates.size() != order) {
			throw new IllegalArgumentException("State history must have size = " + order);
		}
		if(! states.containsAll(previousStates)) {
			throw new IllegalArgumentException("All previous states must exist in the composition.");
		}
		TransitionSet ts;
		if(! transitions.containsKey(previousStates)) {
			ts = new TransitionSet();
			transitions.put(previousStates, ts);
			ts.addTransition(nextState, weight);
		} else {
			ts = transitions.get(previousStates);
			ts.addTransition(nextState, weight);
		}
	}

	public void addTransition(String prev1, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(1);
		list.add(prev1);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(2);
		list.add(prev1);
		list.add(prev2);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String prev3, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(3);
		list.add(prev1);
		list.add(prev2);
		list.add(prev3);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String prev3, String prev4, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(4);
		list.add(prev1);
		list.add(prev2);
		list.add(prev3);
		list.add(prev4);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String prev3, String prev4, String prev5, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(5);
		list.add(prev1);
		list.add(prev2);
		list.add(prev3);
		list.add(prev4);
		list.add(prev5);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String prev3, String prev4, String prev5, String prev6, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(6);
		list.add(prev1);
		list.add(prev2);
		list.add(prev3);
		list.add(prev4);
		list.add(prev5);
		list.add(prev6);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String prev3, String prev4, String prev5, String prev6, String prev7, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(7);
		list.add(prev1);
		list.add(prev2);
		list.add(prev3);
		list.add(prev4);
		list.add(prev5);
		list.add(prev6);
		list.add(prev7);
		addTransition(list, nextState, weight);
	}

	public void addTransition(String prev1, String prev2, String prev3, String prev4, String prev5, String prev6, String prev7, String prev8, String nextState, double weight) {
		ArrayList<String> list = new ArrayList<>(8);
		list.add(prev1);
		list.add(prev2);
		list.add(prev3);
		list.add(prev4);
		list.add(prev5);
		list.add(prev6);
		list.add(prev7);
		list.add(prev8);
		addTransition(list, nextState, weight);
	}


	
	/**
	 * Prepares the Markov Chain - we use the first state as all previous 
	 * states in order to simplify execution.
	 * @param firstState: The state to start with a list of.
	 * @throws IllegalArgumentException if the state doesn't exist
	 */
	public void start(String firstState) {
		if(! states.contains(firstState)) {
			throw new IllegalArgumentException("firstState must be a state which exists.");
		}
		
		previousStates.clear();
		
		for(int i = 0; i < order; i++) {
			previousStates.add(firstState);
		}
	}
	
	/**
	 * Step once along the markov chain.
	 * 
	 * This method can be called even at a terminal state, but behavior is undefined if no transitions exist for the current state.
	 * @return the transition data object for the new state
	 */
	public E step() {
		TransitionSet ts = transitions.get(previousStates);
		if(ts == null) {
			System.err.println(previousStates);
			//TODO: Have the option to fail gracefully
		}
		String nextState = ts.getNextState();
		previousStates.remove(0);
		previousStates.add(nextState);
		return stateObjects.get(nextState);
	}
	
	/**
	 * Runs until a terminal state is reached or the maximum number of steps have been made.
	 * @param maxSteps the maximum number of steps to take looking for a terminal
	 * @return a list of states moved into
	 */
	public List<E> runToTerminal(int maxSteps) {
		int counter = 0;
		List<E> transitionObjects = new ArrayList<>();
		String lastState = null;
		while(! terminals.contains(lastState) && counter < maxSteps) {
			counter++;
			transitionObjects.add(step());
			lastState = previousStates.get(order-1);
		}
		return transitionObjects;
	}
	
	/**
	 * Runs until a terminal state is reached or 1000 steps have been made.
	 * @return a list of states moved into
	 */
	public List<E> runToTerminal() {
		return runToTerminal(1000); // 1000% arbitrary number
	}

	/**
	 * Runs until the maximum number of steps have been made.
	 * @param maxSteps the maximum number of steps to take
	 * @return a list of states moved into
	 */
	public List<E> runToLength(int length) {
		int counter = 0;
		List<E> transitionObjects = new ArrayList<>();
		while(counter < length) {
			counter++;
			transitionObjects.add(step());
		}
		return transitionObjects;
	}
	
	/**
	 * Returns the name of the current state.  Behavior is undefined before start() has been called.
	 * @return the name of the current state
	 */
	public String getCurrentStateName() {
		return previousStates.get(order-1);
	}
	
	/**
	 * Returns the data object associated with the current state.  Behavior is undefined before start() has been called.
	 * @return the data object associated with the current state
	 */
	public E getCurrentStateObject() {
		return stateObjects.get(getCurrentStateName());
	}
	
	/**
	 * Checks if a given state is terminal
	 * @return whether or not the given name is on the terminals list
	 */
	public boolean isStateTerminal(String name) {
		return terminals.contains(name);
	}

	/**
	 * Checks if a current state is terminal
	 * @return whether or not the current state name is on the terminals list
	 */
	public boolean isCurrentStateTerminal() {
		return terminals.contains(getCurrentStateName());
	}
	
	private class TransitionSet {
		private Map<String, Double> probabilityMap;
		
		public TransitionSet() {
			probabilityMap = new HashMap<>();
		}

		public void addTransition(String nextState, double weight) {
			if(probabilityMap.containsKey(nextState)) {
				weight += probabilityMap.get(nextState);
			}
			probabilityMap.put(nextState, weight);
		}
		
		public String getNextState() {
			double sum = 0;
			for(Double d: probabilityMap.values()) {
				sum += d;
			}
			
			double rng = Math.random();
			double normWeight;
			String next = null;
			
			for(Entry<String, Double> e : probabilityMap.entrySet()) {
				normWeight = e.getValue() / sum;
				next = e.getKey();
				if(normWeight > rng) return next;
				rng -= normWeight;
			}
			//floating point error happens
			return next;
		}
	}
}
