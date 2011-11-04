package javateam2;

import static org.testng.AssertJUnit.*;
import org.testng.annotations.Test;

public class LRUMapTest {
	@Test
	public void testConstructor() {
		LRUMap lruMap = new LRUMap(3);
		assertEquals(new Integer(3), lruMap.size());
	}
	
	@Test
	public void testPut() {
		LRUMap lruMap = new LRUMap(3);
		lruMap.put(1,"test001");
		assertEquals("test001", lruMap.get(1));
	}
	
	@Test
	public void testSize() {
		LRUMap lruMap = new LRUMap(1);
		lruMap.put(1,"test002");
		lruMap.put(2,"test003");
		assertEquals(null, lruMap.get(1));		
	}
	@Test
	public void testSize3() {
		LRUMap lruMap = new LRUMap(2);
		lruMap.put(1,"test002");
		lruMap.put(2,"test003");
		lruMap.put(3,"test004");
		assertEquals("test002", lruMap.get(2));
	}
	
}
