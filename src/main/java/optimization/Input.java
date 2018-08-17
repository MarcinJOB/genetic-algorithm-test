package optimization;

import java.io.*;
import java.util.*;

//@Component
//@PropertySource("classpath:input.properties")
public class Input {
	// declaration of static variables
	// precision of calculations is expressed as a number of bits for binary variables, which is the same for all variables
	// it is possible to set individual number of bits for every variable, but it is not important in this project
	
	// global constants for precision of calculations
		private static int numberOfPersons;
		private static int numberOfBits;
		private static int numberOfCombinations;
		private static int numberOfVariables;
		private static double[] rangeMax;
		private static double[] rangeMin;
		private static double[] step;
		
	// constants concerning boundary limits of calculation loop
		private static int generationsMin;
		private static int generationsMax;
		private static int bestPersonMaxAge;
		
	// constants concerning probability in genetic operators
		private static double crossingProbability;
		private static double mutationProbability;
	
// new method to read input file from only file name - without whole path
		public static String[] readFile(String fileName) {
			ArrayList<String> inputLines = new ArrayList<>();
			try (BufferedReader buff = new BufferedReader(new FileReader(fileName)))
			{	
				String line = null;
				while((line = buff.readLine()) != null) {
					inputLines.add(line.trim());
				}
			} catch (IOException e) {
				System.out.println("Error -- " + e.toString());
			} 
			inputLines.trimToSize();
			return inputLines.toArray(new String[inputLines.size()]);
		}
		
// new method to set constants from input file		
		public static void setConstants(String[] inputLines) {
//			try {
				numberOfPersons = Integer.parseInt(inputLines[0]);
				generationsMin = Integer.parseInt(inputLines[1]);
				generationsMax = Integer.parseInt(inputLines[2]); 
				bestPersonMaxAge = Integer.parseInt(inputLines[3]);
				crossingProbability = Double.parseDouble(inputLines[4].replace(',', '.'));
				mutationProbability = Double.parseDouble(inputLines[5].replace(',', '.'));
				numberOfBits = Integer.parseInt(inputLines[6]);
				numberOfCombinations = (int) Math.pow(2, numberOfBits);
				numberOfVariables = Integer.parseInt(inputLines[7]);
				rangeMin = new double[numberOfVariables];
				rangeMax = new double[numberOfVariables];
				step = new double[numberOfVariables];
			
				for (int j = 0; j < numberOfVariables; j++) {
					rangeMin[j] = Double.parseDouble(inputLines[8+(j*2)]);
					rangeMax[j] = Double.parseDouble(inputLines[9+(j*2)]);
					step[j] = (rangeMax[j] - rangeMin[j])/numberOfCombinations;
				}
//			} catch (IndexOutOfBoundsException e) {
//				System.out.println("Input file is not correct. Unable to continue calculations! \nException message: " + e.toString());
//			}
		}

// method to read input file from given file path
// OLD - NOT USED method, left for testing		
		public static void readInput(String filePath) {
			ArrayList<String> inData = new ArrayList<>();
			try (
					FileReader file = new FileReader(filePath);
					BufferedReader buff = new BufferedReader(file)){
				boolean eof = false;
				while(!eof) {
					String line = buff.readLine();
					if(line == null) {
						eof = true;
					} else {
						inData.add(line.trim());
					}
				}
				buff.close();
			} catch (IOException e) {
				System.out.println("Error -- " + e.toString());
			} 
			
			inData.trimToSize();
			
			numberOfPersons = Integer.parseInt(inData.get(0));
			generationsMin = Integer.parseInt(inData.get(1));
			generationsMax = Integer.parseInt(inData.get(2)); 
			bestPersonMaxAge = Integer.parseInt(inData.get(3));
			crossingProbability = Double.parseDouble(inData.get(4).replace(',', '.'));
			mutationProbability = Double.parseDouble(inData.get(5).replace(',', '.'));
			numberOfBits = Integer.parseInt(inData.get(6));
			numberOfCombinations = (int) Math.pow(2, numberOfBits);
			numberOfVariables = Integer.parseInt(inData.get(7));
			rangeMin = new double[numberOfVariables];
			rangeMax = new double[numberOfVariables];
			step = new double[numberOfVariables];
			
			for (int j = 0; j < numberOfVariables; j++) {
				rangeMin[j] = Double.parseDouble(inData.get(8+(j*2)));
				rangeMax[j] = Double.parseDouble(inData.get(9+(j*2)));
				step[j] = (rangeMax[j] - rangeMin[j])/numberOfCombinations;
			}
			
		}
		
	// method to generate report with results of optimization to the output file
		public static void reportStatus(String filePath, Population pop) {
			try ( FileWriter file = new FileWriter(filePath);
				BufferedWriter buffwr = new BufferedWriter(file)){
				buffwr.write("Calculations finished");
				buffwr.newLine();
				buffwr.write(Integer.toString(pop.getAge()));
				buffwr.newLine();
				buffwr.write(Integer.toString(pop.getBestPerson().getAge()));
				buffwr.newLine();
				buffwr.write(Double.toString(pop.getBestPerson().getScore()).replace('.', ','));
			for (int j = 0; j < numberOfVariables; j++) {
				buffwr.newLine();
				buffwr.write(Double.toString(pop.getBestPerson().getVariable(j)).replace('.', ','));
			}
				buffwr.close();
			} catch (IOException e) {
				System.out.println("Something went wrong: " + e.getMessage());
			}
		}
		
			
	//getters for constants
		public static int getNumberOfPersons() {
			return numberOfPersons;
		}
		
		public static int getMinNoGenerations() {
			return generationsMin;
		}
		
		public static int getMaxNoGenerations() {
			return generationsMax;
		}
		
		public static int getBestPersonMaxAge() {
			return bestPersonMaxAge;
		}
		
		public static double getCrossingProbability() {
			return crossingProbability;
		}
		
		public static double getMutationProbability() {
			return mutationProbability;
		}
		
		public static int getNumberOfBits() {
			return numberOfBits;
		}
		
		public static int getNumberOfCombinations() {
			return numberOfCombinations;
		}

		public static int getNumberOfVariables() {
			return numberOfVariables;
		}
		
		public static double getMinValueOfVar(int indexOfVariable) {
			try {
				return rangeMin[indexOfVariable];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Something went wrong here: " + e.getMessage());
				return -1;
			}
		}

		public static double getMaxValueOfVar(int indexOfVariable) {
			try {
				return rangeMax[indexOfVariable];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Something went wrong here: " + e.getMessage());
				return -1;
			}
		}
		
		public static double getStepValueOfVar(int indexOfVariable) {
			try {
				return step[indexOfVariable];
			} catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("Something went wrong here: " + e.getMessage());
				return -1;
			}
		}
}
