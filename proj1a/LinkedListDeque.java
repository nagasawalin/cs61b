/**This LinkedListDeque is based on double ended queue,
 * which accepts generic types.
 * All the method should follow "Deque API" described in
 * https://sp18.datastructur.es/materials/proj/proj1a/proj1a#the-deque-api
 * The amount of memory that this program uses at any given time must be
 * proportional to the number of items.*/

/**I do this part based on the idea of circular sentinel approaches*/

public class LinkedListDeque <T> {
    private int size;

    private class itemNode{
        private T item;
        private itemNode pre;
        private itemNode next;

        public itemNode(T item, itemNode pre, itemNode next){
            this.item = item;
            this.pre = pre;
            this.next = next;
        }
    }
    /**why sentinel should be outside the itemNode Class???*/
    private itemNode sentinel;


    /**create an empty List and a sentinel node
     * I forget to make the list circular after set the default value*/
    public LinkedListDeque(){
        sentinel = new itemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.pre = sentinel;
        size = 0;
    }


    /**Adds an item of type T to the front of the deque*/
    public void addFirst(T item){

        sentinel.next = new itemNode(item, sentinel, sentinel.next);
        sentinel.next.next.pre = sentinel.next;
        size += 1;
    }

    /**Adds an item of type T to the back of the deque*/
    public void addLast(T item){
        sentinel.pre.next = new itemNode(item, sentinel.pre, sentinel);
        sentinel.pre = sentinel.pre.next;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    /**Prints the items in the deque from first to last, separated by space*/
    public void printDeque(){
        itemNode p = sentinel.next;
        while(p != null && p!= sentinel){
            System.out.println(p.item + " ");
            p = p.next;
        }
    }

    /**Removes and returns the item at the front of the deque.
     * If no such item exists, returns null*/
    public T removeLast(){
        if (sentinel.next == sentinel){
            return null;
        }
        T a = sentinel.pre.item;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        /**last line of code changes the arrow form the last node to sentinel node
         * to the arrow from the second last node pointing to sentinel node;
         * So the back and forth arrow between last and the second last node,
         * only remains the one pointing from the last.pre to the second last node,
         * we need to change this arrow in the next line*/
        size -= 1;
        return a;
    }

    public T removeFirst(){
        if (sentinel.next == sentinel){
            return null;
        }
        T a = sentinel.next.item;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return a;
        /*itemNode rFirst = sentinel.next;
        rFirst.item = null;
        rFirst.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        T x = sentinel.next.item;
        return x;*/
    }


    public T get(int index){
        itemNode p = sentinel.next;
        if (index > size - 1){
            return null;
        }
        for (int i = 0; i < index; i++){
            p = p.next;
        }
        return p.item;
    }

    /**getRecursive方法无法递归
     * 因为没有直接访问itemNode,也就是所传参数没有itemNode
     * 不能产生对链表中节点的依次寻址*/
    public T getRecursive(int index){
        //itemNode p = sentinel.next;
        if(index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);

    }

    private T getRecursiveHelper(itemNode p, int i){
        if (i == 0){
            return p.item;
        }
        return getRecursiveHelper(p.next, i--);
    }
}