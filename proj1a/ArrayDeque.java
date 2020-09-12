/**Implemented in circular way*/
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;


    /**Create an empty array deque*/
    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextfirst = 0;
        nextlast = 1;
        size = 0;
    }

    private int plusindex(int x){
        return (x + 1) % items.length;
    }

    private int minusindex(int x){
        return (x - 1 + items.length) % items.length;
    }

    /**resize the array
     * ! :新数组的nextfirst为新数组的最后一个也就是capacity-1;
     * ! :新数组的nextlast为旧数组的size的值，因为旧数组到size-1就不够了*/
    private void resize(int capacity) {
        T[] newitems = (T[]) new Object[capacity];
        /**Line31: the index of the first item in original deque
         * since "nextfirst" is the one ahead of the first item, so we plusindex*/
        int oldindex = plusindex(nextfirst);
        for (int i = 0; i < size; i++){
            newitems[i] = items[oldindex];
            oldindex = plusindex(oldindex);
        }
        items = newitems;
        /**The last one of the new array, also the former one ahead of 0 index*/
        nextfirst = capacity - 1;
        nextlast = size;
    }

    public void addFirst(T item){
        if (size == items.length){
            resize(size * 2);
        }
        items[nextfirst] = item;
        nextfirst = minusindex(nextfirst);
        size += 1;
    }


    public void addLast(T item){
        if (size == items.length){
            resize(size * 2);
        }
        items[nextlast] = item;
        nextlast = plusindex(nextlast);
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    public int size(){
        return size;
    }

    /**prints the items in the deque from first to last,
     * separated by a space*/
    public void printDeque(){
        int i = plusindex(nextfirst);
        for (int j = 0; j < size; j++){
            System.out.println(items[i]);
            i = plusindex(i);
        }
    }

    public T removeFirst(){
        if (size == 0){
            return null;
        }
        T a = items[plusindex(nextfirst)];
        items[plusindex(nextfirst)] = null;
        nextfirst = plusindex(nextfirst);
        size -= 1;
        if (items.length >= 16 && size < (items.length / 4)){
            resize(items.length / 2);
        }
        return a;
    }

    public T removeLast(){
        if (size == 0){
            return null;
        }
        T a = items[minusindex(nextlast)];
        items[minusindex(nextlast)] = null;
        nextlast = minusindex(nextlast);
        size -= 1;
        if (items.length >= 16 && size < (items.length / 4)){
            resize(items.length / 2);
        }
        return a;
    }

    public T get(int index){
        if (index >= size){
            return null;
        }
        int startingpoint = plusindex(nextfirst);
        /**since the first one means the "0" algebra place of the array
         * and we do not know where the array starts, maybe the middle or front or end
         * so if we want to get the 'index' item of the array
         * we have to let index plus the first one*/
        return items[(startingpoint + index) / items.length];
    }


}