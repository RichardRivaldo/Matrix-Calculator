import java.util.Scanner;
import java.io.FileReader;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
/*Main file, main menu, program utama yg akan dijalankan*/
public class main_menu {
    public static void PrintMainMenu(){
        //prosedur penampilan main menu ke layar//
        System.out.print("Main Menu\n1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks Balikan\n4. Interpolasi Polinom\n5. Regresi Linier Berganda\n6. Keluar\n");
    }
    public static void PrintSubMenu1(){
        //prosedur penampilan submenu untuk pilihan nomor 1//
        System.out.print("Sub Menu 1\n1. Metode Eliminasi Gauss\n2. Metode Eliminasi Gauss-Jordan\n3. Metode Matriks Balikan\n4. Kaidah Cramer\n5. Kembali\n");
        System.out.print("Silakan input angka 1-5 sesuai pilihan: ");}
    public static void PrintSubMenu2(){
        //prosedur penampilan submenu untuk pilihan nomor 2//
        System.out.print("Sub Menu 2\n1. Metode ekspansi kofaktor\n2. Metode Reduksi Baris\n3. Kembali\n");
        System.out.print("Silakan input angka 1-3 sesuai pilihan: ");
    }
    public static void PrintSubMenu3(){
        //prosedur penampilan submenu untuk pilihan nomor 3//
        System.out.print("Sub Menu 3\n1. Metode Eliminasi Gauss-Jordan\n2. Metode Adjoin\n3. Kembali\n");
        System.out.print("Silakan input angka 1-3 sesuai pilihan: ");
    }
    public static int IsWithinRange(int x,int a, int b){
        //prosedur check apakah dia dalam suatu range atau tidak//
        Scanner scan=new Scanner(System.in);
        while (x<a || x>b){
            System.out.println("Mohon maaf, pilihan belum benar, silakan coba lagi!");
            System.out.print("Silakan input angka yang sesuai: ");
            x=scan.nextInt();
        }
        return x;
    }
    public static Matrix BacaFileToMatrix(String namafile,int a,int b) throws Exception{
        //prosedur membaca dari namafile.txt ke matrix//
        //parameter namafile:string (nama dari file) a:jumlah row b: jumlah kolom//
        Scanner sc = new Scanner(new BufferedReader(new FileReader(namafile)));
        Matrix M=new Matrix(a,b);
        while(sc.hasNextLine()) {
            for (int i=0; i<M.data.length; i++) {
               String[] line = sc.nextLine().trim().split(" ");
               for (int j=0; j<line.length; j++) {
                  M.data[i][j] = Double.parseDouble(line[j]);
               }
            }
         }
        return M;
    }
    public static void PrintSubMenuInput(){
        System.out.print("Silakan pilih stream input\n");
        System.out.print("1. Baca File\n");
        System.out.print("2. Keyboard\n");
        System.out.print("Masukkan pilihan (1-2): ");
    }
    public static void main(String[] args) {
        //method utama yang akan dijalankan//
        //inisiasi variabel//
        Scanner sc=new Scanner(System.in);
        int pilihan,pilihan1,pilihan2,pilihan3,pilihaninput;
        String filename,filenamesave;
        char save;

        System.out.println("Selamat Datang di Tugas Besar Algeo!");
        System.out.println("Tubes ini adalah hasil kolaborasi Hizkia (13519087), Rian (13519147), & Richard (13519185)");
        PrintMainMenu();
        System.out.print("Silakan input angka 1-6 sesuai pilihan: ");
        pilihan=sc.nextInt();
        //check apakah pilihan dalam range/tidak//
        pilihan=IsWithinRange(pilihan, 1, 6);
        //ketika sudah dalam range 1-6//
        while (pilihan!=6){
            if (pilihan==1){
                //Untuk SPL disini//
                PrintSubMenu1();
                pilihan1=sc.nextInt();
                //check pilihan1 dalam range atau tidak//
                pilihan1=IsWithinRange(pilihan1, 1, 5);
                //kalau pilihan1 sudah in range//
                while (pilihan1!=5){
                    if (pilihan1==1){
                        //SPL metode eliminasi gauss nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan dimensi matrix(harus sesuai dengan yang ada di file):\n ");
                            System.out.print("Masukan jumlah baris: ");
                            int n=sc.nextInt();
                            System.out.print("Masukan jumlah kolom: ");
                            int m=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, m));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);

                            double[] hasil = SPL.gauss(X);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if (pilihaninput==2){
                            Matrix A=Matrix.MakeMatrix();
                            System.out.println("Berikut matrix anda: ");
                            Matrix.TulisMatrix(A);

                            double[] hasil = SPL.gauss(A);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                    }
                    else if (pilihan1==2){
                        //SPL metode eliminasi Gauss Jordan nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan jumlah baris: ");
                            int n=sc.nextInt();
                            System.out.print("Masukan jumlah kolom: ");
                            int m=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, m));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);

