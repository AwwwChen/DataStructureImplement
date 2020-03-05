package hashCode;

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        int a = 42;
        System.out.println(((Integer) a).hashCode());

        Double b = 3.1243;
        System.out.println(b.hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        Student student = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student);

        HashMap<Student, Integer> scores = new HashMap<>();
        scores.put(student, 100);

        Student student2 = new Student(3, 2, "Bobo", "Liu");
        System.out.println(student2.hashCode());
    }
}
