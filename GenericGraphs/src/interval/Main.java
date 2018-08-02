package interval;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("hi");
		
		SegmentNode t = new SegmentNode();
		t.add(1,3);
		t.add(0,2);
		t.add(1,9);
		t.add(5,10);
		t.add(3,10);
		
		// Find intervals that contain a point
		List<Interval> intervals = t.getIntervals(5);
	}
	

}
