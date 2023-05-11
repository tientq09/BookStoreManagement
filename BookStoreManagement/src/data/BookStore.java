/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import utils.Validation;

public class BookStore {

    private List<Book> listBook = new ArrayList();
    private ArrayList<Book> listBookFile = new ArrayList();
    private ArrayList<Publisher> listPublisher = new ArrayList();
    

    public void readPublisherFromFile() {
        try {
            FileReader fr = new FileReader("Publisher.dat");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String id = txt[0].trim();
                String name = txt[1].trim();
                String phone = txt[2].trim();
                listPublisher.add(new Publisher(id, name, phone));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from Publisher.dat fail!");
        }
    }

    public void createAPublisher() {
        String publisherID;
        int indexPublisher;
        do {
            publisherID = Validation.regexString("Enter ID(Pxxxxx): ", "X is digit.Input again!", "^[p|P]\\d{5}$");
            indexPublisher = getIndexPublisher(publisherID);
            if (indexPublisher >= 0) {
                System.out.println("ID duplicated. Input again!");
            }
        } while (indexPublisher >= 0);
        String namePublisher;
        do {
            namePublisher = Validation.getString("Enter Name: ", "Not blank or empty.");
            if (namePublisher.length() < 5 || namePublisher.length() > 30) {
                System.out.println("Name has a length from 5 to 30 characters.");
            }
        } while (namePublisher.length() < 5 || namePublisher.length() > 30); 
        String phone = Validation.regexString("Enter Phone: ", "Phone has a length from 10 to 12.", "^[0-9]{10,12}$");
        listPublisher.add(new Publisher(publisherID, namePublisher, phone));
        System.out.println("Publisher's information has been added to list.");
        savePublisherToFile();
    }

