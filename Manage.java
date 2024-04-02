package flashcard;
import java.util.Scanner;

public class Manage implements ManageInterface {
    private Scanner scanner;

    public Manage() {
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Manage manage = new Manage();
        manage.start();
    }

    public void start() {
        System.out.println("\n****************************");
        System.out.println(" Тавтай морилно уу Flashcards CLI! ");
        System.out.println("****************************\n");

        while (true) {
            System.out.println("Та ямар ажил хийхийг хүсэж байна уу?");
            System.out.println("1. Тоглох");
            System.out.println("2. Карт");
            System.out.println("3. Гарах");

            System.out.print("Таны сонголт (1-3): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    Play play = new Play();
                    System.out.println("Картын нэрийг оруулна уу:");
                    String fileName = scanner.nextLine() + ".txt";
                    play.start(fileName);
                    break;
                case "2":
                    Card card = new Card();
                    card.start();
                    break;
                case "3":
                    System.out.println("Баяртай!");
                    return;
                default:
                    System.out.println("Буруу сонголт. 1-с 3 хүртэл сонгоно уу.");
            }
        }
    }
}
