import java.util.Scanner;

public class Interpolasi {
    public static void Interpolasi(int jumlahTitik){

        /* Scanner input */
        Scanner sc = new Scanner(System.in);
    
        Matrix augInterpolasi = new Matrix(jumlahTitik, jumlahTitik + 1);
    
        for(int i = 0; i < Matrix.GetRow(augInterpolasi); i++){
            System.out.printf("Masukkan nilai X%d: ", i + 1);
            double X = sc.nextDouble();
            System.out.printf("Masukkan nilai Y%d: ", i + 1);
            double Y = sc.nextDouble();
            for(int j = 0; j < Matrix.GetKol(augInterpolasi) - 1; j++){
                Matrix.SetElmt(augInterpolasi, i, j, Math.pow(X, j));
            Matrix.SetElmt(augInterpolasi, i, Matrix.GetKol(augInterpolasi) - 1, Y);
            }
        }
    
        Matrix hasilInterpolasi = new Matrix(Matrix.GetRow(augInterpolasi), Matrix.GetKol(augInterpolasi));
        for(int i = 0; i < Matrix.GetRow(hasilInterpolasi); i++){
            for(int j = 0; j < Matrix.GetKol(hasilInterpolasi); j++){
                Matrix.SetElmt(hasilInterpolasi, i, j, Matrix.GetElmt(augInterpolasi, i, j));
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
    
        System.out.print("Masukkan jumlah titik: ");
        int jumlahTitik = sc.nextInt();
        while(jumlahTitik < 0){
            System.out.print("Jumlah titik invalid. Masukkan jumlah baru: ");
            jumlahTitik = sc.nextInt();
        }
        Interpolasi(jumlahTitik);
    }
}