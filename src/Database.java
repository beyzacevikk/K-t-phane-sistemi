import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    // Veritabanı dosyası proje kökünde oluşsun
    private static final String URL = "jdbc:sqlite:smartlibrary.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void init() {
        try (Connection conn = getConnection();
             Statement st = conn.createStatement()) {

            // Debug için hangi dosyaya yazdığımızı konsola basalım
            System.out.println("DB yolu -> " + new java.io.File("smartlibrary.db").getAbsolutePath());

            String booksTable = "CREATE TABLE IF NOT EXISTS books (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "author TEXT," +
                    "year INTEGER)";

            String studentsTable = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "department TEXT)";

            String loansTable = "CREATE TABLE IF NOT EXISTS loans (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "bookId INTEGER," +
                    "studentId INTEGER," +
                    "dateBorrowed TEXT," +
                    "dateReturned TEXT)";

            st.execute(booksTable);
            st.execute(studentsTable);
            st.execute(loansTable);

            System.out.println("Veritabanı hazır.");

        } catch (SQLException e) {
            System.out.println("Veritabanı hatası: " + e.getMessage());
        }
    }
}
