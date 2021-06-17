import java.util.ArrayList;

abstract class Set {
	private String variableName;
	private String setName;
	private ArrayList<Integer> points;
	private int[] y_axis;
	
	public Set(String variableName, String setName, ArrayList<Integer> points) {
		super();
		this.variableName = variableName;
		this.setName = setName;
		this.points = points;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getSetName() {
		return setName;
	}

	public void setSetName(String setName) {
		this.setName = setName;
	}

	public ArrayList<Integer> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Integer> points) {
		this.points = points;
	}

	public int[] getY_axis() {
		return y_axis;
	}

	public void setY_axis(int[] y_axis) {
		this.y_axis = y_axis;
	}
	
}
