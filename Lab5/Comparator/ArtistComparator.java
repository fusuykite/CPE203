import java.util.Comparator;

public class ArtistComparator implements Comparator<Song>
{
    public int compare(Song s1, Song s2)
    {
        int diff = (int) s1.getArtist().charAt(0) - (int) s2.getArtist().charAt(0);
        if (diff > 0)
        {
            return 1;
        } else if (diff < 0)
        {
            return -1;
        } else
        {
            return diff;
        }
    }
}
