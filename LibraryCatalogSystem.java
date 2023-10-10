import java.util.Scanner;

public class LibraryCatalogSystem {
    private static String[] titles = {
            "The Great Gatsby",
            "To Kill a Mockingbird",
            "1984",
            "Little Women",
            "Animal Farm"
    };

    private static String[] authors = {
            "F. Scott Fitzgerald",
            "Harper Lee",
            "George Orwell",
            "Louisa May Alcott",
            "George Orwell"
    };

    private static String[] isbns = {
            "9780748290213",
            "9780748296247",
            "9780748098235",
            "9780742190786",
            "9780748298203"
    };

    private static boolean[] availability = {true, true, true, true, true};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" ============================================== ");
        System.out.println("|    WELCOME TO THE LIBRARY CATALOG SYSTEM!    |");
        System.out.println(" ============================================== ");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Search for a book");
            System.out.println("2. Checkout a book");
            System.out.println("3. Return a book");
            System.out.println("4. Exit the Library Catalog System");
            System.out.println("Please enter your choice (1-4): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  //takes in the input choice

                switch (choice) {
                    case 1:
                        searchForBook(scanner);
                        break;
                    case 2:
                        checkoutBook(scanner);
                        break;
                    case 3:
                        returnBook(scanner);
                        break;
                    case 4:
                        System.out.println("Thank you for using the Library Catalog System.  Have a happy day!");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.  Please enter an option 1-4.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input.  Please enter a valid number (1-4).");
                scanner.nextLine(); //Moves on from invalid input
            }
        }
    }

    private static void searchForBook(Scanner scanner) {
        System.out.println("Enter search keyword (title, author, or ISBN): ");
        String keyword = scanner.nextLine().toLowerCase();

        System.out.println("\nSearch Results: ");
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].toLowerCase().contains(keyword) ||
                authors[i].toLowerCase().contains(keyword) ||
                isbns[i].equals(keyword)) {
                System.out.println("Title: " + titles[i]);
                System.out.println("Author: " + authors[i]);
                System.out.println("ISBN: " + isbns[i]);
                System.out.println("Available Copies: " + (availability[i] ? "Yes" : "No"));
                System.out.println();
            }
        }
    }

    private static void checkoutBook(Scanner scanner) {
        System.out.println("Enter the ISBN of the book you would like to checkout:  ");
        String isbnToCheckout = scanner.nextLine();

        for (int i = 0; i < isbns.length; i++) {
            if (isbns[i].equals(isbnToCheckout)) {
                if (availability[i]) {
                    availability[i] = false;
                    System.out.println("You have successfully checked out " + titles[i]);
                } else {
                    System.out.println("Sorry, " + titles[i] + " is not available at this time.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("Enter the ISBN of the book you want to return: ");
        String isbnToReturn = scanner.nextLine();

        for (int i = 0; i < isbns.length; i++){
            if (isbns[i].equals(isbnToReturn)) {
                if (!availability[i]) {
                    availability[i] = true;
                    System.out.println("You have successfully returned " + titles[i]);
                } else {
                    System.out.println("You can't return a book that is available. Please try again.");
                }
                return;
            }
        }

        System.out.println("Book not found.");
    }
}