    public int getIndexPublisher(String id) {             
        for (int i = 0; i < listPublisher.size(); i++) { 
            if (listPublisher.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void deletePublisher() {
        String publisherID = Validation.regexString("Enter ID(Pxxxxx) to delete: ", "X is digit.Input again!", "^[p|P]\\d{5}$");
        int indexPublisher = getIndexPublisher(publisherID);
        if (indexPublisher == -1) {
            System.out.println("Publisher's ID does not exist");
            System.out.println("Fail.");
        } else {
            listPublisher.remove(indexPublisher);
            System.out.println("Publisher's information has been deleted.");
            System.out.println("Successfull.");
        }
        savePublisherToFile();
    }
    public void savePublisherToFile() {
        try {
            FileWriter fw = new FileWriter("Publisher.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listPublisher.size(); i++) {
                bw.write(listPublisher.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Save data from Publisher.dat fail!");
        }
        System.out.println("Save to file's Publisher.dat successfull..");
    }

    public void printPublisherFromFile() {
         List<Publisher> listPublisherFile = new ArrayList(); 
        listPublisherFile.clear();
        try {
            FileReader fr = new FileReader("Publisher.dat");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String id = txt[0].trim();
                String name = txt[1].trim();
                String phone = txt[2].trim();
                listPublisherFile.add(new Publisher(id, name, phone));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from Publisher.dat fail!");
        }
        if (listPublisherFile.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
            Collections.sort(listPublisherFile, (o2, o1) -> {
                return o1.getName().compareToIgnoreCase(o2.getName());
            });
            int stt = 1;
            System.out.println("-------------------------------------------------------");
            System.out.println("| STT |    ID    |        NAME        |     PHONE     |");
        System.out.println("-------------------------------------------------------");
            for (int i = 0; i < listPublisherFile.size(); i++) {
                System.out.print("|" + stt++ + "    ");
                listPublisherFile.get(i).showInfor();
            }
           System.out.println("-------------------------------------------------------");
        }
    }
    /////////////////////////////////////////////////////////////

    public void readBookFromFile() {
        try {
            FileReader fr = new FileReader("Book.dat");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String id = txt[0].trim();
                String name = txt[1].trim();
                double price = Double.parseDouble(txt[2].trim());
                int quantity = Integer.parseInt(txt[3].trim());
                String publisherID = txt[4].trim();
                String status = txt[5].trim();
                listBook.add(new Book(id, name, price, quantity, publisherID, status));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from Book.dat fail!");
        }
    }

    public void createABook() {
        String bookID;
        int indexBook;
        do {
            bookID = Validation.regexString("Enter Book ID(Bxxxxx): ", "X is digit.Input again!", "^[B|b]\\d{5}$");
            indexBook = getIDBook(bookID);
            if (indexBook >= 0) {
                System.out.println("Duplicated ID.Input again!");
            }
        } while (indexBook >= 0);
        String nameBook;
        do {
            nameBook = Validation.getString("Enter Name: ", "Not blank or empty.");
            if (nameBook.length() < 5 || nameBook.length() > 30) {
                System.out.println("Name has a length from 5 to 30 characters.");
            }
        } while (nameBook.length() < 5 || nameBook.length() > 30);
        double price = Validation.getADouble("Enter Price: ", "More than 0", 0);
        int quantity = Validation.getAnInteger("Enter Quantity: ", "More tha 0.", 0);
        String status;
        int choiceStatus = Validation.getAnInteger("Enter choice status(1.Available or 2.Not Available): ", "Just 1 or 2.", 1, 2);
        if (choiceStatus == 1) {
            status = "Available";
        } else {
            status = "Not Avaiable";
        }
        int stt = 1;
       System.out.println("-------------------------------------------------------");
        System.out.println("| STT |    ID    |        NAME        |     PHONE     |");
     System.out.println("-------------------------------------------------------");
        for (int i = 0; i < listPublisher.size(); i++) {
            System.out.print("|" + stt++ + "    ");
            listPublisher.get(i).showInfor();
        }
       System.out.println("-------------------------------------------------------");
        int choicePublisher = Validation.getAnInteger("Enter choice publisher: ", "Wrong.Input again!", 1, listPublisher.size());
        listBook.add(new Book(bookID, nameBook, price, quantity, listPublisher.get(choicePublisher - 1).getId(), status));
        System.out.println("Book's information has been added to list.");
        saveBookToFile();
    }

    public int getIDBook(String id) {
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public void searchTheBook() {
        String search = Validation.getString("(Search A part of Book's Name or Publisher's ID): ", "Not blank or empty.");
        boolean check = false;
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getName().contains(search)
                    && listBook.get(i).getPublisherID().equalsIgnoreCase(search)) {
                check = true;
            }
        }
        if (check == false) {
            System.out.println("Have no any Book");
            return;
        }
        System.out.println("HERE IS RESULT SEARCHING BY: " + search);
         System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("|    ID    |        NAME        |  PRICE   | QUANTITY |  PUBLISHER'S ID  |     STATUS    |");
       System.out.println("-------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < listBook.size(); i++) {
            if (listBook.get(i).getName().contains(search)
                    || listBook.get(i).getPublisherID().equalsIgnoreCase(search)) {
                listBook.get(i).showInfor();
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------");
    }

    public void updateBook() {
        String bookID = Validation.regexString("Enter Book ID(Bxxxxx) Updating: ", "X is digit.Input again!", "^[B|b]\\d{5}$");
        int indexBook = getIDBook(bookID);
        if (indexBook == -1) {
            System.out.println("Book's Name does not exist.");
        } else {
            System.out.println("HERE IS OLD INFORMATION: ");
             System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("|    ID    |        NAME        |  PRICE   | QUANTITY |  PUBLISHER'S NAME  |     STATUS    |");
           System.out.println("-------------------------------------------------------------------------------------------------------");
            listBook.get(indexBook).showInfor();
          System.out.println("-------------------------------------------------------------------------------------------------------");

            String nameBook = null;
           
           
                do {
                    nameBook = Validation.getString("Enter Name: ", "Not blank or empty.");
                    if (nameBook.length() < 5 || nameBook.length() > 30) {
                        System.out.println("Name has a length from 5 to 30 characters.");
                    }
                } while (nameBook.length() < 5 || nameBook.length() > 30);
            
            double newPrice = Validation.updateADouble("Enter New Price: ", 0, listBook.get(indexBook).getPrice());
            int newQuantity = Validation.updateAnInteger("Enter New Quantity: ", 0, listBook.get(indexBook).getQuantity());
            String confirmStatus = Validation.regexString("Enter New Status(Y/N): ", "Just Y or N", "^[Y|y|N|n]$");
            if (confirmStatus.equalsIgnoreCase("Y")) {
                if (listBook.get(indexBook).getStatus().equalsIgnoreCase("Available")) {
                    listBook.get(indexBook).setStatus("Not Available");
                } else {
                    listBook.get(indexBook).setStatus("Available");
                }
            }
            int choicePublisher = 0;
            
           
                int stt = 1;
                System.out.println("-------------------------------------------------------");
                System.out.println("| STT |    ID    |        NAME        |     PHONE     |");
              System.out.println("-------------------------------------------------------");
                for (int i = 0; i < listPublisher.size(); i++) {
                    System.out.print("|" + stt++ + "    ");
                    listPublisher.get(i).showInfor();
                }
                System.out.println("----------------------------------------------------------");
                choicePublisher = Validation.getAnInteger("Enter your choice: ", "Wrong.Input again!", 1, listPublisher.size());
            
            listBook.get(indexBook).setName(nameBook);
            listBook.get(indexBook).setPrice(newPrice);
            listBook.get(indexBook).setQuantity(newQuantity);
            listBook.get(indexBook).setPublisherID(listPublisher.get(choicePublisher - 1).getId());
            System.out.println("Book's information has been updated. ");
            System.out.println("Successfull.");
            saveBookToFile();
        }
    }

    public void deleteBook() {
        String bookID = Validation.regexString("Enter Book ID(Bxxxxx) Deleting: ", "X is digit.Input again!", "^[B|b]\\d{5}$");
        int indexBook = getIDBook(bookID);
        if (indexBook == -1) {
            System.out.println("Book's Name does not exist.");
        } else {
            listBook.remove(indexBook);
            System.out.println("Book's information has been deleted.");
            System.out.println("Successfull.");
            saveBookToFile();
        }
    }

    public void saveBookToFile() {
        try {
            FileWriter fw = new FileWriter("Book.dat");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listBook.size(); i++) {
                bw.write(listBook.get(i).toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("Save data from Book.dat fail!");
        }
        System.out.println("Save to file's Book.dat successfull..");
    }

    public void printBookFromFile() {
        listBookFile.clear();
        try {
            FileReader fr = new FileReader("Book.dat");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String id = txt[0].trim();
                String name = txt[1].trim();
                double price = Double.parseDouble(txt[2].trim());
                int quantity = Integer.parseInt(txt[3].trim());
                String publisherID = txt[4].trim();
                String status = txt[5].trim();
                listBookFile.add(new Book(id, name, price, quantity, publisherID, status));
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            System.out.println("Read data from Book.dat fail!");
        }
        if (listBookFile.isEmpty()) {
            System.out.println("List empty.Nothing to print.");
        } else {
   
    
           Collections.sort(listBook, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.getQuantity() == o2.getQuantity()) {
                    if (o1.getPrice() > o2.getPrice()) {
                        return 1;
                    }
                    else if (o1.getPrice() < o2.getPrice()) {
                        return -1;
                    } else {return  0;}
                }
                return o2.getQuantity() - o1.getQuantity();
            }
        });
        
      System.out.println("-------------------------------------------------------------------------------------------------------");
            System.out.println("|    ID    |        NAME        |  PRICE   | QUANTITY | SUBTOTAL |  PUBLISHER'S NAME  |     STATUS    |");
                 System.out.println("-------------------------------------------------------------------------------------------------------");
            for (int i = 0; i < listBookFile.size(); i++) {
                System.out.printf("|%-10s|%-20s|%-10.1f|%-10d|%-10.1f|%-20s|%-15s|\n", listBookFile.get(i).getId(), listBookFile.get(i).getName(),
                        listBookFile.get(i).getPrice(), listBookFile.get(i).getQuantity(), listBookFile.get(i).getPrice() * listBookFile.get(i).getQuantity(),
                        getNamePublisherByID(listBookFile.get(i).getPublisherID()), listBookFile.get(i).getStatus());
            }
          System.out.println("-------------------------------------------------------------------------------------------------------");
        }
    }
 

    public String getNamePublisherByID(String id) {
        String name = "";
        for (int i = 0; i < listPublisher.size(); i++) {
            if (listPublisher.get(i).getId().equalsIgnoreCase(id)) {
                name = listPublisher.get(i).getName();
            }
        }
        return name;
    }
}
