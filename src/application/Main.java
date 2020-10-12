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
	public static void main(String args[]) throws Exception {
		// path for the text file to be read
		readFile("sample-text1.txt");
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of buckets: ");
		int number_of_buckets = 0;
		try {
			number_of_buckets = Integer.valueOf(scanner.nextLine());
			if(number_of_buckets <= 0) {
				throw new Exception("Enter valid number for buckets");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		HashTableImp table1 = new HashTableImp(number_of_buckets); // 1st hashing method
		HashTableImp2 table2 = new HashTableImp2(number_of_buckets); // 2nd hashing method

		for(String word : list){
			table1.insert(word);
			table2.insert(word);
		}
		
		// searching for a word 
		String word;
		System.out.println("Enter a word to search or blank to skip: ");
		word = scanner.nextLine();
		if(!word.isBlank()) {
			System.out.println("Count of the word in HashTable 1: "+table1.search(word));
			System.out.println("Count of the word in HashTable 2: "+table2.search(word));
		}
				
		bucket_status1 = table1.bucketStatus();
		bucket_status2 = table2.bucketStatus();
		
		// calculating min and max entries in a bucket
		int max1 = 0;
		int min1 = bucket_status1.get(0);
		
		int max2 = 0;
		int min2 = bucket_status2.get(0);
		
		for(int i=0; i<bucket_status1.size(); i++) {
			if(bucket_status1.get(i) > max1) max1 = bucket_status1.get(i);
			if(bucket_status1.get(i) < min1) min1 = bucket_status1.get(i);
		}
		for(int i=0; i<bucket_status2.size(); i++) {
			if(bucket_status2.get(i) > max2) max2 = bucket_status2.get(i);
			if(bucket_status2.get(i) < min2) min2 = bucket_status2.get(i);
		}
		
		// finding average values of each hash function
		double fx1 = 0;
		double f1 = (list.size())*(list.size()-1)/2.0;
		double fx2 = 0;
		double f2 = (list.size())*(list.size()-1)/2.0;
		
		for(int i=0; i<bucket_status1.size(); i++) {
			fx1 += (i*bucket_status1.get(i)); 
		}
		for(int i=0; i<bucket_status2.size(); i++) {
			fx2 += (i*bucket_status2.get(i)); 
		}
		
		double average1 = fx1/f1;
		double average2 = fx2/f2;
		
		// calculating standard deviation
		double temp1 = 0;
		for(int i=0; i<bucket_status1.size(); i++) {
			temp1 += (bucket_status1.get(i)*i*i); 
		}
		temp1 -= (Math.pow(average1, 2)*list.size());
		double std_deviation1 = Math.pow((temp1/(list.size()-1)), 0.5);
		
		double temp2 = 0;
		for(int i=0; i<bucket_status2.size(); i++) {
			temp2 += (bucket_status2.get(i)*i*i); 
		}
		temp2 -= (Math.pow(average2, 2)*list.size());
		double std_deviation2 = Math.pow((temp2/(list.size()-1)), 0.5);
		
		System.out.println("\nHash function 1:");
		System.out.printf("Minimum number of entries in a bucket: %d\nMax number of entries in a bucket: %d\n", min1, max1);
		System.out.printf("Average: %f\n",average1);
		System.out.printf("Standard deviation: %f\n\n", std_deviation1);
		
		System.out.println("Hash function 2:");
		System.out.printf("Minimum number of entries in a bucket: %d\nMax number of entries in a bucket: %d\n", min2, max2);
		System.out.printf("Average: %f\n",average2);
		System.out.printf("Standard deviation: %f\n\n", std_deviation2);
		
		// printing bucket status as a chart
		// un-comment below line if you have installed javafx inside eclipse, otherwise this will not work
		
		Chart.launch(Chart.class, args);
		
	}

}