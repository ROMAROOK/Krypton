import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Solitude {

    public static void main(String[] args) {
        greeting();
        menu();
    }

    public static void greeting() {
        System.out.println("""
                Введите  1 , 2 и 3 для выбора действия или 4 для выхода из программы:
                1 - зашифровать
                2 - расшифровать
                3 - подобрать ключ
                4 - выйти
                Для вызова меню нажмите ENTER
                """);
    }

    public static void menu() {
        do {
            switch (new Scanner(System.in).nextLine()) {
                case "1" -> encrypted();
                case "2" -> decrypted();
                case "3" -> bruteForce();
                case "4" -> System.exit(0);
                default -> greeting();
            }
        } while (true);
    }

    public static void encrypted() {
        try {
            String str0 = (""),
                    str1 = (" абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ1234567890+-–.,!?«»…");
            System.out.println("Введите ключ шифрования : ");
            Scanner scanner = new Scanner(System.in);
            int key = scanner.nextInt();
            String str2 = str1.substring(key) + str1.substring(0, key);
            List<String> list = Files.readAllLines(Path.of("packages/text.txt"));
            for (char c : list.toString().toCharArray()) {
                for (int i = 0; i < str1.toCharArray().length; i++) {
                    if (c == str1.charAt(i)) str0 += (str2.charAt(i));
                }
            }
            Files.writeString(Path.of("packages/encrypted.txt"), "");
            Files.writeString(Path.of("packages/encrypted.txt"),
                    str0.replaceAll("\n", ""), StandardOpenOption.APPEND);
            System.out.println("Файл зашифрован\nНажмите ENTER");
        } catch (Exception e) {
            System.out.println("Ошибка шифрования\nНажмите ENTER");
        }
    }

    public static void decrypted() {
        try {
            String str0 = (""),
                    str1 = (" абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ1234567890+-–.,!?«»…");
            System.out.println("Введите ключ дешифрования : ");
            Scanner scanner = new Scanner(System.in);
            int key = scanner.nextInt();
            String str2 = str1.substring(key) + str1.substring(0, key);
            List<String> list = Files.readAllLines(Path.of("packages/encrypted.txt"));
            for (char c : list.toString().toCharArray()) {
                for (int i = 0; i < str1.toCharArray().length; i++) {
                    if (c == str2.charAt(i)) str0 += (str1.charAt(i));
                }
            }
            Files.writeString(Path.of("packages/decrypted.txt"), "");
            Files.writeString(Path.of("packages/decrypted.txt"),
                    str0.replaceAll("\n", ""), StandardOpenOption.APPEND);
            System.out.println("Файл расшифрован\nНажмите ENTER");
        } catch (Exception e) {
            System.out.println("Файл не найден\nНажмите ENTER");
        }
    }

    public static void bruteForce() {
        try {
            String str0 = (""),
                    str1 = (" абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ1234567890+-–.,!?«»…");
            List<String> list = Files.readAllLines(Path.of("packages/encrypted.txt"));
            for (int k = 0; k < str1.toCharArray().length; k++) {
                String str2 = str1.substring(k) + str1.substring(0, k);
                for (char x : list.toString().toCharArray()) {
                    for (int i = 0; i < str1.toCharArray().length; i++) {
                        if (x == str1.charAt(i)) {
                            str0 += (str2.charAt(i));
                        }
                    }
                }
                if (str0.contains(" или ") && str0.contains("как") && str0.contains("или") && str0.contains("это")) {
                    count = k;
                }
                str0 = "";
                str1 = str2;
            }
            System.out.printf("Ключ шифрования равен = %d \nНажмите ENTER", (str1.length() - count));
        } catch (Exception e) {
            System.out.println("Ошибка поиска ключа\nНажмите ENTER");
        }
    }

    public static int count;
}
