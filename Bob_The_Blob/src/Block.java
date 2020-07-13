import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Block 
{
	//	Changing to protected for WinBlock
	protected Rectangle2D.Double theBlock;
	protected double xBlock, yBlock, sizeBlock;
	
	
	public Block(double xBlock, double yBlock)
	{
		this.xBlock = xBlock;
		this.yBlock = yBlock;
		sizeBlock = 30;
		
		theBlock = new Rectangle2D.Double(xBlock, yBlock, sizeBlock, sizeBlock);
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.blue);
		g.fill(theBlock);
		
		g.setColor(Color.black);
		g.draw(theBlock);
	}
	
	
	
	public double getLeftSide()
	{
		return xBlock;
	}
	
	public double getRightSide()
	{
		return xBlock + sizeBlock;
	}
	
	public double getTopSide()
	{
		return yBlock;
	}
	
	public double getBottomSide()
	{
		return yBlock + sizeBlock;
	}
}
