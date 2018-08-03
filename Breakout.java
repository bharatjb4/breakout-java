import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.*;


@SuppressWarnings("serial")
public class game extends GraphicsProgram {
	
	int brickcount = 70;
	
	int Xaxis = 5 ;
	
	int Yaxis = 15 ;
	
	private static final int breadth = 50 ;
	
	private static final int Application_width = 530;
	
	private static final int Application_height = 600;

	private static final int heigth = 15 ;
	
	private static final int balldia = 15 ;
	
	double Yvel = 20 ;
	
	double Xvel = 10 ;
	
	
	
	int ballx = Application_width/2 ;
	int bally = Application_height/2 ;
	
	private GRect bricks;
	
	private GRect slab ;
	
	private GOval ball;
	
	public void run() {
		
		setSize(Application_width , Application_height);
		laybricks();
		createball();
		slab();
		while(true){
		moveball();
		if( ball.getY() >= Application_height)
			break;
		if(brickcount == 0 )
			break;
		}
	}
	private void laybricks() {
		
		for( int i = 0 ; i < 10 ; i++ )
		{		Xaxis = 5;	
				for( int j = 0 ; j < Application_width && j < 10 ; j++ )
				{
					bricks = new GRect( Xaxis , Yaxis , breadth , heigth );
					add(bricks);
					Xaxis+=breadth + 2;
					bricks.setFilled(true);
					
					if( i < 2)
						bricks.setFillColor(Color.red);
					else if ( i == 2 || i == 3 )
						bricks.setFillColor(Color.orange);
					else if ( i == 4 || i == 5 )
						bricks.setFillColor(Color.yellow);
					else if ( i == 6 || i == 7 )
						bricks.setFillColor(Color.green);
					else if ( i == 8 || i == 9 )
						bricks.setFillColor(Color.cyan);
		}
				Yaxis+=heigth + 2 ;
		}
	}
	private void createball() {
		ball = new GOval ( Application_width/2 , Application_height/2  , balldia , balldia);
		ball.setFillColor(Color.black);
		ball.setFilled(true);
		add(ball);
	}
	private void slab(){
		createslab();
		addSlablisteners();
	}
	private void createslab() {
		slab = new GRect( Application_width/2 - 150 , Application_height-50 , breadth+30 , heigth );
		slab.setFilled(true);
		slab.setFillColor(Color.DARK_GRAY);
		add(slab);
	}
	private void addSlablisteners(){
		addMouseListeners();
	}
	public void mouseMoved(MouseEvent e) {
		if ((e.getX() < getWidth() - (breadth+30)/2) && (e.getX() > (breadth+30)/2)) {
			slab.setLocation(e.getX() - (breadth+30)/2, Application_height - 50 - heigth);
		}
		
	}
	private void moveball()  {
			
			if (ball.getX() - balldia < 0) {
		       Xvel = -Xvel; 
		   
		    } else if (ball.getX() + balldia > Application_width ) {
		       Xvel = -Xvel;
		       
		    }
		      if (ball.getY() - balldia < 0) {
		       Yvel = -Yvel;
		       
		    } else if (ball.getY()+balldia > Application_height ) {
		    		Yvel = 0 ;
		    		Xvel = 0 ;
		    }
		      GObject collider = getCollidingObject();
				if (collider == slab) {
						if(ball.getY() >= Application_height - 50 - heigth - balldia && ball.getY() < Application_height - 50 - heigth - balldia + 4) {
							Yvel = -Yvel;	
					}
				}
				else if (collider != null) {
					remove(collider); 
					brickcount--;
					Yvel = -Yvel;
				}
		    ball.move(Xvel, Yvel);  
		    pause(50);
	}
	private GObject getCollidingObject() {
		if((getElementAt(ball.getX(), ball.getY())) != null) {
	         return getElementAt(ball.getX(), ball.getY());
	      }
		else if (getElementAt( (ball.getX() + balldia), ball.getY()) != null ){
	         return getElementAt(ball.getX() + balldia, ball.getY());
	      }
		else if(getElementAt(ball.getX(), (ball.getY() + balldia)) != null ){
	         return getElementAt(ball.getX(), ball.getY() + balldia);
	      }
		else if(getElementAt((ball.getX() + balldia), (ball.getY() + balldia)) != null ){
	         return getElementAt(ball.getX() + balldia, ball.getY() + balldia);
	      }
		else{
	         return null;
	      }
		
	}
}







