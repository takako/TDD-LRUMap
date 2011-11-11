package javateam2;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUMap<E, F> {
	private Integer maxSize;
	private HashMap<E, F> hashMap = new HashMap<E, F>();
	private ArrayList<E> LRUList = new ArrayList<E>();
	
	public LRUMap(Integer size){
		maxSize = size;
	}
	public Integer size(){
		return maxSize;
	}
	public void put(E key, F value){
		if(LRUList.size() >= maxSize)
		{
			hashMap.remove(LRUList.get(0));
			LRUList.remove(0);
		}
		LRUList.add(key);
		hashMap.put(key, value);
	}
	public F get(E key){
		LRUList.remove(0);
		LRUList.add(key);
		return hashMap.get(key);
	}

	
}
