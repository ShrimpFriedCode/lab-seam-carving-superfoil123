import java.util.ArrayList;

public class SeamSegment {
	final int         disruption;
	final Coord       endpoint;
	final SeamSegment parent;

	SeamSegment(int arg_disruption, Coord arg_endpoint, SeamSegment arg_parent) {
		this.disruption = arg_disruption;
		this.endpoint = arg_endpoint;
		this.parent = arg_parent;
	}

	Seam getSeam() {
		ArrayList<Coord> path = new ArrayList<>();

		SeamSegment curr_segment = this;

		do {
			path.add(curr_segment.endpoint);
		} while ((curr_segment = curr_segment.parent) != null);

		return new Seam(this.disruption, path);
	}
}