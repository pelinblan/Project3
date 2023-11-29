public interface HashTable<T extends Comparable<T>, K> {
    public static void main(String[] args) {

    }

    public int insert(T id);

    public K remove(T sequenceID);

    public int hash(T sequenceID);

    public K search(T id);
    
    public K[] print();

}
