package application;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashTableImp2 implements HashTable {

	private ArrayList<LinkedList<Word>> table;
	private int buckets;    

	public HashTableImp2(int buckets) {
		
	// create a open hash table with given number of buckets 
		this.table = new ArrayList<LinkedList<Word>>();
		this.buckets = setBuckets(buckets);
		for(int i=0; i<buckets; i++){
			this.table.add(null);
		}
	}
	
	public int setBuckets(int buckets) {
		double value = Math.log(buckets)/Math.log(2);
		if(value-Math.floor(value) == 0) {
			return buckets;
		}
		
		int r = (int) Math.ceil(value);
		return (int) Math.pow(2, r);
		
	}
	
	
	@Override
	public void insert(String key){
		key = key.toLowerCase();
		int hashvalue = this.hashvalue(key);
		
		LinkedList<Word> temp = this.table.get(hashvalue);
		
		if(temp == null){
			temp = new LinkedList<>();
			temp.add(new Word(key));
			table.add(hashvalue, temp); 
		} else {
			
			boolean isAvailable = false;
			for(int i=0; i<temp.size(); i++){
				if(temp.get(i).getName().equals(key)){
					temp.get(i).increment(); // -------need to add again to hashtable ?
					isAvailable = true;
					table.add(hashvalue, temp); 
				}
			}
			if(!isAvailable){
				temp.add(new Word(key));
				table.add(hashvalue, temp); 
			}
		}
		
	}
	
	@Override
	public int search(String key){
		key = key.toLowerCase();
		int count = 0;
		int hashvalue = this.hashvalue(key);
		LinkedList<Word> temp = this.table.get(hashvalue);

		if(!temp.isEmpty()){
			for(int i=0; i<temp.size(); i++){
				if(temp.get(i).getName().equals(key)){
					count = temp.get(i).getCount();
				}
			}
		}
		return count;

	}

	public int hashvalue(String key){
		int sumOfAscii = 0;
		for(int i=0; i<key.length(); i++){
			sumOfAscii += (int)key.charAt(i);
		}
		// if(key.equals("houses")){
		// 	System.out.println(sumOfAscii);
		// }
		double A = (Math.pow(5, 0.5)-1) / 2;
		
		int hash = (int) Math.floor(this.buckets*((sumOfAscii*A)%1));
		
		return hash;
		
	}
	
	public ArrayList<Integer> bucketStatus(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
//		for(int i=0; i<this.table.get(0).size(); i++) {
//			System.out.print(i+" "+ this.table.get(0).get(i).getName()+" ");
//		}
		
		for(int i=0; i<this.buckets; i++) {
//			System.out.print(this.table.get(i)==null ? 0 : this.table.get(i).size() + " ");
			list.add(this.table.get(i)==null ? 0 : this.table.get(i).size());
		}
		
		return list;
	}
	
}
