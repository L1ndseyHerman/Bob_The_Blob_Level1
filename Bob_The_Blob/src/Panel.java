import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;


public class Panel extends JPanel implements ActionListener, KeyListener, MouseInputListener
	{
		//	A Timer that gets called x times a second on the MyPanel
		Timer t = new Timer(5, this);
		
		//	Need two separate booleans for the up arrow key: jumping and falling.
		boolean jumpingUp = false, fallingDown = false, movingLeft = false, movingRight = false;
		
		//	The two ways of ending the game
		boolean beatLevel = false, gameOver = false;
		
		//	Adding the story before the level!
		boolean storyTime = true;
		
		//	A location in the middle-ish of the screen for storyTime.
		Bob bobTheBlob = new Bob(400, 210);
		
		//	Will be the gap in both the x- and y-direction between letters.
		double letterGap = 5;
		//	Moving this here now.
		double letterSize = 2;
		
		Block[] theBlocks = new Block[15];
		WinBlock theWinBlock = new WinBlock(240, 0);
		
		//	Needs to be one greater than Bob+Block, so 71, not 70 even though 70 sounds right
		double jumpHeight = 71, currentHeight = 460;		
		
		//	220 - 40 = 180
		VoidWraith wraithOne = new VoidWraith(0, 180, 2, 0);
		
		//	For Story Mode
		int clickCounter = 0;
		
		public Panel(int width, int height, Color bgColor)
		{
			//	Every. MyPanel. Ever.
			setPreferredSize(new Dimension(width, height));
			setBackground(bgColor);
			//	IMPT!! PUT IN ALL PROGRAMS TO MAKE THE MOUSE STUFF WORK!!
			this.addMouseListener(this);
			//	ALSO THIS!!
			this.addMouseMotionListener(this);
			//	Starts the Timer
			t.start();
			//	The 3 things below are needed for keyboard input.
			addKeyListener(this);
			setFocusable(true);
			setFocusTraversalKeysEnabled(false);
			

			theBlocks[0] = new Block(300, 470);
			theBlocks[1] = new Block(500, 470);
			theBlocks[2] = new Block(600, 430);
			theBlocks[3] = new Block(500, 360);
			theBlocks[4] = new Block(330, 360);
			theBlocks[5] = new Block(310, 290);
			//	VoidWraith going across starts here.
			theBlocks[6] = new Block(330, 220);
			theBlocks[7] = new Block(400, 220);
			theBlocks[8] = new Block(480, 220);
			theBlocks[9] = new Block(590, 220);
			theBlocks[10] = new Block(720, 220);
			//	VoidWraith going across ends here.
			theBlocks[11] = new Block(730, 150);
			theBlocks[12] = new Block(720, 80);
			theBlocks[13] = new Block(570, 80);
			theBlocks[14] = new Block(390, 80);
			//	Now making WinBlock its own object (not in array) for easier win method.
			
		}
		
		protected void paintComponent(Graphics arg0)
		{
			super.paintComponent(arg0);
			Graphics2D g = (Graphics2D)arg0;
			if (storyTime == true)
			{
				
				if (clickCounter == 0)
				{
					//	NEW!!!!!!!! BOB EMOTIONS!!!!!!!!!
					bobTheBlob.setEmotion("neutralHappy");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 260, 25);
					bobSpeechBubble.draw(g);
					//	Moving the Letter array sizes here so that they can change for each new speech bubble
					Letter lineOne[] = new Letter[6];
					Letter lineTwo[] = new Letter[20];
					
					//	There's got to be a better way to do this, but I can't think of it right now.
					lineOne[0] = new Letter('H', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					lineTwo[0] = new Letter('(', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[1] = new Letter('C', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[2] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[3] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[4] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[5] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[6] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[7] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[8] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[9] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[10] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[11] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[12] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[13] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[14] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[15] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[16] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[17] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[18] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[19] = new Letter(')', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
				
				

					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
				
					for (int index=0; index<lineTwo.length; index++)
					{
						lineTwo[index].draw(g);
					}
					
					
				}
				
				
				else if (clickCounter == 1)
				{
					
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 260, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[15];
					Letter lineTwo[] = new Letter[20];
					
					lineOne[0] = new Letter('M', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('B', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					lineTwo[0] = new Letter('(', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[1] = new Letter('C', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[2] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[3] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[4] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[5] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[6] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[7] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[8] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[9] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[10] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[11] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[12] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[13] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[14] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[15] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[16] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[17] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[18] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[19] = new Letter(')', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
				
					
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
					
					for (int index=0; index<lineTwo.length; index++)
					{
						lineTwo[index].draw(g);
					}
				}
				
				
				else if (clickCounter==2)
				{
					bobTheBlob.setEmotion("animeEcstatic");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 150, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[11];
					
					lineOne[0] = new Letter('I', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('"', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('!', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
				}
				
				
				else if (clickCounter==3)
				{
					bobTheBlob.setEmotion("surprised");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 350, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[27];
					
					lineOne[0] = new Letter('I', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter('v', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('w', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[25] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[26] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
					
				}
				
				
				else if (clickCounter == 4)
				{
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 320, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[25];
					
					lineOne[0] = new Letter('C', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('p', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter('g', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter('?', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
				}
				
				
				else if (clickCounter==5) 
				{
					//	New! Drawing theWinBlock bec Bob is currently talking about it.
					theWinBlock.draw(g);
					
					bobTheBlob.setEmotion("neutralHappy");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 430, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[34];
					
					lineOne[0] = new Letter('T', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('g', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter('w', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[25] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[26] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[27] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[28] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[29] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[30] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[31] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+32*letterGap/2+31*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[32] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+33*letterGap/2+32*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[33] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+34*letterGap/2+33*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
	
				}
				
				
				else if (clickCounter==6)
				{
					//	Talking abt Blocks now.
					theBlocks[0].draw(g);
					
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 400, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[31];
					Letter lineTwo[] = new Letter[12];
					
					lineOne[0] = new Letter('Y', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('j', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('p', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[25] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[26] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[27] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[28] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[29] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[30] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					lineTwo[0] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[1] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[2] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[3] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[4] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[5] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[6] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[7] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[8] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[9] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[10] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[11] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
					
					for (int index=0; index<lineTwo.length; index++)
					{
						lineTwo[index].draw(g);
					}
					
					
				}
				
				
				else if (clickCounter==7)
				{
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 430, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[33];
					Letter lineTwo[] = new Letter[34];
					
					lineOne[0] = new Letter('U', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('f', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter('d', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter('g', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[25] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[26] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[27] = new Letter('w', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[28] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[29] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[30] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[31] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+32*letterGap/2+31*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[32] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+33*letterGap/2+32*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					lineTwo[0] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[1] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[2] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[3] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[4] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[5] = new Letter('v', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[6] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[7] = new Letter(',', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[8] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[9] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[10] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[11] = new Letter('d', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[12] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[13] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[14] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[15] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[16] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[17] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[18] = new Letter('p', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[19] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[20] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[21] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[22] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[23] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[24] = new Letter('w', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[25] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[26] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[27] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[28] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[29] = new Letter('j', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[30] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[31] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+32*letterGap/2+31*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[32] = new Letter('p', bobSpeechBubble.getLeftSide()+letterGap/2+33*letterGap/2+32*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[33] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+34*letterGap/2+33*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
					
					for (int index=0; index<lineTwo.length; index++)
					{
						lineTwo[index].draw(g);
					}
					
					
				}
				
				
				else if (clickCounter==8)
				{
					//	Talking abt VoidWraith
					wraithOne.draw(g);
					
					bobTheBlob.setEmotion("surprised");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 500, 40);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[38];
					
					
					lineOne[0] = new Letter('W', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter('f', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter('V', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('d', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('W', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[25] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[26] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[27] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[28] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[29] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[30] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[31] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+32*letterGap/2+31*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[32] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+33*letterGap/2+32*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[33] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+34*letterGap/2+33*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[34] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+35*letterGap/2+34*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[35] = new Letter('g', bobSpeechBubble.getLeftSide()+letterGap/2+36*letterGap/2+35*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[36] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+37*letterGap/2+36*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[37] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+38*letterGap/2+37*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					
					Letter lineTwo[] = new Letter[39];
					
					lineTwo[0] = new Letter('T', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[1] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[2] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[3] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[4] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[5] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[6] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[7] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[8] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[9] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[10] = new Letter('v', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[11] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[12] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[13] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[14] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[15] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[16] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[17] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[18] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[19] = new Letter('-', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[20] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[21] = new Letter('p', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[22] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[23] = new Letter('d', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[24] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[25] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[26] = new Letter('-', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[27] = new Letter('b', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[28] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[29] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[30] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[31] = new Letter('k', bobSpeechBubble.getLeftSide()+letterGap/2+32*letterGap/2+31*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[32] = new Letter('-', bobSpeechBubble.getLeftSide()+letterGap/2+33*letterGap/2+32*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[33] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+34*letterGap/2+33*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[34] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+35*letterGap/2+34*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[35] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+36*letterGap/2+35*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[36] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+37*letterGap/2+36*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[37] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+38*letterGap/2+37*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[38] = new Letter('.', bobSpeechBubble.getLeftSide()+letterGap/2+39*letterGap/2+38*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					
					Letter lineThree[] = new Letter[25];
					
					lineThree[0] = new Letter('T', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[1] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[2] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[3] = new Letter('c', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[4] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[5] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[6] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[7] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[8] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[9] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[10] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[11] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[12] = new Letter('d', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[13] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[14] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[15] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[16] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[17] = new Letter('"', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[18] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[19] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[20] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[21] = new Letter('d', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[22] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[23] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					lineThree[24] = new Letter('!', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+3*letterGap+2*5*letterSize, letterSize);
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
					
					for (int index=0; index<lineTwo.length; index++)
					{
						lineTwo[index].draw(g);
					}
					
					for (int index=0; index<lineThree.length; index++)
					{
						lineThree[index].draw(g);
					}
					
				}
				
				else if (clickCounter==9)
				{
					bobTheBlob.setEmotion("animeEcstatic");
					bobTheBlob.draw(g);
					TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 400, 25);
					bobSpeechBubble.draw(g);
					
					Letter lineOne[] = new Letter[31];
					
					lineOne[0] = new Letter('W', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[1] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[2] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[3] = new Letter('l', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[4] = new Letter(',', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[5] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[6] = new Letter('w', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[7] = new Letter('h', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[8] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[9] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[10] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+11*letterGap/2+10*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[11] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+12*letterGap/2+11*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[12] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+13*letterGap/2+12*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[13] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+14*letterGap/2+13*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[14] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+15*letterGap/2+14*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[15] = new Letter('y', bobSpeechBubble.getLeftSide()+letterGap/2+16*letterGap/2+15*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[16] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+17*letterGap/2+16*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[17] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+18*letterGap/2+17*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[18] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+19*letterGap/2+18*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[19] = new Letter('w', bobSpeechBubble.getLeftSide()+letterGap/2+20*letterGap/2+19*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[20] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+21*letterGap/2+20*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[21] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+22*letterGap/2+21*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[22] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+23*letterGap/2+22*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[23] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+24*letterGap/2+23*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[24] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+25*letterGap/2+24*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[25] = new Letter('g', bobSpeechBubble.getLeftSide()+letterGap/2+26*letterGap/2+25*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[26] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+27*letterGap/2+26*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[27] = new Letter('f', bobSpeechBubble.getLeftSide()+letterGap/2+28*letterGap/2+27*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[28] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+29*letterGap/2+28*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[29] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+30*letterGap/2+29*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					lineOne[30] = new Letter('?', bobSpeechBubble.getLeftSide()+letterGap/2+31*letterGap/2+30*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
					
					Letter lineTwo[] = new Letter[9];
					
					lineTwo[0] = new Letter('L', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[1] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[2] = new Letter('t', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[3] = new Letter('"', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[4] = new Letter('s', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[5] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[6] = new Letter('g', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[7] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					lineTwo[8] = new Letter('!', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+2*letterGap+1*5*letterSize, letterSize);
					
					for (int index=0; index<lineOne.length; index++)
					{
						lineOne[index].draw(g);
					}
					
					for (int index=0; index<lineTwo.length; index++)
					{
						lineTwo[index].draw(g);
					}
					
					
				}

			}
			if (beatLevel == false && gameOver == false && storyTime == false)
			{
				bobTheBlob.setEmotion("neutralHappy");
				for(int index=0; index<theBlocks.length; index++)
				{
					theBlocks[index].draw(g);
				}
				theWinBlock.draw(g);
				bobTheBlob.draw(g);
				wraithOne.draw(g);
			}
			if (beatLevel == true)
			{
				bobTheBlob.setEmotion("animeEcstatic");
				bobTheBlob.draw(g);
				TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 110, 20);
				bobSpeechBubble.draw(g);
				
				Letter lineOne[] = new Letter[8];
				
				lineOne[0] = new Letter('Y', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[1] = new Letter('o', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[2] = new Letter('u', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[3] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[4] = new Letter('W', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[5] = new Letter('i', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[6] = new Letter('n', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[7] = new Letter('!', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				
				
				for (int index=0; index<lineOne.length; index++)
				{
					lineOne[index].draw(g);
				}
				
			}
			if (gameOver == true)
			{
				bobTheBlob.setEmotion("surprised");
				bobTheBlob.draw(g);
				TextBox bobSpeechBubble = new TextBox (bobTheBlob.getRightSide()+bobTheBlob.getDiameter()/8, bobTheBlob.getTopSide()+7*bobTheBlob.getDiameter()/10, 130, 20);
				bobSpeechBubble.draw(g);
				
				Letter lineOne[] = new Letter[10];
				
				lineOne[0] = new Letter('G', bobSpeechBubble.getLeftSide()+letterGap/2+1*letterGap/2+0*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[1] = new Letter('a', bobSpeechBubble.getLeftSide()+letterGap/2+2*letterGap/2+1*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[2] = new Letter('m', bobSpeechBubble.getLeftSide()+letterGap/2+3*letterGap/2+2*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[3] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+4*letterGap/2+3*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[4] = new Letter(' ', bobSpeechBubble.getLeftSide()+letterGap/2+5*letterGap/2+4*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[5] = new Letter('O', bobSpeechBubble.getLeftSide()+letterGap/2+6*letterGap/2+5*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[6] = new Letter('v', bobSpeechBubble.getLeftSide()+letterGap/2+7*letterGap/2+6*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[7] = new Letter('e', bobSpeechBubble.getLeftSide()+letterGap/2+8*letterGap/2+7*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[8] = new Letter('r', bobSpeechBubble.getLeftSide()+letterGap/2+9*letterGap/2+8*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				lineOne[9] = new Letter('!', bobSpeechBubble.getLeftSide()+letterGap/2+10*letterGap/2+9*5*letterSize, bobSpeechBubble.getTopSide()+letterGap, letterSize);
				
				for (int index=0; index<lineOne.length; index++)
				{
					lineOne[index].draw(g);
				}
			}
		}

		@Override
		public void actionPerformed(ActionEvent arg0) 
		{
			//	The method that gets called whenever the Timer goes off. 
			
			
			if (storyTime == false && gameOver == false && beatLevel == false)
			{
				moveEnemies();
			
			
				checkEnemies();
			
			
				checkWinBlock();
			
			
				repaint();
			
			
				checkBlocksUp();
			

				checkBlocksLeft();
			
			
				checkBlocksRight();
			}
			
		}
		

		//	This method only moves the enemies. The actual checking of whether or not Bob 
		//	is touching the enemy happens in the checkEnemies().
		public void moveEnemies()
		{
			wraithOne.move(wraithOne.getXSpeed(), wraithOne.getYSpeed());
			wraithOne.setX(wraithOne.getX() + wraithOne.getXSpeed());
			wraithOne.setY(wraithOne.getY() + wraithOne.getYSpeed());
			if (wraithOne.getRightSide() + wraithOne.getXSpeed() == 1000 || wraithOne.getLeftSide() + wraithOne.getXSpeed() == 0)
			{
				wraithOne.setXSpeed(wraithOne.getXSpeed() * -1);
			}
			if (wraithOne.getBottomSide() + wraithOne.getYSpeed() == 500 || wraithOne.getTopSide() + wraithOne.getYSpeed() == 0)
			{
				wraithOne.setYSpeed(wraithOne.getYSpeed() * -1);
			}
		}
		
		
		@Override
		public void keyPressed(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			int code = arg0.getKeyCode();
			if (code == KeyEvent.VK_UP)
			{
				if (fallingDown == false && jumpingUp == false)
				{
					jumpingUp = true;
					currentHeight = bobTheBlob.getY();
				}
			}
			if (code == KeyEvent.VK_LEFT)
			{
				movingLeft = true;
			}
			if (code == KeyEvent.VK_RIGHT)
			{
				movingRight = true;
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			int code = arg0.getKeyCode();
			if (code == KeyEvent.VK_LEFT)
			{
				movingLeft = false;
			}
			if (code == KeyEvent.VK_RIGHT)
			{
				movingRight = false;
			}
		}

		@Override
		public void keyTyped(KeyEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}


		
		public void checkBlocksUp()
		{
			if (jumpingUp == true && bobTheBlob.getTopSide() > currentHeight-jumpHeight)
			{
				boolean blockInWay = false;
				for(int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getRightSide() > theBlocks[index].getLeftSide() && bobTheBlob.getLeftSide() < theBlocks[index].getRightSide() && bobTheBlob.getTopSide() <= theBlocks[index].getBottomSide() && bobTheBlob.getTopSide() > theBlocks[index].getTopSide())
					{
						blockInWay = true;
					}
				}
				if (blockInWay == false)
				{
					bobTheBlob.setY(bobTheBlob.getY() - 1);
					bobTheBlob.move(0, -1);
					repaint();
				}
				if (blockInWay == true || bobTheBlob.getTopSide() == currentHeight-jumpHeight)
				{
					jumpingUp = false;
					fallingDown = true;
				}
			}
			if (fallingDown == true)
			{
				boolean blockInWay = false;
				for (int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getBottomSide() == theBlocks[index].getTopSide() && bobTheBlob.getRightSide() > theBlocks[index].getLeftSide() && bobTheBlob.getLeftSide() < theBlocks[index].getRightSide())
					{
						blockInWay = true;
					}
				}
				if (blockInWay == false && bobTheBlob.getTopSide() < 460)
				{
					bobTheBlob.setY(bobTheBlob.getY() + 1);
					bobTheBlob.move(0, 1);
					repaint();
				}
				if (blockInWay == true || bobTheBlob.getTopSide() == 460)
				{
					fallingDown = false;
				}
			}
			//	BOB IS BIGGER THAN THE BLOCKS!! ALL OF BOB WON'T FIT ON ONE, JUST MAKE SURE THE MIDDLE (X + 1/2DIAMETER) IS BETW BLOCK X-VALS INSTEAD!!!!!!!!!!
			//	Actually, doing if Bob is at all touching the block, but his square is bigger than circle, looks to not even be on block but is.
		}
		public void checkBlocksRight()
		{
			//	1000 - 40 - 1 = 959
			if (bobTheBlob.getLeftSide() <= 959 && movingRight == true)
			{
				//	A variable to see if any blocks are in the way. 
				boolean blockInWay = false;
				for(int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getRightSide() == theBlocks[index].getLeftSide() && bobTheBlob.getBottomSide() > theBlocks[index].getTopSide() && bobTheBlob.getTopSide() < theBlocks[index].getBottomSide())
					{
						blockInWay = true;
					}
				}

				if (blockInWay == false)
				{
					bobTheBlob.setX(bobTheBlob.getX() + 1);
					bobTheBlob.move(1, 0);
					for (int index=0; index<theBlocks.length; index++)
					{
						//	CHANGED FROM > TO >=
						if (bobTheBlob.getLeftSide() >= theBlocks[index].getRightSide() && bobTheBlob.getBottomSide() == theBlocks[index].getTopSide() && jumpingUp == false && fallingDown == false)
						{
							fallingDown = true;
						}
						repaint();
					}
				}
			}
		}
		public void checkBlocksLeft()
		{
			if (bobTheBlob.getLeftSide() >= 1 && movingLeft == true)
			{
				//	There is a separate blockInWay variable for each direction, should reset each method call.
				boolean blockInWay = false;
				for(int index=0; index<theBlocks.length; index++)
				{
					if (bobTheBlob.getLeftSide() == theBlocks[index].getRightSide() && bobTheBlob.getBottomSide() > theBlocks[index].getTopSide() && bobTheBlob.getTopSide() < theBlocks[index].getBottomSide())
					{
						blockInWay = true;
					}
				}

				if (blockInWay == false)
				{
					bobTheBlob.setX(bobTheBlob.getX() - 1);
					bobTheBlob.move(-1, 0);
					for (int index=0; index<theBlocks.length; index++)
					{
						//	CHANGED FROM < TO <= 
						if (bobTheBlob.getRightSide() <= theBlocks[index].getLeftSide() && bobTheBlob.getBottomSide() == theBlocks[index].getTopSide() && jumpingUp == false && fallingDown == false) 
						{
							fallingDown = true;
						}
						repaint();
					}
				}
			}
		}
		
		
		
		
		public void checkEnemies()
		{
			if (beatLevel == false)
			{
				if (bobTheBlob.getRightSide() >= wraithOne.getLeftSide() && bobTheBlob.getLeftSide() <= wraithOne.getRightSide() && bobTheBlob.getTopSide() <= wraithOne.getBottomSide() && bobTheBlob.getBottomSide() >= wraithOne.getTopSide())
				{
					gameOver = true;
				}
			}
		}
		
		
		
		public void checkWinBlock()
		{
			if (bobTheBlob.getRightSide() >= theWinBlock.getLeftSide() && bobTheBlob.getLeftSide() <= theWinBlock.getRightSide() && bobTheBlob.getTopSide() <= theWinBlock.getBottomSide() && bobTheBlob.getBottomSide() >= theWinBlock.getTopSide())
			{
				beatLevel = true;
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) 
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) 
		{
			clickCounter++;
			//	REALLY IMPT REPAINT()!!!!!!!!!!!!!
			repaint();
			
			if (storyTime == true && clickCounter==10)
			{
				bobTheBlob.setX(0);
				bobTheBlob.setY(460);
				//	Going from 400 to 0 in x-direction and 210 to 460 in y-direction
				//	500 - 40 == 460.
				bobTheBlob.move(-400, 250);
				storyTime = false;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

			
	}



