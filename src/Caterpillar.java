import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Queue;
import java.util.Iterator;
import java.util.LinkedList;



/**
 * This class creates two caterpillars with a body of 10 points and teaches it how to  move and place itself.
 * @author Mark Ofosu
 * Date: 10/18/2021
 */
public class Caterpillar {
	
	private Color color;
	private Point position;           // Tricky! Notice how positionis accessed by different methods in this class...
	private char direction ='E';
	private Queue body = new LinkedList <Point> ();
	private Queue commands = new LinkedList <Character> ();
	int score = 0;
	
	
	public Caterpillar (Color c, Point sp) {
		
		color= c;
		for (int i= 0; i< 10; i++) {  // each caterpillar isposition
			position = new Point (sp.x+ i, sp.y);  // 10 pieces (circles)
			body.add(position);
		}
	}
	//queue  directions as a set of commands
	public void setDirection(char d) {
		commands.add(d);
		
	}
	 
    public void move (CaterpillarGame game ) { // first see if we should change directionif (commands.size
    	if (commands.size( ) > 0) {
    		Character c = (Character) commands.peek( ); // just peek
    		commands.remove( );
    		direction = c.charValue( );    // Character wrapper to char 
    		 
    			
    		
		if (direction == 'Z') return;
    	}

    	
    	// then find new position
    	Point np= newPosition( );
    	if (game.canMove(np)) {
    		// erase one segment, add another
    		body.remove( );
    		body.add(np);
    		position= np;
    		int score = game.squareScore(np);
    		this.score += score;
    	}
    	
    	
				
    }
    
    private Point newPosition ( ) {
    	int x =  position.x;
    	int y =  position.y;
    	if (direction == 'E') x++;
    	else if (direction == 'W') x--;
    	else if (direction == 'N') y--;
    	else if (direction == 'S') y++;
    	return new Point(x, y);
    }
    // check each points to see if it is not already occupied by the caterpillars
    public boolean inPosition (Point np) {
    	Iterator e = body.iterator();
    	while (e.hasNext()) { // keep looping while there is more points.
    		Point location = (Point) e.next();
    		if (np.equals(location)) return true;
    	}
    	return false;
    }
    // Paint each caterpillar
    public void paint (Graphics g) {
    	g.setColor(color);
    	Iterator e = body.iterator();
    	
    	// iterator stuff
    	while (e.hasNext( )) {
    		Point p = (Point) e.next();
    		g.fillOval(5 + CaterpillarGame.SegmentSize * p.x,
    				15 + CaterpillarGame.SegmentSize * p.y, 
    				CaterpillarGame.SegmentSize,
    				CaterpillarGame.SegmentSize);
    		
    		}
    	
    	
    	}
    }
    


