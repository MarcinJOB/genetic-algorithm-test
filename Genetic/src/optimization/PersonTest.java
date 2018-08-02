package optimization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class PersonTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetScore() {
		
	// should result in -1
		
		Person person = new Person();
		assertEquals(person.getScore(), -1 );
	}

	@Test
	void testGetAge() {
		
	// should result in 0
		
		Person person = new Person();
		assertEquals(person.getAge(), 0 );
	}

	@Test
	void testSetScore() {
		
	// should result in 0.1 and not -1
		
		Person person = new Person();
		person.setScore(0.1);
		assertEquals(person.getScore(), 0.1);
		assertNotEquals(person.getScore(), -1);
	}

	@Test
	void testSetAgeUp() {
		
	// should result in 0, 1 and 2
		
		Person person = new Person();
		assertEquals(person.getAge(), 0 );
		
		person.setAgeUp();
		assertEquals(person.getAge(), 1 );
		assertNotEquals(person.getAge(), 0 );
		
		person.setAgeUp();
		assertEquals(person.getAge(), 2 );
		assertNotEquals(person.getAge(), 0 );
	}

	@Test
	void testSetAgeToZero() {
		
	// should result in 0, 1 and 0	
		
		Person person = new Person();
		assertEquals(person.getAge(), 0 );
		
		person.setAgeUp();
		assertEquals(person.getAge(), 1 );
		assertNotEquals(person.getAge(), 0 );
		
		person.setAgeToZero();
		assertEquals(person.getAge(), 0);
		assertNotEquals(person.getAge(), 1 );
	}

}
