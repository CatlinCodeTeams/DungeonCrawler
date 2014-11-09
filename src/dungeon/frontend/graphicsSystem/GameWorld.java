package dungeon.frontend.graphicsSystem;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import dungeon.connectionInterfaces.DungeonManagerInterface;
import dungeon.frontend.assets.FileLoader;
import dungeon.frontend.physics.Point;

//World object is where all the game's information is stored, as well as all of its
//start, update, and draw functions are.
public abstract class GameWorld implements GameWorldInterface {

	Hashtable<Integer, Boolean> keyPressTable = new Hashtable<Integer, Boolean>();
	Hashtable<Integer, Boolean> mousePressTable = new Hashtable<Integer, Boolean>();

	Hashtable<String, BufferedImage> imageTable = new Hashtable<String, BufferedImage>();

	AdvancedGraphics pen;
	DungeonManagerInterface mainManager;

	// ------------------------------------

	public GameWorld(final DungeonManagerInterface mainManager) {
		this.mainManager = mainManager;
	}

	public DungeonManagerInterface getManager() {
		return mainManager;
	}

	public BufferedImage getImage(final String name) {
		return imageTable.get(name);
	}

	public void drawImage(final String name, final int x, final int y,
			final int width, final int height) {
		pen.drawImage(imageTable.get(name), x, y, width, height, null);
	}

	public void drawImage(final String name, final Point point,
			final int width, final int height) {
		final int x = (int) point.getX();
		final int y = (int) point.getY();
		this.drawImage(name, x, y, width, height);
	}

	public void drawMirroredImage(final String name, final Point point,
			final int width, final int height) {
		BufferedImage img = imageTable.get(name);

		// Flip the image horizontally
		final AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		final AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter(img, null);

		pen.drawImage(img, (int) point.getX(), (int) point.getY(), width,
				height, null);

	}

	public void drawTransparentImage(final String name, final int x,
			final int y, final int width, final int height,
			final float transparency) {

		final Graphics basicPen = pen.getBasicPen();
		final Graphics2D basic2DPen = (Graphics2D) basicPen;

		basic2DPen.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, transparency));

		basic2DPen.drawImage(imageTable.get(name), x, y, width, height, null);

		basic2DPen.setComposite(AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, 1f));
	}

	public void drawTransparentImage(final String name, final Point point,
			final int width, final int height, final float transparency) {

		drawTransparentImage(name, (int) point.getX(), (int) point.getY(),
				width, height, transparency);
	}

	public void loadImage(final String name) {
		try {
			imageTable.put(name,
					ImageIO.read(FileLoader.class.getResource(name)));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void setPen(final AdvancedGraphics newPen) {
		pen = newPen;
	}

	public AdvancedGraphics getPen() {
		return pen;
	}

	public final Point getMouseLocationOnScreen() {
		final double newX = MouseInfo.getPointerInfo().getLocation().getX();
		final double newY = MouseInfo.getPointerInfo().getLocation().getY();

		final Point newPoint = new Point(newX, newY);
		return newPoint;
	}

	public final Point getMouseLocation() {
		return GamePanel.getMousePositionInGamePanel();
	}

	@Override
	public void mouseClicked(final MouseEvent event) {

	}

	@Override
	public void mouseEntered(final MouseEvent event) {

	}

	@Override
	public void mouseExited(final MouseEvent event) {

	}

	// Should be overridden. This function is called once whenever the mouse is
	// pressed.
	public void mouseTriggered(final MouseEvent event) {

	}

	// --------------------------------------
	@Override
	public final void mousePressed(final MouseEvent event) {

		// if the mouse was not pressed before, call the trigger function.

		if (mousePressTable.containsKey(event.getButton())) {
			if (mousePressTable.get(event.getButton()) == false) {
				mouseTriggered(event);
				// System.out.println("(Mouse) This value ("+
				// Integer.toString(event.getButton())
				// +") was previously false.");
			}
		} else {
			mouseTriggered(event);
			// System.out.println("(Mouse) This value ("+Integer.toString(event.getButton())
			// +") was not in the table.");
		}

		mousePressTable.put(event.getButton(), true);

	}

	@Override
	public final void mouseReleased(final MouseEvent event) {
		mousePressTable.put(event.getButton(), false);
	}

	// --------------------------------------------

	public final boolean isMousePressed(final int b) {
		if (mousePressTable.containsKey(b))
			return mousePressTable.get(b);
		else
			return false;
	}

	@Override
	public void mouseDragged(final MouseEvent event) {

	}

	@Override
	public void mouseMoved(final MouseEvent event) {

	}

	// Should be overridden. This function is called once whenever a key is
	// pressed.
	public void keyTriggered(final KeyEvent event) {

	}

	@Override
	// Whoaaaa, the final modifier works with methods, sweet.
	public final void keyPressed(final KeyEvent event) {

		final int kc = event.getExtendedKeyCode();

		// If the table previous held a false value, this is the first iteration
		// at which the key
		// has been pressed. The "key tapped" function (keyTriggered) is called.
		if (keyPressTable.containsKey(kc)) {
			if (keyPressTable.get(kc) == false) {
				keyTriggered(event);
				// System.out.println("(Key) This value ("+
				// Integer.toString(kc)+") was previously false");
			}
		} else {
			keyTriggered(event);
			// System.out.println("(Key) The value ("+
			// Integer.toString(kc)+") was not in the table.");
		}

		keyPressTable.put(kc, true);

	}

	@Override
	public final void keyReleased(final KeyEvent event) {

		final int kc = event.getExtendedKeyCode();
		keyPressTable.put(kc, false);
	}

	// -------------------------------------------------------

	@Override
	public final boolean isKeyPressed(final int kc) {

		if (keyPressTable.containsKey(kc))
			return keyPressTable.get(kc);
		else
			return false;
	}

	@Override
	public final boolean isKeyPressed(char c) {

		c = Character.toUpperCase(c);
		final int kc = c;

		if (keyPressTable.containsKey(kc))
			return keyPressTable.get(kc);
		else
			return false;
	}

	@Override
	public void keyTyped(final KeyEvent event) {
		// Probably don't use. Doesn't work as well for games as the other two
		// key listeners.
	}

}
