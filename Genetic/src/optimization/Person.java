package optimization;

public class Person {
	

	// zmienne instancyjne
	private double[] variable;
	private String[] binaryVariable;
	double score;
	int age;
	
	// konstruktor bez argument�w, tworzy osobnika o danej liczbie zmiennych i losuje dla niego warto�ci tych zmiennych
	public Person() {
		score = -1;
		age = 0;
		this.setRandomVariables();
	}


	// ustawienie losowej warto�ci binarnej i przeliczenie rzeczywistej dla tablicy warto�ci
	public void setRandomVariables() {
		variable = new double[Input.getNumberOfVariables()];
		binaryVariable = new String[Input.getNumberOfVariables()];
	
		for (int i = 0; i < Input.getNumberOfVariables(); i++) {
			int decimal = (int) Math.floor(Math.random()*Input.getNumberOfCombinations());
			this.variable[i] = decimal * Input.getStepValueOfVar(i) + Input.getMinValueOfVar(i);
			this.binaryVariable[i] = Integer.toBinaryString(decimal);
				while (this.binaryVariable[i].length() != Input.getNumberOfBits()) {
					this.binaryVariable[i] = "0" + this.binaryVariable[i];
			}
		}
	}

	// jedyny spos�b zmiany warto�ci zmiennej - przez warto�� binarn�, na potrzeby modyfikator�w genetycznych
	
	public void setBinaryVariable(String binaryVariable, int indexOfVariable) {
		this.binaryVariable[indexOfVariable] = binaryVariable;
		int decimal = Integer.parseInt(binaryVariable, 2);
		this.variable[indexOfVariable] = decimal * Input.getStepValueOfVar(indexOfVariable) 
				+ Input.getMinValueOfVar(indexOfVariable);
	}
	// kopiowanie danych osobnika na potrzeby najlepszego osobnika
	public void copyPersonFrom(Person PersonDoner) {
		this.score = PersonDoner.score;
		this.age = PersonDoner.age;
		for (int i = 0; i < Input.getNumberOfVariables(); i++) {
		this.setBinaryVariable(PersonDoner.getBinaryVariable(i), i);
		}
	}

	
	// pozosta�e gettery i settery
	
	
	public double getVariableValue(int indexOfVariable) {
		return this.variable[indexOfVariable];
	}
	
	public String getBinaryVariable(int indexOfVariable) {
		return this.binaryVariable[indexOfVariable];
	}
	
	public double getScore() {
		return this.score;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public void setAgeUp() {
		this.age++;
	}
	
	public void setAgeToZero() {
		this.age = 0;
	}


}


