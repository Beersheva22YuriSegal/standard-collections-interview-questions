package telran.test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.MultiCountersAppl;

class MultiCountersTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void multiCountersApplTest() {
		MultiCountersAppl counter = new MultiCountersAppl();
		assertEquals(1, counter.addItem("a"));
		assertEquals(2, counter.addItem("a"));
		assertEquals(2, counter.getValue("a"));
		assertEquals(1, counter.addItem(7));
		assertEquals(2, counter.addItem(7));
		assertTrue(counter.remove("a"));
		assertNull(counter.getValue("a"));
		assertFalse(counter.remove("a"));
		
		Set<Object> set = new HashSet<>();
		set.add(7);
		assertEquals(set, counter.getMaxItems());
		counter.addItem(22);
		counter.addItem(22);
		counter.addItem(22);
		set.remove(7);
		set.add(22);
		assertEquals(set, counter.getMaxItems());	
	}

}
