package optimization;

import static java.lang.Math.*;
import java.util.ArrayList;

public class Genetic {

	// selection genetic operator - selects persons to survive in new population with probability based on their score
	
	public static void Selection(Population pop) {
		double[] scoreSum;			
		Population tempPop = new Population();			
		scoreSum = new double[Input.getNumberOfPersons()];
			
		// cumulative probability for selection
		
		for (int i = 0; i < Input.getNumberOfPersons(); i++) {
			if (i==0) {
				scoreSum[i] = pop.getPerson(i).getScore();
			} else {
				scoreSum[i] = scoreSum[i-1] + pop.getPerson(i).getScore();
			}
		}
		
		//selection - drawing persons based on cumulative probability and random number
		
		for (int i = 0; i < Input.getNumberOfPersons(); i++) {
			double randomSelector = random() * scoreSum[Input.getNumberOfPersons()-1];
			int index = 0;
			do {
				if (randomSelector < scoreSum[index]) {
					tempPop.getPerson(i).copyPersonFrom(pop.getPerson(index));
				} else {
					index++;
				}
			} while (randomSelector > scoreSum[index]);
		}
		
		// rewriting temporary population 
		
		tempPop.setAge(pop.getAge());
		tempPop.getBestPerson().copyPersonFrom(pop.getBestPerson());
//		tempPop.setBestPerson(pop.getBestPerson());
		pop = tempPop;
	}
	
	// crossing genetic operator - crossing binary code between two persons
	
	public static void Crossing(Population pop) {
		double random;
		int random2;
		String tempChain = new String();
		String tempCut = new String();
		String tempCut2 = new String();
		ArrayList<Integer> chosen = new ArrayList<>();
		ArrayList<String> binaryChain = new ArrayList<>();
		
		// draw persons for crossing and save their indexes and binary chains
		
		for (int i = 0; i < Input.getNumberOfPersons(); i++) {
			random = random();		
			if (random < Input.getCrossingProbability()) {
				chosen.add(i);
				for (int v = 0; v < Input.getNumberOfVariables(); v++) {
					tempChain += pop.getPerson(i).getBinaryVariable(v);
				}
				binaryChain.add(tempChain);
				tempChain = "";
			}
		}
		// if number of drawn persons is odd - takes the binary chain of best person as donor for the last crossing
		
		if (chosen.size()%2 != 0) {
			for (int v = 0; v < Input.getNumberOfVariables(); v++) {
				tempChain += pop.getBestPerson().getBinaryVariable(v);
			}
			binaryChain.add(tempChain);
		}
		// do the crossing with random sections of binary chains of two following persons

		for (int i = 0; i < binaryChain.size(); i += 2) {
			random2 = (int) floor(random() * binaryChain.get(i).length());
			tempCut = binaryChain.get(i).substring(0, random2) + binaryChain.get(i+1).substring(random2);
			tempCut2 = binaryChain.get(i+1).substring(0, random2) + binaryChain.get(i).substring(random2);
			binaryChain.set(i, tempCut);
			binaryChain.set(i+1,  tempCut2);
		}
		
		for (int personIndex = 0; personIndex < chosen.size(); personIndex++) {
			for (int varIndex = 0; varIndex < Input.getNumberOfVariables(); varIndex++) {
				int start = varIndex * Input.getNumberOfBits();
				int stop = start + Input.getNumberOfBits();
				if (stop > binaryChain.get(personIndex).length()){
					pop.getPerson(chosen.get(personIndex))
						.setBinaryVariable(binaryChain.get(personIndex).substring(start), varIndex);	
				} else {
				pop.getPerson(chosen.get(personIndex))
					.setBinaryVariable(binaryChain.get(personIndex).substring(start, stop), varIndex);
				}
			}
			pop.getPerson(chosen.get(personIndex)).setAgeToZero();
			pop.getPerson(chosen.get(personIndex)).setScore(-1);
		}
	}
	
	// mutation genetic operator - mutates random bin values in binary chains
	
	public static void Mutation(Population pop) {
		boolean isMutated;
		char[] binChar = new char[Input.getNumberOfBits()];
		
		// draw random test for mutation probability
		
		for (int personIndex = 0; personIndex < Input.getNumberOfPersons(); personIndex++) {
			isMutated = false;
			for (int varIndex = 0; varIndex < Input.getNumberOfVariables(); varIndex++) {
				for (int binIndex = 0; binIndex < Input.getNumberOfBits(); binIndex++) {
					if (random() < Input.getMutationProbability()) {
						isMutated = true;
						// mutation of single bit for positive draw test result
						binChar = pop.getPerson(personIndex).getBinaryVariable(varIndex).toCharArray();
						binChar[binIndex] = (binChar[binIndex] == '0') ? '1': '0';
						pop.getPerson(personIndex).setBinaryVariable(String.valueOf(binChar), varIndex);
					}
				}
			}
			
			if (isMutated) {
				pop.getPerson(personIndex).setScore(-1);
				pop.getPerson(personIndex).setAgeToZero();
			}
		}
	}			
}

