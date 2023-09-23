// Virginia Tech Honor Code Pledge:
//f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// Sicheng Fan
package towerofhanoi;

import cs2.Button;
import cs2.Shape;
import cs2.Window;
import cs2.WindowSide;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.print.attribute.standard.Destination;

/**
 * The PuzzleWindow class represents a graphical user interface for the Tower of
 * Hanoi puzzle. It implements the Observer interface to receive updates from
 * the HanoiSolver class, which solves the puzzle.
 * 
 * @author
 */
public class PuzzleWindow implements Observer {

	private HanoiSolver game;
	private Shape left;
	private Shape leftBase;
	private Shape middleBase;
	private Shape rightBase;
	private Shape right;
	private Shape middle;
	private Window window;
	private Button buttonSolve;
	Disk disks[];
	Tower source = null;
	Tower destination = null;
	public final static int WIDTH_FACTOR = 20;
	public final static int DISK_GAP = 0;
	public final static int TOWERS_GAP = 200;
	public final static int DISK_HEIGHT = 10;
	public final static int TOWER_WIDTH = 5;

	/**
	 * 
	 * Creates a new instance of PuzzleWindow with the given HanoiSolver game.
	 * 
	 * @param game the HanoiSolver game to be displayed in the window
	 */
	public PuzzleWindow(HanoiSolver game) {
		this.game = game;
		this.window = new Window("Tower of Hanoi");
		this.window.setSize(800, 400);
		int TOWER_HEIGHT = (game.disks() + 2) * DISK_HEIGHT;
		left = new Shape(100, 100, TOWER_WIDTH, TOWER_HEIGHT, Color.black);
		middle = new Shape(100 + TOWERS_GAP, 100, TOWER_WIDTH, TOWER_HEIGHT, Color.black);
		right = new Shape(100 + 2 * TOWERS_GAP, 100, TOWER_WIDTH, TOWER_HEIGHT, Color.black);
		buttonSolve = new Button("Solve");
		this.initWindow();
	}

	/**
	 * Method that initializes the windows.
	 * It can be used to reset the game 
	 */
	private void initWindow() {
		window.removeAllShapes();
		window.addShape(left);
		left.onClick(this, "clickedTower");
		window.addShape(middle);
		middle.onClick(this, "clickedTower");
		window.addShape(right);
		right.onClick(this, "clickedTower");
		
		disks = new Disk[game.disks()];
		for (int i = game.disks(); i > 0; i--) {
			Disk d = new Disk(i);
			disks[game.disks() - i] = d;
			d.onClick(this, "clickedDisk");
			window.addShape(d);
			window.moveToFront(d);
			game.getTower(Position.LEFT).push(d);
			moveDisk(Position.LEFT);
		}
		Disk firstDisk = disks[0];
		leftBase = new Shape(firstDisk.getX(), firstDisk.getY() + DISK_HEIGHT , firstDisk.getWidth(), TOWER_WIDTH, Color.black);
		window.addShape(leftBase);
		middleBase = new Shape(firstDisk.getX() + TOWERS_GAP, firstDisk.getY() + DISK_HEIGHT , firstDisk.getWidth(), TOWER_WIDTH, Color.black);
		window.addShape(middleBase);
		rightBase = new Shape(firstDisk.getX() + 2 * TOWERS_GAP, firstDisk.getY() + DISK_HEIGHT , firstDisk.getWidth(), TOWER_WIDTH, Color.black);
		window.addShape(rightBase);
		
		window.addButton(buttonSolve, WindowSide.NORTH);
		buttonSolve.onClick(this, "clickedSolve");
		game.addObserver(this);
	}
	/**
	 * Updates the view in response to changes in the model. If the argument is of
	 * type Position, it moves the top disk of the corresponding tower to the
	 * appropriate pole and sleeps for some milliseconds to create an animation
	 * effect.
	 * 
	 * @param o   the Observable object that has been updated
	 * @param arg the argument passed by the Observable object
	 * 
	 */
	public void update(Observable o, Object arg) {
		if (arg.getClass().equals(Position.class)) {
			Position position = (Position) arg;
			moveDisk(position);
			sleep();
		}
	}

	/**
	 * This method causes the current thread to sleep for a specified amount of
	 * time.
	 */
	public void sleep() {
		try {
			Thread.sleep(1000 / game.disks());
		} catch (Exception e) {
		}
	}

	/**
	 * Handles the click event of the "solve" button. Disables the button and starts
	 * a new thread to solve the puzzle.
	 * 
	 * @param button The button that was clicked.
	 */
	public void clickedSolve(Button button) {
		button.disable();
		game.getTower(Position.LEFT).clear();
		game.getTower(Position.MIDDLE).clear();
		game.getTower(Position.RIGHT).clear();
		initWindow();
		new Thread() {
			public void run() {
				game.solve();
			}
		}.start();
	}

	/**
	 * The disk shape clicked event 
	 * @param shape: shape clicked
	 */
	public void clickedDisk(Shape shape) {
		System.out.println(shape.getWidth());
		System.out.println(getDiskByWidth(shape.getWidth()));
		Disk disk = getDiskByWidth(shape.getWidth());
		Tower tower = getTowerByX(shape.getX());
		
		if(tower.peek().getWidth() == disk.getWidth()) {
			source = tower;
		}else {
			source = null;
		}
		
	}
	
	/**
	 * clicked shape event for towers
	 * @param shape 
	 */
	public void clickedTower(Shape shape) {
		destination = getTowerByX(shape.getX());
		if(source != null && source != destination) {
			try {
				Disk disk = source.peek();
				if(disk != null)
				{				
					destination.push(disk);
					moveDisk(destination.position());
					source.pop();
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			}
			
			
		}
	}
	
	/**
	 * Finds the shape with given width
	 * 
	 * @param width:
	 * @return disk with specified width
	 */
	public Disk getDiskByWidth(int width) {
		for (Disk disk : disks) {
			if(disk.getWidth() == width) {
				return disk;
			}
		}
		return null;
	}
	
	/**
	 * get tower at x coordinate specified
	 * @param x coordinate
	 * @return tower
	 */
	public Tower getTowerByX(int x) {
		if(x < TOWERS_GAP) {
			return game.getTower(Position.LEFT);
		}else if(x < TOWERS_GAP * 2) {
			return game.getTower(Position.MIDDLE);
		}else {
			return game.getTower(Position.RIGHT);
		}
	}
	/**
	 * 
	 * Moves the top disk from the tower at the given position to the appropriate
	 * pole on the game window.
	 * 
	 * @param position the position of the tower from which the top disk is to be
	 *                 moved
	 */
	public void moveDisk(Position position) {
		Tower currentTower = game.getTower(position);
		Disk currentDisk = (Disk) currentTower.peek();
		Shape currentPole = null;
		switch (position) {
		case LEFT:
			currentPole = left;
			break;
		case MIDDLE:
			currentPole = middle;
			break;
		case RIGHT:
			currentPole = right;
			break;
		default:
			currentPole = middle;
			break;
		}
		int size = currentTower.size();
		int width = currentDisk.getWidth();
		int x0 = currentPole.getX();
		int y0 = currentPole.getY();
		int x = x0 - (width - currentPole.getWidth()) / 2;
		int y = y0 + (currentPole.getHeight() - size * DISK_HEIGHT);
		currentDisk.moveTo(x, y);
	}

}
