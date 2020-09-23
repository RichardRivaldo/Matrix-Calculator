import java.util.Scanner;

public class Determinan {
    public static double det2(Matrix M){
        return (Matrix.GetElmt(M, 0, 0) * Matrix.GetElmt(M, 1, 1)) - (Matrix.GetElmt(M, 0, 1) * Matrix.GetElmt(M, 1, 0));
    }

    public static Matrix minor(Matrix M, int row, int col){
        //membuat matriks minor dari matriks input
        // masukan adalah baris/kolom yang mau digunakan
        // row: baris yg digunakan untuk mencari minornya, col: kolom digunakan buat minornya
        /* Kamus Lokal:
        hasil: Matrix (matriks minor hasil keluaran)
        i:          int (iterasi baris matriks M)
        j:          int (iterasi kolom matriks M)    
        counterB:   int (menghitung baris untuk matriks hasil, berbeda dari M karena saat M diskip, iterasi hasil tidak berubah namun i
                        bertambah, jadi harus ada counter terpisah)
        counterC:   int (sama seperti counterB, tapi untuk kolom)
        */
        Matrix hasil = new Matrix(Matrix.GetRow(M) - 1,Matrix.GetKol(M) - 1);
        int counterB = 0;
        for (int i = 0; i < Matrix.GetKol(M); i++) {
            //iterasi baris M, skip jika baris row
            if (i == row) continue;
            int counterC = 0;
            
            for (int j = 0; j < Matrix.GetRow(M); j++) {
                // iterasi kolom M, skip jika kolom col
                if (j == col) continue;
                
                //
                Matrix.SetElmt(hasil, counterB, counterC, Matrix.GetElmt(M, i, j));
                counterC++;
            }
            counterB++;
        }
        return hasil;
    }

    //hitung determinan matriks M dengan ekspansi kofaktor
    public static double hitungDeterminanEK(Matrix M) {
        double det = 0;
        int sign = 1;
        double curr = 0;
        for (int i = 0; i < Matrix.GetKol(M); i++) {
            if ((Matrix.GetKol(minor(M, 0, i)) == 2) && (Matrix.GetRow(minor(M, 0, i)) == 2)){
                curr = sign*det2(minor(M, 0, i))*Matrix.GetElmt(M, 0, i);
                det += curr;
                sign *= -1;
            }
            else{
                curr = sign*hitungDeterminanEK(minor(M, 0, i))*Matrix.GetElmt(M, 0, i);
                det += curr;
                sign *= -1;
            }
        }
        return det;
    }
    public static void main(String[] args){
        Matrix M = new Matrix(1,1);
        M = Matrix.MakeMatrix();

        System.out.println(hitungDeterminan(M));
    }
}
