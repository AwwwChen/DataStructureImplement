package array;

public class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) {
        Array<Student> array = new Array<>();
        array.addLast(new Student("zc1", 100));
        array.addLast(new Student("zc2", 99));
        array.addLast(new Student("zc3", 98));
        System.out.println(array.toString());
    }
}
