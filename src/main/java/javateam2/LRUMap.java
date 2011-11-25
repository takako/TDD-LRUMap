package javateam2;

import java.util.ArrayList;
import java.util.HashMap;

public class LRUMap<E, F> {
	private Integer maxSize;
	private HashMap<E, F> hashMap = new HashMap<E, F>();
	// �L�[���Ǘ����郊�X�g �擪����g���Ă��Ȃ����ɕ���ł���
	private ArrayList<E> LRUList = new ArrayList<E>();
	
	public LRUMap(Integer size) throws IllegalArgumentException{
		if(size < 1){
			throw new IllegalArgumentException("�T�C�Y��1�ȏ�");
		}
		maxSize = size;
	}
	public Integer size(){
		return maxSize;
	}
	public void put(E key, F value){
		if(LRUList.size() >= maxSize)
		{
			// ��Ԏg���Ă��Ȃ��L�[�����X�g�������
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
