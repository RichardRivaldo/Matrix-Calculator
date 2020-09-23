import java.util.Scanner;
//file java untuk inverse dan method methodnya//
public class Inverse {
    public static Matrix InverseGaussian(Matrix a)
    {   //inverse matrix dengan gaussian//
        //initialize variabel dan matrix satuan//
        int n= a.nbrs;
        Matrix x=new Matrix(n,n);
        Matrix b= new Matrix(n,n); //matrix satuan//
        int index[]= new int[n];
        for (int i=0;i<n;i++){
            Matrix.SetElmt(a,i,i,1);
        }
        //Ubah matrix ke bentuk upper triangle//
        gaussian(a,index);
        //Update the matrix b sesuai dengan rasio//
        for (int i=0;i<n-1;i++){
            for (int j=i+1;j<n;j++){
                for (int k=0;k<n;k++){
                    b.data[index[j]][k]-=Matrix.GetElmt(a,index[j],i)*Matrix.GetElmt(b, index[i], k);
                }
            }
        }
        //lakukan backward substitution//
        for (int i=0;i<n;i++){
            x.data[n-1][i]=(Matrix.GetElmt(b,index[n-1],i))/(Matrix.GetElmt(a, index[n-1], n-1));
            for (int j=n-1;j>0;j--){
                x.data[j][i]=Matrix.GetElmt(b,index[j],i);
                for (int k=j+1;k<n;k++){
                    x.data[j][i]-=Matrix.GetElmt(a, index[j], k)*Matrix.GetElmt(x, k, i);
                }
                x.data[j][i]/=Matrix.GetElmt(a, index[j], j);
            }
        }
        return x;
    }
}
