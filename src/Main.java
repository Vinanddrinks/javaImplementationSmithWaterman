import java.util.ArrayList;
import java.util.Scanner;
/**
 * Main
 *  Authors Vincent Labouret 40259595, Marine Milosavljevic 40259616
 *  This class is the main class of our project.
 *  It contains the script that allows the user to choose a file from which to get the genes to resolve the Smith-Waterman algorithm
 */
public class Main {
    //Attributes
    private static ArrayList<String> arrayGenes = new ArrayList<>(); //ArrayList of Strings to display the possible genes from the file
    private static Scanner input = new Scanner(System.in); //Scanner declared for user inputs

    public static void main(String[] args) {
        boolean check = false;
        do {
            try{
                System.out.println("Please enter the DNA_data file path: \n");
                String path = input.nextLine();
                DNAFile file = new DNAFile(path);
                arrayGenes.addAll(file.getGenes());
                check = false;
            }catch(Exception ex){
                check = true;
                System.out.println("Wrong path, please enter the correct one.");
            }
        }while (check);
        System.out.println(displayList(arrayGenes));
        check = true;
        boolean step = false;
        do {
            try {
                System.out.println("Please enter the number of the subject chain you want: \n");
                String temp = input.nextLine();
                SmithWaterman.getInstance().setGenes1(arrayGenes.get(Integer.parseInt(temp) - 1));
                step = true;
            } catch (Exception ex) {
                System.out.println("Invalid number or format. (press enter to continue)");
                input.nextLine();
            }
            System.out.println(displayList(arrayGenes));
            if(step) {
                try {
                    System.out.println("Please enter the number of the query chain you want: \n");
                    String temp1 = input.nextLine();
                    SmithWaterman.getInstance().setGenes2(arrayGenes.get(Integer.parseInt(temp1) - 1));
                    check = false;
                } catch (Exception ex) {
                    System.out.println("Invalid number or format.(Press enter to continue.)");
                    input.nextLine();
                }
            }
        }while(check);


        SmithWaterman.getInstance().resolveSmithWaterman();
        System.out.println("\nHere is the result of the Smith-Waterman algorithm: \n");
        System.out.println(SmithWaterman.getInstance().toString());
        System.out.println("Here is the aligned sequence: " + SmithWaterman.getInstance().getLocalAlignedSequence() + ".");
        System.out.println("The max score is " + SmithWaterman.getInstance().getMaxScore() + ".");
        System.out.println("The coordinates of the max score are [" + (SmithWaterman.getInstance().getMaxCoordinates()[0]+1) + "," + (SmithWaterman.getInstance().getMaxCoordinates()[1]+1) + "].");
        input.close();
        }

    private static String displayList(ArrayList<String> list){ //Displays all the genes the user can choose from for better readability
        int i = 1;
        StringBuilder result = new StringBuilder();
        for (String current:
                list) {
            result.append(i).append(".(").append(current).append(")\n\n");
            i++;
        }
        return result.toString();
    }
}
