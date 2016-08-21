package fr.orion78.numberCrosswordHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		System.out.print("Nombre : ");
		int nb = s.nextInt();
		System.out.print("Cases : ");
		int cells = s.nextInt();
		
		s.close();
		
		List<int[]> l = Solver.solve(nb, cells);
		
		for(int[] e : l){
			System.out.println(Arrays.toString(e));
		}
	}
}
