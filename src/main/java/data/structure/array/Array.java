package data.structure.array;

import java.util.Arrays;

/**
 * 怎么去构造一个自定义Array的封装类
 */

public class Array {

    //我们不希望在使用Array类的用户得到[] data，以免进行修改data的数据及其size的大小，从而导致数据的不一致
    private int[] data;
    private int size;

    /**
     * @param capacity
     */
    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    /**
     * 固定创建初始的容量大小
     */
    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(int element) {
        if (size == data.length) {
            throw new IllegalArgumentException("addLast failed. Array is full");
        }
        data[size] = element;
        size++;
    }

    public int get(int index) {
        //防止插入数据的时候，数据超过范围
        if (size == data.length) {
            throw new IllegalArgumentException("addLast failed. Array is full");
        }
        //在数据插入的时候，指定index如果超出了插入的范围则是无效数据
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(" get failed, Require index  >= 0 or index < 0");
        }
        return data[index];
    }


    public void add(int index, int element) {
        //防止插入数据的时候，数据超过范围
        if (size == data.length) {
            throw new IllegalArgumentException("addLast failed. Array is full");
        }
        //在数据插入的时候，指定index如果超出了插入的范围则是无效数据
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(" add failed, Require index  >= 0 or index < 0");
        }
        /**
         *  数据移动过程
         * - 0 - 1 - 2 - 3 - 4 - 5 - 6 - 7  19   2   7   8   34
         *  19   2       7   8   34
         *  19   2   10  7   8   34
         *
         */
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = element;
        size++;
    }

    public void reset(int index, int element) {
        //防止插入数据的时候，数据超过范围
        if (size == data.length) {
            throw new IllegalArgumentException("addLast failed. Array is full");
        }
        //在数据插入的时候，指定index如果超出了插入的范围则是无效数据
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(" add failed, Require index  >= 0 or index < 0");
        }

        data[index] = element;

    }

    public int delete(int index) {
        //在数据插入的时候，指定index如果超出了插入的范围则是无效数据
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(" delete failed, Require index  >= 0 or index < 0");
        }
        int ret = data[index];
        for (int i = index; i < size; i++) {
            data[i] = data[i + 1];
        }
        //操作与否都不需要管，或者改都可以
        data[size] = 0;
        size--;
        return ret;

    }

    public boolean contains(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                return true;
            }
        }
        return false;
    }

    public int find(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public String toString() {
        int[] newDatas = new int[size];
        for (int i = 0; i < size; i++) {
            newDatas[i] = data[i];
        }

        return "Array{" +
                "data=" + Arrays.toString(newDatas) +
                ", size=" + size +
                ", capacity=" + getCapacity() +
                '}';
    }


    public static void main(String[] args) {
        Array array = new Array(100);
        for (int i = 0; i < 12; i++) {
            array.addLast(i);
        }
        array.add(0, 11);
        int delete = array.delete(2);
        System.out.println(delete);

//        System.out.println(array.get(30));
        System.out.println(array.find(11));

    }
}
