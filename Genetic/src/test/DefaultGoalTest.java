package test;

import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import optimization.*;

class DefaultGoalTest {
	
	Goal goal = new DefaultGoal();

	@Test
	void shouldReturn250() {
		double[] variables = {0,0,0};
		Person person = new Person(variables);
		goal.calculateScore(person);
		Assertions.assertEquals(250.0, person.getScore());
	}
	@Test
	void shouldReturn250again() {
		double[] variables = {0,0,0,0};
		Person person = new Person(variables);
		goal.calculateScore(person);
		Assertions.assertEquals(250.0, person.getScore());
	}
	
	@Test
	void shouldThrowException() {
		double[] variables = {0,0};
		Person person = new Person(variables);
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> goal.calculateScore(person));
	}

}
