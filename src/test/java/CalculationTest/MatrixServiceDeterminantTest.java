package CalculationTest;

import model.Matrix;
import model.MatrixService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixServiceDeterminantTest {

    private MatrixService matrixService;

    @Before
    public void init() {
        matrixService = new MatrixService();
    }

    @Test
    public void test1by1() {

        Double[][] tab = {{5.0}};

        Matrix matrix = new Matrix(tab);

        assertEquals(5.0, matrixService.calculateDeterminant(matrix), 0);
    }

    @Test
    public void test2by2() {

        Double[][] tab = {{1.0, 2.0},
                {2.0, 3.0}};

        Matrix matrix = new Matrix(tab);

        assertEquals(-1.0, matrixService.calculateDeterminant(matrix), 0);
    }

    @Test
    public void test3by3() {

        Double[][] tab = {{1.0, 2.0, 3.0},
                {4.0, 5.0, 6.0},
                {9.0, 7.0, 8.0}};

        Matrix matrix = new Matrix(tab);

        assertEquals(-9.0, matrixService.calculateDeterminant(matrix), 0);
    }

    @Test
    public void test4by4() {

        Double[][] tab = {{1.0, 2.0, 3.0, 4.0},
                {8.0, 5.0, 7.0, 6.0},
                {9.0, 10.0, 12.0, 11.0},
                {23.0, 22.0, 24.0, 20.0}};

        Matrix matrix = new Matrix(tab);

        assertEquals(87, matrixService.calculateDeterminant(matrix), 0);
    }

    @Test
    public void test5by5() {

        Double[][] tab = {{1.0, 2.0, 3.0, 4.0, 5.0},
                {5.0, 6.0, 7.0, 8.0, 9.0},
                {9.0, 10.0, 11.0, 12.0, 13.0},
                {20.0, 21.0, 22.0, 23.0, 24.0},
                {20.0, 21.0, 22.0, 23.0, 24.0}};

        Matrix matrix = new Matrix(tab);

        assertEquals(0.0, matrixService.calculateDeterminant(matrix), 0);
    }

}
