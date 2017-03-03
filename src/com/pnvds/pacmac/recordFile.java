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
	
	static void writeFile(String name, String difficulty, int size, int score) throws FileNotFoundException, IOException {
		
		String filePath;
		switch (size){
			case 10: 
				filePath = "./10records.txt";
				break;
			case 15: 
				filePath = "./15records.txt";
				break;
			case 20: 
				filePath = "./20records.txt";
				break;
			case 25: 
				filePath = "./25records.txt";
				break;
			default:
				filePath = "./10records.txt";
		}		
		
		writeHead(filePath);
		writeRecord(name, difficulty, size, score, filePath);
//	    recordSort(filePath);	  

	}	
	
	static void readRecords(String size){
			
			BufferedReader br = null;
			FileReader fr = null;
			try {
				String sCurrentLine;
				 if (size.equals("10x10")){
					createFile("./10records.txt"); 
					br = new BufferedReader(new FileReader("./10records.txt"));
				 } else if(size.equals("15x15")){
					createFile("./15records.txt"); 
					br = new BufferedReader(new FileReader("./15records.txt"));
				 } else if(size.equals("20x20")){
					createFile("./20records.txt"); 
					br = new BufferedReader(new FileReader("./20records.txt"));
				 }else {
					createFile("./25records.txt"); 
					br = new BufferedReader(new FileReader("./25records.txt"));
				 }

				while ((sCurrentLine = br.readLine()) != null) {
					RecordsDataBase.model.addElement(sCurrentLine);
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
	
	static void createFile(String path) throws IOException{
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			file.createNewFile();
//			BufferedWriter bw = null;	
//			FileWriter fw = new FileWriter(file, true);
//	    	bw = new BufferedWriter(fw);
//	    	bw.write("No records a");
//	    	bw.close();
		}
	}
	
	
	static void writeHead(String path) throws IOException{
		BufferedWriter bw = null;
		//Specify the file name and path here
		File file = new File(path);
		FileWriter fw;
		
		if (!file.exists() && !file.isDirectory()) {
 		  	file.createNewFile();
 		  	try {
 				fw = new FileWriter(file, true);
 				bw = new BufferedWriter(fw);
 				bw.write("\t\tUSER\t\t\t\t\t\tDIFFICULTY\t\t\t\t\t\t\tMAZE\t\t\t\t\t\t\tSCORE\n");
 				bw.write("---------------------------------------\n");	
 			} catch (IOException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}	
		}		
	}
	
	static void writeRecord(String name, String difficulty, int size, int score, String path){
		BufferedWriter bw = null;
		//Specify the file name and path here
		File file = new File(path);
		FileWriter fw;
		try {
			fw = new FileWriter(file, true);
			 bw = new BufferedWriter(fw);
			 bw.write("\t\t"+name + "\t" + difficulty + "\t" + size +" cells\t" + score+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
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
				if(words.length>5){
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
			writeRecord(strings[1], strings[2], Integer.parseInt(strings[3]), Integer.parseInt(strings[5]), path); 	
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
			for(int j=i; j>0 && less(Integer.parseInt(records.get(j)[5]),  Integer.parseInt(records.get(j-1)[5])); j--)
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
		if (valueA < valueB)
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
		for (int i = 9; i < records.size(); i++) {
			records.remove(i);
		}
		deleteFile(path);
		toFile(path);
	}
}