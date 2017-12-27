
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;


public class TestCases
{
   private static final Song[] songs = new Song[] {
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Gerry Rafferty", "Baker Street", 1978)
      };

   @Test
   public void testArtistComparator()
   {
      ArtistComparator comp = new ArtistComparator();
      int val = comp.compare(new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007));

      assertEquals(-1, val);

      int newVal = comp.compare(new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
              new Song("Decemberists", "The Mariner's Revenge Song", 2005));

      assertEquals(1, newVal);
   }

   @Test
   public void testLambdaTitleComparator()
   {
      Comparator<Song> newComp = (Song s1, Song s2) ->
      {
         int diff = s1.getTitle().charAt(0) - s2.getTitle().charAt(0);
         if (diff < 0)
            return -1;
         else if (diff > 0)
            return 1;
         else
            return diff;
      };

      int result = newComp.compare(new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007));

      assertTrue(result > 0);

      int result2 = newComp.compare(new Song("Gerry Rafferty", "Baker Street", 1978),
              new Song("Gerry Rafferty", "Baker Street", 1998));

      assertTrue(result2 == 0);
   }

   @Test
   public void testYearExtractorComparator()
   {
      Comparator<Song> extractorComp = Comparator.comparingInt(Song::getYear);
      extractorComp = extractorComp.reversed();
      int result = extractorComp.compare(
              new Song("Avett Brothers", "Talk on Indolence", 2006),
              new Song("City and Colour", "Sleeping Sickness", 2007));

      int result2 = extractorComp.compare(
              new Song("City and Colour", "Sleeping Sickness", 2007),
              new Song("Queen", "Bohemian Rhapsody", 1975));

      assertTrue(result > 0);
      assertTrue(result2 < 0);

   }

   @Test
   public void testComposedComparator()
   {
      ArtistComparator comp = new ArtistComparator();
      Comparator<Song> extractorComp = Comparator.comparingInt(Song::getYear);
      extractorComp = extractorComp.reversed();
      ComposedComparator cc = new ComposedComparator(comp, extractorComp);
      int result = cc.compare(
                        new Song("Gerry Rafferty", "Baker Street", 1978),
                        new Song("Gerry Rafferty", "Baker Street", 1998));

      int result2 = cc.compare(
                        new Song("Rogue Wave", "Love's Lost Guarantee", 2005),
                        new Song("Decemberists", "The Mariner's Revenge Song", 2005));

      int result3 = cc.compare(
                        new Song("Foo Fighters", "Baker Street", 1997),
                        new Song("Gerry Rafferty", "Baker Street", 1978));

      assertTrue(result > 0);
      assertTrue(result2 > 0);
      assertTrue(result3 < 0);

   }

   @Test
   public void testThenComparing()
   {
      Comparator<Song> comp = Comparator.comparing(Song::getTitle);
      Comparator<Song> comp2 = comp.thenComparing(Song::getArtist);

      int result = comp2.compare(
                        new Song("Foo Fighters", "Baker Street", 1997),
                        new Song("Gerry Rafferty", "Baker Street", 1978));

      int result2 = comp2.compare(
                        new Song("Decemberists", "The Mariner's Revenge Song", 2005),
                        new Song("Foo Fighters", "Baker Street", 1997));

      assertTrue(result < 0);



   }

   @Test
 public void runSort()
   {
      List<Song> songList = new ArrayList<>(Arrays.asList(songs));
      List<Song> expectedList = Arrays.asList(
         new Song("Avett Brothers", "Talk on Indolence", 2006),
         new Song("City and Colour", "Sleeping Sickness", 2007),
         new Song("Decemberists", "The Mariner's Revenge Song", 2005),
         new Song("Foo Fighters", "Baker Street", 1997),
         new Song("Gerry Rafferty", "Baker Street", 1978),
         new Song("Gerry Rafferty", "Baker Street", 1998),
         new Song("Queen", "Bohemian Rhapsody", 1975),
         new Song("Rogue Wave", "Love's Lost Guarantee", 2005)
         );

      songList.sort(
            Comparator.comparing(Song::getArtist)
              .thenComparing(Song::getTitle)
              .thenComparing(Song::getYear)
      );
      //not sure why this assert fails, the output list is the same ordering as the expected list
      assertEquals(expectedList, songList);
   }
}
