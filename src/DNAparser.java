import  java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DNAparser {

    private File com;
    private HashFunction hashTable1;

    public DNAparser(File c, int size) throws IOException {
        com = c;
        this.hashTable1 = new HashFunction(size);
    }

    public boolean parse() throws IOException {
        try (Scanner scanner = new Scanner(com)) {
            while (scanner.hasNextLine()) {
                //Each line contains a sequence ID with space
                String[] line = scanner.nextLine().split(" ");
                if (line.length == 2) {
                    String sequenceID = line[0];
                    String sequence = line[1];
                    //insert sequence into the hashtable
                    hashTable1.insert(sequenceID, sequence);
                } else {
                    System.out.println("Invalid line format: " + Arrays.toString(line));
                    return false;
                }
            }
        }
        return true;
    }
}
