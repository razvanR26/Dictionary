package example;

import java.util.*;

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
                System.out.println("To display the entire dictionary filtered by length, enter 6");
                System.out.println("To display words in the dictionary filtered by a fragment, enter 7");
                System.out.println("To exit the application, enter 0");

                System.out.println("Enter your option:");
                   try {
                       int option = scanner.nextInt();
                       scanner.nextLine();
                       running = handleOption(option);
                   } catch (InputMismatchException e) {
                       System.out.println("Enter a valid option" + "\n");
                       scanner.nextLine();
                   }
            }
        }

        private boolean isWordValid (String word) {
            return word.matches("[\\p{L}]{2,}([ -][\\p{L}]+)*");
        }

        private boolean isValidDescription (String description) {
            return description.matches("[\\p{L}]{2,}(-[\\p{L}]+)?( ([\\p{L}]+(-[\\p{L}]+)?)" +
                    "| [\\d]+ [\\p{L}]+(-[\\p{L}]+)?)*[.!?]?");
        }

        private boolean handleOption (int option) {
            switch (option) {
                case 1 -> addWords();
                case 2 -> removeWords();
                case 3 -> changeWords();
                case 4 -> findWords();
                case 5 -> printDict();
                case 6 -> filteredPrintDict();
                case 7 -> searchByFragment();
                case 0 -> {
                    System.out.println("You have exited the application");
                    return false;
                }
                default -> System.out.println("Invalid option: " + option);
            }
            return true;
        }


        private void addWords () {
            while (true) {
                System.out.println("Enter a word that you want to add to the dictionary");
                String word = scanner.nextLine();
                if (isWordValid(word)) {
                    if (existsWord(word)) {
                        System.out.println("The word is already in the dictionary");
                    } else {
                        while (true) {
                            System.out.println("Enter the description of the word");
                            String description = scanner.nextLine();
                            if (isValidDescription(description)) {
                                dictionary.put(word, description);
                                System.out.println("The word and its description have been added to the dictionary");
                                break;
                            } else {
                                System.out.println(description + " is invalid. Enter a valid description");
                            }
                        }
                    }
                    if (!askUserIfWantsToContinue()) {
                        return;
                    }
                } else {
                    System.out.println(word + " is invalid. Enter a valid word");
                }
            }
        }

        private void removeWords () {
            while (true) {
                if (dictionary.isEmpty()) {
                    System.out.println("Dictionary is empty");
                    return;
                }
                printDict();
                System.out.println("Enter a word that you want to remove from the dictionary");
                String word = scanner.nextLine();
                if (isWordValid(word)) {
                    if (existsWord(word)) {
                        dictionary.remove(word);
                        System.out.println("The word and its description were removed from the dictionary");
                    } else {
                        System.out.println("The word is not in the dictionary");
                    }
                    if (!askUserIfWantsToContinue()) {
                        return;
                    }
                } else {
                    System.out.println(word + " is invalid. Enter a valid word");
                }
            }
        }

        private void changeWords () {
            if (dictionary.isEmpty()) {
                System.out.println("Dictionary is empty");
                return;
            }
            while (true) {
                printDict();
                System.out.println("Enter a word that you want to modify");
                String word = scanner.nextLine();
                if (isWordValid(word)) {
                    if (existsWord(word)) {
                        while (true) {
                            System.out.println("Enter a new description for it");
                            String description = scanner.nextLine();
                            if (isValidDescription(description)) {
                                dictionary.replace(word, description);
                                System.out.println("The word now has a new description");
                                break;
                            } else {
                                System.out.println(description + " is invalid. Enter a valid description");
                            }
                        }
                    } else System.out.println("The word is not in the dictionary");
                    if (!askUserIfWantsToContinue()) {
                        return;
                    }
                } else {
                    System.out.println(word + " is invalid. Enter a valid word");
                }
            }
        }

        private void findWords () {
            if (dictionary.isEmpty()) {
                System.out.println("Dictionary is empty");
                return;
            }
            while (true) {
                System.out.println("Enter the word that you are searching for");
                String word = scanner.nextLine();
                if (isWordValid(word)) {
                    if (existsWord(word)) {
                        System.out.println("The word was found and it has this description: " + dictionary.get(word));
                    } else System.out.println("The word is not in the dictionary");
                    if (!askUserIfWantsToContinue()) {
                        return;
                    }
                } else {
                    System.out.println(word + " is invalid. Enter a valid word");
                }
            }
        }

        private void printDict () {
            if (dictionary.isEmpty()) {
                System.out.println("Dictionary is empty");
            } else {
                dictionary.forEach((key, value) -> System.out.println("Word: " + key
                        + ", description: " + value));
            }
        }

        private void filteredPrintDict () {
            if (dictionary.isEmpty()) {
                System.out.println("Dictionary is empty");
                return;
            }
              while (true) {
                  try {
                      System.out.println("Enter the minimum length of words that you want displayed");
                      System.out.println("The filter displays words with a length strictly greater than the entered number");
                      int filter = scanner.nextInt();
                      scanner.nextLine();
                      List <Map.Entry <String, String>> result = dictionary.entrySet().stream()
                              .filter(entry -> entry.getKey().length() > filter).toList();
                      if (result.isEmpty()) {
                          System.out.println("The dictionary contains no words with a length greater than the one entered");
                      } else {
                          System.out.println("Filtered dictionary:");
                          result.forEach(entry -> System.out.println("Word: " + entry.getKey()
                                  + ", description: " + entry.getValue()));
                      }
                      if (!askUserIfWantsToContinue()) {
                          return;
                      }
                  } catch (InputMismatchException e) {
                      System.out.println("Length is invalid. It needs a number");
                      scanner.nextLine();
                  }
              }
        }

        private void searchByFragment () {
            if (dictionary.isEmpty()) {
                System.out.println("Dictionary is empty");
                return;
            }
            while (true) {
                    System.out.println("Enter the fragment that you want to search in the dictionary");
                    String fragment = scanner.nextLine();
                    if (fragment.matches("[\\p{L}]+([ -][\\p{L}]+)*|[ -][\\p{L}]+([ -][\\p{L}]+)*|[\\p{L}]+[ -]?")) {
                        List <Map.Entry<String, String>> result = dictionary.entrySet().stream()
                                .filter(entry -> entry.getKey().toLowerCase(Locale.ROOT).contains(fragment.toLowerCase(Locale.ROOT))).toList();
                        if (result.isEmpty()) {
                            System.out.println("The dictionary has no words that contain the entered fragment");
                        } else {
                            System.out.println("The words that contain the fragment are:");
                            result.forEach(entry -> System.out.println("Word: " + entry.getKey()
                                    + ", description: " + entry.getValue()));
                        }
                        if (!askUserIfWantsToContinue()) {
                            return;
                        }
                    } else {
                        System.out.println(fragment + " is invalid. Enter a valid fragment");
                    }
            }
        }

    private boolean askUserIfWantsToContinue () {
        while (true) {
            System.out.println("Do you want to repeat the action?");
            System.out.println("1 - Yes");
            System.out.println("0 - No - Back to the menu");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                if (option == 1) {
                    return true;
                }
                if (option == 0) {
                    return false;
                }
                System.out.println("Invalid option: " + option);
            } catch (InputMismatchException e) {
                System.out.println("Enter a valid option" + "\n");
                scanner.nextLine();
            }
        }
    }

        private boolean existsWord (String word) {
                return dictionary.containsKey(word);
        }
    }