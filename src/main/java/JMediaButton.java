
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JButton;

/**
 *
 * @author karma
 * @version 1.0
 */

public class JMediaButton extends JButton {
	
	public enum MediaType {RECORD, STOP, PAUSE, PLAY, SKIP_FWD, SKIP_BACK};	
	private MediaType mType;
	private Color c;

	public JMediaButton() {

		this.c = Color.GRAY;
		this.mType = MediaType.PLAY;
		this.setPreferredSize(new Dimension(50, 50));
	}

	public Color getColour() {
		
		return c;
	}

	public void setColour(Color c) {
		
		this.c = c;
	}

	public MediaType getmType() {
		
		return mType;
	}

	public void setmType(MediaType mType) {
		
		this.mType = mType;
	}

	@Override
	public void setText(String string) {
	
		//super.setText(string);
	}
	
	@Override
	public void paintComponent(Graphics grphcs) {
	
		super.paintComponent(grphcs);
		Graphics2D g2d = (Graphics2D) grphcs.create();
		// Enable antialiasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);	
		g2d.setColor(c);
		
		int w = getWidth();
		int h = getHeight();
		
		switch (mType) {
			
			case STOP:
				drawStop(g2d, w, h);
				break;
			case RECORD:
				drawRecord(g2d, w, h);
				break;
			case PLAY:
				drawPlay(g2d, w, h);
				break;
			case PAUSE:
				drawPause(g2d, w, h);
				break;
			case SKIP_FWD:
				drawSkip(g2d, w, h, 0);
				break;
			case SKIP_BACK:
				drawSkip(g2d, w, h, 180);
				break;
		}
	}

	private void drawPlay(Graphics2D g2d, int w, int h) {
		
		int gap = h/4;
		double rad = h/2;
		int[] x = {0 + gap, h - gap, 0 + gap};
		int[] y = {0 + gap, h/2, h - gap};
		g2d.translate(w/2 - rad, h/2 - rad);
		g2d.fillPolygon(x, y, 3);
	}
	
	private void drawStop(Graphics2D g2d, int w, int h) {
		
		double len = h / 2.1;
		double cx = w / 2 - len / 2;
		double cy = h / 2 - len / 2;
		g2d.fillRect((int) cx, (int) cy, (int) len, (int) len);
	}
	
	private void drawRecord(Graphics2D g2d, int w, int h) {
		
		int radius = h / 4;
		int diameter = radius * 2;
		g2d.translate(w / 2 - radius, h / 2 - radius);
		g2d.fillOval(0, 0, diameter, diameter);
	}

	private void drawPause(Graphics2D g2d, int w, int h) {
		
		double recW = h / 6;	//width of each rect
		double recH = h / 2;	//height of each rect
		g2d.translate(w/2 - recW / 2, h/2 - recH / 2);
		g2d.fillRect((int) -recW, 0, (int) recW, (int) recH);
		g2d.fillRect((int) recW, 0, (int) recW, (int) recH);
	}

	private void drawSkip(Graphics2D g2d, int w, int h, double rotate) {
	
		Path2D p2d = new Path2D.Double();

		p2d.moveTo(-1, -1);
		p2d.lineTo(0, 0);
		p2d.lineTo(0, -1);
		p2d.lineTo(.8, -.1);
		p2d.lineTo(.8, -1);
		p2d.lineTo(1, -1);
		p2d.lineTo(1, 1);
		p2d.lineTo(.8, 1);
		p2d.lineTo(.8, .1);
		p2d.lineTo(0, 1);
		p2d.lineTo(0, 0);
		p2d.lineTo(-1, 1);
		p2d.lineTo(-1, -1);
		p2d.closePath();
		
		g2d.translate(w/2, h/2);
		g2d.rotate(Math.toRadians(rotate));
		g2d.scale(h/4, h/4);
		g2d.fill(p2d);
	}
	
	private void drawTest(Graphics2D g2d, int w, int h) {
	
		Path2D p2d = new Path2D.Double();

		p2d.moveTo(-1, -1);
		p2d.lineTo(0, 0);
		p2d.lineTo(0, -1);
		p2d.lineTo(.8, -.1);
		p2d.lineTo(.8, -1);
		p2d.lineTo(1, -1);
		p2d.lineTo(1, 1);
		p2d.lineTo(.8, 1);
		p2d.lineTo(.8, .1);
		p2d.lineTo(0, 1);
		p2d.lineTo(0, 0);
		p2d.lineTo(-1, 1);
		p2d.lineTo(-1, -1);
		p2d.closePath();
		
		g2d.translate(w/2, h/2);
		g2d.rotate(Math.toRadians(180));
		g2d.scale(h/4, h/4);
		g2d.fill(p2d);
	}
}
