package javateam2;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUMap<E, F> {
	private Integer maxSize;
	private HashMap<E, F> hashMap = new HashMap<E, F>();
	// キーを管理するリスト 先頭から使われていない順に並んでいる
	private ArrayList<E> LRUList = new ArrayList<E>();
	
	public LRUMap(Integer size) throws IllegalArgumentException{
		if(size < 1){
			throw new IllegalArgumentException("サイズは1以上");
		}
		maxSize = size;
	}
	public Integer size(){
		return maxSize;
	}
	public void put(E key, F value){
		if(LRUList.size() >= maxSize)
		{
			// 一番使われていないキーをリストから消す
			hashMap.remove(LRUList.get(0));
			LRUList.remove(0);
		}
		LRUList.add(key);
		hashMap.put(key, value);
	}
	public F get(E key){
		LRUList.remove(LRUList.indexOf(key));
		LRUList.add(key);
		return hashMap.get(key);
	}

	
}
