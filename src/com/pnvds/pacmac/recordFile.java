package com.pnvds.pacmac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

public class recordFile {
	
	static ArrayList<String[]> records;
	
	static void writeFile(String name, String difficulty, String gameType, int size, int score) throws FileNotFoundException, IOException {
		
		String filePath = ("./"+difficulty+gameType+size+".txt");		
		writeHead(filePath);
		writeRecord(name, score, filePath);
	    recordSort(filePath);	  

	}	
	
	static void writeHead(String path) throws IOException{
	
		File file = new File(path);	
		if (!file.exists() && !file.isDirectory()) {
 		  	file.createNewFile();
 		  	BufferedWriter bw = null;
 			FileWriter fw = null;
 			try {
 				fw = new FileWriter(file, true);
 				bw = new BufferedWriter(fw);
 				bw.write("\t\tUSER\t\t\t\t\t\tSCORE\n");
 				bw.write("------------------\n");	
 			} catch (IOException e) {
 				e.printStackTrace();
 			}	finally {
 				if (bw != null)
 					bw.close();
 				if (fw != null)
 					fw.close();
 			}
		}	
		
	}
	
	static void writeRecord(String name, int score, String path) throws IOException{
		
		File file = new File(path);
		BufferedWriter bw = null;	
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
			 bw = new BufferedWriter(fw);
			 bw.write("\t\t"+name + "\t\t" + score+" points\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	finally {
			if (bw != null)
				bw.close();
			if (fw != null)
				fw.close();
		}
	}
	
	static void addToRecordsList(int size) throws IOException{	
		readRecord("easyStandard"+size+".txt", ScoresWindow.easyStandard);	
		readRecord("mediumStandard"+size+".txt", ScoresWindow.mediumStandard);
		readRecord("difficultStandard"+size+".txt",ScoresWindow.difficultStandard);
		readRecord("easyRandom"+size+".txt",ScoresWindow.easyRandom);
		readRecord("mediumRandom"+size+".txt",ScoresWindow.mediumRandom);
		readRecord("difficultRandom"+size+".txt",ScoresWindow.difficultRandom);
	}
	
	static void readRecord(String filePath, String[] arrayName) throws IOException{
		
		for (int j = 0; j < arrayName.length; j++) {
			arrayName[j]=null;
		}
		
		File file = new File(filePath);
		if (!file.exists() && !file.isDirectory()) {
 		  	file.createNewFile();
		}
		FileReader fr = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		int i=0;
		String currentLine;
		while ((currentLine = br.readLine()) != null) {
			arrayName[i]=currentLine;
			i++;		
		}
			
		if (br != null)
			br.close();
		if (fr != null)
			fr.close();	
		
		
	}
	
	
	
	private static void toList(String path){
		
		records = new ArrayList<String[]>();
		BufferedReader br = null;
		FileReader fr = null;
		try {
			String sCurrentLine;	
			br = new BufferedReader(new FileReader(path));	 

			while ((sCurrentLine = br.readLine()) != null) {
				String characters= "[\t .,;?!¡¿\'\"\\[\\]]+";
				String[] words = sCurrentLine.split(characters);
				if(words.length>3){
					records.add(words);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();

			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	private static void toFile(String path) throws IOException{
		writeHead(path); 
		for (String[] strings : records) {
			writeRecord(strings[1],Integer.parseInt(strings[2]), path); 	
		}    
	}	
	
	
	private static void deleteFile(String path){
		try{
    		File file = new File(path);
    		file.delete();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
	private static void sort(){
		int N = records.size();
		for(int i=1; i<N; i++){
			for(int j=i; j>0 && less(Integer.parseInt(records.get(j)[2]),  Integer.parseInt(records.get(j-1)[2])); j--)
			switchValues(records, j, j-1);	
		}	
	}
	
	/**
	   * This function compares two values to know what is less
	   * 
	   * @param valueA  
	   * @param valueB
	   * @return 
	   */
	private static boolean less(int valueA, int valueB){
		if (valueA > valueB)
			return 	true;
		else return false;
	}
	
	/**
	   * This function switches the values in positions 1 and 2 inside the array a
	   * 
	   * @param a array that contains the values
	   * @param position1 position of value 1
	   * @param position2 position of value 2
	   */
	private static void switchValues(ArrayList<String[]> a, int position1, int position2){
		String[] temp = a.get(position1);	
		a.set(position1,a.get(position2));
		a.set(position2,temp);
	}
	/**
	   * This function sorts a record list
	   * 
	   * 
	   * @return 
	 * @throws IOException 
	   */
	public static void recordSort(String path) throws IOException{
		toList(path);
		sort();
		if (records.size()>10){
			for (int i = 0; i < records.size()-10; i++) {
				records.remove(i);
			}
}		
		deleteFile(path);
		toFile(path);
	}
}