package application;
import java.util.ArrayList;
import java.util.LinkedList;

/*********************************************
 * 
 * CO322: Data structures and algorithms
 * 
 * Implementation of the hashTable
 *********************************************/


class HashTableImp implements HashTable {

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

    
	/* given the key return the number of times it was inserted 
     
	 * to the table 
     
	*/
    
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
		return sumOfAscii%this.buckets;
	}
	
	public ArrayList<Integer> bucketStatus(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		for(int i=0; i<this.table.get(0).size(); i++) {
			System.out.print(this.table.get(0).get(i).getName()+" ");
		}
		
		for(int i=0; i<this.buckets; i++) {
//			System.out.print(this.table.get(i)==null ? 0 : this.table.get(i).size() + " ");
			list.add(this.table.get(i)==null ? 0 : this.table.get(i).size());
		}
		
		return list;
	}
    



}// end HashTableImp 
