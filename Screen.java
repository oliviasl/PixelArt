import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.ImageIcon;

public class Screen extends JPanel implements MouseListener, ActionListener, MouseMotionListener{
	
	
	private Square[][] grid, undoGrid1, undoGrid2, undoGrid3;
	private int redSet, greenSet, blueSet;
	private boolean store;
	private JButton clearButton, undoButton, fillButton, saveFileButton;
	private JButton drawButton, eyeDropperButton;
	private Font font1;
	private String currentTool;
	private ImageIcon background;
	
	
	public Screen(){
		
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);
		
		//grids
		grid = new Square[16][16];
		for(int r = 0;r < grid.length;r ++){
			for(int c = 0;c < grid[r].length;c ++){
				grid[r][c] = new Square();
			}
		}
		undoGrid1 = new Square[16][16];
		for(int r = 0;r < undoGrid1.length;r ++){
			for(int c = 0;c < undoGrid1[r].length;c ++){
				undoGrid1[r][c] = new Square();
			}
		}
		undoGrid2 = new Square[16][16];
		for(int r = 0;r < undoGrid2.length;r ++){
			for(int c = 0;c < undoGrid2[r].length;c ++){
				undoGrid2[r][c] = new Square();
			}
		}
		undoGrid3 = new Square[16][16];
		for(int r = 0;r < undoGrid3.length;r ++){
			for(int c = 0;c < undoGrid3[r].length;c ++){
				undoGrid3[r][c] = new Square();
			}
		}
		
		//logistics
		redSet = 0;
		greenSet = 0;
		blueSet = 0;
		store = true;
		currentTool = "Draw";
		background = new ImageIcon("ImageAssets/background.png");
		
		
		//font
		font1 = new Font("Calibri",Font.PLAIN,15);
		
		//JButton
		clearButton = new JButton("Clear Canvas");
		clearButton.setBounds(10,500,150,30);
		add(clearButton);
		clearButton.addActionListener(this);
		
		undoButton = new JButton("Undo");
		undoButton.setBounds(342,500,150,30);
		add(undoButton);
		undoButton.addActionListener(this);
		
		fillButton = new JButton("Fill");
		fillButton.setBounds(570,400,150,30);
		add(fillButton);
		fillButton.addActionListener(this);
		
		saveFileButton = new JButton("Save File");
		saveFileButton.setBounds(775,435,150,30);
		add(saveFileButton);
		saveFileButton.addActionListener(this);
		
		drawButton = new JButton("Draw");
		drawButton.setBounds(570,365,150,30);
		add(drawButton);
		drawButton.addActionListener(this);
		
