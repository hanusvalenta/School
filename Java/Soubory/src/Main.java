import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Momentalni adresar: " + System.getProperty("user.dir"));

        File dir = new File("Files");
        dir.mkdir();

        if (dir.isDirectory()) {
            try {
                FileOutputStream NewFos = new FileOutputStream("Files/" + "text" + ".txt");NewFos.write("ඞ".getBytes());NewFos.close();
            }
            catch (Exception e) {
                System.out.println(e);
            }

            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                    System.out.println("\n* " + f.getName());
                }
            } else {
                System.out.println("Adresar prazdny");
            }

            System.out.println("Nazev souboru");
            String input = sc.nextLine();

            try {
                String ch;
                System.out.println("Psat nebo cist nebo porovnat? p | c | po");
                ch = sc.nextLine();
                if (ch.equals("c")) {
                    FileInputStream fis = new FileInputStream("Files/" + input + ".txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }

                    fis.close();
                    br.close();
                }
                if (ch.equals("p")) {
                    FileOutputStream fos = new FileOutputStream("Files/" + input + ".txt");

                    System.out.println("Zadej text");
                    String text = sc.nextLine();

                    fos.write(text.getBytes());
                    fos.close();
                }
                if (ch.equals("po")) {
                    System.out.println("Nazev souboru s kterym porovnat");
                    String compared = sc.nextLine();

                    StringBuilder text = new StringBuilder();

                    FileInputStream fis = new FileInputStream("Files/" + input + ".txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));

                    String line;
                    while ((line = br.readLine()) != null) {
                        text.append(line);
                    }

                    StringBuilder text2 = new StringBuilder();

                    FileInputStream fis2 = new FileInputStream("Files/" + compared + ".txt");
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));

                    String line2 = "";
                    while ((line2 = br2.readLine()) != null) {
                        text2.append(line2);
                    }

                    System.out.println(text + " " + text2);

                    fis.close();
                    br.close();
                    fis2.close();
                    br2.close();

                    if (text.toString().equals(text2.toString()) || text2.toString().equals(text.toString())) {
                        System.out.println("Jsou stejne");
                    }
                    else {
                        System.out.println("Nejsou stejne");
                    }
                }
                else {
                    System.out.println("Spatna akce");
                }
            }
            catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("\nNepovedlo se udelat adresar");
        }
    }
}