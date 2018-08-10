package optimization;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

class InputTest {
	ArrayList<String> test_list = new ArrayList<>();

@Test
	void testReadFile() {
		test_list = Input.readFile("input.txt");
// test_list should have size of 14 variables
		assertEquals(14, test_list.size());
		
	}
@Test
	void testFirstValue() {
		test_list = Input.readFile("input.txt");
// test_list should return value at index 0 equal to 200		
		assertEquals(200,  Integer.parseInt(test_list.get(0)));
	}
}
