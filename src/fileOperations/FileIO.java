package fileOperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileIO {
	private ArrayList<ArrayList<String>> lines;
	
	public FileIO() {
		lines= new ArrayList<ArrayList<String>>();
	}
	
	public ArrayList<ArrayList<String>> readCsv(String filename) { //filename = teamlist
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
}