package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;

public class BookDetailsData {

    private static BookDetailsData instance = new BookDetailsData();

    private final ObservableList<BookDetails> bookDetailsList = FXCollections.observableArrayList();

    private BookDetailsData() {

    }

    public static BookDetailsData getInstance() {
        return instance;
    }

    public ObservableList<BookDetails> getBookDetailsList() {
        return bookDetailsList;
    }

    public void addBookDetails(BookDetails bookDetails) {
        bookDetailsList.add(bookDetails);
    }

    public void deleteBookDetails(BookDetails bookDetails) { bookDetailsList.remove(bookDetails); }

    public void loadBookDetails() throws IOException {
        bookDetailsList.clear();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("bookDetails.txt"))) {
            String input;
            while ((input = bufferedReader.readLine())!=null) {
                String[] strings = input.split("   ");
                BookDetails book = new BookDetails(Integer.parseInt(strings[0]),strings[1],strings[2],strings[3],Integer.parseInt(strings[4]));
                bookDetailsList.add(book);
            }
            System.out.println(getBookDetailsList());
        }catch (IOException e) {
            throw new IOException("I/O EXCEPTION");
        }
    }

    public void saveBookDetails() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("bookDetails.txt"))) {
            for (BookDetails bookDetails : bookDetailsList) {
                bufferedWriter.write(bookDetails.getBookID() + "   ");
                bufferedWriter.write(bookDetails.getTitle() + "   ");
                bufferedWriter.write(bookDetails.getAuthor() + "   ");
                bufferedWriter.write(bookDetails.getGenre() + "   ");
                bufferedWriter.write(bookDetails.getCopies() + "   ");
                bufferedWriter.newLine();
            }
        }catch (IOException e) {
            throw new IOException("I/O EXCEPTION");
        }
    }
}
