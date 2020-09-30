import java.util.Scanner;

public class Hilbert {
    public static void main(String[] args) {
        Matrix A;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Masukkan nilai n untuk Hilbert: ");
        int a = sc.nextInt();
        A = SPL.Hilbert(a); 
        System.out.println("Berikut matrix anda: ");
        Matrix.TulisMatrix(A);

        double[] hasil = SPL.gaussJordan(A);
        for (int i = 0; i < hasil.length; i++) {
            System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
        }
    }
}
