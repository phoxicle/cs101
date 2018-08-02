import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LRUCacheTest {
	
	Cache cache;
	
	@BeforeEach
	void setUp() {
		cache = new LRUCache();
	}

	@Test
	void lruCache_withValue_returnsValue() {
		cache.set("a", "A");
		assertEquals("A", cache.get("a"));
	}
	
	@Test
	void lruCache_withoutValue_returnsNeg1() {
		cache.set("a", "A");
		assertEquals(-1, cache.get("b"));
	}
	
	@Test
	void lruCache_settingSameKey_overridesValue() {
		cache.setMaxSize(2);
		cache.set("a", "A");
		cache.set("a", "A2");
		assertEquals("A2", cache.get("a"));
	}
	
	@Test
	void lruCache_withManyValues_replacesLRUEntryAndReturnsNeg1() {
		cache.setMaxSize(2);
		cache.set("a", "A");
		cache.set("b", "B");
		cache.set("c", "C");
		assertEquals(-1, cache.get("a"));
	}
}
