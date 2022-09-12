import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;

public class DNAFile {
    //Attributes
    private final File dnaFile;
    private final ArrayList<String> genes;

    //Constructors
    DNAFile(String path) throws InvalidPathException,IOException {
        dnaFile = new File(path);
        if (!dnaFile.isFile()){
            throw new IOException("this is not a file");
        }
        genes = readGenes();
        
    }
    //Main test
    public static void main(String[] args){
        try {
            new DNAFile("data/DNA_data.txt");
        }catch(FileNotFoundException ex){
            System.out.println("File not found at specified path.");
        }catch(IOException exception){
            System.out.println("this is not a text file");
        }

    }

    // Read Methods
    private ArrayList<String> readGenes() throws FileNotFoundException{
        Scanner reader = new Scanner(dnaFile);
        ArrayList<String> result = new ArrayList<>();
        boolean trigger = false;
        while(reader.hasNext()){
            if(reader.nextLine().charAt(0) == '<')trigger = true;
            if(trigger){
                result.add(reader.nextLine());
                trigger = false;
            }
        }
        return result;
    }
    public ArrayList<String> getGenes() {
        return genes;
    }
}
