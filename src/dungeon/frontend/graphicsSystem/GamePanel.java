package dungeon.frontend.graphicsSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import dungeon.frontend.physics.Point;

public class GamePanel extends JPanel implements MouseListener,
		MouseMotionListener, KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Color BACKGROUND_COLOR = new Color(200, 200, 200);

	static BufferedImage image;

	//
	Timer timer;
	GameWorld world;

	int camX;
	int camY;

	int WIDTH;
	int HEIGHT;
	int SCALE;

	AdvancedGraphics advancedPen;

	static int relativeMouseX;
	static int relativeMouseY;

	public static Image getImage() {
		return GamePanel.image;
	}

	public GamePanel(final GameWorld w, final int width, final int height,
			final int scale) {

		super(true);

		WIDTH = width;
		HEIGHT = height;
		SCALE = scale;

		setPreferredSize(new Dimension(width * scale, height * scale));
		GamePanel.image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		camX = 0;
		camY = 0;

		GamePanel.relativeMouseX = 0;
		GamePanel.relativeMouseY = 0;

		world = w;
		// Runs the world's start() method.
		world.start();

		// Sets basic attributes and listeners of the panel.
		setFocusable(true);
		requestFocusInWindow();

		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);

		// Sets up the timer. Every timer the timer is called, the GamePanel's
		// update() and repaint() functions are run.
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				update();
				repaint();

				// I'm fairly certain that the panel will be double buffered by
				// default.

			}
		}, 0, 16); // 16 = ms between timer events. 16ms between events is equal
					// to 62.5 FPS.z
	}

	// ----------------------
	// The GamePanel's update function just calls the world's updater.
	public void update() {
		world.update();
	}

	// The call to repaint() in the timer loop just redirects to here. The panel
	// is cleared,
	// and the color set to black (black being the default color.) The world's
	// draw method is then called.
	@Override
	public void paint(final Graphics mainPen) {

		final Graphics basicPen = GamePanel.image.getGraphics();

		if (advancedPen == null) {
			advancedPen = new AdvancedGraphics(basicPen);
		}

		clear(basicPen);
		basicPen.setColor(Color.BLACK);

		advancedPen.setBasicGraphics(basicPen);

		world.setPen(advancedPen);
		world.draw();

		mainPen.drawImage(GamePanel.image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE,
				null);
	}

	// ------------------------------

	// Just draws a white rectangle the width and height of the panel.
	// Essentially "clears" it.
	public void clear(final Graphics pen) {
		pen.setColor(BACKGROUND_COLOR);
		pen.fillRect(0, 0, getWidth(), getHeight());
	}

	// The following listeners all just redirect to the listeners of the same
	// names held by the world object.
	@Override
	public void mouseClicked(final MouseEvent event) {
		world.mouseClicked(event);
	}

	@Override
	public void mouseEntered(final MouseEvent event) {
		world.mouseEntered(event);
	}

	@Override
	public void mouseExited(final MouseEvent event) {
		world.mouseExited(event);
	}

	@Override
	public void mousePressed(final MouseEvent event) {
		world.mousePressed(event);
	}

	@Override
	public void mouseReleased(final MouseEvent event) {
		world.mouseReleased(event);
	}

	@Override
	public void mouseDragged(final MouseEvent event) {
		world.mouseDragged(event);
	}

	@Override
	public void mouseMoved(final MouseEvent event) {
		world.mouseMoved(event);

		GamePanel.relativeMouseX = event.getX();
		GamePanel.relativeMouseY = event.getY();
	}

	public static Point getMousePositionInGamePanel() {
		return new Point(GamePanel.relativeMouseX, GamePanel.relativeMouseY);
	}

	@Override
	public void keyPressed(final KeyEvent event) {
		world.keyPressed(event);
	}

	@Override
	public void keyReleased(final KeyEvent event) {
		world.keyReleased(event);
	}

	@Override
	public void keyTyped(final KeyEvent event) {
		world.keyTyped(event);
	}

}