		eyeDropperButton = new JButton("Eye Dropper");
		eyeDropperButton.setBounds(570,435,150,30);
		add(eyeDropperButton);
		eyeDropperButton.addActionListener(this);
		
	}
	
	
	public Dimension getPreferredSize(){
		return new Dimension(955,575);
		
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//background
		background.paintIcon(this,g,0,0);
		
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
		x = 550;
		y = 10;
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
			x = 550;
		}
		//black to white
		x = 910;
		y = 10;
		int red = 3;
		int green = 3;
		int blue = 3;
		for(int r = 0;r < 9;r ++){
			
			if( r == 8 ){
				g.setColor(Color.white);
			} else if ( r == 0 ){
				g.setColor(Color.black);
			} else {
				g.setColor( new Color(red,green,blue) );
			}
			g.fillRect(x,y,30,30);
			g.setColor(Color.black);
			g.drawRect(x,y,30,30);
			y += 30;
			
			red += 28;
			green += 28;
			blue += 28;
			
		}
		
		//chosen color
		g.setColor( new Color(redSet,greenSet,blueSet) );
		g.fillRect(835,375,30,30);
		g.setColor(Color.black);
		g.drawRect(835,375,30,30);
		g.setFont(font1);
		g.drawString("Current Color",807,360);
		
		//choose tool
		g.setColor(Color.black);
		g.setFont(font1);
		g.drawString("Current Tool: " + currentTool,575,355);
		
		//color palette
		g.drawString("Color Palette",700,300);
		
		
		
	}
	
	
	 public void mousePressed(MouseEvent e) {
		
		//set color
		int x = e.getX();
		int y = e.getY();
		if( x >= 550 && x <= 910 && y >= 10 && y <= 280 ){
		
			int r = (y-10)/30;
			int c = (x-550)/30;
			
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
		} else if ( x >= 910 && x <= 940 && y >= 10 && y <= 280 ){
			int r = (y-10)/30;
			if( r == 8 ){
				redSet = 255;
				greenSet = 255;
				blueSet = 255;
			} else if ( r == 0 ){
				redSet = 0;
				greenSet = 0;
				blueSet = 0;
			} else {
				redSet = 28 * r + 3;
				greenSet = 28 * r + 3;
				blueSet = 28 * r + 3;
			}
		}
		
		//use tools
		x = e.getX();
		y = e.getY();
		if( x <= 490 && x >= 10 && y <= 490 && y >= 10 ){
			//store undo
			if( store ){
				storeUndo();
			}
			store = false;
			
			//fill or place color or eye dropper
			int row = (y-10)/30;
			int column = (x-10)/30;
			if( currentTool.equals("Fill") ){
				if( !compareColor(row,column,redSet,greenSet,blueSet) ){
					fill(row, column);
				}
			} else if ( currentTool.equals("Eye Dropper") ){
				eyeDropper(row,column);
			} else {
				grid[row][column].setColor(redSet,greenSet,blueSet);
			}
		}
		
		repaint();
		
    }
 
 
    public void mouseReleased(MouseEvent e) {
    	store = true;
    }
 
 
    public void mouseEntered(MouseEvent e) {}
 
 
    public void mouseExited(MouseEvent e) {}
 
 
    public void mouseClicked(MouseEvent e) {}
	
	
	public void actionPerformed(ActionEvent e){
		if( e.getSource() == clearButton ){
			storeUndo();
			for(int r = 0;r < grid.length;r ++){
				for(int c = 0;c < grid[r].length;c ++){
					grid[r][c].setColor(255,255,255);
				}
			}
		} else if ( e.getSource() == undoButton ){
			undo();
		} else if ( e.getSource() == fillButton ){
			currentTool = "Fill";
		} else if ( e.getSource() == saveFileButton  ){
			myImage();
		} else if ( e.getSource() == drawButton ){
			currentTool = "Draw";
		} else if ( e.getSource() == eyeDropperButton ){
			currentTool = "Eye Dropper";
		}
		repaint();
	}
	
	
	public void mouseDragged(MouseEvent e){
		if( currentTool.equals("Draw") ){
			int x = e.getX();
			int y = e.getY();
			if( x <= 490 && x >= 10 && y <= 490 && y >= 10 ){
				mousePressed(e);
			}
		}
		
	}
	
	
	public void mouseMoved(MouseEvent e){}
	
	
	public void storeUndo(){
		
		//third undo
		for(int r = 0;r < undoGrid3.length;r ++){
			for(int c = 0;c < undoGrid3[r].length;c ++){
				undoGrid3[r][c].setColor(undoGrid2[r][c].getRed(),undoGrid2[r][c].getGreen(),undoGrid2[r][c].getBlue());
			}
		}
		
		//second undo
		for(int r = 0;r < undoGrid2.length;r ++){
			for(int c = 0;c < undoGrid2[r].length;c ++){
				undoGrid2[r][c].setColor(undoGrid1[r][c].getRed(),undoGrid1[r][c].getGreen(),undoGrid1[r][c].getBlue());
			}
		}
		
		//first undo
		for(int r = 0;r < undoGrid1.length;r ++){
			for(int c = 0;c < undoGrid1[r].length;c ++){
				undoGrid1[r][c].setColor(grid[r][c].getRed(),grid[r][c].getGreen(),grid[r][c].getBlue());
			}
		}
		
	}
	
	
	public void undo(){
		
		//set board
		for(int r = 0;r < grid.length;r ++){
			for(int c = 0;c < grid[r].length;c ++){
				grid[r][c].setColor(undoGrid1[r][c].getRed(),undoGrid1[r][c].getGreen(),undoGrid1[r][c].getBlue());
			}
		}
		
		//move up second undo
		for(int r = 0;r < undoGrid1.length;r ++){
			for(int c = 0;c < undoGrid1[r].length;c ++){
				undoGrid1[r][c].setColor(undoGrid2[r][c].getRed(),undoGrid2[r][c].getGreen(),undoGrid2[r][c].getBlue());
			}
		}
		
		//move up third undo
		for(int r = 0;r < undoGrid2.length;r ++){
			for(int c = 0;c < undoGrid2[r].length;c ++){
				undoGrid2[r][c].setColor(undoGrid3[r][c].getRed(),undoGrid3[r][c].getGreen(),undoGrid3[r][c].getBlue());
			}
		}
		
		repaint();
		
	}
	
	public void fill(int row, int col){
		grid[row][col].fillTrue();
		int red1 = grid[row][col].getRed();
		int green1 = grid[row][col].getGreen();
		int blue1 = grid[row][col].getBlue();
		grid[row][col].setColor(redSet,greenSet,blueSet);
		
		boolean keepFilling = true;
		while( keepFilling ){
			keepFilling = false;
			//loop through every spot
			for(int r = 0;r < grid.length;r ++){
				for(int c = 0;c < grid[r].length;c ++){
					//if one next to the spot is filled
					if( r-1 >= 0 && r-1 <= 15 && compareColor(r,c,red1,green1,blue1) ){
						if( grid[r-1][c].getFill() ){
							grid[r][c].fillTrue();
							grid[r][c].setColor(redSet,greenSet,blueSet);
							keepFilling = true;
						}
					}
					if ( r+1 >= 0 && r+1 <= 15 && compareColor(r,c,red1,green1,blue1) ){
						if( grid[r+1][c].getFill() ){
							grid[r][c].fillTrue();
							grid[r][c].setColor(redSet,greenSet,blueSet);
							keepFilling = true;
						}
					}
					if ( c-1 >= 0 && c-1 <= 15 && compareColor(r,c,red1,green1,blue1) ){
						if( grid[r][c-1].getFill() ){
							grid[r][c].fillTrue();
							grid[r][c].setColor(redSet,greenSet,blueSet);
							keepFilling = true;
						}
					}
					if ( c+1 >= 0 && c+1 <= 15 && compareColor(r,c,red1,green1,blue1) ){
						if( grid[r][c+1].getFill() ){
							grid[r][c].fillTrue();
							grid[r][c].setColor(redSet,greenSet,blueSet);
							keepFilling = true;
						}
					}
				}
			}
		}
		
		for(int r = 0;r < grid.length;r ++){
			for(int c = 0;c < grid[r].length;c ++){
				grid[r][c].fillFalse();
			}
		}
	}
	
	
	public boolean compareColor(int row1, int col1, int red1, int green1, int blue1){
		if( grid[row1][col1].getRed() != red1 ){
			return false;
		} else if ( grid[row1][col1].getGreen() != green1 ){
			return false;
		} else if ( grid[row1][col1].getBlue() != blue1 ){
			return false;
		}
		return true;
	}
	
	public void myImage(){
		int width = 481;
		int height = 480;
		BufferedImage image = null;
		File f = null;
		
		String fileName = JOptionPane.showInputDialog("Enter file name:");
		
		//read image file
		try{
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D g2d = image.createGraphics();
			
			int x = 0;
			int y = 0;
			for(int r = 0;r < grid.length;r ++){
				for(int c = 0;c < grid[r].length;c ++){
					grid[r][c].drawMe(g2d,x,y);
					x += 30;
				}
				x = 0;
				y += 30;
			}	
				
			g2d.dispose();
			
			// Save as PNG
       	 	File file = new File("SavedImages/" + fileName + ".png");
        	ImageIO.write(image, "png", file);
	
		} catch (IOException e){
			System.out.println("Error: " + e);
		}
		
		
	}
	
	public void eyeDropper(int row,int col){
		redSet = grid[row][col].getRed();
		greenSet = grid[row][col].getGreen();
		blueSet = grid[row][col].getBlue();
	}
	
	
}