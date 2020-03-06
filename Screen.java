import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Screen extends JPanel implements MouseListener, ActionListener{
	
	
	private Square[][] grid;
	private int redSet, greenSet, blueSet;
	
	
	public Screen(){
		
		addMouseListener(this);
		
		//grid
		grid = new Square[16][16];
		for(int r = 0;r < grid.length;r ++){
			for(int c = 0;c < grid[r].length;c ++){
				grid[r][c] = new Square();
			}
		}
		
		//color ints
		redSet = 0;
		greenSet = 0;
		blueSet = 0;
		
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(1000,700);
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//grid
		int x = 10;
		int y = 10;
		for(int r = 0;r < grid.length;r ++){
			for(int c = 0;c < grid[r].length;c ++){
				grid[r][c].drawMe(g,x,y);
				x += 30;
			}
			x = 10;
			y += 30;
		}
		
		//color picker grid
		x = 600;
		y =10;
		for(int r = 0;r < 9;r ++){
			for(int c = 0;c < 12;c ++){
				
				int red = 0;
				int blue = 0;
				int green = 0;
				
				//reds
				if( c < 3 || c > 9 ){
					if( r > 4 ){
						red = 255;
					} else {
						red = (r+1)*51;
					}
				} else if ( c == 3 || c == 9 ){
					if( r > 4 ){
						red = r*28;
					} else {
						red = r*28;
					}
				} else {
					if( r > 4 ){
						red = (r-4)*51;
					} else {
						red = 0;
					}
				}
				
				
				//greens
				if( c > 1 && c < 7 ){
					if( r > 4 ){
						green = 255;
					} else {
						green = (r+1)*51;
					}
				} else if ( c == 1 || c == 7 ){
					if( r > 4 ){
						green = r*28;
					} else {
						green = r*28;
					}
				} else {
					if( r > 4 ){
						green = (r-4)*51;
					} else {
						green = 0;
					}
				}
				
				
				//blues
				if( c > 5 && c < 11 ){
					if( r > 4 ){
						blue = 255;
					} else {
						blue = (r+1)*51;
					}
				} else if ( c == 5 || c == 11 ){
					if( r > 4 ){
						blue = r*28;
					} else {
						blue = r*28;
					}
				} else {
					if( r > 4 ){
						blue = (r-4)*51;
					} else {
						blue = 0;
					}
				}
				
				
				
				//draw square
				g.setColor( new Color(red,green,blue) );
				g.fillRect(x,y,30,30);
				g.setColor(Color.black);
				g.drawRect(x,y,30,30);
				x += 30;
			}
			y += 30;
			x = 600;
		}
		
		//chosen color
		g.setColor( new Color(redSet,greenSet,blueSet) );
		g.fillRect(530,50,30,30);
		g.setColor(Color.black);
		g.drawRect(530,50,30,30);
		g.drawString("Current Color",505,30);
		
	}
	
	
	 public void mousePressed(MouseEvent e) {
		 
		//draw on grid
		int x = e.getX();
		int y = e.getY();
		if( x <= 490 && x >= 10 && y <= 490 && y >= 10 ){
			int row = (y-10)/30;
			int column = (x-10)/30;
			grid[row][column].setColor(redSet,greenSet,blueSet);
		}
		
		//set color
		if( x >= 600 && x <= 960 && y >= 10 && y <= 280 ){
		
			int r = (y-10)/30;
			int c = (x-600)/30;
		
			if( c < 3 || c > 9 ){
				if( r > 4 ){
					redSet = 255;
				} else {
					redSet = (r+1)*51;
				}
			} else if ( c == 3 || c == 9 ){
				if( r > 4 ){
					redSet = r*28;
				} else {
					redSet = r*28;
				}
			} else {
				if( r > 4 ){
					redSet = (r-4)*51;
				} else {
					redSet = 0;
				}
			}
				
				
			//greens
			if( c > 1 && c < 7 ){
				if( r > 4 ){
					greenSet = 255;
				} else {
					greenSet = (r+1)*51;
				}
			} else if ( c == 1 || c == 7 ){
				if( r > 4 ){
					greenSet = r*28;
			} else {
					greenSet = r*28;
				}
			} else {
				if( r > 4 ){
					greenSet = (r-4)*51;
				} else {
					greenSet = 0;
				}
			}
				
				
			//blues
			if( c > 5 && c < 11 ){
				if( r > 4 ){
					blueSet = 255;
				} else {
					blueSet = (r+1)*51;
				}
			} else if ( c == 5 || c == 11 ){
				if( r > 4 ){
					blueSet = r*28;
				} else {
					blueSet = r*28;
				}
			} else {
				if( r > 4 ){
					blueSet = (r-4)*51;
				} else {
					blueSet = 0;
				}
			}
		
		}
		
        repaint();
    }
 
 
    public void mouseReleased(MouseEvent e) {}
 
 
    public void mouseEntered(MouseEvent e) {}
 
 
    public void mouseExited(MouseEvent e) {}
 
 
    public void mouseClicked(MouseEvent e) {}
	
	
	public void actionPerformed(ActionEvent e){
		repaint();
	}
	
}