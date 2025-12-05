public class Loan extends BaseEntity {

    private int bookId;
    private int studentId;
    private String dateBorrowed;
    private String dateReturned;

    public Loan() {
    }

    public Loan(int id, int bookId, int studentId, String dateBorrowed, String dateReturned) {
        super(id);
        this.bookId = bookId;
        this.studentId = studentId;
        this.dateBorrowed = dateBorrowed;
        this.dateReturned = dateReturned;
    }

    public Loan(int bookId, int studentId, String dateBorrowed) {
        this(0, bookId, studentId, dateBorrowed, null);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(String dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public String toString() {
        String durum = (dateReturned == null) ? "teslim edilmedi" : ("iade: " + dateReturned);
        return id + " - bookId=" + bookId + ", studentId=" + studentId +
                ", alınma: " + dateBorrowed + ", " + durum;
    }
}
