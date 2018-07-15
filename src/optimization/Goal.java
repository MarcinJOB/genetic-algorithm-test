package optimization;

import static java.lang.Math.*;

/* klasa zawieraj¹ca wszystkie informacje o funkcji celu 
 * oraz funkcjê wykonuj¹c¹ obliczenia wyniku, 
 * a tak¿e ustalenie punktacji dla Score osobników
 */
public class Goal {
	// podane w class Person
	//public static final int numOfVar = 3;
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
	public static void calculateScore(Person per) {
		double score1 = function1(per.getVariableValue(0));
		double score2 = function2(per.getVariableValue(1));
		double score3 = function3(per.getVariableValue(2));
		per.setScore(score1 + score2 + score3);
	}
	
	// metoda obliczeñ z bezpoœrednim wprowadzeniem wartoœci zmiennych - na potrzeby testów
	public static double calculateScore(double var1, double var2, double var3) {
		double score1 = function1(var1);
		double score2 = function2(var2);
		double score3 = function3(var3);
		return (score1 + score2 + score3);
	}
}
