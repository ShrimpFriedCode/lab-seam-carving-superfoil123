import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTest {
	@Test(timeout=10000)
	public void testNoDisruption0() {
		int[][] disruption_matrix = new int[3][3];
		disruption_matrix[0][0] = 1;
		disruption_matrix[0][1] = 0;
		disruption_matrix[0][2] = 1;
		disruption_matrix[1][0] = 1;
		disruption_matrix[1][1] = 0;
		disruption_matrix[1][2] = 1;
		disruption_matrix[2][0] = 1;
		disruption_matrix[2][1] = 0;
		disruption_matrix[2][2] = 1;

		Seam seam = SeamCarving.carve_seam(disruption_matrix);
		assertEquals(0, seam.disruption);
		for (int i = 0; i != seam.size(); ++i) {
			Coord c = seam.get(i);

			assertEquals(i, c.x);
			assertEquals(1, c.y);
		}
	}

	@Test(timeout=10000)
	public void testNoDisruption1() {
		int[][] disruption_matrix = new int[3][3];
		disruption_matrix[0][0] = 0;
		disruption_matrix[0][1] = 1;
		disruption_matrix[0][2] = 2;
		disruption_matrix[1][0] = 0;
		disruption_matrix[1][1] = 0;
		disruption_matrix[1][2] = 1;
		disruption_matrix[2][0] = 1;
		disruption_matrix[2][1] = 1;
		disruption_matrix[2][2] = 0;

		Seam seam = SeamCarving.carve_seam(disruption_matrix);
		assertEquals(0, seam.disruption);
		for (int i = 0; i != seam.size(); ++i) {
			Coord c = seam.get(i);

			assertEquals(i, c.x);
			assertEquals(i, c.y);
		}
	}

	@Test(timeout=10000)
	public void testNoDisruption2() {
		int[][] disruption_matrix = new int[4][4];
		disruption_matrix[0][0] = 1;
		disruption_matrix[0][1] = 2;
		disruption_matrix[0][2] = 0;
		disruption_matrix[0][3] = 3;

		disruption_matrix[1][0] = 1;
		disruption_matrix[1][1] = 2;
		disruption_matrix[1][2] = 3;
		disruption_matrix[1][3] = 0;

		disruption_matrix[2][0] = 1;
		disruption_matrix[2][1] = 2;
		disruption_matrix[2][2] = 3;
		disruption_matrix[2][3] = 0;

		disruption_matrix[3][0] = 1;
		disruption_matrix[3][1] = 2;
		disruption_matrix[3][2] = 0;
		disruption_matrix[3][3] = 3;

		Seam seam = SeamCarving.carve_seam(disruption_matrix);
		assertEquals(0, seam.disruption);
		for (int i = 0; i != seam.size(); ++i) {
			Coord c = seam.get(i);

			assertEquals(i, c.x);

			if (i == 0 || i == 3) {
				assertEquals(2, c.y);
			} else {
				assertEquals(3, c.y);
			}
		}
	}
}