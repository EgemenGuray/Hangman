/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

//Name: Egemen Guray
//Section Leader: Sherman Leung

public class HangmanCanvas extends GCanvas{

	/* Constants for the simple version of the picture (in pixels) */
		private static final double SCAFFOLD_HEIGHT = 360;
		private static final double BEAM_LENGTH = 144;
		private static final double ROPE_LENGTH = 18;
		private static final double HEAD_RADIUS = 36;
		private static final double BODY_LENGTH = 144;
		private static final double ARM_OFFSET_FROM_HEAD = 28;
		private static final double UPPER_ARM_LENGTH = 72;
		private static final double LOWER_ARM_LENGTH = 44;
		private static final double HIP_WIDTH = 36;
		private static final double LEG_LENGTH = 108;
		private static final double FOOT_LENGTH = 28;

	
	/** HangmanCanvas constructor. You can do initialization (if needed) here. */
	public HangmanCanvas(){
		
	}
	
/** Resets the display so that only the scaffold appears */
	public void reset(){
		scaffold();
	}
	/*
	 * Draws scaffold
	 */
	private void scaffold(){
		double uppersX = ((getWidth()/2.0) - UPPER_ARM_LENGTH);
		double uppersY = ((getHeight()/2.0) - (HEAD_RADIUS*2.0)- BODY_LENGTH - ROPE_LENGTH);
		double endsY = (uppersY + SCAFFOLD_HEIGHT);
		GLine scaffold= new GLine (uppersX,uppersY, uppersX, endsY);
		add(scaffold);
		double frX = uppersX + BEAM_LENGTH;
		GLine fulcrum = new GLine(uppersX,uppersY, frX, uppersY);
		add(fulcrum);
		double ropeY = (uppersY + ROPE_LENGTH);
		GLine rope = new GLine (frX, uppersY, frX, ropeY);
		add(rope);
	}

	
	

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word){
		double x = (getWidth()/8);
		double y = (getHeight() - (HEAD_RADIUS*2.0));
		GLabel dottedWord = new GLabel(word, x, y);
		dottedWord.setFont("Halvetica-24");
		if(getElementAt(x,y) != null){
			remove(getElementAt(x,y));
		}
		add(dottedWord);

	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(String letter){
		double x = (getWidth()/8.0);
		double y = (getHeight() - HEAD_RADIUS);
		GLabel wrongLetters = new GLabel(letter, x, y);
		wrongLetters.setFont("Halvetica-24");
		if(getElementAt(x,y) != null) {
			remove(getElementAt(x,y));
		}
		add(wrongLetters);
		if(letter.length() == 1){
			makeHead();
		}
		else if(letter.length() == 2){
			makeBody();
		}
		else if(letter.length() == 3){
			makeLeftArm();
		}
		else if(letter.length() == 4){
			makeRightArm();
		}
		else if(letter.length() == 5){
			makeLeftLeg();
		}
		else if(letter.length() == 6){
			makeRightLeg();
		}
		else if(letter.length() == 7){
			makeLeftFoot();
		}
		else if(letter.length() == 8){
			makeRightFoot();
		}

	}
	/*
	 * Draws head
	 */
	private void makeHead(){
		double x = ((getWidth()/2.0) - (UPPER_ARM_LENGTH + HEAD_RADIUS) + BEAM_LENGTH );
		double y = ((getHeight()/2.0) - BODY_LENGTH - (HEAD_RADIUS*2.0));
		GOval head = new GOval (x, y, (HEAD_RADIUS*2.0), (HEAD_RADIUS*2.0));
		add(head);
	}
	/*
	 * Draws body
	 */
	private void makeBody(){
		double x = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + (HEAD_RADIUS));
		double upperY = ((getHeight()/2.0) - BODY_LENGTH);
		double bottomY = (upperY + BODY_LENGTH);
		GLine body = new GLine (x, upperY, x, bottomY);
		add(body);
	}
	/*
	 * Draws left arm
	 */
	private void makeLeftArm(){
		double initialX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS);
		double finalX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS - UPPER_ARM_LENGTH);
		double heightY = ((getHeight()/2.0) - BODY_LENGTH + ARM_OFFSET_FROM_HEAD);
		GLine upperLeftArm = new GLine (initialX, heightY, finalX, heightY);
		add(upperLeftArm);
		double final2Y = (heightY + LOWER_ARM_LENGTH);
		GLine lowerLeftArm = new GLine (finalX, heightY, finalX, final2Y);
		add(lowerLeftArm);
	}
	/*
	 * Draws right arm
	 */
	private void makeRightArm(){
		double initialX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS);
		double finalX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS + UPPER_ARM_LENGTH);
		double heightY = ((getHeight()/2.0) - BODY_LENGTH + ARM_OFFSET_FROM_HEAD);
		GLine upperRightArm = new GLine (initialX, heightY, finalX, heightY);
		add(upperRightArm);
		double final2Y = (heightY + LOWER_ARM_LENGTH);
		GLine lowerRightArm = new GLine (finalX, heightY, finalX, final2Y);
		add(lowerRightArm);
	}
	/*
	 * Draws left leg
	 */
	private void makeLeftLeg(){
		double initialX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS);
		double finalX = (initialX - HIP_WIDTH);
		double heightY = (getHeight()/2.0);
		GLine leftHip = new GLine(initialX, heightY, finalX, heightY);
		add(leftHip);
		double leftLegY = (heightY + LEG_LENGTH);
		GLine leftLeg = new GLine(finalX, heightY, finalX, leftLegY);
		add(leftLeg);

	}
	/*
	 * Draws right leg
	 */
	private void makeRightLeg(){
		double initialX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS);
		double finalX = (initialX + HIP_WIDTH);
		double heightY = (getHeight()/2.0);
		GLine rightHip = new GLine(initialX, heightY, finalX, heightY);
		add(rightHip);
		double rightLegY = (heightY + LEG_LENGTH);
		GLine rightLeg = new GLine(finalX, heightY, finalX, rightLegY);
		add(rightLeg);
	}
	/*
	 * Draws left food
	 */
	private void makeLeftFoot(){
		double initialX = ((getWidth()/2.0) + (UPPER_ARM_LENGTH/2.0) + HEAD_RADIUS - HIP_WIDTH);
		double finalX = (initialX - FOOT_LENGTH);
		double heightY = ((getHeight()/2.0) + LEG_LENGTH);
		GLine leftFoot = new GLine(initialX, heightY, finalX, heightY);
		add(leftFoot);
	}
	/*
	 * Draws right food
	 */
	private void makeRightFoot(){
		double initialX = ((getWidth()/2.0) + UPPER_ARM_LENGTH/2 + HEAD_RADIUS + HIP_WIDTH);
		double finalX = (initialX + FOOT_LENGTH);
		double heightY = ((getHeight()/2.0) + LEG_LENGTH);
		GLine rightFoot = new GLine(initialX, heightY, finalX, heightY);
		add(rightFoot);
	}


}
