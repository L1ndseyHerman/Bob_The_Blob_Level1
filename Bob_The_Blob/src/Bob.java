import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

//	Maybe put the movement stuff in the Bob class instead of the Panel.
public class Bob 
{
	//	New! Decides what facial expression Bob should have.
	private String emotion;
	
	private Ellipse2D.Double head, leftEye, rightEye, leftPupil, rightPupil, circleMouth;
	private Line2D.Double smileTop, smileLeft, smileRight;
	private double xBob, yBob, diameterBob;
	
	
	public Bob(double xBob, double yBob)
	{
		this.xBob = xBob;
		this.yBob = yBob;
		diameterBob = 40;
		head = new Ellipse2D.Double(xBob, yBob, diameterBob, diameterBob);
		emotion = "blank";
		

		
	}
	
	public void setEmotion(String emotion)
	{
		this.emotion = emotion;
	}
	
	public void draw(Graphics2D g)
	{
		//	Outlines must go after solid colors.
		g.setColor(Color.yellow);
		g.fill(head);

		g.setColor(Color.black);
		g.draw(head);

		
		if (emotion == "neutralHappy")
		{
			leftEye = new Ellipse2D.Double(xBob+diameterBob/5, yBob+diameterBob/4, diameterBob/5, diameterBob/4);
			
			leftPupil = new Ellipse2D.Double(xBob+diameterBob/5+diameterBob/10, yBob+diameterBob/4, diameterBob/10, diameterBob/8);
			rightPupil = new Ellipse2D.Double(xBob+3*diameterBob/5+diameterBob/10, yBob+diameterBob/4, diameterBob/10, diameterBob/8);
			
			rightEye = new Ellipse2D.Double(xBob+3*diameterBob/5, yBob+diameterBob/4, diameterBob/5, diameterBob/4);
			smileTop = new Line2D.Double(xBob+diameterBob/4, yBob+7*diameterBob/10, xBob+3*diameterBob/4, yBob+7*diameterBob/10);
			smileLeft = new Line2D.Double(xBob+diameterBob/4, yBob+7*diameterBob/10, xBob+2*diameterBob/4, yBob+8*diameterBob/10);
			smileRight = new Line2D.Double(xBob+2*diameterBob/4, yBob+8*diameterBob/10, xBob+3*diameterBob/4, yBob+7*diameterBob/10);
			
			
			g.setColor(Color.darkGray);
			g.fill(leftEye);
			g.fill(rightEye);

			g.setColor(Color.black);
			g.draw(leftEye);
			g.draw(rightEye);
			g.draw(smileTop);
			g.draw(smileLeft);
			g.draw(smileRight);
		
			g.setColor(Color.white);
			g.fill(leftPupil);
			g.fill(rightPupil);
		
			g.setColor(Color.darkGray);
			g.draw(leftPupil);
			g.draw(rightPupil);
		}
		
		
		
		else if (emotion == "animeEcstatic")
		{
			leftEye = new Ellipse2D.Double(xBob+diameterBob/5, yBob+diameterBob/4, diameterBob/5, diameterBob/4);
			
			leftPupil = new Ellipse2D.Double(xBob+diameterBob/5+diameterBob/15, yBob+diameterBob/4.5, diameterBob/7, diameterBob/6);
			
			rightPupil = new Ellipse2D.Double(xBob+3*diameterBob/5+diameterBob/15, yBob+diameterBob/4.5, diameterBob/7, diameterBob/6);
			
			rightEye = new Ellipse2D.Double(xBob+3*diameterBob/5, yBob+diameterBob/4, diameterBob/5, diameterBob/4);
			
			
			smileTop = new Line2D.Double(xBob+diameterBob/4, yBob+7*diameterBob/10, xBob+3*diameterBob/4, yBob+7*diameterBob/10);
			smileLeft = new Line2D.Double(xBob+diameterBob/4, yBob+7*diameterBob/10, xBob+2*diameterBob/4, yBob+9*diameterBob/10);
			smileRight = new Line2D.Double(xBob+2*diameterBob/4, yBob+9*diameterBob/10, xBob+3*diameterBob/4, yBob+7*diameterBob/10);
			
			g.setColor(Color.darkGray);
			g.fill(leftEye);
			g.fill(rightEye);

			g.setColor(Color.black);
			g.draw(leftEye);
			g.draw(rightEye);
			g.draw(smileTop);
			g.draw(smileLeft);
			g.draw(smileRight);
		
			g.setColor(Color.white);
			g.fill(leftPupil);
			g.fill(rightPupil);
		
			g.setColor(Color.darkGray);
			g.draw(leftPupil);
			g.draw(rightPupil);
			
			
		}
		
		
		else if (emotion=="surprised")
		{
			leftEye = new Ellipse2D.Double(xBob+diameterBob/5, yBob+diameterBob/4, diameterBob/5, diameterBob/4);
			
			leftPupil = new Ellipse2D.Double(xBob+diameterBob/5+diameterBob/10, yBob+diameterBob/4, diameterBob/10, diameterBob/8);
			rightPupil = new Ellipse2D.Double(xBob+3*diameterBob/5+diameterBob/10, yBob+diameterBob/4, diameterBob/10, diameterBob/8);
			
			rightEye = new Ellipse2D.Double(xBob+3*diameterBob/5, yBob+diameterBob/4, diameterBob/5, diameterBob/4);
			
			circleMouth = new Ellipse2D.Double(xBob+7*diameterBob/16, yBob+7*diameterBob/10, diameterBob/8, diameterBob/8);
			
			
			g.setColor(Color.darkGray);
			g.fill(leftEye);
			g.fill(rightEye);

			g.setColor(Color.black);
			g.draw(leftEye);
			g.draw(rightEye);
			g.draw(circleMouth);
		
			g.setColor(Color.white);
			g.fill(leftPupil);
			g.fill(rightPupil);
		
			g.setColor(Color.darkGray);
			g.draw(leftPupil);
			g.draw(rightPupil);
			
			
			
		}
		
		
	}
	public double getX()
	{
		return xBob;	
	}
	public void setX(double xBob)
	{
		this.xBob = xBob;
	}
	public double getY()
	{
		return yBob;
	}
	public void setY(double yBob)
	{
		this.yBob = yBob;
	}
	
