package optimization;

import java.io.*;
import java.util.*;

public class Input {
	// zmienne statyczne
		/* precyzja obliczeñ wyra¿ona w liczbie bitów zmiennej binarnej, ogólna dla wszystkich zmiennych.
		Mo¿liwa modyfikacja z indywidualnym doborem dok³adnoœci dla ka¿dej zmiennej*/
		
		// sta³e dotycz¹ce zmiennych w klasie Person oraz obliczeñ w klasie Goal
		private static int numberOfBits;
		private static int numberOfCombinations;
		private static int numberOfVariables;
		private static double[] rangeMax;
		private static double[] rangeMin;
		private static double[] step;
		
		// sta³e dotycz¹ce g³ównie klasy Population
		private static int numberOfPersons;
		
		// sta³e dotycz¹ce ograniczeñ w obliczeniach
		private static int generationsMin;
		private static int generationsMax;
		private static int bestPersonMaxAge;
		
		// sta³e dotycz¹ce prawdopodobieñstwa w operatorach genetycznych
		private static double crossingProbability;
		private static double mutationProbability;
		
		// metoda zczytuj¹ca wszystkie sta³e z pliku o podanej œcie¿ce
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
		
		public static void reportStatus(String filePath, Population pop) {
			try ( FileWriter file = new FileWriter(filePath);
				BufferedWriter buffwr = new BufferedWriter(file)){
				buffwr.write("Obliczenia zakoñczone");
				buffwr.newLine();
				buffwr.write(Integer.toString(pop.getAge()));
				buffwr.newLine();
				buffwr.write(Integer.toString(pop.getBestPerson().getAge()));
				buffwr.newLine();
				buffwr.write(Double.toString(pop.getBestPerson().getScore()).replace('.', ','));
			for (int j = 0; j < numberOfVariables; j++) {
				buffwr.newLine();
				buffwr.write(Double.toString(pop.getBestPerson().getVariableValue(j)).replace('.', ','));
			}
				buffwr.close();
			} catch (IOException e) {
				System.out.println("Something went wrong: " + e.getMessage());
			}
		}
		
			
		
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
