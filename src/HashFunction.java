public class HashFunction implements HashTable<String, HashObject> {
    private HashObject[] hashes;

    // Constructor
    public HashFunction(int hashSize) {
        this.hashes = new HashObject[hashSize];
    }

    // using folding on a string, summed 4 bytes at a time
    private long sfold(String s, int M) {
        long sum = 0, mul = 1;
        for(int i = 0; i < s.length(); i++){
            mul =  (i % 4 == 0) ? 1 : mul * 256;
            sum += s.charAt(i) * mul;
        }
        return (int) (Math.abs(sum) % M);
    }


    public int hash(String id) {
        return (int) sfold(id, hashes.length);
    }

    public int insert(String id, String seq) {
        HashObject hashObject = new HashObject(id, seq);
        int hashValue = hash(id);

        for (int i = 0; i < hashes.length; i++) {
            int index = (hashValue + i) % hashes.length;

            if (hashes[index] == null) {
                hashes[index] = hashObject;
                return index; // successful, return the index
            }
        }

        //can't insert
        System.out.println("Error: Unable to insert, table is full.");
        return -1;
    }


    public HashObject remove(String id) {
        int hashValue = hash(id);
        int indexStart = hashValue;

        do {
            if (hashes[hashValue] != null && hashes[hashValue].getId().equals(id)) {
                HashObject removedObject = hashes[hashValue];
                hashes[hashValue] = null;
                return removedObject; // found and removed
            }

            hashValue = (hashValue + 1) % hashes.length; // Linear probing pos = (home + p(k, i)) % M

        } while (hashValue != indexStart);

        // HashObject not found
        System.out.println("Error: Sequence not found.");
        return null;
    }


    public HashObject[] print() {
        int notNull = 0;

      //if the object is not empty, count it
        for (HashObject hashObject : hashes) {
            if (hashObject != null) {
                notNull++;
            }
        }

        HashObject[] result = new HashObject[notNull];
        int index = 0;

        //create result array
        for (HashObject hashObject : hashes) {
            if (hashObject != null) {
                result[index++] = hashObject;
            }
        }

        return result;
    }

    public HashObject search(String id) {
        int hashValue = hash(id);
        int initialIndex = hashValue;

        do {
            if (hashes[hashValue] != null && hashes[hashValue].getId().equals(id)) {
                return hashes[hashValue]; //found the object
            }

            hashValue = (hashValue + 1) % hashes.length; // Linear probing

        } while (hashValue != initialIndex);

        //object not found
        return null;
    }
}
