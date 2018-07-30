package optimization;

public class Population {

	// instance variables
	private int age;
	private Person[] person;
	private Person bestPerson;
	
	// no-argument constructor - creates defined number of persons and best person
	public Population() {
		person = new Person[Input.getNumberOfPersons()];
		bestPerson = new Person();
		age = 0;
	
		for (int i=0; i < Input.getNumberOfPersons(); i++) {
			person[i] = new Person();
		}
	}
	
	// calculation of goal function score for all persons and saving the best person
	public void calculateScores() {	
		int bestIndex = 0;		// temporary index of the best person
		bestPerson.setAgeUp();
		
		for (int i=0; i < Input.getNumberOfPersons(); i++) {
			if (person[i].getAge() == 0) {
				Goal.calculateScore(person[i]);
				if (person[i].getScore() > person[bestIndex].getScore())
					bestIndex = i;
			}
			person[i].setAgeUp();
		}
		if (person[bestIndex].getScore() > bestPerson.getScore()) {
			this.getBestPerson().copyPersonFrom(person[bestIndex]);
//			this.setBestPerson(person[bestIndex]);
		}
		this.setPopulationAgeUp();
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int populationAge) {
		this.age = populationAge;
	}
	
	public void setPopulationAgeUp() {
		this.age++;
	}
//	setPerson method is probably no longer needed - person.copyPersonFrom method is used
	
//	public void setPerson(Person person,int indexOfPerson) {
//		this.person[indexOfPerson].copyPersonFrom(person);
//	}
	
	public Person getPerson(int indexOfPerson) {
		return this.person[indexOfPerson];
	}
	
// as above - setBestPerson method probably no longer needed - person.copyPersonFrom method is used directly
	
//	public void setBestPerson(Person person) {
//		this.bestPerson.copyPersonFrom(person);
//	}
	
	
	public Person getBestPerson() {
		return this.bestPerson;
	}
}
