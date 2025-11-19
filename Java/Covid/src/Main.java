import java.nio.file.Files;
import java.util.Scanner;
import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Student {
    String poradi;
    String jmeno;
    String cas;

    public Student(String poradi, String jmeno) {
        this.poradi = poradi;
        this.jmeno = jmeno;
        this.cas = "";
    }
}

public class Main {

    private static List<Student> readStudentData(File file) throws IOException {
        List<Student> students = new ArrayList<>();
        List<String> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            int poradiCol = -1;
            int jmenoCol = -1;
            Row headerRow = sheet.getRow(0);

            if (headerRow != null) {
                for (Cell cell : headerRow) {
                    String header = getCellValueAsString(cell).trim().toUpperCase();
                    if ("PORADI".equals(header)) {
                        poradiCol = cell.getColumnIndex();
                    } 
                    else if ("JMENO".equals(header)) {
                        jmenoCol = cell.getColumnIndex();
                    }
                }
            }

            if (poradiCol == -1 || jmenoCol == -1) {
                throw new IOException();
            }

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell poradiCell = row.getCell(poradiCol);
                Cell jmenoCell = row.getCell(jmenoCol);

                //numbers.set(i, String.valueOf(jmenoCol));

                String poradi = (poradiCell == null || poradiCell.getCellType() == CellType.BLANK) ? "" : getCellValueAsString(poradiCell);
                String jmeno = (jmenoCell == null || jmenoCell.getCellType() == CellType.BLANK) ? "" : getCellValueAsString(jmenoCell);

                if (!poradi.isEmpty() && !jmeno.isEmpty()) {
                    students.add(new Student(poradi, jmeno));
                }
            }
        }
        catch (Exception e) {
            throw new IOException(e);
        }
        return students;
    }

    private static String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case FORMULA -> cell.getCachedFormulaResultType() == CellType.STRING ? cell.getStringCellValue() : String.valueOf((int) cell.getNumericCellValue());
            default -> "";
        };
    }

    private static void writeStudentSchedule(List<Student> students, String outputFileName) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream out = new FileOutputStream(outputFileName)) {

            Sheet sheet = workbook.createSheet("Rozvrh CAS");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("PORADI");
            headerRow.createCell(1).setCellValue("JMENO");
            headerRow.createCell(2).setCellValue("CAS");

            int rowNum = 1;
            for (Student student : students) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(student.poradi);
                row.createCell(1).setCellValue(student.jmeno);
                row.createCell(2).setCellValue(student.cas);
            }

            for (int i = 0; i < 3; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(out);
            System.out.println("\n" + outputFileName + " Zapsan");

        }
        catch (IOException e) {
            throw e;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String filesDirName = "Files";
        System.out.println("Slozka " + System.getProperty("user.dir"));

        File dir = new File(filesDirName);
        if (!dir.exists()) {
            dir.mkdir();
            System.out.println("Vytvorena slozka 'Files'");
        }

        if (dir.isDirectory()) {
            File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith(".xlsx") || name.toLowerCase().endsWith(".xls"));
            
            System.out.println("\nSoubory v slozce ->");
            if (files != null) {
                for (File f : files) {
                    System.out.println("* " + f.getName());
                }
            }

            String inputFileName;
            File inputFile;
            while (true) {
                System.out.println("\nZadej nazev souboru");
                inputFileName = sc.nextLine().trim();
                inputFile = new File(filesDirName, inputFileName);
                if (inputFile.exists() && (inputFileName.toLowerCase().endsWith(".xlsx") || inputFileName.toLowerCase().endsWith(".xls"))) {
                    break;
                }
            }

            List<Student> students;
            try {
                students = readStudentData(inputFile);
            }
            catch (IOException e) {
                return;
            }

            int N = students.size();
            if (N == 0) {
                return;
            }
            System.out.println("\nPocet studentu - " + N);

            Collections.shuffle(students);

            LocalTime startTime;
            LocalTime endTime;
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

            while (true) {
                System.out.println("\nZadej start cas");
                String startTimeStr = sc.nextLine().trim();
                System.out.println("Zadej end cas");
                String endTimeStr = sc.nextLine().trim();

                try {
                    startTime = LocalTime.parse(startTimeStr, timeFormatter);
                    endTime = LocalTime.parse(endTimeStr, timeFormatter);

                    if (endTime.isAfter(startTime)) {
                        break;
                    }
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            Duration totalDuration = Duration.between(startTime, endTime);
            long totalSeconds = totalDuration.getSeconds();
            long slotSeconds = totalSeconds / N;
            long remainderSeconds = totalSeconds % N;

            List<String> timeSlots = new ArrayList<>();
            LocalTime currentTime = startTime;
            for (int i = 0; i < N; i++) {
                timeSlots.add(currentTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
                currentTime = currentTime.plusSeconds(slotSeconds);
                if (i < remainderSeconds) {
                    currentTime = currentTime.plusSeconds(1);
                }
            }
            
            System.out.println("Pocet studentu " + N + " | cas zkouseni " + totalDuration.toMinutes() + " minut" + " | bude zbyvat " + remainderSeconds + " sec zhodiny");
            System.out.printf("Delka jednoho studenta - %d minut a %d sekund\n", slotSeconds / 60, slotSeconds % 60);

            for (int i = 0; i < N; i++) {
                students.get(i).cas = timeSlots.get(i);
            }

            String outputFileName = "Zkouseni_" + inputFileName;
            try {
                writeStudentSchedule(students, outputFileName);
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
}