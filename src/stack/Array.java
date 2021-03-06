package stack;

public class Array<E> {
    private E[] data;
    private int size;

    /**
     * 构造函数 传入数组容量capacity构造数组
     * @param capacity 数组容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * 构造函数 无参数
     * 默认容量为10
     */
    public Array() {
        this(10);
    }

    /**
     * 获取数组元素个数
     * @return 数组元素个数
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取数组容量
     * @return 数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 判断数组是否为空
     * @return true: 数组为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向数组末尾添加元素
     * @param element 新增元素
     */
    public void addLast(E element) {
        // 复用add()
        add(size, element);
    }

    public void addFirst(E element) {
        // 复用add()
        add(0, element);
    }

    /**
     * 向数组的指定位置插入元素
     * @param index 元素位置
     * @param element 插入元素
     */
    public void add(int index, E element) {
        // 数组扩容
        if(size == data.length) {
            resize(2 * data.length);
        }
        if(0 > index || index > size) {
            throw new IllegalArgumentException("AddLast failed. Array is full.");
        }
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    /**
     * 获取index位置的元素
     * @param index 索引位置
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回数组最后一个元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 返回数组第一个元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 设置index位置的元素为element
     * @param index 索引位置
     * @param element 元素值
     */
    public void set(int index, E element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        }
        data[index] = element;
    }

    /**
     * 查看数组中是否包含元素element
     * @param element 查找元素
     * @return
     */
    public boolean contains(E element) {
        for (E d : data) {
            if(element.equals(d))
                return true;
        }
        return false;
    }

    /**
     * 查找元素e在数组中的索引
     * @param element 查找元素
     * @return 索引 没有找到返回-1
     */
    public int find(E element) {
        for(int i = 0; i < size; i ++) {
            if(element.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 从数组中删除元素 并返回被删除元素
     * @param index 索引位置
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }
        E temp = data[index];
        for(int i = index; i < size - 1; i ++) {
            data[i] = data[i + 1];
        }
        size--;
        data[size] = null; // loitering objects != memory leak
        if (size == data.length / 4 &&  data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return temp;
    }

    /**
     * 从数组中删除第一个元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 从数组中删除最后一个元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * 从数组中删除元素element
     * @param element 删除元素
     */
    public void removeElement(E element) {
        int index = find(element);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i ++) {
            res.append(data[i]);
            if (i != size - 1) {
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    /**
     * 扩容/缩容数组
     * @param newCapacity 数组新的容量
     */
    private void resize(int newCapacity) {
        E[] tempArray = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i ++) {
            tempArray[i] = data[i];
        }
        data = tempArray;
    }
}
