package com.challengeone.literalura.main;

import com.challengeone.literalura.entity.Book;
import com.challengeone.literalura.model.API_Search;
import com.challengeone.literalura.service.API_Helper;
import com.challengeone.literalura.service.BookService;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class CommandMain {

    private static BookService bookService;

    private static final Scanner input = new Scanner(System.in);
    private static String next;
    private static int option;

    public static void begin(BookService bookService) {
        CommandMain.bookService = bookService;
        intro();
        start();
    }

    private static void intro() {
        System.out.println("**************************************************");
        System.out.println("*                LiterAlura v1.0                 *");
        System.out.println("**************************************************");
    }

    private static void start() {
        showOptions("Select:",
                Arrays.asList(
                        "Search for a book (download Info to the Database)",
                        "List the books in the Database"
                ),"¡¡¡ SELECT ONE OF THE FOLLOWING OPTIONS !!!"
        );
        switch (option) {
            case 1:
                typeBook();
                break;
            case 2:
                break;
        }
    }

    private static void typeBook() {
        typeWithCondition("Type the book that you want to search",
                (text) -> text.trim().length() >= 3,
                "¡¡¡ MUST BE A WORD WITH 3 OR MORE CHARACTERS !!!");
        String json = API_Helper.request("search=" + next);
        if (json != null) {
            API_Search search = API_Helper.JSON_Object(json, API_Search.class);
            if (search != null && !search.results().isEmpty()) {
                System.out.println("The following results were found:");
                AtomicInteger counter = new AtomicInteger();
                search.results().stream().limit(10).forEach(b -> {
                    System.out.println("---------- BOOK Nº " + counter.incrementAndGet() + " ----------");
                    System.out.println(b.toStringConsole());
                    System.out.println();
                });
                typeWithCondition("Please type NUMBER of the book you want:",
                        (text) -> {
                            try {
                                int number = Integer.parseInt(text);
                                if (number > 0 && number <= search.results().size()) {
                                    return true;
                                }
                            }
                            catch (NumberFormatException ignored) {
                            }
                            return false;
                        },
                        "¡¡¡ MUST BE A NUMBER BETWEEN 1 AND " + search.results().size() + " !!!");
                int selection = Integer.parseInt(next);
                showOptions("You selected:\n" + search.results().get(selection - 1).toStringConsole() + "\n",
                        Arrays.asList(
                                "Confirm",
                                "Cancel"
                        ),"¡¡¡ SELECT ONE OF THE FOLLOWING OPTIONS !!!"
                );
                if (option == 1) {
                    Book book = Book.GenerateFromRecord(search.results().get(selection - 1));
                    if (bookService.findBookById(book.getId()).isEmpty()) {
                        bookService.saveBook(book);
                        System.out.println("Book SAVED in the Database...");
                    } else {
                        System.out.println("!!! THIS BOOK IS ALREADY IN THE DATABASE ¡¡¡");
                    }
                }
            } else {
                System.out.println("!!! NO RESULTS WERE FOUND ¡¡¡, please search again");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }
            start();
        } else {
            System.out.println("An error occurred while searching for the book, please try again");
            start();
        }
    }

    private static void showOptions(String titleMessage, List<String> options, String errorMessage) {
        boolean showError = false;
        option = 0;
        do {
            System.out.println();
            if (showError) {
                System.out.println(errorMessage);
            }
            System.out.println(titleMessage);
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i+1) + ".\t" + options.get(i));
            }
            next = input.nextLine().trim();
            try {
                option = Integer.parseInt(next);
            } catch (NumberFormatException ignored) {
            }
            showError = true;
        } while (option == 0 || option > options.size());
    }

    private static void typeWithCondition(String titleMessage, Function<String, Boolean> condition, String errorMessage) {
        boolean showError = false;
        do {
            System.out.println();
            if (showError) {
                System.out.println(errorMessage);
            }
            System.out.println(titleMessage);
            next = input.nextLine().trim();
            showError = true;
        } while (!condition.apply(next));
    }
}
