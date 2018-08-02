import java.util.HashMap;
import java.util.Map;

/**
 * Least-recently used cache.
 * 
 * This implementation stores the cache in value and prioritizes
 * the performance of Get operations over Put.
 * 
 * Here we achieve O(1) Get, and O(n) Put by using a HashMap.
 * The alternative is using an ordered data structure, like TreeMap,
 * yielding O(log(n)) access times.
 */
public class LRUCache implements Cache {
	
	private int maxSize = 5;
	
	private HashMap<String,CacheObject> map = new HashMap<String,CacheObject>(); 
	
	public LRUCache() {
	}
	
	public LRUCache(int _maxSize) {
		maxSize = _maxSize;
	}

	/**
	 * Get value from cache.
	 */
	@Override
	public Object get(String key) {
		CacheObject cacheObject = map.getOrDefault(key, new CacheObject());
		return cacheObject.value();
	}

	/** 
	 * Set a value in the cache.
	 * If cache has reached its max size, then remove the LRU object
	 * before inserting.
	 */
	@Override
	public void set(String key, Object value) {
		/* 
		 * If we will just overwrite the key, no need to remove.
		 * Make synchronized in case multiple threads are checking the same
		 * missing value, so that they don't both scan the whole map + remove + write.
		 * (helps prevent cache miss storm)
		 * @TODO unit test with concurrency
		 */
		synchronized(this) {
			if (!map.containsKey(key) && map.size() >= maxSize) {
				removeLRUEntry();
			}
		}
		map.put(key, new CacheObject(value));
	}
	
	public void unset(String key) {
		map.remove(key);
	}
	
	private void removeLRUEntry() {
		// Scan through entire map, check last access time
		String lruKey = null;
		long lruDate = System.nanoTime();
		for (Map.Entry<String, CacheObject> entry : map.entrySet()) {
			long lastAccess = entry.getValue().getLastAccess();
			if (lastAccess < lruDate) {
				lruKey = entry.getKey();
				lruDate = lastAccess;
			}
		};
		
		// Remove item with oldest last access
		map.remove(lruKey);
	}

	@Override
	public void setMaxSize(int _maxSize) {
		maxSize = _maxSize;
	}
}
