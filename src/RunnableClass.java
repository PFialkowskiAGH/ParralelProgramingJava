public class RunnableClass implements Runnable
{
    Obraz obraz;
    int searchedChars[];

    public RunnableClass(int[] searchedChars, Obraz obraz)
    {
        this.obraz = obraz;
        this.searchedChars = searchedChars;
    }

    @Override
    public void run() {
        for (int i = 0; i < searchedChars.length; i++)
        {
            obraz.calculate_histogram_for_char(i);
            obraz.print_histogram_for_char(i);
        }
    }
}
