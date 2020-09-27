import java.util.Scanner;

public class RegresiGanda {
    /* Masih salah metodenya */
    public static void MultiRegresi(int jumlahData, int jumlahVariabel){
        
        Scanner sc = new Scanner(System.in);

        Matrix titikAbsis = new Matrix(jumlahData, jumlahVariabel + 1);
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

        Matrix titikOrdinat = new Matrix(jumlahData, 1);
        for(int i = 0; i < Matrix.GetRow(titikOrdinat); i++){
            System.out.printf("Masukkan nilai variabel dependen pada data %d: ", i + 1);
            double Y = sc.nextDouble();
            Matrix.SetElmt(titikOrdinat, i, 0, Y);
        }
        /*
        Matrix.TulisMatrix(titikAbsis);
        Matrix.TulisMatrix(titikOrdinat);
        */

        Matrix XTranspose = new Matrix(Matrix.GetKol(titikAbsis), Matrix.GetRow(titikAbsis));
        XTranspose = Matrix.Transpose(titikAbsis);
        /* Matrix.TulisMatrix(XTranspose); */

        Matrix TransposeXKaliX = new Matrix(Matrix.GetRow(XTranspose), Matrix.GetKol(titikAbsis));
        TransposeXKaliX = Matrix.KaliMatriks(XTranspose, titikAbsis);
        /* Matrix.TulisMatrix(TransposeXKaliX); */

        Matrix InverseTransXKaliX = new Matrix(Matrix.GetRow(TransposeXKaliX), Matrix.GetKol(TransposeXKaliX));
        InverseTransXKaliX = Inverse.InverseGaussian(TransposeXKaliX);
        /* Matrix.TulisMatrix(InverseTransXKaliX); */

        Matrix XTransposeKaliY = new Matrix(Matrix.GetRow(XTranspose), Matrix.GetKol(titikOrdinat));
        XTransposeKaliY = Matrix.KaliMatriks(XTranspose, titikOrdinat);
        /* Matrix.TulisMatrix(XTransposeKaliY); */

        Matrix hasil = new Matrix(jumlahVariabel + 1, 1);
        hasil = Matrix.KaliMatriks(InverseTransXKaliX, XTransposeKaliY);
        /* Matrix.TulisMatrix(hasil); */

        Matrix predictData = new Matrix(1, jumlahVariabel + 1);
        System.out.println("Masukkan data yang ingin diregresi: ");
        
        for(int j = 0; j < Matrix.GetKol(predictData); j++){
            Matrix.SetElmt(predictData, 0, j, 1);
        }

        for(int j = 1; j < Matrix.GetKol(predictData); j++){
            System.out.printf("Masukkan data %d: ", j);
            double data = sc.nextDouble();
            Matrix.SetElmt(predictData, 0, j, data);
        }
        /* Matrix.TulisMatrix(predictData); */

        boolean isNan = false;

        for(int i = 0; i < Matrix.GetRow(hasil); i++){
            for(int j = 0; j < Matrix.GetKol(hasil); j++){
                if(Double.isNaN(Matrix.GetElmt(hasil, i, j))){
                    isNan = true;
                    break;
                }
            }
        }

        if(isNan){
            System.out.println("Persamaan regresi linier tidak terdefinisi!");
        }
        else{
            System.out.println("Persamaan regresi linier yang dihasilkan:");
            System.out.printf("y = %.4f ", Matrix.GetElmt(hasil, 0, 0));
            for(int i = 1; i < Matrix.GetRow(hasil); i++){
                if(Matrix.GetElmt(hasil, i, 0) > 0 || Matrix.GetElmt(hasil, i, 0) < 0){
                    System.out.print("+ ");
                }
                else{
                    System.out.print("");
                }

                if(Matrix.GetElmt(hasil, i, 0) > 0){
                    System.out.printf("%.4fx%d ", Matrix.GetElmt(hasil, i, 0), i);
                }
                else if(Matrix.GetElmt(hasil, i, 0) < 0){
                    System.out.printf("(%.4fx%d) ", Matrix.GetElmt(hasil, i, 0), i);
                }
                else{
                    System.out.print("");
                }
            }
            System.out.print("\n");
        }

        Matrix dataKaliHasil = new Matrix(1, 1);
        dataKaliHasil = Matrix.KaliMatriks(predictData, hasil);
        double hasilRegresi = Matrix.GetElmt(dataKaliHasil, 0, 0);
        System.out.printf("Nilai tersebut dari persamaan regresi di atas adalah: %.4f", hasilRegresi);
    }