	//	Needed for blocks
	//	NOW NEEDED FOR SPEECH BUBBLE!!!!!!!!!!!
	public double getDiameter()
	{
		return diameterBob;
	}
	public void move(double deltaX, double deltaY)
	{
		head.setFrame(head.getX()+deltaX, head.getY()+deltaY, head.getWidth(), head.getHeight());
		leftEye.setFrame(leftEye.getX()+deltaX, leftEye.getY()+deltaY, leftEye.getWidth(), leftEye.getHeight());
		
		leftPupil.setFrame(leftPupil.getX()+deltaX, leftPupil.getY()+deltaY, leftPupil.getWidth(), leftPupil.getHeight());
		rightPupil.setFrame(rightPupil.getX()+deltaX, rightPupil.getY()+deltaY, rightPupil.getWidth(), rightPupil.getHeight());
		
		rightEye.setFrame(rightEye.getX()+deltaX, rightEye.getY()+deltaY, rightEye.getWidth(), rightEye.getHeight());
		smileTop.setLine(smileTop.getX1()+deltaX, smileTop.getY1()+deltaY, smileTop.getX2()+deltaX, smileTop.getY2()+deltaY);
		smileLeft.setLine(smileLeft.getX1()+deltaX, smileLeft.getY1()+deltaY, smileLeft.getX2()+deltaX, smileLeft.getY2()+deltaY);
		smileRight.setLine(smileRight.getX1()+deltaX, smileRight.getY1()+deltaY, smileRight.getX2()+deltaX, smileRight.getY2()+deltaY);
	}
	
	
	//	New! To make as easy as Block
	public double getLeftSide()
	{
		return xBob;
	}
	
	public double getRightSide()
	{
		return xBob + diameterBob;
	}
	
	public double getTopSide()
	{
		return yBob;
	}
	
	public double getBottomSide()
	{
		return yBob + diameterBob;
	}
}
