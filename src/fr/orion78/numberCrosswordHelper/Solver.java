package fr.orion78.numberCrosswordHelper;

import java.util.ArrayList;
import java.util.List;

public class Solver {

	public static List<int[]> solve(int nb, int cells) {
		return solve(nb, cells, 1);
	}

	private static List<int[]> solve(int nb, int cells, int start) {
		List<int[]> l = new ArrayList<>();
		
		if(nb < start){
			// No solution
		} else if(cells == 1){
			if(nb < 10){
				// Unique solution
				int[] sol = {nb};
				l.add(sol);
			}
		} else {
			for(int i = start; i < 10; i++){
				List<int[]> l2 = solve(nb - i, cells-1, i+1);
				
				for(int[] e : l2){
					int[] sol = new int[cells];
					sol[0] = i;
					for(int j = 0; j < e.length; j++){
						sol[j+1] = e[j];
					}
					l.add(sol);
				}
			}
		}
		
		return l;
	}
}
