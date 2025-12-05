import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public void add(Book book) {
        String sql = "INSERT INTO books(title, author, year) VALUES(?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kitap ekleme hatası: " + e.getMessage());
        }
    }

    public void update(Book book) {
        String sql = "UPDATE books SET title=?, author=?, year=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getYear());
            ps.setInt(4, book.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kitap güncelleme hatası: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Kitap silme hatası: " + e.getMessage());
        }
    }

    public Book getById(int id) {
        String sql = "SELECT * FROM books WHERE id=?";
        Book book = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                book = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                );
            }

        } catch (SQLException e) {
            System.out.println("Kitap getirme hatası: " + e.getMessage());
        }

        return book;
    }

    public List<Book> getAll() {
        String sql = "SELECT * FROM books";
        List<Book> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Book b = new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                );
                list.add(b);
            }

        } catch (SQLException e) {
            System.out.println("Kitap listeleme hatası: " + e.getMessage());
        }

        return list;
    }
}
