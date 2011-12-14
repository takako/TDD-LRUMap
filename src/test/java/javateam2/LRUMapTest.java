package javateam2;
import java.util.*;
import static org.testng.AssertJUnit.*;
import org.testng.annotations.Test;

public class LRUMapTest {
	@Test
	public void testConstructorSize() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		assertEquals(3, lruMap.size());
	}

	@Test
	public void testConstructorType1() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(1, "1");
		assertEquals("1", lruMap.get(1));
	}

	@Test
	public void testConstructorType2() {
		LRUMap<String, Double> lruMap = new LRUMap<String, Double>(3);
		lruMap.put("hoge", 1.5);
		assertEquals(1.5, lruMap.get("hoge"));
	}

	@Test
	public void testPut() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(1,"test001");
		assertEquals("test001", lruMap.get(1));
	}

	@Test
	public void testSize() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(1);
		lruMap.put(1,"test002");
		lruMap.put(2,"test003");
		assertEquals(null, lruMap.get(1));
	}
	@Test
	public void testSize3() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(2);
		lruMap.put(1,"test002");
		lruMap.put(2,"test003");
		lruMap.put(3,"test004");
		assertEquals("test003", lruMap.get(2));
	}
	@Test
	public void testGetNull() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(1,"1");
		lruMap.put(2,"2");
		lruMap.put(3,"3");
		lruMap.get(1);
		lruMap.get(2);
		lruMap.put(4,"4");
		assertEquals("1", lruMap.get(1));
		assertEquals("2", lruMap.get(2));
		assertEquals("4", lruMap.get(4));
		assertEquals(null, lruMap.get(3));
	}
	@Test
	public void testGetNull2() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(1,"1");
		lruMap.put(2,"2");
		lruMap.put(3,"3");
		lruMap.get(2);
		assertEquals("1", lruMap.get(1));
		assertEquals("2", lruMap.get(2));
		assertEquals("3", lruMap.get(3));
	}
	@Test
	public void testConstructorException() {
		try {
			LRUMap<Integer, String> lrumap = new LRUMap<Integer, String>(-1);
			fail();
		} catch (Exception e) {
			assertTrue(true);
			assertEquals("サイズは1以上:input=-1", e.getMessage());
		}
	}
	@Test
	public void testPutSameKey() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(2,"2");
		lruMap.put(1,"first");
		lruMap.put(1,"second");
		lruMap.put(3,"3");
		assertEquals("2", lruMap.get(2));
		assertEquals("second", lruMap.get(1));
	}

	@Test
	public void testResize() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(1,"1st");
		lruMap.put(2,"2nd");
		lruMap.put(3,"3rd");
		lruMap.resize(4);
		assertEquals(4, lruMap.size());
	}
	@Test
	public void testResize2() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(2);
		lruMap.put(1,"1st");
		lruMap.put(2,"2nd");
		lruMap.resize(1);
		assertEquals(1, lruMap.size());
		assertEquals(null, lruMap.get(1));
		assertEquals("2nd", lruMap.get(2));
	}
	@Test
	public void testResize3() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3);
		lruMap.put(1,"1st");
		lruMap.put(2,"2nd");
		lruMap.put(3,"3rd");
		lruMap.resize(4);
		lruMap.put(4, "4th");
		assertEquals("1st", lruMap.get(1));
		assertEquals("4th", lruMap.get(4));
	}
	@Test
	public void testDiscardWithTime() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3, 1);
		lruMap.put(1,"1st");
		lruMap.put(2,"2nd");
		lruMap.put(3,"3rd");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		assertEquals(null, lruMap.get(1));
	}

	// 2倍の時間経過したら2つ消えるテスト
	@Test
	public void testDiscardWithTime2() {
		LRUMap<Integer, String> lruMap = new LRUMap<Integer, String>(3, 1);
		lruMap.put(1,"1st");
		lruMap.put(2,"2nd");
		lruMap.put(3,"3rd");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		assertEquals(null, lruMap.get(1));
		assertEquals(null, lruMap.get(2));
	}
}