import java.util.Random;

public class Utilities {
	public static void print_disruption_matrix(int[][] disruption_matrix) {
		for (int x_index = 0; x_index < disruption_matrix.length; ++x_index) {
			for (int y_index = 0; y_index < disruption_matrix[x_index].length; ++y_index) {
				System.out.print(String.format(" %d", disruption_matrix[x_index][y_index]));
			}

			System.out.println();
		}
	}

	public static void print_seam(int[][] disruption_matrix, Seam seam) {
		int[][] local_d_matrix = new int[disruption_matrix.length][disruption_matrix[0].length];

		for (int x_index = 0; x_index < disruption_matrix.length; ++x_index) {
			for (int y_index = 0; y_index < disruption_matrix[x_index].length; ++y_index) {
				local_d_matrix[x_index][y_index] = disruption_matrix[x_index][y_index];
			}
		}

		for (Coord point : seam.points) {
			local_d_matrix[point.x][point.y] = -1;
		}

		for (int x_index = 0; x_index < disruption_matrix.length; ++x_index) {
			for (int y_index = 0; y_index < disruption_matrix[x_index].length; ++y_index) {
				if (local_d_matrix[x_index][y_index] == -1) {
					System.out.print(" |");
				} else {
					System.out.print(String.format(" %d", local_d_matrix[x_index][y_index]));
				}
			}

			System.out.println();
		}
	}

	public static boolean isValidSeam(int[][] disruption_matrix, Seam seam) {
		if (seam.points.size() != disruption_matrix.length) {
			System.out.println("Seam does not contain enough points\n" +
							   "\tExpected: " + disruption_matrix.length + " (number of rows in image)\n" +
							   "\tGot     : " + seam.points.size());

			return false;

		} else if (seam.points.get(0).x == 0) {
			return isValidTopDownSeam(disruption_matrix, seam);

		} else if (seam.points.get(0).x == disruption_matrix.length - 1) {
			return isValidBottomUpSeam(disruption_matrix, seam);

		} else {
			System.out.println("Seam does not start at top or bottom edge of image.");
			return false;
		}
	}

	private static boolean isValidTopDownSeam(int[][] disruption_matrix, Seam seam) {
		for (int x_index = 0; x_index < disruption_matrix.length; ++x_index) {
			Coord point = seam.points.get(x_index);

			// In bounds
			if (point.x != x_index || point.y >= disruption_matrix[x_index].length) {
				return false;
			}

			// Connected to next point
			if (x_index < disruption_matrix.length - 1) {
				Coord next_point = seam.points.get(x_index + 1);

				if (!point.isYAdjacent(next_point, disruption_matrix[x_index].length)) {
					return false;
				}
			}
		}

		return seam.points.get(seam.points.size() - 1).x == disruption_matrix.length - 1;
	}

	private static boolean isValidBottomUpSeam(int[][] disruption_matrix, Seam seam) {
		for (int x_index = disruption_matrix.length - 1; x_index >= 0; --x_index) {
			Coord point = seam.points.get(x_index);

			// In bounds
			if (point.x != x_index || point.y >= disruption_matrix[x_index].length) {
				return false;
			}

			// Connected to next point
			if (x_index > 0) {
				Coord next_point = seam.points.get(x_index - 1);

				if (!point.isYAdjacent(next_point, disruption_matrix[x_index].length)) {
					return false;
				}
			}
		}

		return seam.points.get(seam.points.size() - 1).x == disruption_matrix.length - 1;
	}

	public static Pixel[][] generateRandomBWImage(int height, int width, long seed) {
		Random rand = new Random();
		rand.setSeed(seed);

		Pixel[][] image = new Pixel[height][width];

		for (int x_index = 0; x_index < height; ++x_index) {
			for (int y_index = 0; y_index < width; ++y_index) {
				image[x_index][y_index] = rand.nextBoolean() ? new Pixel(0, 0, 0) :
										  new Pixel(255, 255, 255);
			}
		}

		return image;
	}


	public static Pixel[][] generateRandomImage(int height, int width, long seed) {
		Random rand = new Random();
		rand.setSeed(seed);

		Pixel[][] image = new Pixel[height][width];

		for (int x_index = 0; x_index < height; ++x_index) {
			for (int y_index = 0; y_index < width; ++y_index) {
				int red   = rand.nextInt(256);
				int blue  = rand.nextInt(256);
				int green = rand.nextInt(256);

				image[x_index][y_index] = new Pixel(red, green, blue);
			}
		}

		return image;
	}
}