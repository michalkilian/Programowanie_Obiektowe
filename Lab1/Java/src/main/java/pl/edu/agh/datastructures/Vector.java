package main.java.pl.edu.agh.datastructures;

import java.util.List;
import java.util.Arrays;

/**
 * Geometric Vector with arbitrary dimension.
 *
 * @author Michał Wypych
 */
public class Vector {

    /**
     * Creates new vector without initialization.
     *
     * @param dim
     */
    public Vector(int dim) {
        elems = new double[dim];
    }

    /**
     * Creates new null vector.
     *
     * Created vector has <em>dim</em> dimensions
     * and all its elements are set to 0.0.
     *
     * @param dim dimension of the vector.
     * @return new null Vector object.
     * @see Vector#fromArray(double[])
     */
    public static Vector zero(int dim) {
        Vector v = new Vector(dim);
        assert v.elems != null : "element table was not properly initilized";
        Arrays.fill(v.elems, 0.0);
        return v;
    }

    /**
     * Creates new Vector as copy of regular array.
     *
     * Size of the specified array is new dimension of the vector.
     *
     * @param array elements to be copied to new Vector.
     * @return new Vector with dimension equals to specified array.
     */
    public static Vector fromArray(double[] array) {
        Vector v = new Vector(array.length);
        assert v.elems != null : "element table was not properly initilized";
        v.elems = Arrays.copyOf(array, array.length);
        return v;
    }

    /**
     * Creates new Vector as copy of regular array.
     *
     * Size of the specified array is new dimension of the vector.
     *
     * @param array elements to be copied to new Vector.
     * @return new Vector with dimension equals to specified array.
     */
    public static Vector fromArray(List<Double> array) {
        Vector v = new Vector(array.size());
        assert v.elems != null : "element table was not properly initilized";
        int i = 0;
        for( Double d : array ) {
            v.elems[i++] = d;
        }
        return v;
    }

    /**
     * Adds specified vector to this vector.
     *
     *
     * @param v vector to be added to this vector.
     */
    public void add(Vector v){
        for( int i = 0; i<length(); i++ ) {
            elems[i] += v.getElem(i);
        }
    }

    /**
     * Returns particular vector element.
     *
     * @param pos indicates element index. Must be one of [0,length()).
     * @return value of specified element.
     */
    public double getElem(int pos) {
        assert elems != null : "element table was not properly initilized";
        return elems[pos];
    }

    /**
     * Sets particular vector element.
     * @param pos element index to be set.
     * @param value new value of the specified element.
     */
    public void setElem(int pos, double value) {
        elems[pos] = value;
    }

    /**
     * Returns dimension of the Vector.
     * @return positive integer greater than 0.
     */
    public int length() {
        return elems.length;
    }

    /**
     * Prints vector values.
     */
    @Override
    public String toString() {
        return Arrays.toString(elems).replace('[', '(').replace(']', ')');
    }

    /**
     * Elements of the vector
     * as 1-D array of doubles.
     */
    private double[] elems;

}

