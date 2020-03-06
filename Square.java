import java.awt.Graphics;
import java.awt.Color;

public class Square{

	public int red, green, blue;
	
	public Square(){
		red = 255;
		green = 255;
		blue = 255;
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

}