package flashcard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Card implements CardInterface {
    private Scanner scanner;

    public Card() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String FilterCollection() {
        try {
            File folder = new File(".");
            File[] files = folder.listFiles();
    
            System.out.println("Нээлттэй цуглуулгууд:");
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    System.out.println(file.getName().replace(".txt", ""));
                }
            }
    
            System.out.print("Цуглуулгын нэрээ оруулна уу: ");
            String selectedFile = scanner.nextLine();
            return selectedFile + ".txt"; // Append ".txt" to match the actual file name
        } catch (Exception e) {
            System.out.println("Цуглуулгуудыг жагсаахад алдаа гарлаа: " + e.getMessage());
        }
        return "";
    }
    
    @Override
    public String CreateCollection() {
        try {
            System.out.println("Таслалын нэрээ оруулна уу:");
            String name = scanner.nextLine();
            String fullName = name + ".txt";

            File myFile = new File(fullName);
            if (myFile.createNewFile()) {
                System.out.println("Файл үүсгэгдлээ: " + myFile.getName());
                return fullName;
            } else {
                System.out.println("Файл өмнө нь үүссэн байна");
            }
        } catch (IOException e) {
            System.out.println("Цуглуулгыг үүсгэхэд алдаа гарлаа: " + e.getMessage());
        }
        return "";
    }

    @Override
    public void SetCard(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName, true); // Append mode
            System.out.print("Асуулт:\t");
            String question = scanner.nextLine();
            System.out.print("Хариулт:\t");
            String answer = scanner.nextLine();

            myWriter.write(question + ":\t" + answer + "\n");
            myWriter.close();
            System.out.println("Амжилттай картыг цуглуулгад нэмлээ.");
        } catch (IOException e) {
            System.out.println("Карт нэмэхэд алдаа гарлаа: " + e.getMessage());
        }
    }

    @Override
    public void GetCard(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String flashcard = fileScanner.nextLine();
                System.out.println(flashcard);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл уншихад алдаа гарлаа: " + e.getMessage());
        }
    }

    @Override
    public void UpdateCard(String fileName) {
        try {
            File file = new File(fileName);
            Scanner fileScanner = new Scanner(file);
            StringBuilder updatedContent = new StringBuilder();
    
            while (fileScanner.hasNextLine()) {
                String flashcard = fileScanner.nextLine();
    
                String[] parts = flashcard.split(":\t");
                String question = parts[0];
                String answer = parts[1];
    
                System.out.println("Асуулт: " + question + "\tХариулт: " + answer);
                System.out.println("1. Устгах");
                System.out.println("2. Засах");
                System.out.println("3. Алгасах");
    
                System.out.print("Таны сонголт (1-3): ");
                String choice = scanner.nextLine();
    
                switch (choice) {
                    case "1":
                        break;
                    case "2":
                        System.out.print("Асуулт: ");
                        String newQuestion = scanner.nextLine();
                        System.out.print("хариулт: ");
                        String newAnswer = scanner.nextLine();
                        updatedContent.append(newQuestion).append(":").append(newAnswer).append("\n");
                        break;
                    case "3":
                        updatedContent.append(flashcard).append("\n");
                        break;
                    default:
                        System.out.println("Буруу сонголт. 1, 2 эсвэл 3-г оруулна уу.");
                        break;
                }
            }
    
            fileScanner.close();
    
            FileWriter writer = new FileWriter(fileName);
            writer.write(updatedContent.toString());
            writer.close();
    
            System.out.println("Картуудыг амжилттай шинэчиллээ.");
        } catch (IOException e) {
            System.out.println("Файлыг шинэчлэхэд алдаа гарлаа: " + e.getMessage());
        }
    }
    

    public void start() {
        System.out.println("1. Шинэ цуглуулга үүсгэх");
        System.out.println("2. Цуглуулгыг засах");
        System.out.println("3. Цуглуулгад карт нэмэх");
        System.out.println("4. Буцах");
        System.out.print("Таны сонголт (1-4): ");
    
        String choice = scanner.nextLine();
    
        switch (choice) {
            case "1":
                String setName = CreateCollection();
    
                boolean loop = true;
                while (loop) {
                    System.out.println("1. Карт нэмэх");
                    System.out.println("2. Буцах");
    
                    String ans = scanner.nextLine();
                    switch (ans) {
                        case "1":
                            SetCard(setName);
                            break;
                        case "2":
                            loop = false;
                            break;
                        default:
                            System.out.print("Буруу сонголт. 1 эсвэл 2-г оруулна уу: ");
                    }
                }
                break;
            case "2":
                String collName = FilterCollection();
                UpdateCard(collName);
                break;


            case "3":
                String collName2 = FilterCollection();
                SetCard(collName2);
            case "4":
                return;
            default:
                System.out.println("Буруу сонголт. 1-с 3 хүртэл сонгоно уу.");
        }
    }
    
}
