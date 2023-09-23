// Virginia Tech Honor Code Pledge:
//f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// Sicheng Fan)
package towerofhanoi;

/**
 *
 * @author
 */
public class Tower extends LinkedStack<Disk> {

	private Position position;

	/**
	 * Constructs a Tower object with the specified position.
	 * 
	 * @param position the position of the tower puzzle game.
	 */
	public Tower(Position position) {
		this.position = position;
	}

	/**
	 * Returns the position of this tower.
	 * 
	 * @return the position of this tower.
	 * 
	 */
	public Position position() {
		return this.position;
	}

	/**
	 * Pushes the specified disk onto the top of this tower.
	 * 
	 * @param disk the disk to be pushed onto the top of this tower.
	 * @throws IllegalStateException if the specified disk is wider than the top
	 *                               disk of this tower, or if this tower is full.
	 * @precondition disk != null
	 */
	public void push(Disk disk) {

		if (isEmpty() || peek().compareTo(disk) > 0) {
			super.push(disk);
		} else {
			throw new IllegalStateException();
		}
	}
}
