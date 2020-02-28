package map;

public class LinkedListMap<K, V> implements Map<K, V> {
    private class Entry {
        public K key;
        public V value;
        public Entry next;
        public Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Entry(K key) {
            this(key, null, null);
        }

        public Entry() {
            this(null, null, null);
        }
    }

    private Entry dummyHead;
    private int size;

    public LinkedListMap() {
        this.dummyHead = new Entry();
        this.size = 0;
    }

    private Entry getEntry(K key) {
        Entry entry = dummyHead.next;
        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Entry entry = getEntry(key);
        if (entry == null) {
            dummyHead.next = new Entry(key, value, dummyHead.next);
            size ++;
        } else {
            entry.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Entry prev = dummyHead;
        while (prev.next != null) {
            if(prev.next.key.equals(key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Entry delEntry = prev.next;
            prev.next = delEntry.next;
            delEntry.next = null;
            size --;
            return delEntry.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getEntry(key) != null;
    }

    @Override
    public V get(K key) {
        Entry entry = getEntry(key);
        return entry == null ? null : entry.value;
    }

    @Override
    public void set(K key, V newValue) {
        Entry entry = getEntry(key);
        if (entry == null) {
            throw new IllegalArgumentException("key doesn't exist!");
        } else {
            entry.value = newValue;
        }
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }
}