    public static void FileRegresi(){

        Scanner sc = new Scanner(System.in);

        System.out.print("Masukkan nama file berisikan matriks augmented beserta formatnya: ");
        String filename = sc.next();
        System.out.print("Masukkan jumlah baris dari matriks augmented yang ada di dalam file tersebut: ");
        int jumlahbaris = sc.nextInt();
        System.out.print("Masukkan jumlah kolom dari matriks augmented yang ada di dalam file tersebut: ");
        int jumlahkolom = sc.nextInt();

        Matrix dataRegresi = new Matrix(jumlahbaris, jumlahkolom);
        try {dataRegresi = (main_menu.BacaFileToMatrix(filename, jumlahbaris, jumlahkolom));}
        catch (Exception e){
            System.out.println("File tidak ditemukan.");
        }

        Matrix titikAbsis = new Matrix(jumlahbaris, jumlahkolom - 1);
        for(int i = 0; i < Matrix.GetRow(titikAbsis); i++){
            for(int j = 0; j < Matrix.GetKol(titikAbsis); j++){
                Matrix.SetElmt(titikAbsis, i, j, 1);
            }
        }
        for(int i = 1; i < Matrix.GetRow(titikAbsis); i++){
            for(int j = 0; i < Matrix.GetKol(titikAbsis); j++){
                Matrix.SetElmt(titikAbsis, i, j, Matrix.GetElmt(dataRegresi, i - 1, j));
            }
        }
        
        Matrix titikOrdinat = new Matrix(jumlahbaris, 1);
        for(int i = 0; i < Matrix.GetRow(titikOrdinat); i++){
            Matrix.SetElmt(titikOrdinat, i, 0, Matrix.GetElmt(dataRegresi, i, Matrix.GetKol(dataRegresi) - 1));
        }

        Matrix XTranspose = new Matrix(Matrix.GetKol(titikAbsis), Matrix.GetRow(titikAbsis));
        XTranspose = Matrix.Transpose(titikAbsis);
        /* Matrix.TulisMatrix(XTranspose); */

        Matrix TransposeXKaliX = new Matrix(Matrix.GetRow(XTranspose), Matrix.GetKol(titikAbsis));
        TransposeXKaliX = Matrix.KaliMatriks(XTranspose, titikAbsis);
        /* Matrix.TulisMatrix(TransposeXKaliX); */

        Matrix InverseTransXKaliX = new Matrix(Matrix.GetRow(TransposeXKaliX), Matrix.GetKol(TransposeXKaliX));
        InverseTransXKaliX = Inverse.InverseGaussian(TransposeXKaliX);
        /* Matrix.TulisMatrix(InverseTransXKaliX); */

        Matrix XTransposeKaliY = new Matrix(Matrix.GetRow(XTranspose), Matrix.GetKol(titikOrdinat));
        XTransposeKaliY = Matrix.KaliMatriks(XTranspose, titikOrdinat);
        /* Matrix.TulisMatrix(XTransposeKaliY); */

        Matrix hasil = new Matrix(Matrix.GetKol(titikAbsis), 1);
        hasil = Matrix.KaliMatriks(InverseTransXKaliX, XTransposeKaliY);
        /* Matrix.TulisMatrix(hasil); */

        Matrix predictData = new Matrix(1, Matrix.GetKol(titikAbsis));
        System.out.println("Masukkan data yang ingin diregresi: ");
        
        for(int j = 0; j < Matrix.GetKol(predictData); j++){
            Matrix.SetElmt(predictData, 0, j, 1);
        }

        for(int j = 1; j < Matrix.GetKol(predictData); j++){
            System.out.printf("Masukkan data %d: ", j);
            double data = sc.nextDouble();
            Matrix.SetElmt(predictData, 0, j, data);
        }
        /* Matrix.TulisMatrix(predictData); */

        boolean isNan = false;

        for(int i = 0; i < Matrix.GetRow(hasil); i++){
            for(int j = 0; j < Matrix.GetKol(hasil); j++){
                if(Double.isNaN(Matrix.GetElmt(hasil, i, j))){
                    isNan = true;
                    break;
                }
            }
        }

        if(isNan){
            System.out.println("Persamaan regresi linier tidak terdefinisi!");
        }
        else{
            System.out.println("Persamaan regresi linier yang dihasilkan:");
            System.out.printf("y = %.4f ", Matrix.GetElmt(hasil, 0, 0));
            for(int i = 1; i < Matrix.GetRow(hasil); i++){
                if(Matrix.GetElmt(hasil, i, 0) > 0 || Matrix.GetElmt(hasil, i, 0) < 0){
                    System.out.print("+ ");
                }
                else{
                    System.out.print("");
                }

                if(Matrix.GetElmt(hasil, i, 0) > 0){
                    System.out.printf("%.4fx%d ", Matrix.GetElmt(hasil, i, 0), i);
                }
                else if(Matrix.GetElmt(hasil, i, 0) < 0){
                    System.out.printf("(%.4fx%d) ", Matrix.GetElmt(hasil, i, 0), i);
                }
                else{
                    System.out.print("");
                }
            }
            System.out.print("\n");
        }

        Matrix dataKaliHasil = new Matrix(1, 1);
        dataKaliHasil = Matrix.KaliMatriks(predictData, hasil);
        double hasilRegresi = Matrix.GetElmt(dataKaliHasil, 0, 0);
        System.out.printf("Nilai tersebut dari persamaan regresi di atas adalah: %.4f", hasilRegresi);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan jumlah data: ");
        int jumlahData = sc.nextInt();
        System.out.print("Masukkan jumlah variabel independen: ");
        int jumlahVariabel = sc.nextInt();
        while(jumlahData < 0 || jumlahVariabel < 0){
            if(jumlahData < 0 && jumlahVariabel < 0){
                System.out.println("Jumlah data dan variabel independen invalid. Masukkan lagi:");
                System.out.print("Jumlah data: ");
                jumlahData = sc.nextInt();
                System.out.print("Jumlah variabel independen: ");
                jumlahVariabel = sc.nextInt();
            }
            else if(jumlahData < 0){
                System.out.print("Jumlah data invalid. Masukkan jumlah baru: ");
                jumlahData = sc.nextInt();
            }
            else if(jumlahVariabel < 0){
                System.out.print("Jumlah variabel independen invalid. Masukkan jumlah baru: ");
                jumlahVariabel = sc.nextInt();
            }
        }

        MultiRegresi(jumlahData, jumlahVariabel);
    }
}