                            double[] hasil = SPL.gaussJordan(X);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if (pilihaninput==2){
                            Matrix A=Matrix.MakeMatrix();
                            System.out.println("Berikut matrix anda: ");
                            Matrix.TulisMatrix(A);

                            double[] hasil = SPL.gaussJordan(A);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                    }
                    else if (pilihan1==3){
                        //SPL metode matrix balikan nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan jumlah baris: ");
                            int n=sc.nextInt();
                            System.out.print("Masukan jumlah kolom: ");
                            int m=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, m));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);

                            double[] hasil = SPL.inverseSPL(X);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if (pilihaninput==2){
                            Matrix A=Matrix.MakeMatrix();
                            System.out.println("Berikut matrix anda: ");
                            Matrix.TulisMatrix(A);

                            double[] hasil = SPL.inverseSPL(A);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                    }
                    else if(pilihan1==4){
                        //SPL metode Cramer nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan jumlah baris: ");
                            int n=sc.nextInt();
                            System.out.print("Masukan jumlah kolom: ");
                            int m=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, m));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);

                            double[] hasil = SPL.cramer(X);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if (pilihaninput==2){
                            Matrix A=Matrix.MakeMatrix();
                            System.out.println("Berikut matrix anda: ");
                            Matrix.TulisMatrix(A);

                            double[] hasil = SPL.cramer(A);
                            for (int i = 0; i < hasil.length; i++) {
                                System.out.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    for (int i = 0; i < hasil.length; i++) {
                                        printWriter.println("Nilai x" + (i+1) + " adalah: " + hasil[i]);
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan\n");
                                }
                            }
                        }
                    }
                    PrintSubMenu1();
                    pilihan1=sc.nextInt();
                    //check range lagi//
                    pilihan1=IsWithinRange(pilihan1, 1, 5);
                }
            }    
            else if (pilihan==2){
                //Untuk Determinan nanti disini//
                PrintSubMenu2();
                pilihan2=sc.nextInt();
                //check range pilihan2//
                pilihan2=IsWithinRange(pilihan2, 1, 3);
                while(pilihan2!=3){
                    if (pilihan2==1){
                        //Determinan metode ekspansi kofaktor nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            //input dari file//
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan dimensi matrix(harus sesuai dengan yang ada di file): ");
                            int n=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, n));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);
                            double det=Determinan.hitungDeterminanEK(X);
                            System.out.println("");
                            System.out.println("Determinan dari matrix tersebut adalah "+det);
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    printWriter.println("Determinan dari matrix tersebut adalah "+det);
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if (pilihaninput==2){
                            //input dari keyboard//
                            Matrix A=Matrix.MakeSquareMatrix();
                            System.out.println("Berikut matrix anda: ");
                            Matrix.TulisMatrix(A);
                            System.out.println("");
                            double det=Determinan.hitungDeterminanEK(A);
                            System.out.println("Determinan dari matrix tersebut adalah "+det);
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    printWriter.println("Determinan dari matrix tersebut adalah "+det);
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                    }
                    else if(pilihan2==2){
                        //Determinan metode row reduction nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            //input dari file//
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan dimensi matrix(harus sesuai dengan yang ada di file): ");
                            int n=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, n));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);
                            double det=Determinan.hitungDeterminanRB(X);
                            System.out.println("");
                            System.out.println("Determinan dari matrix tersebut adalah "+det);
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    printWriter.println("Determinan dari matrix tersebut adalah "+det);
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if (pilihaninput==2){
                            //input dari keyboard//
                            Matrix A=Matrix.MakeSquareMatrix();
                            System.out.println("Berikut matrix anda: ");
                            Matrix.TulisMatrix(A);
                            System.out.println("");
                            double det=Determinan.hitungDeterminanRB(A);
                            System.out.println("Determinan dari matrix tersebut adalah "+det);
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    printWriter.println("Determinan dari matrix tersebut adalah "+det);
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                    }
                    PrintSubMenu2();
                    pilihan2=sc.nextInt();
                    //check range lagi//
                    pilihan2=IsWithinRange(pilihan2, 1, 3);
                }

            }
            else if (pilihan==3){
                //Matrix balikan nanti disini//
                PrintSubMenu3();
                pilihan3=sc.nextInt();
                //check range pilihan3//
                pilihan3=IsWithinRange(pilihan3, 1, 3);
                while (pilihan3!=3){
                    if (pilihan3==1){
                        //Matrix balikan metode gauss jordan nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            //Matrix balikan Gauss jordan input dr File//
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan dimensi matrix(harus sesuai dengan yang ada di file): ");
                            int n=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, n));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            //copy the matrix//
                            Matrix Y=new Matrix(X.nbrs,X.nkol);
                            for (int i=0;i<X.nbrs;i++){
                                for (int j=0;j<X.nkol;j++){
                                    Y.data[i][j]=X.data[i][j];
                                }
                            }
                            //
                            System.out.println("");
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);
                            System.out.println("");
                            if (Determinan.hitungDeterminanRB(Y)==0){
                                System.out.println("Matrix tidak memiliki inverse!");
                                System.out.println("");
                            }
                            else{
                                System.out.println("Berikut Inversenya: ");
                                X=Inverse.InverseGaussian(X);
                                Matrix.TulisMatrix(X);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    if (Determinan.hitungDeterminanRB(Y)==0){
                                        printWriter.println("Matrix tidak memiliki inverse!");
                                    }
                                    else{
                                        for (int i=0;i<X.nbrs;i++){
                                            for (int j=0;j<X.nkol;j++){
                                                if (j == X.nkol - 1) {
                                                    printWriter.println(X.data[i][j]+"\n");
                                                } 
                                                else {
                                                    printWriter.print(X.data[i][j]+"  ");
                                                }  
                                            }
                                        }
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if(pilihaninput==2){
                            //Matrix balikan gauss jordan, input dr keyboard//
                            Matrix A= Matrix.MakeSquareMatrix();
                            System.out.println("Berikut matrix Anda: ");
                            Matrix.TulisMatrix(A);
                            Matrix B=new Matrix(A.nbrs,A.nkol);
                            //apparently we needed a new matrix so the old matrix ga diubah bentuknya, just to check determinan//
                            for (int i=0;i<A.nbrs;i++){
                                for (int j=0;j<A.nbrs;j++){
                                    B.data[i][j]=A.data[i][j];
                                }
                            }
                            double det=Determinan.hitungDeterminanRB(B);
                            if(det==0){
                                System.out.println("Matrix tidak memiliki inverse!");
                            }
                            else{
                                A=Inverse.InverseGaussian(A);
                                Matrix.TulisMatrix(A);}
                                System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                                save=sc.next().charAt(0);
                                if (save=='Y' || save=='y'){
                                    System.out.print("Masukan nama file: ");
                                    filenamesave=sc.next();
                                    try{
                                        FileWriter fileWriter = new FileWriter(filenamesave);
                                        PrintWriter printWriter = new PrintWriter(fileWriter);
                                        if (Determinan.hitungDeterminanRB(B)==0){
                                            printWriter.println("Matrix tidak memiliki inverse!");
                                        }
                                        else{
                                            for (int i=0;i<A.nbrs;i++){
                                                for (int j=0;j<A.nkol;j++){
                                                    if (j == A.nkol - 1) {
                                                        printWriter.println(A.data[i][j]+"\n");
                                                    } 
                                                    else {
                                                        printWriter.print(A.data[i][j]+"  ");
                                                    }  
                                                }
                                            }
                                        }
                                        printWriter.close();
                                        System.out.println("Data berhasil disimpan!");
                                    }
                                    catch (Exception e){
                                        System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                    }
                                }
                        }
                    }
                    else if (pilihan3==2){
                        //Matrix balikan metode adjoin nanti disini//
                        PrintSubMenuInput();
                        pilihaninput=sc.nextInt();
                        pilihaninput=IsWithinRange(pilihaninput, 1, 2);
                        if (pilihaninput==1){
                            //Matrix balikan Adjoin input dr File//
                            Matrix X= new Matrix(5,5);
                            System.out.print("Masukan nama file: ");
                            filename=sc.next();
                            System.out.print("Masukan dimensi matrix(harus sesuai dengan yang ada di file): ");
                            int n=sc.nextInt();
                            try{X=(BacaFileToMatrix(filename, n, n));}
                            catch (Exception e){
                                System.out.println("File tidak ditemukan!");
                            }
                            //copy the matrix
                            Matrix Y=new Matrix(X.nbrs,X.nkol);
                            for (int i=0;i<X.nbrs;i++){
                                for (int j=0;j<X.nbrs;j++){
                                    Y.data[i][j]=X.data[i][j];
                                }
                            }
                            System.out.println("");
                            System.out.println("Berikut matrix dari file tersebut: ");
                            Matrix.TulisMatrix(X);
                            System.out.println("");
                            if (Determinan.hitungDeterminanRB(Y)==0){
                                System.out.println("Matrix tidak memiliki inverse!");
                                System.out.println("");
                            }
                            else{
                                System.out.println("Berikut Inversenya: ");
                                X=Inverse.InverseAdjoin(X);
                                Matrix.TulisMatrix(X);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    if (Determinan.hitungDeterminanRB(Y)==0){
                                        printWriter.println("Matrix tidak memiliki inverse!");
                                    }
                                    else{
                                        for (int i=0;i<X.nbrs;i++){
                                            for (int j=0;j<X.nkol;j++){
                                                if (j == X.nkol - 1) {
                                                    printWriter.println(X.data[i][j]+"\n");
                                                } 
                                                else {
                                                    printWriter.print(X.data[i][j]+"  ");
                                                }  
                                            }
                                        }
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                        else if(pilihaninput==2){
                            //Matrix balikan Adjoin, input dr keyboard//
                            Matrix A= Matrix.MakeSquareMatrix();
                            System.out.println("Berikut matrix Anda: ");
                            Matrix.TulisMatrix(A);
                            Matrix B=new Matrix(A.nbrs,A.nkol);
                            //copy the matrix//
                            for (int i=0;i<A.nbrs;i++){
                                for (int j=0;j<A.nbrs;j++){
                                    B.data[i][j]=A.data[i][j];
                                }
                            }
                            double det=Determinan.hitungDeterminanRB(B);
                            if(det==0){
                                System.out.println("Matrix tidak memiliki inverse!");
                            }
                            else{
                                A=Inverse.InverseAdjoin(A);
                                Matrix.TulisMatrix(A);
                            }
                            System.out.print("Apakah anda ingin save jawaban? (Y/N): ");
                            save=sc.next().charAt(0);
                            if (save=='Y' || save=='y'){
                                System.out.print("Masukan nama file: ");
                                filenamesave=sc.next();
                                try{
                                    FileWriter fileWriter = new FileWriter(filenamesave);
                                    PrintWriter printWriter = new PrintWriter(fileWriter);
                                    if (Determinan.hitungDeterminanRB(B)==0){
                                        printWriter.println("Matrix tidak memiliki inverse!");
                                    }
                                    else{
                                        for (int i=0;i<A.nbrs;i++){
                                            for (int j=0;j<A.nkol;j++){
                                                if (j == A.nkol - 1) {
                                                    printWriter.println(A.data[i][j]+"\n");
                                                } 
                                                else {
                                                    printWriter.print(A.data[i][j]+"  ");
                                                }  
                                            }
                                        }
                                    }
                                    printWriter.close();
                                    System.out.println("Data berhasil disimpan!");
                                }
                                catch (Exception e){
                                    System.out.println("Mohon maaf tidak bisa dilakukan!\n");
                                }
                            }
                        }
                    }
                    PrintSubMenu3();
                    pilihan3=sc.nextInt();
                    //check range lagi//
                    pilihan3=IsWithinRange(pilihan3, 1, 3);
                }
            }
            else if (pilihan==4){
                PrintSubMenuInput();
                pilihaninput = sc.nextInt();
                pilihaninput=IsWithinRange(pilihaninput, 1, 2);

                if(pilihaninput == 1){
                    Interpolasi.FileInterpolasi();
                }
                else{
                    Interpolasi.main(null);
                }
                System.out.println("");
            }
            else if (pilihan==5){
                PrintSubMenuInput();
                pilihaninput = sc.nextInt();
                pilihaninput=IsWithinRange(pilihaninput, 1, 2);

                if(pilihaninput == 1){
                    RegresiGanda.FileRegresi();
                }
                else{
                    RegresiGanda.main(null);
                }
                System.out.println("");
            }
            PrintMainMenu();
            System.out.print("Silakan input angka 1-6 sesuai pilihan: ");
            pilihan=sc.nextInt();
            //check range pilihan//
            pilihan=IsWithinRange(pilihan, 1, 6);
        }
        System.out.println("Terima Kasih! Sampai jumpa kembali.");
        System.out.println("Shutting down...");
    }
}
