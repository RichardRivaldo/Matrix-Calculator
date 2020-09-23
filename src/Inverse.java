import java.util.Scanner;
//file java untuk inverse dan method methodnya//
public class Inverse {
    public static void main(String[] args) {
        Matrix A= Matrix.MakeSquareMatrix();
        Matrix.TulisMatrix(A);
        A=InverseGaussian(A);
        Matrix.TulisMatrix(A);
    }
    public static Matrix InverseGaussian(Matrix a)
    {   //inverse matrix dengan gaussian//
        //initialize variabel dan matrix satuan//
        int n= a.data.length;
        Matrix x=new Matrix(n,n);
        Matrix b= new Matrix(n,n); //matrix satuan//
        int index[]= new int[n];
        for (int i=0;i<n;++i){
            Matrix.SetElmt(b,i,i,1);
        }
        //Ubah matrix ke bentuk upper triangle//
        gaussian(a,index);
        //Update the matrix b sesuai dengan rasio//
        for (int i=0;i<n-1;++i){
            for (int j=i+1;j<n;++j){
                for (int k=0;k<n;++k){
                    b.data[index[j]][k]-=Matrix.GetElmt(a,index[j],i)*Matrix.GetElmt(b, index[i], k);
                }
            }
        }
        //lakukan backward substitution//
        for (int i=0;i<n;++i){
            x.data[n-1][i]=(Matrix.GetElmt(b,index[n-1],i))/(Matrix.GetElmt(a, index[n-1], n-1));
            for (int j=n-2;j>=0;--j){
                x.data[j][i]=Matrix.GetElmt(b,index[j],i);
                for (int k=j+1;k<n;++k){
                    x.data[j][i]-=Matrix.GetElmt(a, index[j], k)*Matrix.GetElmt(x, k, i);
                }
                x.data[j][i]/=Matrix.GetElmt(a, index[j], j);
            }
        }
        return x;
    }
    public static void gaussian(Matrix a, int index[]){
        int n=index.length;
        double c[]=new double[n];
        //Initialize index
        for (int i=0;i<n;++i){
            index[i]=i;
        }
        //find rescaling factors//
        for (int i=0;i<n;++i){
            double c1=0;
            for (int j=0;j<n;++j){
                double c0=Math.abs(a.data[i][j]);
                if(c0>c1){
                    c1=c0;
                }
            }
            c[i]=c1;
        }
        //search pivot element//
        int k=0;
        for (int j=0;j<n-1;++j){
            double pivot1=0;
            for(int i=j;i<n;++i){
                double pivot0=Math.abs(a.data[index[i]][j]);
                pivot0/=c[index[i]];
                if (pivot0>pivot1){
                    pivot1=pivot0;
                    k=i;
                }
            }
            //swap rows according to the pivot
            int temp=index[j];
            index[j]=index[k];
            index[k]=temp;
            for (int i=j+1;i<n;++i){
                double pj=Matrix.GetElmt(a, index[i], j)/Matrix.GetElmt(a, index[j], j);
                //record pivoting ratios//
                a.data[index[i]][j]=pj;
                //modify the other elements//
                for (int l=j+1;l<n;++l){
                    a.data[index[i]][l]-=pj*a.data[index[j]][l];
                }
            }
        }
    }
}
