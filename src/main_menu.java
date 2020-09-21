import java.util.Scanner;
/*Main file, main menu, program utama yg akan dijalankan*/
public class main_menu {
    public static void PrintMainMenu(){
        //prosedur penampilan main menu ke layar//
        System.out.print("Main Menu\n1. Sistem Persamaan Linier\n2. Determinan\n3. Matriks Balikan\n4. Interpolasi Polinom\n5. Regresi Linier Berganda\n6. Keluar\n");
    }
    public static void PrintSubMenu1(){
        //prosedur penampilan submenu untuk pilihan nomor 1//
        System.out.print("Sub Menu 1\n1. metode eliminasi gauss\n2. metode eliminasi gauss-jordan\n3. metode matriks balikan\n4. kaidah cramer\n5. kembali\n");
        System.out.print("Silakan input angka 1-5 sesuai pilihan: ");}
    public static void PrintSubMenu2(){
        //prosedur penampilan submenu untuk pilihan nomor 2//
        System.out.print("Sub Menu 2\n1. metode ekspansi kofaktor\n2. metode reduksi baris\n3. kembali\n");
        System.out.print("Silakan input angka 1-3 sesuai pilihan: ");
    }
    public static void PrintSubMenu3(){
        //prosedur penampilan submenu untuk pilihan nomor 3//
        System.out.print("Sub Menu 3\n1. metode eliminasi gauss-jordan\n2. metode adjoin\n3. kembali\n");
        System.out.print("Silakan input angka 1-3 sesuai pilihan: ");
    }
    public static void IsWithinRange(int x,int a, int b){
        Scanner scan=new Scanner(System.in);
        if (x<a || x>b){
            while (x<a || x>b){
            System.out.println("Mohon maaf, pilihan belum benar, silakan coba lagi");
            System.out.print("Silakan input angka yang sesuai : ");
            x=scan.nextInt();}
            }
    }

    public static void main(String[] args) {
        //method utama yang akan dijalankan//
        //inisiasi variabel//
        Scanner sc=new Scanner(System.in);
        int pilihan,pilihan1,pilihan2,pilihan3;

        System.out.println("Selamat Datang di Tugas Besar Algeo!");
        System.out.println("Tubes ini adalah hasil kolaborasi Hizkia (13519087), Rian (13519147), & Richard (13519185)");
        PrintMainMenu();
        System.out.print("Silakan input angka 1-6 sesuai pilihan: ");
        pilihan=sc.nextInt();
        //check apakah pilihan dalam range/tidak//
        IsWithinRange(pilihan, 1, 6);
        //ketika sudah dalam range 1-6//
        while (pilihan!=6){
            if (pilihan==1){
                //Untuk SPL disini//
                PrintSubMenu1();
                pilihan1=sc.nextInt();
                //check pilihan1 dalam range atau tidak//
                IsWithinRange(pilihan1, 1, 5);
                //kalau pilihan1 sudah in range//
                while (pilihan1!=5){
                    if (pilihan1==1){
                        //SPL metode eliminasi gauss nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    else if (pilihan1==2){
                        //SPL metode eliminasi Gauss Jordan nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    else if (pilihan1==3){
                        //SPL metode matrix balikan nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    else if(pilihan1==4){
                        //SPL metode Cramer nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    PrintSubMenu1();
                    pilihan1=sc.nextInt();
                    //check range lagi//
                    IsWithinRange(pilihan1, 1, 5);
                }
            }    
            else if (pilihan==2){
                //Untuk Determinan nanti disini//
                PrintSubMenu2();
                pilihan2=sc.nextInt();
                //check range pilihan2//
                IsWithinRange(pilihan2, 1, 3);
                while(pilihan2!=3){
                    if (pilihan2==1){
                        //Determinan metode ekspansi kofaktor nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    else if(pilihan2==2){
                        //Determinan metode row reduction nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    PrintSubMenu2();
                    pilihan2=sc.nextInt();
                    //check range lagi//
                    IsWithinRange(pilihan2, 1, 3);
                }

            }
            else if (pilihan==3){
                //Matrix balikan nanti disini//
                PrintSubMenu3();
                pilihan3=sc.nextInt();
                //check range pilihan3//
                IsWithinRange(pilihan3, 1, 3);
                while (pilihan3!=3){
                    if (pilihan3==1){
                        //Matrix balikan metode gauss jordan nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    else if (pilihan3==2){
                        //Matrix balikan metode adjoin nanti disini//
                        System.out.println("Masih dalam progress :)");
                    }
                    PrintSubMenu3();
                    pilihan3=sc.nextInt();
                    //check range lagi//
                    IsWithinRange(pilihan3, 1, 3);
                }
            }
            else if (pilihan==4){
                //Interpolasi polinom nanti disini//
                System.out.println("Masih dalam progress :)");
            }
            else if (pilihan==5){
                //Regresi linier nanti disini//
                System.out.println("Masih dalam progress :)");
            }
            PrintMainMenu();
            System.out.print("Silakan input angka 1-6 sesuai pilihan: ");
            pilihan=sc.nextInt();
            //check range pilihan//
            IsWithinRange(pilihan, 1, 6);
        }
        System.out.println("Terima Kasih !! Sampai jumpa kembali");
    }
        
}
