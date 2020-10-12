package application;

import java.util.ArrayList;
import java.util.LinkedList;

/*********************************************
 * 
 * Second hashing method : 
 * Multiplication method is used for generating 
 * the hash value 
 * 
 *********************************************/

public class HashTableImp2 implements HashTable {

	private ArrayList<LinkedList<Word>> table;
	private int buckets;    

	public HashTableImp2(int buckets) {
		
		// create a open hash table with given number of buckets 
		this.table = new ArrayList<LinkedList<Word>>();
		this.buckets = setBuckets(buckets);
		for(int i=0; i<this.buckets; i++){
			this.table.add(null);
		}
	}
	
	// determining the number of buckets
	public int setBuckets(int buckets) {
		// number of buckets should be a power of 2 -->   m = 2^p   -->   buckets = 2^value
		double value = Math.log(buckets)/Math.log(2);
		
		// number of buckets is already a power of 2
		if(value-Math.floor(value) == 0) {
			return buckets;
		}
		
		// taking the nearest integer value
		int r = (int) Math.ceil(value);
		
		// returning power of 2 as the number of buckets
		return (int) Math.pow(2, r);
		
	}
	
	
	@Override
	public void insert(String key){
		key = key.toLowerCase(); // convert to lower case
		int hashvalue = this.hashvalue(key); // generating hash value
		
		// get the linked list corresponding to hash value
		LinkedList<Word> temp = this.table.get(hashvalue);
		
		// if the linked list is not yet used
		if(temp == null){
			temp = new LinkedList<>();
			temp.add(new Word(key));
			table.set(hashvalue, temp); // updating the entry
		} else {
			// check if the key is already present, then incrementing the count
			boolean isAvailable = false;
			for(int i=0; i<temp.size(); i++){
				if(temp.get(i).getName().equals(key)){
					temp.get(i).increment();
					isAvailable = true;
					table.set(hashvalue, temp); // updating the entry
					break;
				}
			}
			// if the key is not present, add it
			if(!isAvailable){
				temp.add(new Word(key));
				table.set(hashvalue, temp); // updating the entry
			}
		}
	}
	
	@Override
	public int search(String key){
		key = key.toLowerCase(); // convert to lower case
		int count = 0;
		int hashvalue = this.hashvalue(key); // generating the hash value
		// get the linked list corresponding to hash value
		LinkedList<Word> temp = this.table.get(hashvalue);
		
		// getting the count of the given key
		if(!temp.isEmpty() && temp != null){
			for(int i=0; i<temp.size(); i++){
				if(temp.get(i).getName().equals(key)){
					count = temp.get(i).getCount();
					break;
				}
			}
		}
		return count;

	}
	
	/*
	 *  function for generating the hash value
	 *  - multiplication method
	 * */
	public int hashvalue(String key){
		int sumOfAscii = 0;
		// adding sum of ascii values of the string
		for(int i=0; i<key.length(); i++){
			sumOfAscii += (int)key.charAt(i);
		}
		
		// Optimal choice for "A" is taken as it is given in the text book
		double A = (Math.pow(5, 0.5)-1) / 2;
		
		// generating hash value
		int hash = (int) Math.floor(this.buckets*((sumOfAscii*A)%1));
		
		return hash;
		
	}
	
	// getting the size of the filled buckets in the hash table
	public ArrayList<Integer> bucketStatus(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<this.buckets; i++) {
			list.add(this.table.get(i)==null ? 0 : this.table.get(i).size());
		}		
		return list;
	}
	
} // end HashTableImp
