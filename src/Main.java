import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static BookRepository bookRepo = new BookRepository();
    private static StudentRepository studentRepo = new StudentRepository();
    private static LoanRepository loanRepo = new LoanRepository();

    public static void main(String[] args) {

        Database.init();

        while (true) {
            showMenu();
            int secim = readInt("Seçim: ");

            switch (secim) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBooks();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    giveBook();
                    break;
                case 6:
                    listLoans();
                    break;
                case 7:
                    returnBook();
                    break;
                case 0:
                    System.out.println("Program kapatılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- SmartLibrary ---");
        System.out.println("1) Kitap ekle");
        System.out.println("2) Kitapları listele");
        System.out.println("3) Öğrenci ekle");
        System.out.println("4) Öğrencileri listele");
        System.out.println("5) Kitap ödünç ver");
        System.out.println("6) Ödünç listesini görüntüle");
        System.out.println("7) Kitap geri teslim al");
        System.out.println("0) Çıkış");
    }

    private static int readInt(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Sayı gir.");
            }
        }
    }

    private static String readString(String msg) {
        System.out.print(msg);
        return input.nextLine();
    }

    private static void addBook() {
        String title = readString("Kitap adı: ");
        String author = readString("Yazar: ");
        int year = readInt("Yıl: ");

        Book b = new Book(title, author, year);
        bookRepo.add(b);
        System.out.println("Kitap kaydedildi.");
    }

    private static void listBooks() {
        List<Book> list = bookRepo.getAll();
        System.out.println("\nKitaplar:");
        if (list.isEmpty()) {
            System.out.println("Kayıt yok.");
        } else {
            for (Book b : list) {
                System.out.println(b);
            }
        }
    }

    private static void addStudent() {
        String name = readString("Öğrenci adı: ");
        String department = readString("Bölüm: ");

        Student s = new Student(name, department);
        studentRepo.add(s);
        System.out.println("Öğrenci kaydedildi.");
    }

    private static void listStudents() {
        List<Student> list = studentRepo.getAll();
        System.out.println("\nÖğrenciler:");
        if (list.isEmpty()) {
            System.out.println("Kayıt yok.");
        } else {
            for (Student s : list) {
                System.out.println(s);
            }
        }
    }

    private static void giveBook() {
        int studentId = readInt("Öğrenci ID: ");
        int bookId = readInt("Kitap ID: ");
        String date = readString("Tarih (ör: 2025-12-02): ");

        Student s = studentRepo.getById(studentId);
        if (s == null) {
            System.out.println("Öğrenci bulunamadı.");
            return;
        }

        Book b = bookRepo.getById(bookId);
        if (b == null) {
            System.out.println("Kitap bulunamadı.");
            return;
        }

        if (loanRepo.isBookOnLoan(bookId)) {
            System.out.println("Bu kitap zaten ödünçte.");
            return;
        }

        Loan loan = new Loan(bookId, studentId, date);
        loanRepo.add(loan);
        System.out.println("Kitap ödünç verildi.");
    }

    private static void listLoans() {
        List<Loan> list = loanRepo.getAll();
        System.out.println("\nÖdünç kayıtları:");

        if (list.isEmpty()) {
            System.out.println("Kayıt yok.");
            return;
        }

        for (Loan l : list) {
            Book b = bookRepo.getById(l.getBookId());
            Student s = studentRepo.getById(l.getStudentId());

            String kitapAdi = (b != null) ? b.getTitle() : ("Kitap#" + l.getBookId());
            String ogrenciAdi = (s != null) ? s.getName() : ("Öğrenci#" + l.getStudentId());

            String durum = (l.getDateReturned() == null)
                    ? "teslim edilmedi"
                    : "iade: " + l.getDateReturned();

            System.out.println(
                    l.getId() + " - " +
                            kitapAdi + " | " +
                            ogrenciAdi + " | " +
                            "alınma: " + l.getDateBorrowed() + " | " + durum
            );
        }
    }


    private static void returnBook() {
        int loanId = readInt("İade edilecek ödünç ID: ");
        Loan loan = loanRepo.getById(loanId);

        if (loan == null) {
            System.out.println("Kayıt bulunamadı.");
            return;
        }

        if (loan.getDateReturned() != null) {
            System.out.println("Zaten iade edilmiş.");
            return;
        }

        String date = readString("İade tarihi: ");
        loan.setDateReturned(date);
        loanRepo.update(loan);

        System.out.println("İade işlemi tamam.");
    }
}
