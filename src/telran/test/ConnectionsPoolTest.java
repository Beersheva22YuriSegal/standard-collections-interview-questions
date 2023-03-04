package telran.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.structure.Connection;
import telran.structure.ConnectionsPoolImpl;

class ConnectionsPoolTest {
	
	ConnectionsPoolImpl pool;

	@BeforeEach
	void setUp() {
		pool = new ConnectionsPoolImpl();
		pool.addConnection(new Connection(3, "aa", 1));
		pool.addConnection(new Connection(6, "bb", 2));
		pool.addConnection(new Connection(9, "cc", 3));
		pool.addConnection(new Connection(12, "dd", 4));
		pool.addConnection(new Connection(15, "", 5));
	}

	@Test
	void connectionsPoolTest() {
		assertFalse(pool.addConnection(new Connection(3, "aa", 1)));
		assertTrue(pool.addConnection(new Connection(18, "ee", 6)));
		assertFalse(pool.addConnection(new Connection(18, "ee", 6)));
		assertNull(pool.getConnection(3));
		assertTrue(pool.addConnection(new Connection(3, "ee", 1)));
		assertNotNull(pool.getConnection(3));
		assertFalse(pool.addConnection(new Connection(15, "", 5)));
	}
	
	@Test
	void getConnectionTest() {
		assertEquals(15, pool.getConnection(15).id);
		assertEquals("bb", pool.getConnection(6).ipAddress);
		assertEquals(4, pool.getConnection(12).port);
		assertNull(pool.getConnection(21));
	}
}
