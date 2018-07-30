package optimization;

import static java.lang.Math.*;

// The class performing all calculations for goal function and saving score in Person's 'score' variable.
// This is the class where You put Your goal function calculations.
// Remember that input file has to contain all variables used in this class.

public class Goal {
	
	public static void calculateScore(Person person) {
		double score1 = function1(person.getVariableValue(0));
		double score2 = function2(person.getVariableValue(1));
		double score3 = function3(person.getVariableValue(2));
		person.setScore(score1 + score2 + score3);
	}
	
	private static double function1(double var) {
		double value = pow(var, 5) + (-7*pow(var, 4)) + (12*pow(var, 3)) + (-18*pow(var, 2)) + (50*var) + 120;
		return value;
	}
	
	private static double function2(double var) {
		double value = (0.5*pow(var, 5)) + (-4*pow(var, 4)) + (9*pow(var, 3)) + (-16*pow(var, 2)) + (50*var) + 80;
		return value;
	}
	
	private static double function3(double var) {
		double value = (-1*pow(var, 5))  + (6*pow(var, 4)) + (-4*pow(var, 3)) + (-4*pow(var, 2)) + (-10*var) + 50;
		return value;
	}
}
