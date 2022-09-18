import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;

// Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
//This class deals with the file related operations like declaration and reading

public class DNAFile {
    //Attributes
    private final File file; //Variable from the java File class, allows operations on our file
    private final ArrayList<String> genes; //ArrayList of Strings to store the genes read from the file

    //Constructors
    DNAFile(String path) throws InvalidPathException,IOException { //Override constructor. Creates an instance file of the File class from a specified path
        file = new File(path);
        if (!file.isFile()){
            throw new IOException("this is not a file");
        }
        genes = readGenes();
        
    }

    /*
    public static void main(String[] args){ // Class test function
        try {
           DNAFile hello = new DNAFile("data/DNA_data.txt");
            System.out.println(hello.getFile().canRead());
            Scanner reader = new Scanner(hello.getFile());
           System.out.println(hello.getGenes().get(1));
        }catch(FileNotFoundException ex){
            System.out.println("File not found at specified path.");
        }catch(IOException exception){
            System.out.println("this is not a text file");
        }
    }
    */

    // Read Methods
    private ArrayList<String> readGenes() throws FileNotFoundException{ //Read the genes from the file and store them in an ArrayList
        Scanner reader = new Scanner(file);
        ArrayList<String> result = new ArrayList<>();
        while(reader.hasNextLine()){
            String temp = reader.nextLine();
            if( !temp.equals("") && temp.charAt(0) == '>'){
                result.add(reader.nextLine());
            }
        }
        reader.close();
        return result;
    }

    //Getters
    public ArrayList<String> getGenes() {
        return genes;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString(){ //Overriden function for better readability
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (String current:
             this.genes) {
            result.append(i).append(".(").append(current).append(")\n\n");
            i++;
        }
        return result.toString();
    }
}
