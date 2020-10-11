package application;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Author: Kavindu Chamith (E/16/057)
 * 
 * Design: Implementing a hash table
 * 
 * Date: 11/10/2020
 * 
 * */


class Main {
	
	// store the words read from the text file
	static ArrayList<String> list = new ArrayList<>();
	
	// store the number of words filled in the each bucket
	public static ArrayList<Integer> bucket_status1; // for 1st hash function
	public static ArrayList<Integer> bucket_status2; // for 2nd hash function


	public static void readFile(String name){
		try(Scanner scanner = new Scanner(Paths.get(name))){
			// read the file
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				// split words using regex pattern
				String arr[] = line.split("[:,.;-]+|\\s|\\d");
				for(String word : arr){
					if(!word.isEmpty()){ 
						
						// replacing characters like apostrophes( ' )
						String truncated = word.replaceAll("[^a-zA-Z]+", "");
						list.add(truncated.toLowerCase());
					} 
				}
			}

		} catch(Exception e){
			System.out.println(e);
		}
		
		// printing the list of words
		
//		 for(String word: list){
//		 	System.out.println(word);
//		 }
		
	}
	public static void main(String args[]) {
		// path for the text file to be read
		readFile("sample-text1.txt");
		
		int number_of_buckets = 250;
		
		HashTableImp table1 = new HashTableImp(number_of_buckets); // 1st hashing method
		HashTableImp2 table2 = new HashTableImp2(number_of_buckets); // 2nd hashing method

		for(String word : list){
			table1.insert(word);
			table2.insert(word);
		}
		
		// searching for a word
		System.out.println(table1.search("Seventeenth"));
		
		bucket_status1 = table1.bucketStatus();
		bucket_status2 = table2.bucketStatus();
		
		
		
		// printing bucket status as a chart
		// un-comment below line if you have installed javafx inside eclipse, otherwise this will not work
		
		Chart.launch(Chart.class, args);
		
	}

}