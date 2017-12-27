import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import java.awt.Color;
import java.awt.Point;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

public class TestCases {
   public static final double DELTA = 0.00001;

   /* ------------------------- CIRCLE -------------------------------*/
   @Test
   public void testCircleRadius() {
      Circle c = new Circle(2.55, new Point(0, 0), Color.BLACK);

      assertEquals(2.55, c.getRadius(), DELTA);
   }

   @Test
   public void testCircleCenter() {
      Circle c = new Circle(2.55, new Point(0, 0), Color.BLACK);
      assertEquals(new Point(0, 0), c.getCenter());
   }

   @Test
   public void testCircleColor() {
      Circle c = new Circle(2.55, new Point(0, 0), Color.BLACK);
      assertEquals(Color.BLACK, c.getColor());
   }

   @Test
   public void testCircleSetColor() {
      Circle c = new Circle(2.55, new Point(0, 0), Color.BLACK);
      c.setColor(Color.PINK);

      assertEquals(Color.PINK, c.getColor());
   }

   @Test
   public void testCircleGetArea() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(101.2839543, c.getArea(), DELTA);
   }

   @Test
   public void testCircleGetPerimeter() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      assertEquals(35.6759261, c.getPerimeter(), DELTA);
   }

   @Test
   public void testCircleTranslate() {
      Circle c = new Circle(5.678, new Point(2, 3), Color.BLACK);

      Point newP = new Point(-1, 1);
      c.translate(newP);

      assertEquals(new Point(1, 4), c.getCenter());
   }

      /* ---------------------------- RECTANGLE ---------------------------------*/

   @Test
   public void testRectangleWidth() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      assertEquals(10.0, r.getWidth(), DELTA);
   }

   @Test
   public void testRectangleSetWidth() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      r.setWidth(3.0);
      assertEquals(3.0, r.getWidth(), DELTA);
   }

   @Test
   public void testRectangleHeight() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      assertEquals(5.0, r.getHeight(), DELTA);
   }

   @Test
   public void testRectangleSetHeight() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      r.setHeight(10.0);
      assertEquals(10.0, r.getHeight(), DELTA);
   }

   @Test
   public void testRectangleTopLeft() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      assertEquals(new Point(0, 5), r.getTopLeft());
   }

   @Test
   public void testRectangleColor() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      assertEquals(Color.BLACK, r.getColor());
   }

   @Test
   public void testRectangleSetColor() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      r.setColor(Color.PINK);
      assertEquals(Color.PINK, r.getColor());
   }

   @Test
   public void testRectangleGetArea() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      assertEquals(50.0, r.getArea(), DELTA);
   }

   @Test
   public void testRectangleGetPerimeter() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      assertEquals(30.0, r.getPerimeter(), DELTA);
   }

   @Test
   public void testRectangleTranslate() {
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);
      r.translate(new Point(-1, -5));
      assertEquals(new Point(-1, 0), r.getTopLeft());
   }

   /* ---------------------------- TRIANGLE ---------------------------------*/
   @Test
   public void testTriangleVertexA() {
      Triangle t = new Triangle(new Point(6, 1), new Point(1, 5), new Point(-2, 2), Color.BLACK);
      assertEquals(new Point(6, 1), t.getVertexA());
   }

   @Test
   public void testTriangleVertexB() {
      Triangle t = new Triangle(new Point(6, 1), new Point(1, 5), new Point(-2, 2), Color.BLACK);
      assertEquals(new Point(1, 5), t.getVertexB());
   }

   @Test
   public void testTriangleVertexC() {
      Triangle t = new Triangle(new Point(6, 1), new Point(1, 5), new Point(-2, 2), Color.BLACK);
      assertEquals(new Point(-2, 2), t.getVertexC());
   }

   @Test
   public void testTriangleColor() {
      Triangle t = new Triangle(new Point(6, 1), new Point(1, 5), new Point(-2, 2), Color.BLACK);
      assertEquals(Color.BLACK, t.getColor());
   }

   @Test
   public void testTriangleSetColor() {
      Triangle t = new Triangle(new Point(6, 1), new Point(1, 5), new Point(-2, 2), Color.BLACK);
      t.setColor(Color.PINK);
      assertEquals(Color.PINK, t.getColor());
   }

   @Test
   public void testTriangleArea() {
      Triangle t = new Triangle(new Point(5, 5), new Point(0, 5), new Point(0, 0), Color.BLACK);
      assertEquals(12.5, t.getArea(), DELTA);
   }

   @Test
   public void testTrianglePerimeter() {
      Triangle t = new Triangle(new Point(5, 5), new Point(0, 5), new Point(0, 0), Color.BLACK);
      assertEquals(17.071067811865476, t.getPerimeter(), DELTA);
   }

   @Test
   public void testTriangleTranslate() {
      Triangle t = new Triangle(new Point(6, 1), new Point(1, 5), new Point(-2, 2), Color.BLACK);
      t.translate(new Point(5, 0));
      assertEquals(new Point(11, 1), t.getVertexA());
      assertEquals(new Point(6, 5), t.getVertexB());
      assertEquals(new Point(3, 2), t.getVertexC());
   }

   /* -------------------------- CONVEX POLYGON -------------------------------*/
   @Test
   public void testConvexVertex() {
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };

      ConvexPolygon cp = new ConvexPolygon(verticies, Color.BLACK);

      assertEquals(new Point(0, 0), cp.getVertex(1));
   }

   @Test
   public void testConvexColor() {
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };

      ConvexPolygon cp = new ConvexPolygon(verticies, Color.BLACK);

      assertEquals(Color.BLACK, cp.getColor());
   }

   @Test
   public void testConvexSetColor() {
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };
      ConvexPolygon cp = new ConvexPolygon(verticies, Color.BLACK);

      cp.setColor(Color.PINK);
      assertEquals(Color.PINK, cp.getColor());
   }

   @Test
   public void testConvexArea() {
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };
      ConvexPolygon cp = new ConvexPolygon(verticies, Color.BLACK);

      assertEquals(75.0, cp.getArea(), DELTA);
   }

   @Test
   public void testConvexPerimeter() {
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };
      ConvexPolygon cp = new ConvexPolygon(verticies, Color.BLACK);

      assertEquals(40.0, cp.getPerimeter(), DELTA);
   }

   @Test
   public void testConvexTranslate() {
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };
      ConvexPolygon cp = new ConvexPolygon(verticies, Color.BLACK);

      cp.translate(new Point(0, -5));

      assertEquals(new Point(0, 5), cp.getVertex(0));
      assertEquals(new Point(0, -5), cp.getVertex(1));
      assertEquals(new Point(10, -5), cp.getVertex(2));
      assertEquals(new Point(10, 0), cp.getVertex(3));
      assertEquals(new Point(5, 0), cp.getVertex(4));
      assertEquals(new Point(5, 5), cp.getVertex(5));
   }

      /* -------------------------- WORKSPACE -------------------------------*/

   @Test
   public void testWorkSpaceAddGetSize() {
      WorkSpace ws = new WorkSpace();

      Rectangle r1 = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Triangle t1 = new Triangle(new Point(0, 0), new Point(2, -4), new Point(3, 0), Color.BLACK);

      ws.add(r1);
      ws.add(c1);
      ws.add(t1);

      assertEquals(c1, ws.get(1));
      assertEquals(3, ws.size());
   }

   @Test
   public void testWorkSpaceGetCircles() {
      WorkSpace ws = new WorkSpace();
      List<Circle> expected = new LinkedList<>();

      // Have to make sure the same objects go into the WorkSpace as
      // into the expected List since we haven't overriden equals in Circle.
      Circle c1 = new Circle(5.678, new Point(2, 3), Color.BLACK);
      Circle c2 = new Circle(1.11, new Point(-5, -3), Color.RED);

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(c1);
      ws.add(new Triangle(new Point(0, 0), new Point(2, -4), new Point(3, 0), Color.BLACK));
      ws.add(c2);

      expected.add(c1);
      expected.add(c2);

      // Doesn't matter if the "type" of lists are different (e.g Linked vs
      // Array).  List equals only looks at the objects in the List.
      assertEquals(expected, ws.getCircles());
   }

   @Test
   public void testWorkSpaceGetRectangles() {
      WorkSpace ws = new WorkSpace();
      List<Rectangle> expected = new LinkedList<>();

      Rectangle r1 = new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK);
      Rectangle r2 = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLACK);

      ws.add(new Circle(5.0, new Point(2, 3), Color.BLACK));
      ws.add(r1);
      ws.add(r2);
      ws.add(new Triangle(new Point(0, 0), new Point(2, -4), new Point(3, 0), Color.BLACK));

      expected.add(r1);
      expected.add(r2);

      assertEquals(expected, ws.getRectangles());
   }

   @Test
   public void testWorkSpaceGetTriangles() {
      WorkSpace ws = new WorkSpace();
      List<Triangle> expected = new LinkedList<>();

      Triangle t1 = new Triangle(new Point(0, 0), new Point(2, -3), new Point(4, 0), Color.BLACK);
      Triangle t2 = new Triangle(new Point(2, 3), new Point(4, 4), new Point(3, 10), Color.BLACK);

      ws.add(t1);
      ws.add(new Circle(4.0, new Point(0, 0), Color.BLACK));
      ws.add(new Rectangle(10.0, 4.0, new Point(0, 0), Color.BLACK));
      ws.add(t2);

      expected.add(t1);
      expected.add(t2);

      assertEquals(expected, ws.getTriangles());
   }

   @Test
   public void testWorkSpaceConvexPolygons() {
      WorkSpace ws = new WorkSpace();
      List<ConvexPolygon> expected = new LinkedList<>();
      Point[] points1 = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5), new Point(5, 5),
              new Point(5, 10) };

      Point[] points2 = { new Point(0, 5), new Point(0, -5), new Point(10, -5), new Point(10, 0), new Point(5, 0),
              new Point(5, 5) };

      ConvexPolygon cp1 = new ConvexPolygon(points1, Color.BLACK);
      ConvexPolygon cp2 = new ConvexPolygon(points2, Color.BLACK);

      ws.add(new Circle(4.0, new Point(0, 0), Color.BLACK));
      ws.add(cp1);
      ws.add(new Triangle(new Point(0, 0), new Point(4, 4), new Point(3, 4), Color.BLACK));
      ws.add(new Rectangle(10.0, 3.0, new Point(1, 0), Color.BLACK));
      ws.add(cp2);

      expected.add(cp1);
      expected.add(cp2);

      assertEquals(expected, ws.getConvexPolygons());
   }

   @Test
   public void testWorkSpaceGetShapesByColor(){
      WorkSpace ws = new WorkSpace();
      List<Shape> expected = new LinkedList<>();
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };

      Circle c = new Circle(5.678, new Point(2, 3), Color.PINK);
      Rectangle r = new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLUE);
      Triangle t = new Triangle(new Point(5, 5), new Point(0, 5), new Point(0, 0), Color.BLACK);
      ConvexPolygon cp = new ConvexPolygon(verticies, Color.PINK);

      ws.add(c);
      ws.add(r);
      ws.add(t);
      ws.add(cp);

      expected.add(c);
      expected.add(cp);

      assertEquals(expected, ws.getShapesByColor(Color.PINK));
   }

   @Test
   public void testWorkSpaceAreaOfAllShapes() {
      WorkSpace ws = new WorkSpace();

      ws.add(new Rectangle(1.234, 5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Circle(5.678, new Point(2, 3), Color.BLACK));
      ws.add(new Triangle(new Point(0, 0), new Point(2, -4), new Point(3, 0), Color.BLACK));

      assertEquals(114.2906063, ws.getAreaOfAllShapes(), DELTA);
   }

   @Test
   public void testWorkSpacePerimeterOfAllShapes(){
      WorkSpace ws = new WorkSpace();
      Point[] verticies = { new Point(0, 10), new Point(0, 0), new Point(10, 0), new Point(10, 5),
              new Point(5, 5), new Point(5, 10) };

      ws.add(new Circle(5.678, new Point(2, 3), Color.PINK));
      ws.add(new Rectangle(10.0, 5.0, new Point(0, 5), Color.BLUE));
      ws.add(new Triangle(new Point(5, 5), new Point(0, 5), new Point(0, 0), Color.BLACK));
      ws.add(new ConvexPolygon(verticies, Color.PINK));

      assertEquals(122.7469939118655, ws.getPerimeterOfAllShapes(), DELTA);
   }

   /* --------------------------- IMPLEMENTATION TESTS -----------------------------*/
   @Test
   public void testCircleImplSpecifics() throws NoSuchMethodException {
      final List<String> expectedMethodNames = Arrays.asList("getColor", "setColor", "getArea", "getPerimeter",
              "translate", "getRadius", "setRadius", "getCenter");

      final List<Class> expectedMethodReturns = Arrays.asList(Color.class, void.class, double.class, double.class,
              void.class, double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[] { Color.class },
              new Class[0], new Class[0], new Class[] { Point.class }, new Class[0],
              new Class[] { double.class }, new Class[0]);

      verifyImplSpecifics(Circle.class, expectedMethodNames, expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testRectangleImplSpecifics() throws NoSuchMethodException {
      final List<String> expectedMethodNames = Arrays.asList("getColor", "setColor", "getArea", "getPerimeter",
              "translate", "getWidth", "setWidth", "getHeight", "setHeight", "getTopLeft");

      final List<Class> expectedMethodReturns = Arrays.asList(Color.class, void.class, double.class, double.class,
              void.class, double.class, void.class, double.class, void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[] { Color.class },
              new Class[0], new Class[0], new Class[] { Point.class }, new Class[0],
              new Class[] { double.class }, new Class[0], new Class[] { double.class }, new Class[0]);

      verifyImplSpecifics(Rectangle.class, expectedMethodNames, expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testTriangleImplSpecifics() throws NoSuchMethodException {
      final List<String> expectedMethodNames = Arrays.asList("getColor", "setColor", "getArea", "getPerimeter",
              "translate", "getVertexA", "getVertexB", "getVertexC");

      final List<Class> expectedMethodReturns = Arrays.asList(Color.class, void.class, double.class, double.class,
              void.class, Point.class, Point.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[] { Color.class },
              new Class[0], new Class[0], new Class[] { Point.class }, new Class[0], new Class[0],
              new Class[0]);

      verifyImplSpecifics(Triangle.class, expectedMethodNames, expectedMethodReturns, expectedMethodParameters);
   }

   @Test
   public void testConvexPolygonImplSpecifics() throws NoSuchMethodException {
      final List<String> expectedMethodNames = Arrays.asList("getColor", "setColor", "getArea", "getPerimeter",
              "translate", "getVertex");

      final List<Class> expectedMethodReturns = Arrays.asList(Color.class, void.class, double.class, double.class,
              void.class, Point.class);

      final List<Class[]> expectedMethodParameters = Arrays.asList(new Class[0], new Class[] { Color.class },
              new Class[0], new Class[0], new Class[] { Point.class }, new Class[] { int.class });

      verifyImplSpecifics(ConvexPolygon.class, expectedMethodNames, expectedMethodReturns,
              expectedMethodParameters);
   }

   private static void verifyImplSpecifics(final Class<?> clazz, final List<String> expectedMethodNames,
                                           final List<Class> expectedMethodReturns, final List<Class[]> expectedMethodParameters)
           throws NoSuchMethodException {
      assertEquals("Unexpected number of public fields", 0, clazz.getFields().length);

      final List<Method> publicMethods = Arrays.stream(clazz.getDeclaredMethods())
              .filter(m -> Modifier.isPublic(m.getModifiers())).collect(Collectors.toList());

      assertEquals("Unexpected number of public methods", expectedMethodNames.size(), publicMethods.size());

      assertTrue("Invalid test configuration", expectedMethodNames.size() == expectedMethodReturns.size());
      assertTrue("Invalid test configuration", expectedMethodNames.size() == expectedMethodParameters.size());

      for (int i = 0; i < expectedMethodNames.size(); i++) {
         Method method = clazz.getDeclaredMethod(expectedMethodNames.get(i), expectedMethodParameters.get(i));
         assertEquals(expectedMethodReturns.get(i), method.getReturnType());
      }
   }
}
