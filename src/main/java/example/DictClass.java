package example;

import java.util.Scanner;
import java.util.TreeMap;

public class DictClass {
        private final TreeMap<String, String> dictionary = new TreeMap <> (String.CASE_INSENSITIVE_ORDER);
        private final Scanner scanner = new Scanner(System.in);

        public void startDictionaryOperations () {
            boolean running = true;
            while (running) {
                System.out.println("Welcome to the dictionary menu. " +
                        "Below are the available options:");
                System.out.println("To add a word and its description, enter 1");
                System.out.println("To delete a word and its description, enter 2");
                System.out.println("To modify a word, enter 3");
                System.out.println("To find a word, enter 4");
                System.out.println("To display the entire dictionary, enter 5");
                System.out.println("To exit the application, enter 0");

                System.out.println("Enter your option:");

                int option = scanner.nextInt();
                scanner.nextLine();

                running = getValidatedIntegerInput(option);
            }
        }

        public boolean getValidatedStringInput (String word) {
            return word.matches("[A-Za-z -]+");
        }

        public boolean getValidatedIntegerInput (int option) {
            switch (option) {
                case 1 -> addWords();
                case 2 -> removeWords();
                case 3 -> changeWords();
                case 4 -> findWords();
                case 5 -> printDict();
                case 0 -> {
                    System.out.println("You have exited the application");
                    return false;
                }
                default -> System.out.println("Invalid option: " + option);
            }
            return true;
        }


        public void addWords () {
            while (true) {
                System.out.println("Enter a word that you want to add to the dictionary");
                String word = scanner.nextLine();
                if (existsWord(word)) {
                    System.out.println("The word is already in the dictionary");
                } else {
                    System.out.println("Enter the description of the word");
                    String description = scanner.nextLine();
                    dictionary.put(word, description);
                    System.out.println("The word and its description have been added to the dictionary");
                }
                if (!askUserIfWantsToContinue()) {
                    return;
                }
            }
        }

        public void removeWords () {
            while (true) {
                if (dictionary.isEmpty()) {
                    System.out.println("Dictionary is empty");
                    return;
                }
                printDict();
                System.out.println("Enter a word that you want to remove from the dictionary");
                String word = scanner.nextLine();
                if (existsWord(word)) {
                    dictionary.remove(word);
                    System.out.println("The word and its description were removed from the dictionary");
                } else {
                    System.out.println("The word is not in the dictionary");
                }
                if (!askUserIfWantsToContinue()) {
                    return;
                }
            }
        }

        public void changeWords () {
            while (true) {
                if (dictionary.isEmpty()) {
                    System.out.println("Dictionary is empty");
                    return;
                }
                printDict();
                System.out.println("Enter a word that you want to modify");
                String word = scanner.nextLine();
                if (existsWord(word)) {
                    System.out.println("Enter a new description for it");
                    String description = scanner.nextLine();
                    dictionary.replace(word, description);
                    System.out.println("The word now has a new description");
                } else System.out.println("The word is not in the dictionary");
                if (!askUserIfWantsToContinue()) {
                    return;
                }
            }
        }

        public void findWords () {
            while (true) {
                if (dictionary.isEmpty()) {
                    System.out.println("Dictionary is empty");
                    return;
                }
                System.out.println("Enter the word that you are searching for");
                String word = scanner.nextLine();
                if (existsWord(word)) {
                    System.out.println("The word was found and it has this description: " + dictionary.get(word));
                } else System.out.println("The word is not in the dictionary");
                if (!askUserIfWantsToContinue()) {
                    return;
                }
            }
        }

        public void printDict () {
            if (dictionary.isEmpty()) {
                System.out.println("Dictionary is empty");
            } else {
                System.out.println(dictionary);
            }
        }

    public boolean askUserIfWantsToContinue () {
        while (true) {
            System.out.println("Do you want to repeat the action?");
            System.out.println("1 - Yes");
            System.out.println("0 - No - Back to the menu");

            int option = scanner.nextInt();
            scanner.nextLine();

            if (option == 1) {
                return true;
            }

            if (option == 0) {
                return false;
            }
            System.out.println("Invalid option: " + option);
        }
    }

        private boolean existsWord (String word) {
            if (getValidatedStringInput(word)) {
                return dictionary.containsKey(word);
            }
            return false;
        }
    }