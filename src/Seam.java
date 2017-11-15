import java.util.ArrayList;

public class Seam {
	int              disruption;
	ArrayList<Coord> points;

	Seam(int arg_disruption, ArrayList<Coord> arg_points) {
		this.disruption = arg_disruption;
		this.points = arg_points;
	}

	int size() {
		return this.points.size();
	}

	Coord get(int index) {
		return this.points.get(index);
	}
}