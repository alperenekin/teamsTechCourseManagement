package fileOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	
	public ArrayList<ArrayList<String>> readCsv(String filename) { //filename = teamlist
		 ArrayList<ArrayList<String>> lines = new ArrayList<ArrayList<String>>();
		 File file =new File(filename+".csv");
		 String[] values;
		 try {
				Scanner inputStream=new Scanner(file);
				while(inputStream.hasNext()) {
					ArrayList<String> line= new ArrayList<String>();
					String data=inputStream.nextLine(); 
					values=data.split(",");// the line is splitted
					for(String value:values) {
						line.add(value);
					}
					lines.add(line);
				} 
				inputStream.close();
		 }
		 
		 catch (FileNotFoundException e) {
					e.printStackTrace();
		}	
		return lines;
	}
	
	public void replaceLines(String oldLineItem, String oldLineItem2,String newLine,String filename) {
	    try {
	        BufferedReader file = new BufferedReader(new FileReader(filename+".csv"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;
	        while ((line = file.readLine()) != null) {
	        	if(oldLineItem2 != null) { // to prevent duplicate names..
	        		if(line.contains(oldLineItem) && line.contains(oldLineItem2)) {
		        		line = newLine;
		        	}	
	        	}
	        	else{
	        		if(line.contains(oldLineItem)) {
		        		line = newLine;
		        	}
	        	}
	            inputBuffer.append(line);
	            inputBuffer.append('\n');
	        }
	        file.close();

	        // write the new string with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream(filename +".csv");
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	}
	
	public void deleteLines(String oldLineItem, String filename) {
	    try {
	        BufferedReader file = new BufferedReader(new FileReader(filename+".csv"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;
	        while ((line = file.readLine()) != null) {
	        	if(line.contains(oldLineItem)) {
		        }else {
		            inputBuffer.append(line);
		            inputBuffer.append('\n');
		        }
	        	
	        }
	        file.close();

	        // write the new string with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream(filename +".csv");
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Problem reading file.");
	    }
	}
	
	public void addLine(String newLine, String filename) {
		 try {
		        BufferedReader file = new BufferedReader(new FileReader(filename+".csv"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;
		        while ((line = file.readLine()) != null) {
		        	inputBuffer.append(line);
			        inputBuffer.append('\n');  	
		        }
		        inputBuffer.append(newLine);
		        inputBuffer.append('\n');  	
		        file.close();

		        // write the new string with the replaced line OVER the same file
		        FileOutputStream fileOut = new FileOutputStream(filename +".csv");
		        fileOut.write(inputBuffer.toString().getBytes());
		        fileOut.close();

		    } catch (Exception e) {
		        System.out.println("Problem reading file.");
		    }
	}
}