import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class VoidWraith 
{
	private double xWraith, yWraith, diameterWraith, wraithSpeedX, wraithSpeedY;
	private Ellipse2D.Double head, testing;
	
	
	//	These are the spike variables in a clockwise direction, starting from the straight ones closest to the circle, then
	//	moving outwards to the diagonal ones.
	private Line2D.Double upSpikeStraight, urSpikeStraight, rightSpikeStraight, rdSpikeStraight, downSpikeStraight, dlSpikeStraight, leftSpikeStraight, luSpikeStraight;
	private Line2D.Double upSpikeDiagR, urSpikeDiagR, rightSpikeDiagR, rdSpikeDiagR, downSpikeDiagR, dlSpikeDiagR, leftSpikeDiagR, luSpikeDiagR;
	private Line2D.Double upSpikeDiagL, urSpikeDiagL, rightSpikeDiagL, rdSpikeDiagL, downSpikeDiagL, dlSpikeDiagL, leftSpikeDiagL, luSpikeDiagL;
	
	
	public VoidWraith(double xWraith, double yWraith, double wraithSpeedX, double wraithSpeedY)
	{
		this.xWraith = xWraith;
		this.yWraith = yWraith;
		//	Making it so that the diameter is the full size of the VoidWraith including its spikes, not just its circular center.
		//	Final size will be 40, bigger for now so I can see the spikes I'm coding.
		diameterWraith = 40;
		
		this.wraithSpeedX = wraithSpeedX;
		this.wraithSpeedY = wraithSpeedY;
		
		head = new Ellipse2D.Double(xWraith+diameterWraith/4, yWraith+diameterWraith/4, diameterWraith/2, diameterWraith/2);
		
		//testing = new Ellipse2D.Double(xWraith, yWraith, diameterWraith, diameterWraith);
		
		upSpikeStraight = new Line2D.Double(xWraith+diameterWraith/2, yWraith+diameterWraith/4, xWraith+diameterWraith/2, yWraith+diameterWraith/4-diameterWraith/12);
			upSpikeDiagR = new Line2D.Double(xWraith+diameterWraith/2, yWraith+diameterWraith/4-diameterWraith/12, xWraith+diameterWraith/2+diameterWraith/6, yWraith+diameterWraith/4-diameterWraith/6);
				upSpikeDiagL = new Line2D.Double(xWraith+diameterWraith/2+diameterWraith/6, yWraith+diameterWraith/4-diameterWraith/6, xWraith+diameterWraith/2, yWraith+diameterWraith/4-diameterWraith/6-diameterWraith/12);
		urSpikeStraight = new Line2D.Double(xWraith+2*diameterWraith/3, yWraith+diameterWraith/3, xWraith+2*diameterWraith/3+diameterWraith/16, yWraith+diameterWraith/3-diameterWraith/16);
			urSpikeDiagR = new Line2D.Double(xWraith+2*diameterWraith/3+diameterWraith/16, yWraith+diameterWraith/3-diameterWraith/16, xWraith+2*diameterWraith/3+diameterWraith/4, yWraith+diameterWraith/3-diameterWraith/32);
				urSpikeDiagL = new Line2D.Double(xWraith+2*diameterWraith/3+diameterWraith/4, yWraith+diameterWraith/3-diameterWraith/32, xWraith+2*diameterWraith/3+diameterWraith/6, yWraith+diameterWraith/3-diameterWraith/6);
		rightSpikeStraight = new Line2D.Double(xWraith+3*diameterWraith/4, yWraith+diameterWraith/2, xWraith+3*diameterWraith/4+diameterWraith/12, yWraith+diameterWraith/2);
			rightSpikeDiagR = new Line2D.Double(xWraith+3*diameterWraith/4+diameterWraith/12, yWraith+diameterWraith/2, xWraith+3*diameterWraith/4+diameterWraith/6, yWraith+diameterWraith/2+diameterWraith/6);
				rightSpikeDiagL = new Line2D.Double(xWraith+3*diameterWraith/4+diameterWraith/6, yWraith+diameterWraith/2+diameterWraith/6, xWraith+3*diameterWraith/4+diameterWraith/6+diameterWraith/12, yWraith+diameterWraith/2);
		rdSpikeStraight = new Line2D.Double(xWraith+2*diameterWraith/3, yWraith+2*diameterWraith/3, xWraith+2*diameterWraith/3+diameterWraith/16, yWraith+2*diameterWraith/3+diameterWraith/16);
			rdSpikeDiagR = new Line2D.Double(xWraith+2*diameterWraith/3+diameterWraith/16, yWraith+2*diameterWraith/3+diameterWraith/16, xWraith+2*diameterWraith/3+diameterWraith/32, yWraith+2*diameterWraith/3+diameterWraith/4);
				rdSpikeDiagL = new Line2D.Double(xWraith+2*diameterWraith/3+diameterWraith/32, yWraith+2*diameterWraith/3+diameterWraith/4, xWraith+2*diameterWraith/3+diameterWraith/8+diameterWraith/16, yWraith+2*diameterWraith/3+diameterWraith/4-diameterWraith/16);
		downSpikeStraight = new Line2D.Double(xWraith+diameterWraith/2, yWraith+3*diameterWraith/4, xWraith+diameterWraith/2, yWraith+3*diameterWraith/4+diameterWraith/12);
			downSpikeDiagR = new Line2D.Double(xWraith+diameterWraith/2, yWraith+3*diameterWraith/4+diameterWraith/12, xWraith+diameterWraith/2-diameterWraith/6, yWraith+3*diameterWraith/4+diameterWraith/6);
				downSpikeDiagL = new Line2D.Double(xWraith+diameterWraith/2-diameterWraith/6, yWraith+3*diameterWraith/4+diameterWraith/6, xWraith+diameterWraith/2, yWraith+3*diameterWraith/4+diameterWraith/6+diameterWraith/12);
		dlSpikeStraight = new Line2D.Double(xWraith+diameterWraith/3, yWraith+2*diameterWraith/3, xWraith+diameterWraith/3-diameterWraith/16, yWraith+2*diameterWraith/3+diameterWraith/16);
			dlSpikeDiagR = new Line2D.Double(xWraith+diameterWraith/3-diameterWraith/16, yWraith+2*diameterWraith/3+diameterWraith/16, xWraith+diameterWraith/3-diameterWraith/4, yWraith+2*diameterWraith/3+diameterWraith/32);
				dlSpikeDiagL = new Line2D.Double(xWraith+diameterWraith/3-diameterWraith/4, yWraith+2*diameterWraith/3+diameterWraith/32, xWraith+diameterWraith/3-diameterWraith/6, yWraith+2*diameterWraith/3+diameterWraith/6);
		leftSpikeStraight = new Line2D.Double(xWraith+diameterWraith/4, yWraith+diameterWraith/2, xWraith+diameterWraith/4-diameterWraith/12, yWraith+diameterWraith/2);
			leftSpikeDiagR = new Line2D.Double(xWraith+diameterWraith/4-diameterWraith/12, yWraith+diameterWraith/2, xWraith+diameterWraith/4-diameterWraith/6, yWraith+diameterWraith/2-diameterWraith/6);
				leftSpikeDiagL = new Line2D.Double(xWraith+diameterWraith/4-diameterWraith/6, yWraith+diameterWraith/2-diameterWraith/6, xWraith+diameterWraith/4-diameterWraith/6-diameterWraith/12, yWraith+diameterWraith/2);
		luSpikeStraight = new Line2D.Double(xWraith+diameterWraith/3, yWraith+diameterWraith/3, xWraith+diameterWraith/3-diameterWraith/16, yWraith+diameterWraith/3-diameterWraith/16);
			luSpikeDiagR = new Line2D.Double(xWraith+diameterWraith/3-diameterWraith/16, yWraith+diameterWraith/3-diameterWraith/16, xWraith+diameterWraith/3-diameterWraith/32, yWraith+diameterWraith/3-diameterWraith/4);
				luSpikeDiagL = new Line2D.Double(xWraith+diameterWraith/3-diameterWraith/32, yWraith+diameterWraith/3-diameterWraith/4, xWraith+diameterWraith/3-diameterWraith/8-diameterWraith/16, yWraith+diameterWraith/3-diameterWraith/4+diameterWraith/16);
	}
	
	public void draw(Graphics2D g)
	{
		//	Outlines must go after solid colors.
		g.setColor(Color.black);
		g.fill(head);
		
		//g.draw(testing);
		
		g.draw(upSpikeStraight);
			g.draw(upSpikeDiagR);
				g.draw(upSpikeDiagL);
		g.draw(urSpikeStraight);
			g.draw(urSpikeDiagR);
				g.draw(urSpikeDiagL);
		g.draw(rightSpikeStraight);
			g.draw(rightSpikeDiagR);
				g.draw(rightSpikeDiagL);
		g.draw(rdSpikeStraight);
			g.draw(rdSpikeDiagR);
				g.draw(rdSpikeDiagL);
		g.draw(downSpikeStraight);
			g.draw(downSpikeDiagR);
				g.draw(downSpikeDiagL);
		g.draw(dlSpikeStraight);
			g.draw(dlSpikeDiagR);
				g.draw(dlSpikeDiagL);
		g.draw(leftSpikeStraight);
			g.draw(leftSpikeDiagR);
				g.draw(leftSpikeDiagL);
		g.draw(luSpikeStraight);
			g.draw(luSpikeDiagR);
				g.draw(luSpikeDiagL);
	}
	
	public void move(double deltaX, double deltaY)
	{
		head.setFrame(head.getX()+deltaX, head.getY()+deltaY, head.getWidth(), head.getHeight());
		//testing.setFrame(testing.getX()+deltaX, testing.getY()+deltaY, testing.getWidth(), testing.getHeight());
		
		upSpikeStraight.setLine(upSpikeStraight.getX1()+deltaX, upSpikeStraight.getY1()+deltaY, upSpikeStraight.getX2()+deltaX, upSpikeStraight.getY2()+deltaY);
			upSpikeDiagR.setLine(upSpikeDiagR.getX1()+deltaX, upSpikeDiagR.getY1()+deltaY, upSpikeDiagR.getX2()+deltaX, upSpikeDiagR.getY2()+deltaY);
				upSpikeDiagL.setLine(upSpikeDiagL.getX1()+deltaX, upSpikeDiagL.getY1()+deltaY, upSpikeDiagL.getX2()+deltaX, upSpikeDiagL.getY2()+deltaY);
		urSpikeStraight.setLine(urSpikeStraight.getX1()+deltaX, urSpikeStraight.getY1()+deltaY, urSpikeStraight.getX2()+deltaX, urSpikeStraight.getY2()+deltaY);
			urSpikeDiagR.setLine(urSpikeDiagR.getX1()+deltaX, urSpikeDiagR.getY1()+deltaY, urSpikeDiagR.getX2()+deltaX, urSpikeDiagR.getY2()+deltaY);
				urSpikeDiagL.setLine(urSpikeDiagL.getX1()+deltaX, urSpikeDiagL.getY1()+deltaY, urSpikeDiagL.getX2()+deltaX, urSpikeDiagL.getY2()+deltaY);
		rightSpikeStraight.setLine(rightSpikeStraight.getX1()+deltaX, rightSpikeStraight.getY1()+deltaY, rightSpikeStraight.getX2()+deltaX, rightSpikeStraight.getY2()+deltaY);
			rightSpikeDiagR.setLine(rightSpikeDiagR.getX1()+deltaX, rightSpikeDiagR.getY1()+deltaY, rightSpikeDiagR.getX2()+deltaX, rightSpikeDiagR.getY2()+deltaY);
				rightSpikeDiagL.setLine(rightSpikeDiagL.getX1()+deltaX, rightSpikeDiagL.getY1()+deltaY, rightSpikeDiagL.getX2()+deltaX, rightSpikeDiagL.getY2()+deltaY);
		rdSpikeStraight.setLine(rdSpikeStraight.getX1()+deltaX, rdSpikeStraight.getY1()+deltaY, rdSpikeStraight.getX2()+deltaX, rdSpikeStraight.getY2()+deltaY);
			rdSpikeDiagR.setLine(rdSpikeDiagR.getX1()+deltaX, rdSpikeDiagR.getY1()+deltaY, rdSpikeDiagR.getX2()+deltaX, rdSpikeDiagR.getY2()+deltaY);
				rdSpikeDiagL.setLine(rdSpikeDiagL.getX1()+deltaX, rdSpikeDiagL.getY1()+deltaY, rdSpikeDiagL.getX2()+deltaX, rdSpikeDiagL.getY2()+deltaY);
		downSpikeStraight.setLine(downSpikeStraight.getX1()+deltaX, downSpikeStraight.getY1()+deltaY, downSpikeStraight.getX2()+deltaX, downSpikeStraight.getY2()+deltaY);
			downSpikeDiagR.setLine(downSpikeDiagR.getX1()+deltaX, downSpikeDiagR.getY1()+deltaY, downSpikeDiagR.getX2()+deltaX, downSpikeDiagR.getY2()+deltaY);
				downSpikeDiagL.setLine(downSpikeDiagL.getX1()+deltaX, downSpikeDiagL.getY1()+deltaY, downSpikeDiagL.getX2()+deltaX, downSpikeDiagL.getY2()+deltaY);
		dlSpikeStraight.setLine(dlSpikeStraight.getX1()+deltaX, dlSpikeStraight.getY1()+deltaY, dlSpikeStraight.getX2()+deltaX, dlSpikeStraight.getY2()+deltaY);
			dlSpikeDiagR.setLine(dlSpikeDiagR.getX1()+deltaX, dlSpikeDiagR.getY1()+deltaY, dlSpikeDiagR.getX2()+deltaX, dlSpikeDiagR.getY2()+deltaY);
				dlSpikeDiagL.setLine(dlSpikeDiagL.getX1()+deltaX, dlSpikeDiagL.getY1()+deltaY, dlSpikeDiagL.getX2()+deltaX, dlSpikeDiagL.getY2()+deltaY);
		leftSpikeStraight.setLine(leftSpikeStraight.getX1()+deltaX, leftSpikeStraight.getY1()+deltaY, leftSpikeStraight.getX2()+deltaX, leftSpikeStraight.getY2()+deltaY);
			leftSpikeDiagR.setLine(leftSpikeDiagR.getX1()+deltaX, leftSpikeDiagR.getY1()+deltaY, leftSpikeDiagR.getX2()+deltaX, leftSpikeDiagR.getY2()+deltaY);
				leftSpikeDiagL.setLine(leftSpikeDiagL.getX1()+deltaX, leftSpikeDiagL.getY1()+deltaY, leftSpikeDiagL.getX2()+deltaX, leftSpikeDiagL.getY2()+deltaY);
		luSpikeStraight.setLine(luSpikeStraight.getX1()+deltaX, luSpikeStraight.getY1()+deltaY, luSpikeStraight.getX2()+deltaX, luSpikeStraight.getY2()+deltaY);
			luSpikeDiagR.setLine(luSpikeDiagR.getX1()+deltaX, luSpikeDiagR.getY1()+deltaY, luSpikeDiagR.getX2()+deltaX, luSpikeDiagR.getY2()+deltaY);
				luSpikeDiagL.setLine(luSpikeDiagL.getX1()+deltaX, luSpikeDiagL.getY1()+deltaY, luSpikeDiagL.getX2()+deltaX, luSpikeDiagL.getY2()+deltaY);
	}
	
	
	public double getLeftSide()
	{
		return xWraith;
	}
	
	public double getRightSide()
	{
		return xWraith + diameterWraith;
	}
	
	public double getTopSide()
	{
		return yWraith;
	}
	
	public double getBottomSide()
	{
		return yWraith + diameterWraith;
	}
	
	
	public void setX(double xWraith)
	{
		this.xWraith = xWraith;
	}
	
	public void setY(double yWraith)
	{
		this.yWraith = yWraith;
	}
	
	public double getX()
	{
		return xWraith;	
	}
	
	public double getY()
	{
		return yWraith;
	}
	
	public double getXSpeed()
	{
		return wraithSpeedX;
	}
	
	public double getYSpeed()
	{
		return wraithSpeedY;
	}
	
	public void setXSpeed(double wraithSpeedX)
	{
		this.wraithSpeedX = wraithSpeedX;
	}
	
	public void setYSpeed(double wraithSpeedY)
	{
		this.wraithSpeedY = wraithSpeedY;
	}
}
