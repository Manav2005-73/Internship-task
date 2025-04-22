public class Student {
    private final String name;
    private final int age;
    private final String email;
    private final String course;

    public Student(String name, int age, String email, String course) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.course = course;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
}