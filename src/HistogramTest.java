import java.util.Scanner;

public class HistogramTest
{
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Set image size: n (#rows), m(#kolumns)");
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Obraz obraz_1 = new Obraz(n, m);

        obraz_1.calculate_histogram();
        obraz_1.print_histogram();

        System.out.println("---------------------------------------------------------------------------");
        //System.out.println("Set number of threads");
        int num_threads = 94;//scanner.nextInt();

        Watek[] NewThr = new Watek[num_threads];

        for (int i = 0; i < num_threads; i++) {
            (NewThr[i] = new Watek(i,obraz_1)).start();
       }

        for (int i = 0; i < num_threads; i++) {
             try {
         	NewThr[i].join();
             } catch (InterruptedException e) {}
        }

        obraz_1.compareHistogram();

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Set number of threads");
        num_threads = scanner.nextInt();
        int d = 94 % num_threads;
        int r = 94 / num_threads;

        for (int i = 0; i < num_threads; i++)
        {
            if (i == num_threads-1) new Thread(new RunnableClass(i,i+r+d,obraz_1)).start();
            else new Thread(new RunnableClass(i,i+r,obraz_1)).start();
        }

        obraz_1.compareHistogram();
    }
}
