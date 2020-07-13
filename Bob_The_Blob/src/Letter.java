import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Letter 
{
	
	private char theLetter;
	private double letterSize, topLeftX, topLeftY;
	private int counter;
	//	0 = x, 1 = y
	private double[][] letterGridLocations = new double[25][2];
	private Rectangle2D.Double[] letterGridRectangles = new Rectangle2D.Double[25];
	
	public Letter(char theLetter, double topLeftX, double topLeftY, double letterSize)
	{
		this.theLetter = theLetter;
		this.letterSize = letterSize;
		this.topLeftX = topLeftX;
		this.topLeftY = topLeftY;
		//	Goes up to the first number in letterGrid [25].
		counter = 0;
		for (int index=0; index<5; index++)
		{
			for (int index2=0; index2<5; index2++)
			{
				letterGridLocations[counter][0] = topLeftX;
				letterGridLocations[counter][1] = topLeftY;
				topLeftX = topLeftX + letterSize;
				counter++;
			}
			topLeftY = topLeftY + letterSize;
			topLeftX = topLeftX-5*letterSize;
		}
		
		for (int index=0; index<letterGridRectangles.length; index++)
		{
			letterGridRectangles[index] = new Rectangle2D.Double(letterGridLocations[index][0], letterGridLocations[index][1], letterSize, letterSize);
		}
	}
	

	
	
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.black);
		if (theLetter == ' ')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
			}
		}
		
		else if (theLetter=='B')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index== 1 || index==2 || index==3 || index==5 || index==9 || index==10 || index==11 || index==12 || index==13 || index==15 || index==19 || index==20 || index==21 || index==22 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter=='C')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==4 || index==3 || index==2 || index==1 || index==5 || index==10 || index==15 || index==21 || index==22 || index==23 || index==24)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'G')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==3 || index==2 || index==1 || index==5 || index==10 || index==15 || index==21 || index==22 || index==23 || index==19 || index==14 || index==13 || index==12)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'H')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==5 || index==10 || index==15 || index==20 || index==11 || index==12 || index==13 || index==4 || index==9 || index==14 || index==19 || index==24)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'I')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==1 || index==2 || index==3 || index==4 || index==7 || index==12 || index==17 || index==20 || index==21 || index==22 || index==23 || index==24)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'L')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==5 || index==10 || index==15 || index==20 || index==21 || index==22 || index==23 || index==24)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'M')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==20 || index==15 || index==10 || index==5 || index==1 || index==7 || index==12 || index==3 || index== 9 || index==14 || index==19 || index==24)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'O')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==3 || index==2 || index==1 || index==5 || index==10 || index==15 || index==21 || index==22 || index==23 || index==19 || index==14 || index==9)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'T')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==1 || index==2 || index==3 || index==4 || index==7 || index==12 || index==17 || index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'U')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==5 || index==10 || index==15 || index==21 || index==22 || index==23 || index==19 || index==14 || index==9 || index==4)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'V')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==5 || index==11 || index==16 || index==22 || index==18 || index==13 || index==9 || index==4)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'W')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==5 || index==10 || index==15 || index==21 || index==17 || index==12 || index==23 || index==19 || index==14 || index==9 || index==4)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'Y')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==0 || index==6 || index==4 || index==8 || index==12 || index==17 || index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'a')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==3 || index==8 || index==7 || index==6 || index==11 || index==16 || index==17 || index==13 || index==18 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'b')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==6 || index==11 || index==12 || index==13 || index==16 || index==18 || index==21 || index==22 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'c')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==13 || index==12 || index==11 || index==16 || index==21 || index==22 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'd')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==3 || index==8 || index==11 || index==12 || index==13 || index==16 || index==18 || index==21 || index==22 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'e')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==2 || index==3 || index==6 || index==8 || index==11 || index==12 || index==13 || index==16 || index==21 || index==22 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'f')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==21 || index==16 || index==11 || index==10 || index==12 || index==6 || index==2 || index==3)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'g')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==2 || index==3 || index==6 || index==8 || index==11 || index==12 || index==13 || index==18 || index==22 || index==21)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'h')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==6 || index==11 || index==16 || index==21 || index==12 || index==13 || index==18 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'i')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==2 || index==12 || index==17 || index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'j')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==2 || index==12 || index==17 || index==22 || index==21)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'k')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==6 || index==11 || index==16 || index==21 || index==17 || index==13 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'l')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==2 || index==7 || index==12 || index==17 || index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'm')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==20 || index==15 || index==10 || index==11 || index==12 || index==17 || index==22 || index==13 || index==14 || index==19 || index==24)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'n')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==11 || index==16 || index==21 || index==12 || index==13 || index== 18 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		
		else if (theLetter == 'o')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==11 || index==12 || index==13 || index== 16 || index==18 || index==21 || index==22 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'p')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==2 || index==3 || index==8 || index==13 || index==12 || index==6 || index==11 || index==16 || index==21)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'r')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==21 || index==16 || index==11 || index==12 || index==13 || index==18)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 's')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==3 || index==2 || index==6 || index==12 || index==18 || index==22 || index==21)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 't')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==2 || index==7 || index==12 || index==17 || index==22 || index==11 || index==13)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'u')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==11 || index==16 || index==21 || index==22 || index==23 || index==18 || index==13)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'v')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==10 || index==16 || index==22 || index==18 || index==14)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'w')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==10 || index==15 || index==21 || index==17 || index==12 || index==23 || index==19 || index==14)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == 'y')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==6 || index==8 || index==12 || index==16 || index==20)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		
		else if (theLetter == '.')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == '!')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==2 || index==7 || index==12 || index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == '?')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==2 || index==8 || index==12 || index==22)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		
		
		else if (theLetter == '(')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==3 || index==7 || index==12 || index==17 || index==23)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == ')')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==1 || index==7 || index==12 || index==17 || index==21)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		//	APOSTRAPHIES ARE WERID, AN INVALID CHARACTER, SO HAVE TO DO QUOTES INSTEAD!!
		else if (theLetter == '"')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==2 || index==7)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == ',')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==17|| index==21)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		else if (theLetter == '-')
		{
			for (int index=0; index<letterGridRectangles.length; index++)
			{
				//g.draw(letterGridRectangles[index]);
				if (index==11 || index==12 || index==13)
				{
					g.fill(letterGridRectangles[index]);
				}
			}
		}
		
		
	}
}
