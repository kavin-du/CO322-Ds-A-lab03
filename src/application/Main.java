package application;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class Main {

	static ArrayList<String> list = new ArrayList<>();
	public static ArrayList<Integer> bucket_status;


	public static void readFile(String name){
		try(Scanner scanner = new Scanner(Paths.get(name))){
			
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				String arr[] = line.split("[:,.;-]+|\\s|\\d");
				for(String word : arr){
					if(!word.isEmpty()){
						String truncated = word.replaceAll("[^a-zA-Z]+", "");
						// if(truncated.toLowerCase().equals("houses")){
						// 	System.out.println("houses");
						// }
						list.add(truncated.toLowerCase());
					} 
				}
			}

		} catch(Exception e){
			System.out.println(e);
		}
		// for(String word: list){
		// 	System.out.println(word);
		// }
		
	}
	public static void main(String args[]) {
		readFile("sample-text1.txt");

		HashTableImp table = new HashTableImp(200);

		for(String word : list){
			table.insert(word);
		}

//		System.out.println(table.search("Seventeenth"));
		
		bucket_status = table.bucketStatus();
//		for(int i=0; i<bucket_status.size(); i++) {
//			System.out.print(bucket_status.get(i)+" ");
//		}
		
		Chart.launch(Chart.class, args);
		
	}

}