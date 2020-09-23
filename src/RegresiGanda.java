import java.util.Scanner;

public class RegresiGanda {
    public static void MultiRegresi(int jumlahTitik){
        int derajatinterpolasi = jumlahTitik - 1;
        Matrix titikAbsis = new Matrix(jumlahTitik, jumlahTitik);
        Matrix titikOrdinat = new Matrix(jumlahTitik, 1);
        Matrix slope = new Matrix(jumlahTitik, 1);
        Scanner sc = new Scanner(System.in);

        for(int n = 0; n < jumlahTitik; n++){
            int X = sc.nextFloat();
            int Y = sc.nextFloat();
            for(int i = 0; i < Matrix.GetRow(titikAbsis); i++){
                for(int j = 0; j < Matrix.GetKol(titikAbsis); j++){
                    Matrix.SetElmt(titikAbsis, i, j, Math.pow(X, j));
                }
            }
            for(int i = 0; i < Matrix.GetRow(titikOrdinat); i++){
                for(int j = 0; i < Matrix.GetRow(titikOrdinat); j++){
                    Matrix.SetElmt(titikOrdinat, i, j, Y);
                }
            }
        }
        Matrix transpose = Matrix.Transpose(titikAbsis);
        Matrix kalitranspose = Matrix.KaliMatriks(transpose, titikAbsis);
        Matrix invers = Inverse.InverseGaussian(kalitranspose);
        Matrix kalitransposeY = Matrix.KaliMatriks(transpose, titikOrdinat);
        slope = Matrix.KaliMatriks(kalitranspose, kalitransposeY);
    }
}
