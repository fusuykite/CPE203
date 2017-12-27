import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.time.LocalTime;
import java.util.List;

import org.junit.Test;

public class TestCases

{

   private static final List<CourseSection> courseList = Arrays.asList(
           new CourseSection("CSC", "203", 35,
                   LocalTime.of(9, 40), LocalTime.of(11, 0)),
           new CourseSection("CSC", "225", 40,
                   LocalTime.of(10, 20), LocalTime.of(12, 20)),
           new CourseSection("CSC", "357", 12,
                   LocalTime.of(10, 10), LocalTime.of(12, 10)));
   @Test
   public void testExercise1()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertTrue(one.equals(two));
      assertTrue(two.equals(one));
   }

   @Test
   public void testExercise2()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(1, 10), LocalTime.of(2, 0));

      assertFalse(one.equals(two));
      assertFalse(two.equals(one));
   }

   @Test
   public void testExercise3()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 40), LocalTime.of(11, 0));
      final CourseSection two = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 40), LocalTime.of(11, 0));

      assertEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testExercise4()
   {
      final CourseSection one = new CourseSection("CSC", "203", 35,
              LocalTime.of(9, 10), LocalTime.of(10, 0));
      final CourseSection two = new CourseSection("CSC", "203", 34,
              LocalTime.of(9, 10), LocalTime.of(10, 0));

      assertNotEquals(one.hashCode(), two.hashCode());
   }

   @Test
   public void testStudentEq()
   {
      final Student s1 = new Student("Lam", "Derek", 19, Arrays.asList(courseList.get(0), courseList.get(1)));
      final Student s2 = new Student("Lam", "Derek", 19, Arrays.asList(courseList.get(0), courseList.get(1)));
      assertTrue(s1.equals(s2));

   }

   @Test
   public void testStudentHash()
   {
      final Student s1 = new Student("Lam", "Derek", 19, Arrays.asList(courseList.get(0), courseList.get(1)));
      final Student s2 = new Student("Lam", "Derek", 19, Arrays.asList(courseList.get(0), courseList.get(1)));
      assertEquals(s1.hashCode(), s2.hashCode());
   }
}
