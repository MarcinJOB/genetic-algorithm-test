package optimization;

import java.io.IOException;

//ensure this class is managed by spring.
// DONE - Goal function is managed by Spring inside Population class

public class Optimizer {
	
	//managed by spring
	Population population = new Population();

	public static void main (String[] args) throws IOException {
		long timeStart, timeEnd, timeDuration;
		boolean PopulationNotOld = true;
		boolean BestPersonNotOld = true;
		
		timeStart = System.currentTimeMillis();
				
		// read the input data from text file generated by Excel file and initial creation of population and goal function calculations
						 
		String[] readFileLines = Input.readFile("src/resources/input.txt");
		Input.setConstants(readFileLines);
		
		Optimizer optimizer = new Optimizer();

		optimizer.population.calculateScores();
		
		//perform loop of genetic operators on population and calculation of goal function till boundary limits of population age are met
		
		do{
			if (optimizer.population.getAge() % 10 == 0) {
			System.out.println("Calculating generation number " + optimizer.population.getAge() + ", Best score till now is: " + optimizer.population.getBestPerson().getScore());
			}
			
			Genetic.Selection(optimizer.population);
			Genetic.Crossing(optimizer.population);
			Genetic.Mutation(optimizer.population);
			
			optimizer.population.calculateScores();
			
			PopulationNotOld = optimizer.population.getAge() < Input.getMaxNoGenerations();
			BestPersonNotOld = optimizer.population.getBestPerson().getAge() < Input.getBestPersonMaxAge();			
			
		} while (PopulationNotOld && BestPersonNotOld);
		
		timeEnd = System.currentTimeMillis();
		timeDuration = (timeEnd - timeStart);


		// generate output file with results of genetic optimization
		System.out.println("Genetic optimization is finished.\nResulted values of variables are:");
		for (int i = 0; i < Input.getNumberOfVariables(); i++) {
			System.out.println("Variable " + i + ": " + optimizer.population.getBestPerson().getVariable(i));
		}
		System.out.println("Score is: " + optimizer.population.getBestPerson().getScore());
		System.out.println("Time of calculation in milisec was: " + timeDuration);

//	TO WORK OUT - Reporting status temporary disabled.		
//		Input.reportStatus(pathOut, optimizer.population);

	
	}

}
