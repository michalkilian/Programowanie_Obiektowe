package Database;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String year;

    public Book(String isbn, String title, String author, String year){
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void print() {
        System.out.println("ISBN " + isbn);
        System.out.println("Title " + title);
        System.out.println("Author " + author);
        System.out.println("Year " + year +'\n');
    }
}
