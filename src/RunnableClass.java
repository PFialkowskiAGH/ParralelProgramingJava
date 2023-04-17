public class RunnableClass implements Runnable
{
    Obraz obraz;
    int startChar;
    int endChar;

    public RunnableClass(int startChar, int endChar, Obraz obraz)
    {
        this.obraz = obraz;
        this.startChar = startChar;
        this.endChar = endChar;
    }

    @Override
    public void run()
    {
        for (int i = startChar; i < endChar; i++)
        {
            obraz.calculate_histogram_for_char(i);
            obraz.print_histogram_for_char(i);
        }
    }
}
