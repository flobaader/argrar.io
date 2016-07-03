package agrar.io.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import agrar.io.model.Score;
import agrar.io.view.GameWindow;
import agrar.io.view.MenuView;

public class MenuController {

	private Controller parent;
	private MenuView menuView;
	private GameWindow window;

	public MenuController(Controller parent, MenuView menuView, GameWindow window) {
		this.parent = parent;
		this.menuView = menuView;
		this.window = window;
	}

	/**
	 * 
	 * @return The main menu of the game
	 */
	public void showMainMenu() {

		menuView.clear();

		menuView.addImage("/banner.jpg");

		menuView.addButton("Start", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Action performed");
				parent.start();
			}
		});

		menuView.addButton("Beenden", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.quit();
			}
		});

		window.showMenu();
	}

	/**
	 * 
	 * @return A menu to display when the game is paused
	 */
	public void showPauseMenu() {

		menuView.clear();

		menuView.addTitle("Pausiert");

		menuView.addLabel("Pausiert");

		menuView.addButton("Weiter", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				parent.resumeGame();
			}
		});

		menuView.addButton("Hauptmenu", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.stopGame();
				int sure = JOptionPane.showConfirmDialog(null,
						"Wenn du die Runde jetzt verl�sst, wird dein Punktestand nicht gespeichert.",
						"Wirklich beenden?", JOptionPane.OK_CANCEL_OPTION);
				if (sure == 0) {
					showMainMenu();
				}
			}
		});

		menuView.addButton("Beenden", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int sure = confirmQuit();
				if (sure == 0) {
					parent.quit();
				}
			}

		});

		window.showMenu();
	}

	/**
	 * Shows a menu to the user to confirm quitting the game
	 * 
	 * @return 0 if the user wants to quit, 1 otherwise
	 */
	public int confirmQuit() {
		return JOptionPane.showConfirmDialog(null,
				"Wenn du Agrar.io jetzt beendest, wir das Spiel abgebrochen und dein Punktestand nicht gespeicher. Willst du wirklich beenden?",
				"Beenden?", JOptionPane.OK_CANCEL_OPTION);
	}

	public void showNameMenu() {

		menuView.clear();

		menuView.addTitle("Namen ausw�hlen");

		JTextField nameField = menuView.addTextField("Name");

		JPasswordField passwordField = menuView.addPasswordField("Passwort");

		menuView.addButton("OK", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String name = nameField.getText();
				String passwd = String.valueOf(passwordField.getPassword());

				// Check for empty name or password field
				if (name.equals("") || passwd.equals("")) {
					JOptionPane.showMessageDialog(null, "Passwort und Name d�rfen nicht leer sein!",
							"Passwort oder Name ist leer!", JOptionPane.ERROR_MESSAGE);
					return;
				}

				Score s = new Score(0, name, passwd);

				DatabaseAdapter dbAdapter = parent.getDatabaseAdapter();

				parent.StartGame(s);

				/*
				 * try { if (dbAdapter.existsInDatabase(s)) { if
				 * (dbAdapter.checkPassword(s)) { startGame(s); } else {
				 * showConnectionError();} } else { startGame(s); } } catch
				 * (SQLException e1) { JOptionPane.showMessageDialog(null,
				 * "Ein Fehler ist aufgetreten bei der Verbindung mit der Datenbank. Versuche es sp�ter nochmal."
				 * , "Verbindungsfehler", JOptionPane.ERROR_MESSAGE); }
				 */
			}

		});

		window.showMenu();

	}

	/**
	 * Shows the "game over" screen to the user
	 * @param s Score containing player name and score
	 */
	public void showDeathMenu(Score s) {

		menuView.clear();
		
		menuView.addTitle("Game over");

		menuView.addLabel("<html>Punktestand: <b>" + s.getScore() + "</b></hmtl>");

		menuView.addButton("Zum Hauptmen�", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				showMainMenu();
			}
		});

		menuView.addButton("Neue Runde", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.StartGame(s);
			}
		});

		menuView.addButton("Beenden", new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parent.quit();
			}
		});

		window.showMenu();
	}

	/**
	 * Shows a Dialog to notify the user that no connection to the DB could be established
	 */
	public void showConnectionError() {
		JOptionPane.showMessageDialog(null, "Das Passwort stimmt nicht mit dem gespeicherten �berein.",
				"Falsches Passwort", JOptionPane.ERROR_MESSAGE);
	}
}
