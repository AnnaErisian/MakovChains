package test.jbinkrot;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import lib.jbinkrot.MarkovChain;

public class MKAcceptanceTest {
	
	public static void main(String[] args) {
//		testFirstOrder();
		testSecondOrder();
	}

	private static void testSecondOrder() {

		MarkovChain<String> mk = new MarkovChain<>(2);
		
		mk.addState("Start", null);
		mk.addState("A", "Alice");
		mk.addState("B", "Bob");
		mk.addState("C", "Carol");
		mk.addState("D", "Dan");
		mk.addTerminalState("F", "Frank");

		mk.addTransition("Start", "Start", "A", .1);//
		mk.addTransition("Start", "Start", "B", .3);//
		mk.addTransition("Start", "Start", "C", .1);//

		mk.addTransition("Start", "A", "D", .25);//
		mk.addTransition("D",     "A", "B", .25);//
		mk.addTransition("B",     "A", "D", .25);//
		mk.addTransition("B",     "A", "B", .25);//
		mk.addTransition("D",     "A", "D", .25);//
		mk.addTransition("D",     "A", "F", .25);//
		mk.addTransition("C",     "A", "B", .5);//
		mk.addTransition("C",     "A", "C", .2);//
		mk.addTransition("F",     "A", "C", .2);//

		mk.addTransition("Start", "B", "A", .3);//
		mk.addTransition("Start", "B", "B", .1);//
		mk.addTransition("Start", "B", "C", .3);//
		mk.addTransition("Start", "B", "D", .7);//
		mk.addTransition("A",     "B", "C", .3);//
		mk.addTransition("B",     "B", "C", .3);//
		mk.addTransition("B",     "B", "A", .3);//
		mk.addTransition("C",     "B", "C", .4);//
		mk.addTransition("C",     "B", "F", .1);//
		mk.addTransition("F",     "B", "F", .1);//

		mk.addTransition("Start", "C", "B", .25);//
		mk.addTransition("A",     "C", "F", .25);//
		mk.addTransition("B",     "C", "D", .25);//
		mk.addTransition("B",     "C", "A", .25);//
		mk.addTransition("D",     "C", "B", .25);//
		mk.addTransition("D",     "C", "F", .25);//
		mk.addTransition("F",     "C", "F", .25);//

		mk.addTransition("A",     "D", "A", .3);//
		mk.addTransition("A",     "D", "C", .3);//
		mk.addTransition("A",     "D", "F", .1);//
		mk.addTransition("B",     "D", "C", .3);//
		mk.addTransition("B",     "D", "F", .1);//
		mk.addTransition("C",     "D", "A", .3);//
		mk.addTransition("C",     "D", "F", .1);//
		mk.addTransition("F",     "D", "A", .1);//

		mk.addTransition("A", "F", "A", 1);//
		mk.addTransition("B", "F", "B", 1);//
		mk.addTransition("C", "F", "C", 1);//
		mk.addTransition("D", "F", "D", 1);//
		
		System.out.println("Running to Terminal");
		mk.start("Start");
		List<String> glance = mk.runToTerminal(); 
		for(String s : glance) System.out.println(s);
		
		System.out.println("Running to Length (100)");
		mk.start("Start");
		glance = mk.runToLength(100);
		for(String s : glance) System.out.println(s);

		System.out.println("Checking Start -> ? probabilities");
		HashMap<String, Integer> counts = new HashMap<>();
		counts.put("Alice", 0);
		counts.put("Bob", 0);
		counts.put("Carol", 0);
		counts.put("Dan", 0);
		counts.put("Eve", 0);
		String s;
		for(int i = 0; i < 1000000; i++) {
			mk.start("Start");
			s = mk.step();
			counts.put(s, counts.get(s) + 1);
		}
		for(Entry<String, Integer> e : counts.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}

	private static void testFirstOrder() {
		MarkovChain<String> mk = new MarkovChain<>();
		
		mk.addState("Start", null);
		mk.addState("A", "Alice");
		mk.addState("B", "Bob");
		mk.addState("C", "Carol");
		mk.addState("D", "Dan");
		mk.addState("E", "Eve");
		mk.addTerminalState("F", "Frank");

		mk.addTransition("Start", "A", .1);
		mk.addTransition("Start", "B", .3);
		mk.addTransition("Start", "C", .1);
		mk.addTransition("Start", "D", .1);
		mk.addTransition("Start", "E", .3);

		mk.addTransition("A", "B", .25);
		mk.addTransition("A", "D", .65);
		mk.addTransition("A", "F", .1);

		mk.addTransition("B", "A", .3);
		mk.addTransition("B", "C", .7);

		mk.addTransition("C", "B", .25);
		mk.addTransition("C", "D", .65);
		mk.addTransition("C", "F", .1);

		mk.addTransition("D", "A", .3);
		mk.addTransition("D", "C", .7);
		
		mk.addTransition("E", "B", .25);
		mk.addTransition("E", "D", .65);
		mk.addTransition("E", "F", .1);

		mk.addTransition("F", "A", 1);
		mk.addTransition("F", "C", 1);
		mk.addTransition("F", "E", 1);
		
		System.out.println("Running to Terminal");
		mk.start("Start");
		List<String> glance = mk.runToTerminal(); 
		for(String s : glance) System.out.println(s);
		
		System.out.println("Running to Length (100)");
		mk.start("Start");
		glance = mk.runToLength(100); 
		for(String s : glance) System.out.println(s);

		System.out.println("Checking Start -> ? probabilities");
		HashMap<String, Integer> counts = new HashMap<>();
		counts.put("Alice", 0);
		counts.put("Bob", 0);
		counts.put("Carol", 0);
		counts.put("Dan", 0);
		counts.put("Eve", 0);
		String s;
		for(int i = 0; i < 1000000; i++) {
			mk.start("Start");
			s = mk.step();
			counts.put(s, counts.get(s) + 1);
		}
		for(Entry<String, Integer> e : counts.entrySet()) {
			System.out.println(e.getKey() + ": " + e.getValue());
		}
	}
}
