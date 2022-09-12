import java.io.Serializable;
import java.util.ArrayList;

public class MatrixList<T> implements Cloneable,Serializable {
    private final ArrayList<ArrayList<T>> matrix;
    private int verticalSize, horizontalSize;

    MatrixList(int column,int row,T standard){
        horizontalSize = 1;
        verticalSize = 0;
        if(column <= 0 || row <= 0){
            throw new IllegalArgumentException("matrix need at least one column and one row");
        }
        matrix = new ArrayList<>();
        for (int i = 0; i < column; i++) {
            addColumn(standard);
        }
        for (int i = 0; i < row-1; i++) {
            addRow(standard);
        }
    }
    private ArrayList<T> getColumn(int column){
        if(0 > column && column < verticalSize)throw new IndexOutOfBoundsException("not inside the matrix");
        return matrix.get(column);
    }
    public T getValue(int column,int row){
        if(isOffMatrix(column,row))throw new IndexOutOfBoundsException("not inside the matrix");
        return getColumn(column).get(row);

    }

    public int getVerticalSize() {
        return verticalSize;
    }

    public int getHorizontalSize() {
        return horizontalSize;
    }

    public void addRow(T value){// add a row with given value
        for (int i = 0; i < verticalSize; i++) {
            getColumn(i).add(value);
        }
        horizontalSize++;
    }
    public void addColumn(T value){// add a row with given value
        matrix.add(new ArrayList<>());
        for (int i = 0; i < horizontalSize; i++) {
            getColumn(verticalSize).add(value);
        }
        verticalSize++;
    }

    public void setValue(int vertical,int horizontal,T value){
        if(isOffMatrix(vertical,horizontal))throw new IndexOutOfBoundsException("not inside the matrix");
        matrix.get(vertical).set(horizontal,value);
    }
    private boolean isOffMatrix(int vertical, int horizontal){
        return !( (vertical >= 0 && vertical < verticalSize) && (horizontal >= 0 && horizontal < horizontalSize));
    }

    public static void main(String[] args) { //class test function
        MatrixList<String> hello = new MatrixList<>(4, 5, "Hello");
        System.out.println(hello.verticalSize+"*"+hello.horizontalSize);
        System.out.println(hello.getValue(4, 4));
    }
    @Override
    public MatrixList<T> clone(){
        MatrixList<T> clone = new MatrixList<>(verticalSize, horizontalSize, getValue(0, 0));
        for (int i = 0; i < verticalSize; i++) {
            for (int j = 0; j < horizontalSize; j++) {
                clone.setValue(i,j,getValue(i,j));
            }
        }
        return clone;
    }
}
