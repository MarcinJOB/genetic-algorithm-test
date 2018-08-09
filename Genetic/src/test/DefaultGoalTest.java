package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import optimization.*;

class DefaultGoalTest {
	
	Goal goal = new DefaultGoal();

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}

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
	void shouldReturnException() {
		double[] variables = {0,0};
		Person person = new Person(variables);
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> goal.calculateScore(person));
	}

}
