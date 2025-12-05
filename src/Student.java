public class Student extends BaseEntity {

    private String name;
    private String department;

    public Student() {
    }

    public Student(int id, String name, String department) {
        super(id);
        this.name = name;
        this.department = department;
    }

    public Student(String name, String department) {
        this(0, name, department);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + department + ")";
    }
}
