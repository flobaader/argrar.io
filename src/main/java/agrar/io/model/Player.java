package agrar.io.model;

import java.awt.Color;

import agrar.io.controller.Controller;
import agrar.io.controller.PlayerBehavior;
import agrar.io.util.Vector;

/**
 * Represents a Player
 * 
 * @author FBaader
 *
 */
public abstract class Player extends Circle {

	private String name;
	protected PlayerBehavior behavior;

	/**
	 * Creates a new Player
	 * 
	 * @param parent
	 *            The game controller
	 * @param loc
	 *            The spawn location
	 * @param size
	 *            The initial size
	 * @param col
	 *            The color
	 * @param name
	 *            The name of the player
	 */
	public Player(Controller parent, Vector loc, int size, Color col, String name) {
		super(parent, loc, size, col);
		this.name = name;
	}

	/**
	 * Returns that this Circle is a Player
	 */
	@Override
	public boolean isPlayer() {
		return true;
	}

	/**
	 * Returns the name of the Circle, which is displayed in the UI
	 * 
	 * @return The Name as String
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets Location of the Circle to the new given Location
	 * 
	 * @param newLoc
	 *            the new given Location
	 */
	public void setLocation(Vector newLoc) {
		this.location = newLoc;
	}

	/**
	 * Returns the Behavior of the Player
	 * 
	 * @return The player Behavior
	 */
	public PlayerBehavior getBehavior() {
		return behavior;
	}

	/**
	 * Returns the score of the Player, which is equals to all gained size
	 * points
	 * 
	 * @return
	 */
	public int getScore() {
		return behavior.getScore();
	}
}
