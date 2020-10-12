package application;
import java.util.ArrayList;
import java.util.LinkedList;

/*********************************************
 * 
 * First hashing method :
 * Division method is used for generating 
 * the hash value 
 * 
 *********************************************/


class HashTableImp implements HashTable {
	
	// array of linked lists to store the words
	private ArrayList<LinkedList<Word>> table;
	private int buckets;    

	public HashTableImp(int buckets) {
		
		// create a open hash table with given number of buckets 
		this.table = new ArrayList<LinkedList<Word>>();
		this.buckets = buckets;
		for(int i=0; i<buckets; i++){
			this.table.add(null);
		}
	}
	
	
	/* insert the given key to a open hash tabled.     
	 * With each key you have a count of how many times it     
	 * was inserted 
     
	 */
   
	@Override
	public void insert(String key){
		key = key.toLowerCase(); // convert string to lowercase
//		if(key.equals("shall")) System.out.println(key);
		int hashvalue = this.hashvalue(key); // generating hash value
		
		// get the relevant linked list for the given hash value
		LinkedList<Word> temp = this.table.get(hashvalue); 
		
		// if the linked list is not yet used
		if(temp == null){
			temp = new LinkedList<>();
			temp.add(new Word(key));
			table.set(hashvalue, temp); // updating the entry
		} else {
			// if the key is already present, then increment the value
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

    
	/* given the key return the number of times it was inserted 
     
	 * to the table 
     
	*/
    
	@Override
	public int search(String key){
		key = key.toLowerCase(); // convert key to lower case
		int count = 0;
		int hashvalue = this.hashvalue(key); // generate the hash value
		// get the linked list corresponding to hash value
		LinkedList<Word> temp = this.table.get(hashvalue);

		if(!temp.isEmpty() && temp != null){
			// linear searching for the given key
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
	 *  function for generating hash value
	 * 	-division method
	 * */
	public int hashvalue(String key){
		int sumOfAscii = 0;
		// generating the sum of ascii values of the string
		for(int i=0; i<key.length(); i++){
			sumOfAscii += (int)key.charAt(i);
		}
		// return the remainder of "sum of ascii values" when divided by number of buckets
		return sumOfAscii%this.buckets;
	}
	
	// getting the size of filled buckets in the hash table
	public ArrayList<Integer> bucketStatus(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<this.buckets; i++) {
			list.add(this.table.get(i)==null ? 0 : this.table.get(i).size());
		}		
		return list;
	}
	
}// end HashTableImp 
