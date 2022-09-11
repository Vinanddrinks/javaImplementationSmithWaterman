import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.Scanner;

public class DNAFile {
    //Attributes
    private File dnaFile = null;
    //method pour read et pour write

    //Constructors
    DNAFile(String path) throws InvalidPathException{
        dnaFile = new File(path);
        if (!dnaFile.isFile()){
            System.out.println("Please enter the path of the DNA_data file.");
        }
    }
    //Main test
    public static void main(String[] args){
        try {
            DNAFile myFile = new DNAFile("C:\\users\\marin\\IdeaProjects\\javaImplementationSmithWaterman\\data/DNA_data.txt");
            myFile.readFile();
        }catch(FileNotFoundException ex){
            System.out.println("File not found at specified path.");
        }

    }

    //Methods
    public void readFile() throws FileNotFoundException{
        Scanner input = new Scanner(dnaFile);
        System.out.println("I have accessed the file.");//TRUC POUR TENTER

        input.close();
    }

    public void writeFile(File dnaFile){
        //
    }

}
