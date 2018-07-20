package optimization;

import static java.lang.Math.*;
import java.util.ArrayList;

public class Genetic {

	// operator genetyczny selekcji
	
	public static void Selection(Population pop) {
		double[] scoreSum;			
		Population tempPop = new Population();			
		scoreSum = new double[Input.getNumberOfPersons()];
			
		// wyznaczenie sumy wynik�w na potrzeby selekcji - prawdopodobie�stwo skumulowane
		
		for (int i = 0; i < Input.getNumberOfPersons(); i++) {
			if (i==0) {
				scoreSum[i] = pop.getPerson(i).getScore();
			} else {
				scoreSum[i] = scoreSum[i-1] + pop.getPerson(i).getScore();
			}
		}
		
		//selekcja - losowanie osobnik�w, kt�re przetrwa�y i zapisanie do roboczej populacji
		
		for (int i = 0; i < Input.getNumberOfPersons(); i++) {
			double randomSelector = random() * scoreSum[Input.getNumberOfPersons()-1];
			int index = 0;
			do {
				if (randomSelector < scoreSum[index]) {
					tempPop.setPerson(pop.getPerson(index), i);
				} else {
					index++;
				}
			} while (randomSelector > scoreSum[index]);
		}
		
		// przepisanie referencji z roboczej populacji na g��wn�
		
		tempPop.setAge(pop.getAge());
		tempPop.setBestPerson(pop.getBestPerson());
		pop = tempPop;
	}
	
	// operator genetyczny krzy�owania
	
	public static void Crossing(Population pop) {
		double random;
		int random2;
		String tempChain = new String();
		String tempCut = new String();
		String tempCut2 = new String();
		ArrayList<Integer> chosen = new ArrayList<>();
		ArrayList<String> binaryChain = new ArrayList<>();
		
		// losowanie osobnik�w do krzy�owania i zapisanie ich indeks�w oraz �a�cuch�w kod�w binarnych
		
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
		// je�li liczba wylosowanych osobnik�w jest nieparzysta - dobiera jako dawc� najlepszego
		
		if (chosen.size()%2 != 0) {
			for (int v = 0; v < Input.getNumberOfVariables(); v++) {
				tempChain += pop.getBestPerson().getBinaryVariable(v);
			}
			binaryChain.add(tempChain);
		}
		// wykonanie krzy�owania - zamiana cz�ci kodu mi�dzy �a�cuchami binarnymi dw�ch kolejnych os�b

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
	
	// operator mutacji	
	
	public static void Mutation(Population pop) {
		boolean isMutated;
		char[] binChar = new char[Input.getNumberOfBits()];
		
		// pr�ba mutacji dla ka�dej binarnej warto�ci ka�dej zmiennej, ka�dego osobnika w populacji
		
		for (int personIndex = 0; personIndex < Input.getNumberOfPersons(); personIndex++) {
			isMutated = false;
			for (int varIndex = 0; varIndex < Input.getNumberOfVariables(); varIndex++) {
				for (int binIndex = 0; binIndex < Input.getNumberOfBits(); binIndex++) {
					if (random() < Input.getMutationProbability()) {
						isMutated = true;
						// mutacja pojedy�czej warto�ci binarnej o wskazanym indeksie i zapis nowej binarnej zmiennej
						binChar = pop.getPerson(personIndex).getBinaryVariable(varIndex).toCharArray();
						binChar[binIndex] = (binChar[binIndex] == '0') ? '1': '0';
						pop.getPerson(personIndex).setBinaryVariable(String.valueOf(binChar), varIndex);
					}
				}
			}
			// zerowanie wyniku i wieku je�li mutacja nast�pi�a w dowolnym z bin�w osobnika
			if (isMutated) {
				pop.getPerson(personIndex).setScore(-1);
				pop.getPerson(personIndex).setAgeToZero();
			}
		}
	}			
}

