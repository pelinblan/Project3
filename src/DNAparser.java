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
        Scanner scanner = new Scanner(com);

//****************** Your Codes Here ******************/


    }

}
