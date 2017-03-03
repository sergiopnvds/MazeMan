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
	
	static void writeRecord(String name, String difficulty, int size, int score) throws FileNotFoundException, IOException {
		
		 BufferedWriter bw = null;
		 boolean newFile = false;
		 String newLine1 = "\t\tUSER\t\t\t\t\t\tDIFFICULTY\t\t\t\t\t\t\tMAZE\t\t\t\t\t\t\t\tSCORE\n";
		 String newLine2 = "---------------------------------------\n";
		 
		
	     try {
	    	 String mycontent = ("\t\t"+name + "\t\t\t\t\t\t\t\t" + difficulty + "\t\t\t\t\t\t\t\t" + size +" cells\t\t\t\t\t\t\t\t" + score+"\n");
	    	  //Specify the file name and path here
	    	 File file;
	    	 if (size == 10){
	    		 file = new File("./10records.txt");
			 } else if(size == 15){
				 file = new File("./15records.txt");
			 } else if(size == 20){
				 file = new File("./20records.txt");
			 }else {
				 file = new File("./25records.txt");
			 }
			 
	    	  /* This logic will make sure that the file 
	    	   * gets created if it is not present at the
	    	   * specified location*/
	    	  if (!file.exists() && !file.isDirectory()) {
	    		  	file.createNewFile();
	    		  	newFile=true;
	    	  }

	    	  FileWriter fw = new FileWriter(file, true);
	    	  bw = new BufferedWriter(fw);
	    	  if(newFile) {
	    		  bw.write(newLine1);
	    		  bw.write(newLine2);
	    		  newFile=false;
	    	  }
	    	  bw.write(mycontent);
	    	  
	    	  if (size == 10){
		    		recordSort("./10records.txt");
				 } else if(size == 15){
					 recordSort("./15records.txt");
				 } else if(size == 20){
					 recordSort("./20records.txt");
				 }else {
					 recordSort("./25records.txt");
				 }
	     } 
	     catch (IOException ioe) {
		   ioe.printStackTrace();
	     }
		finally { 
		   try {
		      if(bw!=null)
			 bw.close();
		   }
		   catch(Exception ex){
		    }
		}
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
	
	
	
	private static void toFile(String path){
		
		BufferedWriter bw = null;
		 boolean newFile = false;
		 String newLine1 = "\t\tUSER\t\t\t\t\t\tDIFFICULTY\t\t\t\t\t\t\tMAZE\t\t\t\t\t\t\t\tSCORE\n";
		 String newLine2 = "---------------------------------------\n";
		 
		for (String[] strings : records) {
			 try {
		    	 String mycontent = ("\t\t"+ strings[1] + "\t\t\t\t\t\t\t\t" + strings[2] + "\t\t\t\t\t\t\t\t" + strings[3] +" cells\t\t\t\t\t\t\t\t" + strings[5]+"\n");
		    	  //Specify the file name and path here
		    	 File file = new File(path);
				
				 
		    	  /* This logic will make sure that the file 
		    	   * gets created if it is not present at the
		    	   * specified location*/
		    	  if (!file.exists() && !file.isDirectory()) {
		    		  	file.createNewFile();
		    		  	newFile=true;
		    	  }

		    	  FileWriter fw = new FileWriter(file, true);
		    	  bw = new BufferedWriter(fw);
		    	  if(newFile) {
		    		  bw.write(newLine1);
		    		  bw.write(newLine2);
		    		  newFile=false;
		    	  }
		    	  bw.write(mycontent);
		     } 
		     catch (IOException ioe) {
			   ioe.printStackTrace();
		     }
			finally { 
			   try {
			      if(bw!=null)
				 bw.close();
			   }
			   catch(Exception ex){
			    }
			}
			
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
	   */
	public static void recordSort(String path){
		toList(path);
		sort();
		for (int i = 9; i < records.size(); i++) {
			records.remove(i);
		}
		deleteFile(path);
		toFile(path);
	}
}