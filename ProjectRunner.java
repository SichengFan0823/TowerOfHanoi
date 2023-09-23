package towerofhanoi;

// Virginia Tech Honor Code Pledge:
//f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// Sicheng Fan

/**
 * This class is responsible for running the Tower of Hanoi puzzle game.
 * 
 * @author
 */
public class ProjectRunner {

	/**
	 * 
	 * The main method of this class. It creates a new HanoiSolver object with a
	 * given number of disks, and then creates a new PuzzleWindow object with the
	 * HanoiSolver as a parameter, which displays the game window and allows the
	 * user to play the puzzle.
	 * 
	 * @param args an array of command-line arguments
	 */
	public static void main(String[] args) {
		int disks = Integer.parseInt(args[0]);
		new PuzzleWindow(new HanoiSolver(disks));
	}
}
