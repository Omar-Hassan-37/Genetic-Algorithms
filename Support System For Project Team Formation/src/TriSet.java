import java.util.ArrayList;

public class TriSet extends Set{

	public TriSet(String variableName, String setName, ArrayList<Integer> points) {
		super(variableName, setName, points);
		int[] y_axis = {0,1,0};
		setY_axis(y_axis);
	}

}
