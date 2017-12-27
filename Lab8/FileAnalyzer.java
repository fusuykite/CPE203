import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

public class FileAnalyzer {
    public static void main(String[] args){
        List<Point> points = new ArrayList<Point>();
        final String fileIn = getFilename(args);
        final String fileOut = "drawMe.txt";
        points = makePoints(points, fileIn, fileOut);
        points = stepTwo(points);
        outputFile(points, fileOut);
    }

    private static List<Point> stepTwo(List<Point> points){
        List<Point> res = points.stream()
                .filter(point -> point.getZ() <= 2)
                .map(point -> new Point(point.getX() * 0.5, point.getY() * 0.5, point.getZ() * 0.5))
                .map(point -> new Point(point.getX()-150, point.getY()-37, point.getZ()))
                .collect(Collectors.toList());

        return res;
    }

    private static List<Point> makePoints(List<Point> points, String fileIn, String fileOut){
        try(Scanner input = new Scanner(new File(fileIn))){

            while(input.hasNextLine()){
                final String[] values = (input.nextLine()).split("\n");

                if (values.length == 0){
                    break;
                }

                else{
                    for (String value : values) {
                        Point point = makePoint(value);
                        points.add(point);
                    }
                }

            }
        }
        catch(FileNotFoundException f){
            System.out.print("File not found.");
        }

        return points;
    }

    private static void outputFile(List<Point> points, String fileOut){
        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(fileOut));

            for (Point point: points){
                double x = point.getX();
                double y = point.getY();
                int z = (int)point.getZ();

                writer.write(Double.toString(x) + ", " + Double.toString(y) + ", " + Integer.toString(z) + "\n");
            }
        }
        catch (IOException ex){
            System.out.print("?");
        }

        try{
            if (writer != null){
                writer.close();
            }
        }
        catch (IOException ex){
            System.out.print("Done");
        }
    }

    private static Point makePoint(String value){
        String[] point = value.split(",");
        double x = Double.parseDouble(point[0]);
        double y = Double.parseDouble(point[1]);
        double z = Double.parseDouble(point[2]);

        Point newPoint = new Point(x, y, z);

        return newPoint;
    }

    private static String getFilename(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Log file not specified.");
            System.exit(1);
        }
        return args[0];
    }
}

