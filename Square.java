import java.awt.Graphics;
import java.awt.Color;

public class Square{

	public int red, green, blue;
	public boolean fill;
	
	public Square(){
		red = 255;
		green = 255;
		blue = 255;
		fill = false;
	}
	
	public void drawMe(Graphics g, int x, int y){
		g.setColor( new Color(red,green,blue) );
		g.fillRect(x,y,30,30);
		g.setColor(Color.black);
		g.drawRect(x,y,30,30);
	}
	
	public void setColor(int red, int green, int blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public int getRed(){
		return red;
	}
	
	public int getGreen(){
		return green;
	}
	
	public int getBlue(){
		return blue;
	}
	
	public void print(){
		System.out.println(red);
	}
	
	public void fillTrue(){
		fill = true;
	}
	
	public void fillFalse(){
		fill = false;
	}
	
	public boolean getFill(){
		return fill;
	}

}