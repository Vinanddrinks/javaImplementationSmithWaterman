import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Scanner;

public class DNAFile {
    //Attributes
    private final File file;
    private final ArrayList<String> genes;

    //Constructors
    DNAFile(String path) throws InvalidPathException,IOException {
        file = new File(path);
        if (!file.isFile()){
            throw new IOException("this is not a file");
        }
        genes = readGenes();
        
    }

    //Main test
    /*
    public static void main(String[] args){
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
    private ArrayList<String> readGenes() throws FileNotFoundException{
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
    public ArrayList<String> getGenes() {
        return genes;
    }

    public File getFile() {
        return file;
    }

    @Override
    public String toString(){
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
