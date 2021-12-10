import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private Book book;
    private ShippingInfo shippingInfo;
    private BillingInfo billingInfo;
    private User user;

    List<Book> books = new ArrayList<>();

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public ShippingInfo getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(ShippingInfo shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    public BillingInfo getBillingInfo() {
        return billingInfo;
    }

    public void setBillingInfo(BillingInfo billingInfo) {
        this.billingInfo = billingInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean modify() {
        return false;
    }

    public boolean checkAvailibility() {
        return false;
    }

    public boolean isFullFilled() {
        if (book != null && shippingInfo != null && billingInfo != null && user != null) {
            return true;
        }
        return false;
    }
}
