import java.util.Scanner;

public class RegresiGanda {
    /* Masih salah metodenya */
    public static void MultiRegresi(int jumlahTitik, int jumlahVariabel){
        
        Scanner sc = new Scanner(System.in);

        Matrix titikAbsis = new Matrix(jumlahTitik, jumlahVariabel + 1);
        for(int i = 0; i < Matrix.GetRow(titikAbsis); i++){
            for(int j = 0; j < Matrix.GetKol(titikAbsis); j++){
                Matrix.SetElmt(titikAbsis, i, j, 1);
            }
        }
        for(int i = 0; i < Matrix.GetRow(titikAbsis); i++){
            System.out.printf("Masukkan nilai-nilai tiap variabel independen pada data %d:\n", i + 1);
            for(int j = 1; j < Matrix.GetKol(titikAbsis); j++){
                System.out.printf("Masukkan nilai X%d: ", j);
                double X = sc.nextDouble();
                Matrix.SetElmt(titikAbsis, i, j, X);
            }
        }

        Matrix titikOrdinat = new Matrix(jumlahTitik, 1);
        for(int i = 0; i < Matrix.GetRow(titikOrdinat); i++){
            System.out.printf("Masukkan nilai variabel dependen pada data %d: ", i + 1);
            double Y = sc.nextDouble();
            Matrix.SetElmt(titikOrdinat, i, 0, Y);
        }
        Matrix.TulisMatrix(titikAbsis);
        Matrix.TulisMatrix(titikOrdinat);

        Matrix XTranspose = new Matrix(Matrix.GetKol(titikAbsis), Matrix.GetRow(titikAbsis));
        XTranspose = Matrix.Transpose(titikAbsis);
        Matrix.TulisMatrix(XTranspose);

        Matrix TransposeXKaliX = new Matrix(Matrix.GetRow(XTranspose), Matrix.GetKol(titikAbsis));
        TransposeXKaliX = Matrix.KaliMatriks(XTranspose, titikAbsis);
        Matrix.TulisMatrix(TransposeXKaliX);

        /* -------------------------------------------------- */
        /* Perlu debug di bagian setelah ini / KaliMatriks */
        Matrix InverseTransXKaliX = new Matrix(Matrix.GetRow(TransposeXKaliX), Matrix.GetKol(TransposeXKaliX));
        InverseTransXKaliX = Inverse.InverseAdjoin(TransposeXKaliX);
        Matrix.TulisMatrix(InverseTransXKaliX);

        Matrix XTransposeKaliY = new Matrix(Matrix.GetRow(XTranspose), Matrix.GetKol(titikOrdinat));
        XTransposeKaliY = Matrix.KaliMatriks(XTranspose, titikOrdinat);
        Matrix.TulisMatrix(XTransposeKaliY);

        Matrix hasil = new Matrix(jumlahVariabel + 1, 1);
        hasil = Matrix.KaliMatriks(InverseTransXKaliX, XTransposeKaliY);
        Matrix.TulisMatrix(hasil);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah titik: ");
        int jumlahTitik = sc.nextInt();
        System.out.print("Masukkan jumlah variabel dependen: ");
        int jumlahVariabel = sc.nextInt();
        while(jumlahTitik < 0 || jumlahVariabel < 0){
            if(jumlahTitik < 0 && jumlahVariabel < 0){
                System.out.print("Jumlah titik dan variabel dependen invalid. Masukkan lagi:\n");
                System.out.print("Jumlah titik: ");
                jumlahTitik = sc.nextInt();
                System.out.print("Jumlah variabel dependen: ");
                jumlahVariabel = sc.nextInt();
            }
            else if(jumlahTitik < 0){
                System.out.print("Jumlah titik invalid. Masukkan jumlah baru: ");
                jumlahTitik = sc.nextInt();
            }
            else if(jumlahVariabel < 0){
                System.out.print("Jumlah variabel independen invalid. Masukkan jumlah baru: ");
                jumlahVariabel = sc.nextInt();
            }
        }
        MultiRegresi(jumlahTitik, jumlahVariabel);
    }
}
