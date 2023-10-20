/**
 * @author zhou
 * @since 2023/10/20
 * description: 设计模式-迭代器模式
 */
public class Iterator {
    public static void main(String[] args) {

        NameRepository repository = new Iterator.NameRepository();
        for (TestIterator iterator = repository.getIterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    void test() {
        NameRepository repository = new NameRepository();
        for (TestIterator iterator = repository.getIterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }

    public interface TestIterator {
        boolean hasNext();

        Object next();
    }

    public interface Container {
        public TestIterator getIterator();
    }

    public static class NameRepository implements Container {
        public String[] names = {"Robert", "John", "Julie", "Lora"};

        @Override
        public TestIterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements TestIterator {

            int index;

            @Override
            public boolean hasNext() {
                if (index < names.length) {
                    return true;
                }
                return false;
            }

            @Override
            public Object next() {
                if (this.hasNext()) {
//                    这里会有疑惑为什么迭代器不会跳过第一个元素 是因为index++会先使用index值在进行累加 而++index先进行累加在赋值
                    return names[index++];
                }
                return null;
            }
        }
    }
}
