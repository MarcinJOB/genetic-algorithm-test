package optimization;

public class Population {

	// deklaracja zmiennych obiektu
	private int age;
	private Person[] person;
	private Person bestPerson;
	
	// konstruktor populacji o liczbie osobników podanej w argumencie, z dodatkowym osobnikiem najlepszym
	public Population() {
		person = new Person[Input.getNumberOfPersons()];
		bestPerson = new Person();
		age = 0;
	
		for (int i=0; i < Input.getNumberOfPersons(); i++) {
			person[i] = new Person();
		}

	}
	// obliczanie funkcji celu dla wszystkich osobników oraz wy³onienie najlepszego osobnika z grupy
	public void calculateScores() {	
		int bestIndex = 0;		// indeks najlepszego osobnika
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
			this.setBestPerson(person[bestIndex]);
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
	
	
	// w celu skopiowania osobnika u¿ywaæ nazwa_os.copyPersonFrom(nazwa_os2)
	public void setPerson(Person person,int indexOfPerson) {
		this.person[indexOfPerson] = person;
	}
	
	public Person getPerson(int indexOfPerson) {
		return this.person[indexOfPerson];
	}
	
	
	public void setBestPerson(Person person) {
		this.bestPerson.copyPersonFrom(person);
	}
	
	
	public Person getBestPerson() {
		return this.bestPerson;
	}
}
