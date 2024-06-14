/*
Name : Rishi kumar
CWID : 20015656

citation 1 : https://www.codespeedy.com/generate-random-matrix-in-java/
citation 2: https://en.wikipedia.org/wiki/Gram%E2%80%93Schmidt_process
 */


import java.util.Random;

public class GramSchmidt {
    public static void main(String[] args)
    {

        int n;
        if (args.length == 1)
        {
            n = Integer.parseInt(args[0]);
        } else
        {
            n = 3; // Default value of 3
        }


        double[][] matrix = generateRandomMatrix(n);

        System.out.println("Original Matrix:");
        displayMatrix(matrix);


        normalizeColumns(matrix);

        System.out.println("Normalized Matrix:");
        displayMatrix(matrix);


        gramSchmidt(matrix);

        System.out.println("Orthogonal Matrix:");
        displayMatrix(matrix);


        normalizeColumns(matrix);

        System.out.println("Orthonormal Matrix:");
        displayMatrix(matrix);
    }


    public static double[][] generateRandomMatrix(int n)
    {
        double[][] matrix = new double[n][n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                matrix[i][j] = random.nextDouble();
            }
        }
        return matrix;
    }


    public static void normalizeColumns(double[][] matrix)
    {
        int n = matrix.length;
        for (int j = 0; j < n; j++)
        {
            double magnitude_matrix = 0.0;
            for (int i = 0; i < n; i++)
            {
                magnitude_matrix += matrix[i][j] * matrix[i][j];
            }
            magnitude_matrix = Math.sqrt(magnitude_matrix);
            for (int i = 0; i < n; i++) {
                matrix[i][j] /= magnitude_matrix;
            }
        }
    }


    public static void gramSchmidt(double[][] matrix)
    {
        int n = matrix.length;
        for (int j = 0; j < n; j++)
        {
            for (int i = 0; i < j; i++)
            {
                double dot_product = 0.0;
                for (int k = 0; k < n; k++)
                {
                    dot_product += matrix[k][i] * matrix[k][j];
                }
                for (int k = 0; k < n; k++)
                {
                    matrix[k][j] -= dot_product * matrix[k][i];
                }
            }
        }
    }


    public static void displayMatrix(double[][] matrix)
    {
        int n = matrix.length;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
