import java.io.Serializable;
import java.util.ArrayList;

public class MatrixList<T> implements Cloneable,Serializable {
    private ArrayList<ArrayList<T>> matrix;
    private int sizeVert,sizeHorz;

    MatrixList(int column){
        sizeHorz = 0;
        sizeVert = 1;
        if(column <= 0){
            throw new IllegalArgumentException("matrix need at least one column");
        }
        matrix = new ArrayList<ArrayList<T>>();
        for (int i = 0; i < column -1; i++) {
            matrix.add(new ArrayList<T>());
            sizeVert++;
        }
    }
    public ArrayList<T> getColumn(int column){
        if(0 > column && column < sizeVert)throw new IndexOutOfBoundsException("not inside the matrix");
        return matrix.get(column);
    }
    public T getValue(int column,int row){
        if(0 > column && column < sizeVert && 0 > row && row < getColumn(column).size())throw new IndexOutOfBoundsException("not inside the matrix");
        return getColumn(column).get(row);

    }

    public static void main(String[] args) {
        MatrixList<String> hello = new MatrixList<String>(0);
        System.out.println(hello.sizeVert);
        System.out.println(hello.getValue(3, 0));
    }
}
