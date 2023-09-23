// Virginia Tech Honor Code Pledge:
//f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// Sicheng Fan
package towerofhanoi;

import java.util.Iterator;
import java.util.Observable;

/**
 * This class represents a solver for the Tower of Hanoi puzzle.
 * 
 * @author
 */
public class HanoiSolver extends Observable {

	private Tower left;
	private Tower middle;
	private Tower right;
	private int numDisks;

	/**
	 * 
	 * Constructs a HanoiSolver object with the specified number of disks.
	 * Initializes the left, middle, and right towers with their respective
	 * positions.
	 * 
	 * @param numDisks the number of disks in the puzzle.
	 * 
	 */
	public HanoiSolver(int numDisks) {
		this.numDisks = numDisks;
		this.left = new Tower(Position.LEFT);
		this.middle = new Tower(Position.MIDDLE);
		this.right = new Tower(Position.RIGHT);
	}

	/**
	 * Returns the number of disks in the Tower of Hanoi puzzle.
	 * 
	 * @return the number of disks in the Tower of Hanoi puzzle.
	 * 
	 */
	public int disks() {
		return numDisks;
	}

	/**
	 * 
	 * Returns the tower at the specified position.
	 * 
	 * @param position the position of the tower to be returned.
	 * @return the tower at the specified position.
	 */
	public Tower getTower(Position position) {
		switch (position) {
		case LEFT:
			return left;
		case RIGHT:
			return right;
		case MIDDLE:
			return middle;
		default:
			return middle;
		}
	}

	/**
	 * 
	 * Returns a string representation of the current state of the Tower of Hanoi
	 * puzzle. The string includes the disks of the left, middle, and right towers.
	 * 
	 * @return a string representation of the current state of the Tower of Hanoi
	 *         puzzle.
	 */
	@Override
	public String toString() {
		return left.toString() + middle.toString() + right.toString();
	}

	/**
	 * Moves the top disk from the source tower to the destination tower. Notifies
	 * the observers of the destination tower's position after the move is complete.
	 * 
	 * @param source      the tower from which the disk will be moved.
	 * @param destination the tower to which the disk will be moved.
	 */
	private void move(Tower source, Tower destination) {
		Disk pop = (Disk) source.pop();
		destination.push(pop);
		setChanged();
		notifyObservers(destination.position());
	}

	/**
	 * 
	 * disks. Moves the disks from the start pole to the end pole using the
	 * temporary pole as necessary.
	 * 
	 * @param currentDisks the number of disks to move from the start pole to the
	 *                     end pole.
	 * @param startPole    the pole to start moving the disks from.
	 * @param tempPole     the temporary pole to use for moving the disks.
	 * @param endPole      the pole to move the disks to.
	 */
	private void solveTowers(int currentDisks, Tower startPole, Tower tempPole, Tower endPole) {
		if (currentDisks == 1) {
			move(startPole, endPole);
			return;
		}
		solveTowers(currentDisks - 1, startPole, endPole, tempPole);
		move(startPole, endPole);
		solveTowers(currentDisks - 1, tempPole, startPole, endPole);

	}

	/**
	 * Solves the Tower of Hanoi puzzle with the current number of disks and tower
	 * positions.
	 */
	public void solve() {
		solveTowers(numDisks, left, middle, right);
	}

}
