/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.BookStore;
import ui.Menu;

public class Main {
    public static void main(String[] args) {
        BookStore bs = new BookStore();
        Menu mainMenu = new Menu("BOOK STORE MANAGEMENT.");
        mainMenu.addNewOption("             1. Publisher Management.");
        mainMenu.addNewOption("             2. Book Management.");
        mainMenu.addNewOption("             3. Exit.");
        
        Menu menuPublisher = new Menu("PUBLISHER MANAGEMENT.");
        menuPublisher.addNewOption("             1. Create a Publisher.");
        menuPublisher.addNewOption("             2. Delete the Publisher.");
        menuPublisher.addNewOption("             3. Save the Publishers list to file.");
        menuPublisher.addNewOption("             4. Print the Publisher list from file.");
        menuPublisher.addNewOption("             5. Return main menu.");
        bs.readPublisherFromFile();
        
        Menu menuBook = new Menu("BOOK MANAGEMENT");
        menuBook.addNewOption("             1. Create a Book.");
        menuBook.addNewOption("             2. Search the Book.");
        menuBook.addNewOption("             3. Update a Book.");
        menuBook.addNewOption("             4. Delete the Book.");
        menuBook.addNewOption("             5. Save the Book list to file.");
        menuBook.addNewOption("             6. Print the Book list from the file.");
        menuBook.addNewOption("             7. Return main menu.");
        bs.readBookFromFile();
        
        int choiceMenu;
        do{
            mainMenu.printMenu();
            choiceMenu = mainMenu.getChoice();
            switch(choiceMenu){
                case 1:
                    int choicePublisher;
                    do{
                        menuPublisher.printMenu();
                        choicePublisher = menuPublisher.getChoice();
                        switch(choicePublisher){
                            case 1:
                                bs.createAPublisher();
                                break;
                            case 2:
                                bs.deletePublisher();
                                break;
                            case 3:
                                bs.savePublisherToFile();
                                break;
                            case 4:
                                bs.printPublisherFromFile();
                                break;
                            case 5:
                                System.out.println("Return to Main Menu.");
                                break;
                        }
                    }while(choicePublisher != 5);
                    break;
                case 2:
                    int choiceBook;
                    do{
                        menuBook.printMenu();
                        choiceBook = menuBook.getChoice();
                        switch(choiceBook){
                            case 1:
                                bs.createABook();
                                break;
                            case 2:
                                bs.searchTheBook();
                                break;
                            case 3:
                                bs.updateBook();
                                break;
                            case 4:
                                bs.deleteBook();
                                break;
                            case 5:
                                bs.saveBookToFile();
                                break;
                            case 6:
                                bs.printBookFromFile();
                                break;
                            case 7:
                                System.out.println("Return to Main Menu");
                                break;
                        }
                    }while(choiceBook != 7);
                    break;
                case 3:
                    System.out.println("Bye,bye.");
                    break;
            }
        }while(choiceMenu != 3);
    }
}
