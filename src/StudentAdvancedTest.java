import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentAdvancedTest {
	@Test(timeout = 10000)
	public void testBWImage0() {
		Pixel[][] image             = Utilities.generateRandomBWImage(10, 10, 42);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(2460, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testBWImage1() {
		Pixel[][] image             = Utilities.generateRandomBWImage(16, 16, 112);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(4646, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testBWImage2() {
		Pixel[][] image             = Utilities.generateRandomBWImage(32, 32, 73);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(7080, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testBWImage3() {
		Pixel[][] image             = Utilities.generateRandomBWImage(51, 47, 357);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(13137, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testBWImage4() {
		Pixel[][] image             = Utilities.generateRandomBWImage(78, 93, 185762);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(18536, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testColorImage0() {
		Pixel[][] image             = Utilities.generateRandomImage(24, 24, 42587);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(1865, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testColorImage1() {
		Pixel[][] image             = Utilities.generateRandomImage(67, 67, 2241);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(5845, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testColorImage2() {
		Pixel[][] image             = Utilities.generateRandomImage(100, 100, 4475);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(8722, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}

	@Test(timeout = 10000)
	public void testColorImage3() {
		Pixel[][] image             = Utilities.generateRandomImage(100, 100, 785);
		int[][]   disruption_matrix = Stencil.disruption(image);

		Seam seam = SeamCarving.carve_seam(disruption_matrix);

		assertEquals(8277, seam.disruption);
		assertTrue(Utilities.isValidSeam(disruption_matrix, seam));
	}
}