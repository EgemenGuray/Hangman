/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

//Name: Egemen Guray
//Section Leader: Sherman Leung

public class Hangman extends ConsoleProgram{
	
	private HangmanCanvas canvas;
	private HangmanLexicon words;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int guessleft = 8;
	private String hypenWord;
	private char ch;
	private String word = choose();
	private String incorrectLetters = "";

    public void run(){
    	canvas = new HangmanCanvas();
    	setLayout(new GridLayout(1, 2));
    	add(canvas);
    	validate();
    	canvas.reset();
    	hypenWord = hypenedLetters();
    	canvas.displayWord(hypenWord);
    	println("Welcome to Hangman!");
    	println("The word now looks like this: " + hypenWord);
    	println("You have " + guessleft + " guesses left.");
    	while(guessleft > 0){
			String getChar = readLine("Your guess: ");
			while (true){
				if(getChar.length() > 1){
					getChar = readLine("Try again: ");
				}
				if(getChar.length() == 1){
					break;
				}
			}
			ch = getChar.charAt(0);
			if(Character.isLowerCase(ch)){
				ch = Character.toUpperCase(ch);
			}
			check();
			if (hypenWord.equals(word)){
				println("You guessed the word: " + word);
				println("You win");
				break;
			}
			println("The word now looks like this: " + hypenWord);
			println("You have " + guessleft + " guesses left");

		}
		if (guessleft == 0){
			println("You're completely hung");
			println("The word was:" + word);
			println("You lose");
		}	
	}  	
	/*
	 * Chooses a random word from the file
	 */
     private String choose(){
    	words = new HangmanLexicon();
    	int random = rgen.nextInt(0, (words.getWordCount())); 
    	String choosen = words.getWord(random);
    	return choosen;
    }
     /*
      * Hyphens the word
      */
	private String hypenedLetters() {
		String hyphens = "";
		for(int i = 0; i< word.length(); i++) {
			hyphens +="-";
		}
		return hyphens;
	}
	/*
	 * Checks for the choosen letter is it in the word or not 
	 */
	private void check() {
		if(word.indexOf(ch) == -1) {
			println("There are no " + ch + "'s in the word");
			guessleft--;
			incorrectLetters = incorrectLetters + ch;
			canvas.noteIncorrectGuess(incorrectLetters);
		}
		if(word.indexOf(ch) != -1) {
			println("The guess is correct.");
		}
		for(int i = 0; i < word.length(); i++) {
			if (ch == word.charAt(i)) {
				if (i > 0) {
					hypenWord = hypenWord.substring(0, i) + ch + hypenWord.substring(i + 1);
				}
				if(i == 0) {
					hypenWord = ch + hypenWord.substring(1);
				}
				canvas.displayWord(hypenWord);
			}
		}
	}
}
  







    







