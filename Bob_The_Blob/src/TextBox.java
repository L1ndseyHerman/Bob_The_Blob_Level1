import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class TextBox 
{
	
	private double xBox, yBox, widthBox, heightBox;
	private double triangleSize;
	private Line2D.Double triangleLineUp, triangleLineDown, leftSideTop, leftSideBottom;
	private Line2D.Double topSide, bottomSide, rightSide;
	
	public TextBox(double xBox, double yBox, double widthBox, double heightBox)
	{
		this.xBox = xBox;
		this.yBox = yBox; 
		this.widthBox = widthBox;
		this.heightBox = heightBox;
		
		triangleSize = 10;
		
		triangleLineUp = new Line2D.Double(xBox, yBox, xBox+triangleSize, yBox-triangleSize*0.5);
		triangleLineDown = new Line2D.Double(xBox, yBox, xBox+triangleSize, yBox+triangleSize*0.5);
		leftSideBottom = new Line2D.Double(xBox+triangleSize, yBox+triangleSize*0.5, xBox+triangleSize, yBox+triangleSize*0.5+3*heightBox/4);
		leftSideTop = new Line2D.Double(xBox+triangleSize, yBox-triangleSize*0.5, xBox+triangleSize, yBox-triangleSize*0.5-heightBox/4);
		topSide = new Line2D.Double(xBox+triangleSize, yBox-triangleSize*0.5-heightBox/4, xBox+triangleSize+widthBox, yBox-triangleSize*0.5-heightBox/4);
		bottomSide = new Line2D.Double(xBox+triangleSize, yBox+triangleSize*0.5+3*heightBox/4, xBox+triangleSize+widthBox, yBox+triangleSize*0.5+3*heightBox/4);
		rightSide = new Line2D.Double(xBox+triangleSize+widthBox, yBox+triangleSize*0.5+3*heightBox/4, xBox+triangleSize+widthBox, yBox-triangleSize*0.5-heightBox/4);
	}
	
	public double getLeftSide()
	{
		return xBox+triangleSize;
	}
	
	public double getTopSide()
	{
		return yBox-triangleSize*0.5-heightBox/4;
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.black);
		g.draw(triangleLineUp);
		g.draw(triangleLineDown);
		g.draw(leftSideTop);
		g.draw(leftSideBottom);
		g.draw(topSide);
		g.draw(bottomSide);
		g.draw(rightSide);
	}
}
