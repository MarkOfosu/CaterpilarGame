import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Random;

/**
 * This game is a two caterpillars that moves to catch a point on the screen.
 * The goal of the game is to get a high score.
 * @author Mark Ofosu
 * Date: 10/18/2021
 */

public class CaterpillarGame extends Frame {
	// setup board dimensions
	final static int BoardWidth= 60;
	final static int BoardHeight= 40;
	final static int SegmentSize= 10;
	int playewr1Score =0;
	int playewr2Score =0;
	Point numberSquare;
	int scoreValue = 0;
	
	// setup the two Caterpillars in their starting positions
	private Caterpillar playerOne= new Caterpillar (Color.blue, new Point(20, 10));
	private Caterpillar playerTwo= new Caterpillar (Color.red, new Point(20, 30));
	
	
//	public newNumberSquare numberSquare = new newNumberSquare (color.blue, numberSquare);
	// constructor
	public CaterpillarGame( ) {
		setSize((BoardWidth+1)*SegmentSize, BoardHeight*SegmentSize+ 30);
		setTitle("Caterpillar Game");
		addKeyListener(new KeyReader( ));
		addWindowListener(new CloseQuit()); 
		
	}
	
	public static void main (String [ ] args) { 
		CaterpillarGame world = new CaterpillarGame( ); 
		world.setVisible(true);
		world.run( );
	}
	
	
	// paints players and newNumberSquare on the screen
	public void paint (Graphics g) {
		playerOne.paint(g);
		playerTwo.paint(g);
		if (numberSquare == null) {
			newNumberSquare();
		}
		paintNumberSquare(g);
    	
	}
	
	//Paint numberSquare giving the various dimensions same as the body of the caterpillar
	private void paintNumberSquare(Graphics g) {
		g.setColor(Color.green);
		g.fillOval(5 + CaterpillarGame.SegmentSize * numberSquare.x,
    				15 + CaterpillarGame.SegmentSize * numberSquare.y, 
    				CaterpillarGame.SegmentSize,
    				CaterpillarGame.SegmentSize);
		
		// print scores for playerOne and playerTwo on the screen
		g.setColor(Color.orange);
		g.drawString("playewr1Score: " + playerOne.score, (int) (CaterpillarGame.BoardWidth + 20), (int) (CaterpillarGame.BoardHeight + 380));
    	g.drawString("playewr2Score: " + playerTwo.score, (int) (CaterpillarGame.BoardWidth + 150), (int) (CaterpillarGame.BoardHeight + 380));
	}

	
	// This method moves the the two caterpillars
	public void movePieces( ) {    
		playerOne.move(this);     
		playerTwo.move(this);     
		                         
		
	}	
		
		public void run ( ) {

			
			while (true) {
				movePieces( );
				repaint( );
				
				
				try {
					Thread.sleep(100);  // create  animation illusion
				} catch (Exception e) {  }  // must be in try-catch}}
			}
		}
		
		
		
		public boolean canMove(Point np) {
			// get x, y coordinate
			int x = np.x;
			int y = np.y;
			
			// test playing board boundaries
			if ((x <= 0) || (y <= 0)) return false;
			if ((x >= BoardWidth) || (y >= BoardHeight)) return false;
			
			// test caterpillars: can’t move through self or other Caterpillar
			if (playerOne.inPosition(np)) return false;
			if (playerTwo.inPosition(np)) return false;
			
			// ok, safe square
			return true;
		}
		// Why is canMove( )handled by CaterpillarGame?
		// Can the caterpillars decide for themselves?
		// Explanation follows on slides 24 & 25
		// For additional discussion use the Canvas Discussion Forum
		
		
		private class KeyReader extends KeyAdapter {
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar( );
				switch (c) {
					// player One keys
					case 'q': playerOne.setDirection('Z'); break;
					case 'a': playerOne.setDirection('W'); break;
					case 'd': playerOne.setDirection('E'); break;
					case 'w': playerOne.setDirection('N'); break;
					case 's': playerOne.setDirection('S'); break;
					// player Two keys
					case 'p': playerTwo.setDirection('Z'); break;
					case 'j': playerTwo.setDirection('W'); break;
					case 'l': playerTwo.setDirection('E'); break;
					case 'i': playerTwo.setDirection('N'); break;
					case 'k': playerTwo.setDirection('S'); break;
					// ignore all other keys
				} // end switch
			}  // end keyPressed
		}  // end KeyReaderprivate inner (nested) class
		
		
		
		//generate a new random point on the screen when called
		public void newNumberSquare() {
			   Random rand = new Random();
			   // the purpose of this loop is to keep trying random positions for the numberSquare until you find one that does not clash with either of the caterpillars.
			   while (true) {
				   
			     int x = rand.nextInt(BoardWidth);
			     
			     int y = rand.nextInt(BoardHeight);
			     
			     numberSquare = new Point(x, y);
			     
			   
			     if (!playerOne.inPosition(numberSquare) && !playerTwo.inPosition(numberSquare)) { 
			       scoreValue++;
//
			       break;
			     }
			   }
		}
		
		//checks if players clashes with the new generated point and Keep records of score for that player.
		public int squareScore (Point np) {
			int score = 0;
			
			
			 if (numberSquare.equals(np)){
				  
				  score += scoreValue;
				  newNumberSquare();
	   	 
			 }
			
			 return score;
			 
		}
		
		
		
		public class CloseQuit extends WindowAdapter {// system exit function
			public void windowClosing (WindowEvent e) {
				System.exit(0);
			}
		}
		
	}  // public class CaterpillarGame
			
		
		
		
	
	