import java.lang.ref.SoftReference;

/**
 * CacheObject encapsulates logic related to garbage collecting.
 */
public class CacheObject {

	/*
	 * Use softReference so that we don't hit OutOfMemoryErrors.
	 * This means though that the value of any item could be dereferenced,
	 * so we need to protect ourselves against that in the getter.
	 */
	private SoftReference<Object> value;
	
	/*
	 * Default date to now on instantiation.
	 */
	private long lastAccess = System.nanoTime();
	
	public CacheObject() {
	}
	
	public CacheObject(Object _value) {
		value = new SoftReference<Object>(_value);
	}
	
	/**
	 * Get a value from the cache.
	 * If empty, return -1.
	 * @return
	 */
	public Object value() {
		// Update last access time
		touch();
		
		// Protection for garbage-collected value
		if (value == null || value.get() == null) return -1;
		return value.get();
	}

	public long getLastAccess() {
		return lastAccess;
	}

	private void touch() {
		this.lastAccess = System.nanoTime();
	}
}
