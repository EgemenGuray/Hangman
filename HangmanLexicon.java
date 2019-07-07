/*
 * File: HangmanLexicon.java

 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;

import java.io.*;
import java.util.*;
import acm.util.*;

//Name: Egemen Guray
//Section Leader: Sherman Leung

public class HangmanLexicon{

	/** Declare Instance Variables you need here */
	private ArrayList <String> wordList = new ArrayList <String> ();
	
	/** HangmanLexicon constructor. Do any initialization of your lexicon here. */
	public HangmanLexicon(){
		try{
			BufferedReader words = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while(true){
				String line = words.readLine();
				if(line == null){ 
					break;
				}
				wordList.add(line);
			}
			words.close();
		}catch(IOException ex){
			throw new ErrorException(ex);
		}
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount(){
		return wordList.size();
	}

/** Returns the word at the specified index. */
	public String getWord(int index){
		return wordList.get(index);
	};
}
