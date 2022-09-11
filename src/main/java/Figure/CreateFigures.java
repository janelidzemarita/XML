package Figure;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateFigures implements Runnable {

    public static synchronized ArrayList<Circle> createCircles(String args) throws IOException {
        Pattern formatCircle = Pattern.compile("(\\d+).(\\d+)");
        try (FileInputStream inStrm = new FileInputStream(args);
             BufferedInputStream bf = new BufferedInputStream(inStrm);
             BufferedReader read = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
             FileOutputStream outStrm = new FileOutputStream(
                     "src/main/java/OutputFiles/Circles_out_unique.dat");
             OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
             BufferedWriter bfw = new BufferedWriter(bfo)) {

            ArrayList<Circle> setCircle = new ArrayList<>();
            if(read.ready()){
                String[] str = read.readLine().split("\n");
                for(String st: str){
                    Matcher match = formatCircle.matcher(st);
                    if (match.matches()) {
                        setCircle.add(new Circle("Circle", Double.parseDouble(st)));
                    }
                }
            }

            for (Circle c : setCircle) {
                bfw.write(c.getRadius() + "#");
            }

            return setCircle; //Task 2 (27/07/2022)
        }
    }

    public static synchronized ArrayList<Rectangle> rectanglesArrayList(String args) throws IOException {
        Pattern formatRectangle = Pattern.compile("(\\d+.\\d+)-(\\d+.\\d+)");
        try (FileInputStream inStrm = new FileInputStream(args);
             BufferedInputStream bf = new BufferedInputStream(inStrm);
             BufferedReader read = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
             FileOutputStream outStrm = new FileOutputStream(
                     "src/main/java/OutputFiles/Rectangles_out.dat");
             OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
             BufferedWriter bfw = new BufferedWriter(bfo)) {
            ArrayList<Rectangle> setRectangle = new ArrayList<>();
            while (read.ready()) {
                String str = read.readLine();
                Matcher match = formatRectangle.matcher(str);
                if (match.matches()) {
                    String[] lst = str.split("-");
                    setRectangle.add(new Rectangle(Double.parseDouble(lst[0]), Double.parseDouble(lst[1])));

                }
            }
            for (Rectangle rec : setRectangle) {
                bfw.write(rec.getLength() + "#" + rec.getHeight() + "\n");
            }
            return setRectangle;
        }
    }

    public static synchronized ArrayList<Triangle> trianglesArrayList(String args) throws IOException {
        Pattern formatTriangle = Pattern.compile("(\\d+.\\d+[-]\\d+.\\d+[-]\\d+.\\d+)");
        try (FileInputStream inStrm = new FileInputStream(args);
             BufferedInputStream bf = new BufferedInputStream(inStrm);
             BufferedReader read = new BufferedReader(new InputStreamReader(bf, StandardCharsets.UTF_8));
             FileOutputStream outStrm = new FileOutputStream(
                     "src/main/java/OutputFiles/Triangles_out.dat.dat");
             OutputStreamWriter bfo = new OutputStreamWriter(outStrm);
             BufferedWriter bfw = new BufferedWriter(bfo)) {
            ArrayList<Triangle> setTriangle = new ArrayList<>();

            while (read.ready()) {
                String str = read.readLine();
                Matcher match = formatTriangle.matcher(str);
                if (match.matches()) {
                    String[] lst = str.split("-");
                    setTriangle.add(new Triangle(Double.parseDouble(lst[0]), Double.parseDouble(lst[1]),
                            Double.parseDouble(lst[2])));
                }
            }

            for (Triangle triangle : setTriangle) {
                bfw.write(triangle.getAB() + "#" +
                        triangle.getBC() + "#" + triangle.getCA() + "\n");
            }
            return setTriangle;
        }
    }

    @Override
    public void run() {
        try {
            ArrayList<Circle> circles =  new ArrayList<>(
                    createCircles("src/main/java/InputFiles/Circles_in.dat"));
            ArrayList<Rectangle> rectangles = new ArrayList<>(
                    rectanglesArrayList("src/main/java/InputFiles/Rectangles_in.dat"));
            ArrayList<Triangle> triangles = new ArrayList<>(
                    trianglesArrayList("src/main/java/InputFiles/Triangles_in.dat")) ;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
