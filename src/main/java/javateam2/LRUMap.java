package javateam2;

import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;

public class LRUMap<E, F> {
	private int maxSize;
	private long discardTime; //�~���b
	private Date lastAccess = null;
	private HashMap<E, F> hashMap = new HashMap<E, F>();
	// �L�[���Ǘ����郊�X�g �擪����g���Ă��Ȃ����ɕ���ł���
	private ArrayList<E> LRUList = new ArrayList<E>();

	public LRUMap(int size, long time){
		if(time < 0){
			throw new IllegalArgumentException("�������Ԃ�0�b�ȏ�:input="+time);
		}
		discardTime = time * 1000;
		this.lastAccess = new Date();
		if(size < 1){
			throw new IllegalArgumentException("�T�C�Y��1�ȏ�:input="+size);
		}
		maxSize = size;
	}

	public LRUMap(int size){
		if(size < 1){
			throw new IllegalArgumentException("�T�C�Y��1�ȏ�:input="+size);
		}
		maxSize = size;
		discardTime = 0;
	}
	public int size(){
		discardOld();
		return maxSize;
	}
	public void put(E key, F value){
		discardOld();
		if(LRUList.indexOf(key) != -1){
			// �L�[���d�������ꍇ�͏㏑��
			updateKey(key,value);
		}
		else{
			putNewKey(key,value);
		}
		LRUList.add(key);
		hashMap.put(key, value);
	}
	public F get(E key){
		//���ݎ������Ō�̃A�N�Z�X����discardTime�𒴂��Ă���Έ�̂Ă�
		discardOld();

		if(hashMap.get(key) == null){
			// �}�b�v�ɂȂ��L�[���w�肳�ꂽ�ꍇ��null��Ԃ�
			return null;
		}
		LRUList.remove(LRUList.indexOf(key));
		LRUList.add(key);
		return hashMap.get(key);
	}

	// ���Ԑ�����������Â��̂��̂Ă�
	private void discardOld() {
		if (this.discardTime != 0) {
			Date now = new Date();
			long elapsedTime = now.getTime() - this.lastAccess.getTime(); // �o�ߎ��ԁi�~���b�j
			//System.out.println("elapsedTime = "+elapsedTime); //�f�o�b�O�p
			for (int i = 1; elapsedTime >= i * this.discardTime; i++) {
				this.remove(this.LRUList.get(0));
				//System.out.println("remove "+i); //�f�o�b�O�p
			}
			this.lastAccess = now;
		}
	}

	private void putNewKey(E key, F value){
		if(LRUList.size() >= maxSize)
		{
			// ��Ԏg���Ă��Ȃ��L�[�����X�g�������
			remove(LRUList.get(0));
		}

	}
	private void updateKey(E key, F value){
		remove(key);
	}

	private void remove(E key){
		// ���X�g�ƃ}�b�v����L�[������
		// ���X�g�Ɏw��L�[��������Ή������Ȃ�
		if(LRUList.indexOf(key) != -1){
			hashMap.remove(key);
			LRUList.remove(LRUList.indexOf(key));
		}
	}

	public void resize(int newSize) {
		discardOld();
		if (newSize < maxSize) {
			for (int diffSize = maxSize - newSize; diffSize > 0; diffSize--) {
				remove(LRUList.get(0));
			}
		}
		maxSize = newSize;
	}
}