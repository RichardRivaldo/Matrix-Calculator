import java.util.Scanner;

public class Hilbert {
    public static Matrix MHilbert(int a) {
        Matrix M = new Matrix(a,a+1);
        int counter = 0;
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < a+1; j++) {
                double diva = j+1+counter;
                double d = 1/diva;
                if (j == a){
                    Matrix.SetElmt(M, i, j, 0);
                }
                else{
                    Matrix.SetElmt(M, i, j, d);
                }
            }
            counter++;
        }
        Matrix.SetElmt(M, 0, a, 1);
        return M;
    }
    public static void main(String[] args) {
        Matrix A;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Masukkan nilai n untuk Hilbert: ");
        int a = sc.nextInt();
        A = MHilbert(a); 
        System.out.println("Berikut matrix anda: ");
        Matrix.TulisMatrix(A);

        double[] hasil = SPL.gaussJordan(A);
        for (int i = 0; i < hasil.length; i++) {
            System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
        }
    }
}
