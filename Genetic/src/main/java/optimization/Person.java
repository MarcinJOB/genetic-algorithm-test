package main.java.optimization;

public class Person {
	
	// instance variables
	private double[] variable;
	private String[] binaryVariable;
	private double score;
	private int age;
	
	// no-argument constructor - creates person with random values for all variables
	public Person() {
		score = -1;
		age = 0;
		this.setRandomVariables();
	}

	// setting random binary value of variables and calculating decimal values
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
	
	// the only way to change value of variable - through binary value (used by genetic modificators)
	
	public void setBinaryVariable(String binaryVariable, int indexOfVariable) {
		this.binaryVariable[indexOfVariable] = binaryVariable;
		int decimal = Integer.parseInt(binaryVariable, 2);
		this.variable[indexOfVariable] = decimal * Input.getStepValueOfVar(indexOfVariable) 
				+ Input.getMinValueOfVar(indexOfVariable);
	}
	
	// copy person details for saving the best person
	public void copyPersonFrom(Person PersonDonor) {
		this.score = PersonDonor.score;
		this.age = PersonDonor.age;
		for (int i = 0; i < Input.getNumberOfVariables(); i++) {
		this.setBinaryVariable(PersonDonor.getBinaryVariable(i), i);
		}
	}

	// other setters & getters
	
	
	public double getVariable(int indexOfVariable) {
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
	
	// constructor for testing (need to work on simplifying code, so this won't be necessary)
	public Person(double[] variables) {
		score = -1;
		age = 0;
		this.variable = variables;
	}


}


