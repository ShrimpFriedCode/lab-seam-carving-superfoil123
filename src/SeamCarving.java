import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.sql.SQLSyntaxErrorException;
import java.util.*;

public class SeamCarving {

	// TODO: Find the seam with the lowest disruption measure
	public static Seam carve_seam(int[][] disruption_matrix) {

		//return traceBack of the translated disruption_matrix

		return traceBack(translate(disruption_matrix));

	}

	private static Seam traceBack(int[][] e){

		int width = e[0].length;
		int height = e.length;

		int dis = 0;

		//traceBack a given energy matrix

		ArrayList<Coord> points = new ArrayList<>();

		//start at bottom row, look for lowest value above, and add it as a coord.

		Coord curr = new Coord(0, 0);
		int min = e[height-1][0];

		for(int j = 0; j < width; j ++){
			if(e[height-1][j] < min){
				min = e[height-1][j];
				curr.y = height-1;
				curr.x = j;
			}
		}

		dis += min;
		points.add(new Coord(curr.y, curr.x));

		//System.out.println(e[0][1]);

		//go up from coord to find path


		for(int i = height-1; i > 0; i--){

			if(curr.x == 0){//on left edge

				if(e[curr.y-1][curr.x] < e[curr.y-1][curr.x+1]){
					points.add(new Coord(curr.y-1, curr.x));
					dis += e[curr.y-1][curr.x];
					curr.y = curr.y-1;
					//System.out.println("left edge dis: " + dis);
				}
				else {
					points.add(new Coord(curr.y-1, curr.x+1));
					dis += e[curr.y-1][curr.x+1];
					curr.x = curr.x+1;
					curr.y = curr.y-1;
					//System.out.println("left edge dis: " + dis);
				}
			}
			else if(curr.x == width-1){//on right edge

				if(e[curr.y-1][curr.x] < e[curr.y-1][curr.x-1]){
					points.add(new Coord(curr.y-1, curr.x));
					dis += e[curr.y-1][curr.x];
					curr.y = curr.y-1;
					//System.out.println("right edge dis: " + dis);
				}
				else {
					points.add(new Coord(curr.y-1, curr.x-1));
					dis += e[curr.y-1][curr.x-1];
					curr.x = curr.x-1;
					curr.y = curr.y-1;
					//System.out.println("right edge dis: " + dis);
				}

			}
			else{//mid

				min = Math.min(Math.min(e[curr.y-1][curr.x], e[curr.y-1][curr.x-1]), e[curr.y-1][curr.x+1]);

				if(min == e[curr.y-1][curr.x]){
					points.add(new Coord(curr.y-1, curr.x));
					dis += e[curr.y-1][curr.x];
					//System.out.println(curr.x + ", " + (curr.y-1));
					//System.out.println(e[curr.x][curr.y-1]);
					curr.y = curr.y-1;
					//System.out.println(i + " gen dis 0: " + dis);
				}
				else if(min == e[curr.y-1][curr.x-1]){
					points.add(new Coord(curr.y-1, curr.x-1));
					dis += e[curr.y-1][curr.x-1];
					curr.x = curr.x-1;
					curr.y = curr.y-1;
					//System.out.println("gen dis 1: " + dis);
				}
				else{
					points.add(new Coord(curr.y-1, curr.x+1));
					dis += e[curr.y-1][curr.x+1];
					curr.x = curr.x+1;
					curr.y = curr.y-1;
					//System.out.println("gen dis 2: " + dis);
				}

			}
		}

		Collections.reverse(points);

		for(Coord c : points){
			System.out.println(c.x + ", " + c.y);
		}

		return new Seam(dis, points);

	}

	private static int[][] translate(int[][] d){

		//translate a given disruption matrix into a matrix of dynamic decided paths

		int width = d[0].length;
		int height = d.length;

		int[][] energy = new int[height][width];

		int val;

		//populate first row as there is no math involved
		for(int i = 0; i < width; i++){
			energy[0][i] = d[0][i];
		}

		//dynamic algo

		//iterate through each row of d, and pick best path for energy
		for(int i = 1; i < height; i++){ //skip first row as we have already populated it
			for(int j = 0; j < width; j++){
				//hard part here

				//look at above row and find lowest disruption value
				//make current node that value + this node's disruption val

				//have to check for OOB
				//if on an edge, reduce available checks

				if(j == 0){//if on left edge
					val = d[i][j] + Math.min(energy[i-1][j], energy[i-1][j+1]); //top and right
					energy[i][j] = val;

				}
				else if(j == (width - 1)){//if on right edge

					val = d[i][j] + Math.min(energy[i-1][j], energy[i-1][j-1]); //top and left\
					energy[i][j] = val;

				}
				else{//if not on an edge

					val = d[i][j] + Math.min(Math.min(energy[i-1][j], energy[i-1][j+1]), energy[i-1][j-1]); //top, left, right
					energy[i][j] = val;

				}
			}
		}

		//list is now compiled, return
		return energy;
	}

	public static void main(String args[]){

		int[][] disruption_matrix = new int[3][3];
		disruption_matrix[0][0] = 5;
		disruption_matrix[0][1] = 0;
		disruption_matrix[0][2] = 5;
		disruption_matrix[1][0] = 5;
		disruption_matrix[1][1] = 0;
		disruption_matrix[1][2] = 5;
		disruption_matrix[2][0] = 5;
		disruption_matrix[2][1] = 0;
		disruption_matrix[2][2] = 5;
		/*
		for(int i = 0; i < disruption_matrix.length; i++){
			for(int j = 0; j < disruption_matrix[0].length; j++){
				System.out.print(disruption_matrix[i][j]);
			}
			System.out.println("");
		}
*/
		Seam s = carve_seam(disruption_matrix);
		System.out.println(s.disruption);

	}

}
