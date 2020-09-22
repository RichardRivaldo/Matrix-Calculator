import java.util.Scanner;

public class Determinan {
    public static double det2(Matrix M){
        return (Matrix.GetElmt(M, 0, 0) * Matrix.GetElmt(M, 1, 1)) - (Matrix.GetElmt(M, 0, 1) * Matrix.GetElmt(M, 1, 0));
    }

    public static Matrix minor(Matrix M, int row, int col){
        //membuat matriks minor dari matriks input
        // masukan adalah baris/kolom yang mau digunakan
        // row: jika true memilih baris ke-masukan, jika false memilih kolom ke-masukan

        Matrix hasil = new Matrix(Matrix.GetRow(M) - 1,Matrix.GetKol(M) - 1);
        int counterB = 0;
        for (int i = 0; i < Matrix.GetKol(M); i++) {
            //iterasi baris M, skip jika baris row
            if (i == row) continue;
            int counterC = 0;
            
            for (int j = 0; j < Matrix.GetRow(M); j++) {
                // iterasi kolom M, skip jika kolom col
                if (j == col) continue;

                Matrix.SetElmt(hasil, counterB, counterC, Matrix.GetElmt(M, i, j));
                counterC++;
            }
            counterB++;
        }
        return hasil;
    }
    public static void main(String[] args){
        Matrix M = new Matrix(1,1);
        M = Matrix.MakeMatrix();

        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan row: ");
        int row = sc.nextInt();

        System.out.println("Masukkan kolom: ");
        int col = sc.nextInt();

        Matrix.TulisMatrix(minor(M, row, col));
    }
}
