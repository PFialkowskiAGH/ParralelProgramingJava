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

        Watek[] newThr = new Watek[num_threads];

        for (int i = 0; i < num_threads; i++) {
            (newThr[i] = new Watek(i,obraz_1)).start();
       }

        for (int i = 0; i < num_threads; i++) {
             try {
         	newThr[i].join();
             } catch (InterruptedException e) {}
        }

        obraz_1.compareHistogram();

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Set number of threads");
        obraz_1.clear_only_parallelHistogram();
        num_threads = scanner.nextInt();
        int d = 94 % num_threads;
        int r = 94 / num_threads;

        Thread[] newThr2 = new Thread[num_threads];

        for (int i = 0; i < num_threads; i ++)
        {
            if (i == num_threads-1) (newThr2[i] = new Thread(new RunnableClass(i*r,i*r+r+d,obraz_1))).start();
            else (newThr2[i] = new Thread(new RunnableClass(i*r,i*r+r,obraz_1))).start();
        }
        for (int i = 0; i < num_threads; i++) {
            try {
                newThr2[i].join();
            } catch (InterruptedException e) {}
        }

        obraz_1.compareHistogram();

        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Every row of random charsTab is new Thread");
        obraz_1.clear_only_parallelHistogram();

        num_threads = n;
        Thread[] newThr3 = new Thread[num_threads];

        for (int i = 0; i < num_threads; i++)
        {
            (newThr3[i] = new Thread(new RunnableClass2(i,obraz_1))).start();
        }
        for (int i = 0; i < num_threads; i++) {
            try {
                newThr3[i].join();
            } catch (InterruptedException e) {}
        }

        obraz_1.compareHistogram();
    }
}
