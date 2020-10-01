public class SPL {
    public static double[] gaussJordan(Matrix M) {
        if (IsOK(M) && (Matrix.GetKol(M) - Matrix.GetRow(M) == 1)){
            int counter = 0;

            for (int i = 0; i < Matrix.GetRow(M); i++) {
                double pivot = Matrix.GetElmt(M, i, counter);

                if (pivot != 1){
                    for (int j = 0; j < Matrix.GetKol(M); j++) {
                        Matrix.SetElmt(M, i, j, Matrix.GetElmt(M, i, j)/pivot);
                    }
                }

                for (int k = 0; k < Matrix.GetRow(M); k++) {
                    if (k == i) continue;

                    double konst = Matrix.GetElmt(M, k, counter);
                    for (int j = 0; j < Matrix.GetKol(M); j++) {
                        double a = Matrix.GetElmt(M, k, j);
                        double b = konst * Matrix.GetElmt(M, i, j);
                        Matrix.SetElmt(M, k, j, a-b);
                    }
                }

                counter++;
            }
            
            
            double[] hasil = new double[Matrix.GetRow(M)];
            for (int i = 0; i < Matrix.GetRow(M); i++) {
                hasil[i] = Matrix.GetElmt(M, i, Matrix.GetKol(M)-1);
            }
            return hasil;
        }
        else {
            System.out.println("Matriks tidak ada solusi atau multi solusi");
            double[] dummy = new double[1];
            return dummy;
        }
    }

    

    public static Matrix doGauss(Matrix M) {
        int counter = 0;

        for (int i = 0; i < Matrix.GetRow(M); i++) {
            double pivot = Matrix.GetElmt(M, i, counter);

            if (pivot == 0) continue;
            else if (pivot != 1){
                for (int j = 0; j < Matrix.GetKol(M); j++) {
                    Matrix.SetElmt(M, i, j, Matrix.GetElmt(M, i, j)/pivot);
                }
            }

            for (int k = i+1; k < Matrix.GetRow(M); k++) {

                double konst = Matrix.GetElmt(M, k, counter);
                for (int j = 0; j < Matrix.GetKol(M); j++) {
                    double a = Matrix.GetElmt(M, k, j);
                    double b = konst * Matrix.GetElmt(M, i, j);
                    Matrix.SetElmt(M, k, j, a-b);
                }
            }

            counter++;
        }
        return M;
    }

    public static boolean IsOK(Matrix M) {
            Matrix m = new Matrix(Matrix.GetRow(M),Matrix.GetKol(M));
            boolean ok = false;
            m = doGauss(M);
            for (int j = 0; j < Matrix.GetKol(M); j++) {
                if (Matrix.GetElmt(M, Matrix.GetRow(M)-1, j) != 0){
                    ok  = true;
                }
            }

            return ok;
        
    }

    public static boolean IsNotOK(Matrix M) {
        return !IsOK(M);
    }

    public static double[] gauss(Matrix M) {
        M = doGauss(M);

        if (IsNotOK(M) && Matrix.GetElmt(M, Matrix.GetRow(M)-1, Matrix.GetKol(M)-1) != 0){
            System.out.println("Matrix tidak memiliki solusi");
            double[] dummy = new double[1];
            return dummy;
        }
        else if ((IsNotOK(M) && Matrix.GetElmt(M, Matrix.GetRow(M)-1, Matrix.GetKol(M)-1) == 0) || (Matrix.GetKol(M) - Matrix.GetRow(M) != 1)){
            System.out.println("Matrix memiliki banyak solusi");

            double[] dummy = new double[1];
            return dummy;
        }
        else{
            double[] hasil = new double[Matrix.GetKol(M)];
            hasil[Matrix.GetRow(M) - 1] = Matrix.GetElmt(M, Matrix.GetRow(M) - 1, Matrix.GetKol(M) - 1);
            for (int i = Matrix.GetRow(M)-2; i >= 0; i--) {
                double sum = 0;
                for (int j = i+1; j < Matrix.GetKol(M)-1; j++) {
                    sum += hasil[j]*Matrix.GetElmt(M, i, j);
                }
                hasil[i] = Matrix.GetElmt(M, i, Matrix.GetKol(M) - 1) - sum;
            }
        return hasil;
        }
        
    }

    public static double[] cramer(Matrix M) {
        if (IsOK(M)){
            double[][] temp = new double[Matrix.GetRow(M)][Matrix.GetRow(M)];
            for(int i = 0; i< Matrix.GetRow(M); i++){
            
                for(int j = 0; j< Matrix.GetRow(M); j++){
                    temp[i][j] = Matrix.GetElmt(M, i, j);
                }
            }

            Matrix d = new Matrix(temp);
            Matrix copy = new Matrix(temp);
            double groundDet = Determinan.hitungDeterminanEK(d);

            double[] data = new double[Matrix.GetRow(M)];

            for(int i = 0; i < Matrix.GetRow(M); i++){ // iterasi x0, x1,...

                for (int j = 0; j < data.length; j++) {
                    for (int j2 = 0; j2 < data.length; j2++) {
                        Matrix.SetElmt(d, j, j2, Matrix.GetElmt(copy, j, j2));
                    }
                }
                
                for(int j = 0; j< Matrix.GetRow(M); j++){ //iterasi baris per baris
                    
                    for(int k = 0; k< Matrix.GetRow(M); k++){ // iterasi kolom
                        if(k == i) Matrix.SetElmt(d, j, k, Matrix.GetElmt(M, j, Matrix.GetKol(M)-1));
                    }
                }
        
                data[i] = Determinan.hitungDeterminanEK(d) / groundDet;
            }
            return data;
        }
        else{
            System.out.println("Matriks tidak ada solusi atau multi solusi");
            double[] dummy = new double[1];
            return dummy;
        }
        
    }

    public static double[] inverseSPL(Matrix M) {
        if (IsOK(M)){
            double[][] vari = new double[Matrix.GetRow(M)][Matrix.GetRow(M)];
            for (int i = 0; i < vari.length; i++) {
                for (int j = 0; j < vari.length; j++) {
                    vari[i][j] = Matrix.GetElmt(M, i, j);
                }
            }
            Matrix m = new Matrix(vari);
            Matrix ma = new Matrix(Matrix.GetRow(M),Matrix.GetRow(M)); 
            ma = Inverse.InverseAdjoin(m);

            double[][] konst = new double[Matrix.GetRow(M)][1];

            for (int i = 0; i < konst.length; i++) {
                konst[i][0] = Matrix.GetElmt(M, i, Matrix.GetKol(M)-1);
            }

            Matrix m1 = new Matrix(1,1);
            Matrix m2 = new Matrix(konst);

            m1 = Matrix.KaliMatriks(ma,m2);

            return Matrix.Transpose(m1).data[0];
        }
        else{
            // kalo no/multi solusi
            System.out.println("Matriks tidak memiliki solusi atau multi solusi");
            double[] dummy = new double[1];
            return dummy;
        }
        
    }
    public static void main(String[] args) {
        Matrix M;
        System.out.println("(Untuk spl, jumlah kolom = jumlah baris + 1)");
        M = Matrix.MakeMatrix();
        
        double[] s = new double[Matrix.GetRow(M)];
        s = gauss(M);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }
    }
}
