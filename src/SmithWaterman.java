public class SmithWaterman {
    //data attributes
    private static SmithWaterman instance = null;
    private final MatrixList<Integer> matrixList;

    private String genes1 = "",genes2 = "";

    //resolved attributes;

    private int maxScore;
    private final int[] maxCoordinates = new int[2];
    String localAlignedSequence;

    private SmithWaterman(){
        matrixList = new MatrixList<>(11,11,0);
    }

    public static SmithWaterman getInstance() {
        if (instance == null) {
            instance = new SmithWaterman();
        }
        return instance;
    }

    public void setGenes1(String genes1) {
        StringBuilder worker = new StringBuilder(this.genes1);
        this.genes1 = worker.append(genes1).toString();
    }

    public void setGenes2(String genes2) {
        StringBuilder worker = new StringBuilder(this.genes2);
        this.genes2 = worker.append(genes2).toString();

    }
    public void computeCell(int vertical,int horizontal) throws NullPointerException{
        int left = matrixList.getValue(vertical,horizontal-1) -2;
        int up  = matrixList.getValue(vertical-1,horizontal) -2;
        int diagonal = matrixList.getValue(vertical-1,horizontal-1);
        if(genes1.charAt(vertical-1)==genes2.charAt(horizontal-1))diagonal++;
        else diagonal--;
        diagonal = Integer.max(left,Integer.max(up,diagonal));
        if(diagonal <0)diagonal = 0;
        matrixList.setValue(vertical,horizontal,diagonal);
    }

    public void setMaxScore(){
        maxScore = 0;
        for (int i = 0; i < matrixList.getVerticalSize(); i++) {
            for (int j = 0; j < matrixList.getHorizontalSize(); j++) {
                if(maxScore < matrixList.getValue(i,j)){
                    maxScore = matrixList.getValue(i,j);
                    maxCoordinates[0] = i;
                    maxCoordinates[1] = j;
                }
            }
        }
    }
    public void setLocalAlignedSequence(){

        localAlignedSequence = "";
        int i = maxCoordinates[0]-1,j = maxCoordinates[1]-1;
        while (matrixList.getValue(i,j) != 0){
            localAlignedSequence += genes1.charAt(i);
            i--;
            j--;
        }
    }

    public void resolveSmithWaterman() throws NullPointerException{
        if(genes1.length() != genes2.length())throw new IllegalArgumentException("genes chains have not been set correctly");
        for (int i = 1; i < matrixList.getHorizontalSize(); i++) {
            for (int j = 1; j < matrixList.getVerticalSize(); j++) {
                computeCell(j,i);
            }
        }
        setMaxScore();
        setLocalAlignedSequence();
    }

    //getters


    public int getMaxScore() {
        return maxScore;
    }

    public int[] getMaxCoordinates() {
        return maxCoordinates;
    }

    public String getLocalAlignedSequence() {
        return localAlignedSequence;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("S |   ");
        for (int h = 1; h <= genes1.length();h++){
            result.append(genes1.charAt(h-1)).append(" ");
        }
        result.append("\n  | ").append("0 ".repeat(Math.max(0, matrixList.getHorizontalSize()))).append("\n");
        for (int i = 1; i < matrixList.getHorizontalSize(); i++) {
            result.append(genes2.charAt(i-1)).append(" | 0");
            for (int j = 1; j < matrixList.getVerticalSize(); j++) {
                result.append(" ").append(matrixList.getValue(j,i));
                if (j==matrixList.getVerticalSize()-1) result.append("\n");
            }
        }
        return result.toString();
    }
}
