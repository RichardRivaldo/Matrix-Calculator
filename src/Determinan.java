
public class Determinan {

    //det matriks 1x1
    public static double det(Matrix M){
        return Matrix.GetElmt(M, 0, 0);
    }
    // det matriks 2x2
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

    //hitung determinan matriks M 2x2++ dengan ekspansi kofaktor
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
    
    // operasi penjumlahan buat baris i1 - baris i2*mult
    public static void BarBar(Matrix M, int i1, int i2, double mult){
        for (int j = 0; j < Matrix.GetKol(M); j++) {
            double a,b;
            a = Matrix.GetElmt(M, i1, j);
            b = Matrix.GetElmt(M, i2, j);

            Matrix.SetElmt(M, i1, j, a+(b*mult));
        }
    }
    //determinan dengan reduksi baris 
    //construction done
    public static double hitungDeterminanRB(Matrix M){
        double det = 1;
        
        for (int j = 0; j < Matrix.GetRow(M); j++) { //iterasi baris dari 0 sampe abis
            for (int i = j+1; i < Matrix.GetRow(M); i++) { //iterasi baris setelah baris i sampe abis
                double multT = Matrix.GetElmt(M, i, j) * (-1) / Matrix.GetElmt(M, j, j); //intinya multiplier supaya kalo dikurangin jadi 0
                BarBar(M,i,j,multT); //baris yg iterasi i dikurangin sama (baris iterasi j * multT), bakal jadi 0.
            }
        }
        
        for (int i = 0; i < Matrix.GetRow(M); i++) {
            det *= Matrix.GetElmt(M, i, i);
        }
        
        return det;
    };

    public static void main(String[] args){
        Matrix M = new Matrix(1,1);
        M = Matrix.MakeMatrix();

        System.out.println(hitungDeterminanRB(M));
    }
}
