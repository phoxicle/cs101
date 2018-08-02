
public interface Cache {
	
	public Object get(String key);
	
	public void set(String key, Object value);
	
	public void unset(String key);

	public void setMaxSize(int i);
	
}
