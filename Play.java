package flashcard;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Play implements PlayInterface {
    private HashMap<String, String> flashcards;

    public Play() {
        this.flashcards = new HashMap<>();
    }

    @Override
    public void start(String fileName) {
        loadFlashcards(fileName);

        if (flashcards.isEmpty()) {
            System.out.println("Сурах карт байхгүй байна. Та тухайн тохиргоо хийнэ үү.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        for (String question : flashcards.keySet()) {
            System.out.println(question);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(flashcards.get(question))) {
                System.out.println("Зөв!");
            } else {
                System.out.println("Буруу. Зөв хариулт: " + flashcards.get(question));
            }
        }

        System.out.println("\tСурах карт дууслаа.");
    }

    private void loadFlashcards(String fileName) {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(":\t");
                if (parts.length == 2) {
                    flashcards.put(parts[0], parts[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Картуудыг уншихад алдаа гарлаа: " + e.getMessage());
        }
    }
}
