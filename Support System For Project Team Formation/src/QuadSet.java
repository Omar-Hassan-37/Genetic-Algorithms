import java.util.ArrayList;

public class QuadSet extends Set{

	public QuadSet(String variableName, String setName, ArrayList<Integer> points) {
		super(variableName, setName, points);
		int[] y_axis = {0,1,1,0};
		setY_axis(y_axis);
	}

}
