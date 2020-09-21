public class Determinan {
    public static double det2(Matrix M){
        return (Matrix.GetElmt(M, 0, 0) * Matrix.GetElmt(M, 1, 1)) - (Matrix.GetElmt(M, 0, 1) * Matrix.GetElmt(M, 1, 0));
    }
    public static void main(String[] args){
        Matrix M = new Matrix(1,1);
        M = Matrix.MakeMatrix();
        System.out.println(det2(M));
        Matrix.TulisMatrix(M);
    }
}
