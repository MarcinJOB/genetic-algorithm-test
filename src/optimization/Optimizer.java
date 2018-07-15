package optimization;

import java.io.IOException;

public class Optimizer {

	public static void main (String[] args) throws IOException {
		long timeStart, timeEnd, timeDuration;
		boolean PopulationNotOld = true;
		boolean BestPersonNotOld = true;
		
		timeStart = System.currentTimeMillis();
		
		String pathIn = "C:\\Users\\Marcin\\Desktop\\Java\\Genetic2\\Indata.txt";
		String pathOut = "C:\\Users\\Marcin\\Desktop\\Java\\Genetic2\\Outdata.txt";
		
		// zczytanie danych wejœciowych z pliku wygenerowanego przez Excel oraz utworzenie populacji osobników
		
		if (args.length > 1){
			pathIn = args[0];
			pathOut = args[1];
		} else {
			throw new IOException("\n------------\nThe program cannot run without input and output file path."
					+ "\nTry again with correct input and output file paths.\n------------"); 
		}
				 
		Input.readInput(pathIn);

		Population population = new Population();
		population.calculateScores();
		
		//wykonanie obliczeñ funkcji celu dla aktualnego pokolenia i wykonanie operatorów genetycznych
		
		do{
			if (population.getAge() % 10 == 0) {
			System.out.println("Calculating generation number " + population.getAge() + ", Best score till now is: " + population.getBestPerson().getScore());
			}
			
			Genetic.Selection(population);
			Genetic.Crossing(population);
			Genetic.Mutation(population);
			
			population.calculateScores();
			
			PopulationNotOld = population.getAge() < Input.getMaxNoGenerations();
			BestPersonNotOld = population.getBestPerson().getAge() < Input.getBestPersonMaxAge();			
			
		} while (PopulationNotOld && BestPersonNotOld);
		
		timeEnd = System.currentTimeMillis();
		timeDuration = (timeEnd - timeStart);


		
		System.out.println("Genetic optimization is finished.\nResulted values of variables are:");
		for (int i = 0; i < Input.getNumberOfVariables(); i++) {
			System.out.println("Variable " + i + ": " + population.getBestPerson().getVariableValue(i));
		}
		System.out.println("Score is: " + population.getBestPerson().getScore());
		System.out.println("Time of calculation in milisec was: " + timeDuration);
		
		Input.reportStatus(pathOut, population);

	
	}

}
