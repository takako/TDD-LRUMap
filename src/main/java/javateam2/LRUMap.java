package javateam2;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUMap {
	private Integer maxSize;
	private HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
	//private ArrayList<Integer> LRUList = new ArrayList<Integer>();
	public LRUMap(Integer size){
		maxSize = size;
	}
	public Integer size(){
		return maxSize;
	}
	public void put(Integer key, String value){
		hashMap.clear();
		hashMap.put(key, value);
	}
	public String get(Integer key){
		return hashMap.get(key);
	}

	
}
