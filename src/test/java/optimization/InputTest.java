package optimization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputTest {
	String[] myList;
	// tests for - static String[] readFile(fileName)
@Test
		void shouldRead14LinesOfFile() {
			myList = Input.readFile("src/main/resources/input.txt");	
			Assertions.assertEquals(14, myList.length);	
		}
@Test
	void shouldReturnFirstLineEqual200() {
		myList = Input.readFile("src/main/resources/input.txt");		
		Assertions.assertEquals(200,  Integer.parseInt(myList[0]));
	}
@Test
	void shouldPrintAllLines() {
		myList = Input.readFile("src/main/resources/input.txt");
		for (String line: myList) {
			System.out.print(line+", ");
		}	
	}
//tests for - static boolean setConstants(String[] inputLines)
@Test
	void shouldNotBeNull(){
		myList = Input.readFile("src/main/resources/input.txt");
		Input.setConstants(myList);
		Assertions.assertNotNull(Input.getMaxValueOfVar(2));
	}
@Test
	void shoulThrowException() {
		String[] wrongList = {"1","2","3","4","5","6","7","1","0"};
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> Input.setConstants(wrongList));
	}
}

