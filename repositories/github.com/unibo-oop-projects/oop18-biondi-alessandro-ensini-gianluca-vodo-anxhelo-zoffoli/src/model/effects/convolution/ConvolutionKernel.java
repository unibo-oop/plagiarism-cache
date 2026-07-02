package model.effects.convolution;

import java.io.Serializable;
import java.util.stream.IntStream;

/**
 * Represents a matrix rows * columns .
 */
public class ConvolutionKernel implements Serializable {

    private static final long serialVersionUID = 1L;
    private final float[] matrix;
    private final int rows;
    private final int columns;

    /**
     * @param rows    the specified number of rows of the matrix
     * @param columns the specified number of columns of the matrix
     * @param matrix  the specified matrix in order to apply convolution
     * @param divider the specified divider for the kernel matrix
     */
    public ConvolutionKernel(final int rows, final int columns, final float[] matrix, final int divider) {
        final int div;

        if (divider > 1) {
            div = divider;
        } else {
            div = 1;
        }

        this.rows = rows;
        this.columns = columns;

        float[] k = new float[rows * columns];
        IntStream.range(0, matrix.length).forEach(i -> k[i] = matrix[i] / div);
        this.matrix = k;
    }

    /**
     * @return the matrix kernel on array form
     */
    public float[] getKernelMatrix() {
        return this.matrix.clone();
    }

    /**
     * @return the specified number of rows of the matrix
     */
    public int getRows() {
        return this.rows;
    }

    /**
     * @return the specified number of columns of the matrix
     */
    public int getColumns() {
        return this.columns;
    }

}
