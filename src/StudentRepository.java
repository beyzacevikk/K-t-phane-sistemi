import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public void add(Student student) {
        String sql = "INSERT INTO students(name, department) VALUES(?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Öğrenci ekleme hatası: " + e.getMessage());
        }
    }

    public void update(Student student) {
        String sql = "UPDATE students SET name=?, department=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getDepartment());
            ps.setInt(3, student.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Öğrenci güncelleme hatası: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Öğrenci silme hatası: " + e.getMessage());
        }
    }

    public Student getById(int id) {
        String sql = "SELECT * FROM students WHERE id=?";
        Student student = null;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department")
                );
            }

        } catch (SQLException e) {
            System.out.println("Öğrenci getirme hatası: " + e.getMessage());
        }

        return student;
    }

    public List<Student> getAll() {
        String sql = "SELECT * FROM students";
        List<Student> list = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department")
                );
                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Öğrenci listeleme hatası: " + e.getMessage());
        }

        return list;
    }
}

