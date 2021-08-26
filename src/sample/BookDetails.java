package sample;

import java.util.Objects;

public class BookDetails {

    private final int bookID;
    private final String title;
    private final String author;
    private final String genre;
    private int copies;

    public BookDetails(int bookID, String title, String author, String genre, int copies) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.copies = copies;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDetails that = (BookDetails) o;
        return bookID == that.bookID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID);
    }

    @Override
    public String toString() {
        return bookID + "";
    }
}
