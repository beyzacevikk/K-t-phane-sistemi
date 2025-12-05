import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    public void add(Loan loan) {
        String sql = "INSERT INTO loans(bookId, studentId, dateBorrowed, dateReturned) VALUES(?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getStudentId());
            ps.setString(3, loan.getDateBorrowed());
            ps.setString(4, loan.getDateReturned());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ödünç ekleme hatası: " + e.getMessage());
        }
    }

    public void update(Loan loan) {
        String sql = "UPDATE loans SET bookId=?, studentId=?, dateBorrowed=?, dateReturned=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, loan.getBookId());
            ps.setInt(2, loan.getStudentId());
            ps.setString(3, loan.getDateBorrowed());
            ps.setString(4, loan.getDateReturned());
            ps.setInt(5, loan.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ödünç güncelleme hatası: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM loans WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ödünç silme hatası: " + e.getMessage());
        }
    }

    public Loan getById(int id) {
        String sql = "SELECT * FROM loans WHERE id=?";
        Loan loan = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                loan = new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                );
            }

        } catch (SQLException e) {
            System.out.println("Ödünç getirme hatası: " + e.getMessage());
        }

        return loan;
    }

    public List<Loan> getAll() {
        String sql = "SELECT * FROM loans";
        List<Loan> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Loan loan = new Loan(
                        rs.getInt("id"),
                        rs.getInt("bookId"),
                        rs.getInt("studentId"),
                        rs.getString("dateBorrowed"),
                        rs.getString("dateReturned")
                );
                list.add(loan);
            }

        } catch (SQLException e) {
            System.out.println("Ödünç listeleme hatası: " + e.getMessage());
        }

        return list;
    }

    public boolean isBookOnLoan(int bookId) {
        String sql = "SELECT COUNT(*) AS c FROM loans WHERE bookId=? AND dateReturned IS NULL";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("c") > 0;
            }

        } catch (SQLException e) {
            System.out.println("Ödünç kontrol hatası: " + e.getMessage());
        }

        return false;
    }
}
