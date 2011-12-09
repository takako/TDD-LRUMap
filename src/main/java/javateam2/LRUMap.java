package javateam2;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUMap<E, F> {
	private int maxSize;
	private int discardTime; //秒
	private HashMap<E, F> hashMap = new HashMap<E, F>();
	// キーを管理するリスト 先頭から使われていない順に並んでいる
	private ArrayList<E> LRUList = new ArrayList<E>();
	
	public LRUMap(int size, int time){
		if(time < 0){
			throw new IllegalArgumentException("消去時間は0秒以上:input="+time);
		}
		discardTime = time;
		if(size < 1){
			throw new IllegalArgumentException("サイズは1以上:input="+size);
		}
		maxSize = size;
	}
	public LRUMap(int size){
		if(size < 1){
			throw new IllegalArgumentException("サイズは1以上:input="+size);
		}
		maxSize = size;
		discardTime = 0;
	}
	public int size(){
		return maxSize;
	}
	public void put(E key, F value){
		if(LRUList.indexOf(key) != -1){
			// キーが重複した場合は上書き
			updateKey(key,value);
		}
		else{
			putNewKey(key,value);
		}
		LRUList.add(key);
		hashMap.put(key, value);
	}
	public F get(E key){
		if(hashMap.get(key) == null){
			// マップにないキーを指定された場合はnullを返す
			return null;
		}
		LRUList.remove(LRUList.indexOf(key));
		LRUList.add(key);
		return hashMap.get(key);
	}
	
	private void putNewKey(E key, F value){
		if(LRUList.size() >= maxSize)
		{
			// 一番使われていないキーをリストから消す
			remove(LRUList.get(0));
		}

	}
	private void updateKey(E key, F value){
		remove(key);
	}
	
	private void remove(E key){
		// リストとマップからキーを消す
		// リストに指定キーが無ければ何もしない
		if(LRUList.indexOf(key) != -1){
			hashMap.remove(key);
			LRUList.remove(LRUList.indexOf(key));
		}
	}	

	public void resize(int newSize) {
		if (newSize < maxSize) {
			for (int diffSize = maxSize - newSize; diffSize > 0; diffSize--) {
				remove(LRUList.get(0));
			}
		}
		maxSize = newSize;
	}
}
