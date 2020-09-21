import java.util.Scanner;
/*File header untuk program yang akan dipakai*/
//File ini berisi definisi matriks, fungsi primitif konstruktor, dan selektor matriks//

final public class Matrix{
    //Kamus Lokal//
    private static final int NBrsMin=0; //jumlah row minimum
    private static final int NKolMin=0; //jumlah row maksimum
    private final int nbrs;             // jumlah row
    private final int nkol;             // jumlah column
    private final double[][] data;      // inisialisasi matriks

    //KONSTRUKTOR MATRIX//
    public Matrix(int brs,int kol) {
    //membuat matrix dengan ukuran brs x kol berisi 0//
        this.nbrs = brs;
        this.nkol = kol;
        data = new double[brs][kol];
    }

    public Matrix(double[][] data) {
    /*membuat matrix dari data bertipe double jika sudah ada sebelumnya
    contohnya doubl[][]d= {1,2,3},{2,3,4},{3,4,5}*/
        nbrs = data.length;
        nkol = data[0].length;
        this.data = new double[nbrs][nkol];
        for (int i = NBrsMin; i < nbrs; i++)
            for (int j = NKolMin; j < nkol; j++)
                this.data[i][j] = data[i][j];
    }
    //TYPEDEF MATRIX//
    //dari kedua konstruktor diatas, dibuat suatu adt yang bisa dipanggil yaitu Matrix//
    public Matrix(Matrix A) {
        this(A.data);
    }
    //FUNGSI BOOLEAN//
    public static boolean IsBrsValid(int nbaris){
        //mengembalikan true jika baris jumlah valid, yaitu diatas 0//
        boolean valid=true;
        if (nbaris<NBrsMin){
            valid=false;
        }
        return valid;
    }
    public static boolean IsKolValid(int nkolom){
        //mengembalikan true jika jumlah kolom valid, yaitu diatas 0//
        boolean valid2=true;
        if (nkolom<NKolMin){
            valid2=false;
        }
        return valid2;
    }
    //FUNGSI INPUT//
    public static Matrix MakeMatrix() {
    //I.S. sembarang//
    //F.S. terdefinisi suatu matrix dengan ukuran sesuai input dan nilai isi sesuai input//
    //cara pakai -> Matrix nama_variabel=MakeMatrix();//
    /*Proses menerima input a,b yang adalah jumlah kolom dan baris, jika belum benar, 
    masing-masing diinput hingga benar, kemudian menerima input elemen matrix secara traversal*/
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukan jumlah baris: ");
        int a = sc.nextInt();
        while (!IsBrsValid(a)){
            System.out.println("Maaf Masukan belum benar");
            System.out.print("Masukan jumlah baris: ");
            a = sc.nextInt();
        }
        System.out.print("Masukan jumlah kolom: ");
        int b = sc.nextInt();
        while (!IsKolValid(b)){
            System.out.println("Maaf Masukan belum benar");
            System.out.print("Masukan jumlah kolom: ");
            b = sc.nextInt();
        }
        Matrix A = new Matrix(a, b);
        for (int i = NBrsMin; i < a; i++) {
            for (int j = NKolMin; j < b; j++) {
                System.out.print("Masukan elemen ke [" + i + "][" + j + "]: ");
                A.data[i][j] = sc.nextDouble();
            }
        }
        return A;
    }
    //FUNGSI OUTPUT//
    public static double GetElmt(Matrix M, int i, int j){
        return M.data[i][j];
    }
    public static void TulisMatrix(Matrix M) {
        //I.S. Matrix terdefinisi//
        //F.S. Matrix di output ke layar//
        // cara pakai -> TulisMatrix(M) (asumsi M matrix yg sudah terdefinisi)//
        //Proses mengoutputkan matrix ke layar dengan format yang enak dilihat//
        for (int i = NBrsMin; i < M.nbrs; i++) {
            for (int j = NKolMin; j < M.nkol; j++) {
                if (j == M.nkol - 1) {
                    System.out.println(M.data[i][j]+"\n");
                } 
                else {
                    System.out.print(M.data[i][j]+"  ");
                }
            }
        }
    }

    public static void main( String[] args) {
        Matrix M1=MakeMatrix();
        System.out.println("");
        TulisMatrix(M1);
    }
}
