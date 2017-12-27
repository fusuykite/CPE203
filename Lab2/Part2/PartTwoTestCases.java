import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class PartTwoTestCases
{
   public static final double DELTA = 0.00001;

   @Test
   public void testCirclePeri()
   {
      Circle c1 = new Circle (new Point (0.0, 0.0), 5);
      assertEquals(31.41593, c1.perimeter(), DELTA);

   }
   @Test
   public void testRectPerimeter()
   {
      Rectangle r1 = new Rectangle(new Point(0,5), new Point(6,0));
      assertEquals(22.0, r1.perimeter(), DELTA);
   }

   @Test
   public void testPolyPerimeter()
   {
      List<Point> pointlst = new ArrayList<Point>();
      pointlst.add(new Point(0.0, 0.0));
      pointlst.add(new Point(3.0, 0.0));
      pointlst.add(new Point(3.0, 4.0));
      pointlst.add(new Point(0.0, 4.0));

      Polygon poly1 = new Polygon(pointlst);
      assertEquals(14.0, poly1.perimeter(), DELTA);
   }

   @Test
   public void testPolyPerimeter2()
   {
      List<Point> pointlst = new ArrayList<Point>();
      pointlst.add(new Point(0.0, 0.0));
      pointlst.add(new Point(3.0, 0.0));
      pointlst.add(new Point(3.0, 4.0));
      pointlst.add(new Point(0.0, 4.0));
      pointlst.add(new Point(-2.0, 2.0));

      Polygon poly1 = new Polygon(pointlst);
      assertEquals(15.65685, poly1.perimeter(), DELTA);
   }

   @Test
   public void testWhichIsBigger()
   {
      Circle c1 = new Circle(new Point(0.0, 0.0), 10);
      Rectangle r1 = new Rectangle(new Point(0.0, 6.0), new Point(6.0, 0.0));

      List<Point> pointLst = new ArrayList<Point>();
      pointLst.add(new Point(0.0, 0.0));
      pointLst.add(new Point(3.0, 0.0));
      pointLst.add(new Point(3.0, 4.0));
      pointLst.add(new Point(0.0, 4.0));

      Polygon p1 = new Polygon(pointLst);

      assertEquals(62.83185, Bigger.whichIsBigger(c1, r1, p1), DELTA);
   }

   @Test
   public void testCircleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getCenter", "getRadius", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, double.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getTopLeft", "getBottomRight", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         Point.class, Point.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0], new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testPolygonImplSpecifics()
      throws NoSuchMethodException
   {
      final List<String> expectedMethodNames = Arrays.asList(
         "getPoints", "perimeter");

      final List<Class> expectedMethodReturns = Arrays.asList(
         List.class, double.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(
         new Class[0], new Class[0]);

      verifyImplSpecifics(Polygon.class, expectedMethodNames,
         expectedMethodReturns, expectedMethodParameters);
   }

   private static void verifyImplSpecifics(
      final Class<?> clazz,
      final List<String> expectedMethodNames,
      final List<Class> expectedMethodReturns,
      final List<Class[]> expectedMethodParameters)
      throws NoSuchMethodException
   {
      assertEquals("Unexpected number of public fields",
         0, Point.class.getFields().length);

      final List<Method> publicMethods = Arrays.stream(
         clazz.getDeclaredMethods())
            .filter(m -> Modifier.isPublic(m.getModifiers()))
            .collect(Collectors.toList());

      assertEquals("Unexpected number of public methods",
         expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration",
         expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++)
      {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i),
            expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
