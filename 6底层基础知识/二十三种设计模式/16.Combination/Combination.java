import java.util.List;

/**
 * @author zhou
 * @since 2023/10/20
 * description: 设计模式-组合模式
 */
public class Combination {

    public class Student {
        private String name;
        private String age;
    }

    public class Teacher {
        private String name;
        private String age;
        private List<Student> studentList;
    }
}
