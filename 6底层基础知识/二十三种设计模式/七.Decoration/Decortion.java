/**
 * @author zhou
 * @since 2023/10/12
 * description: 装饰品模式-我现在有一些装饰品(分为衣服和小礼物)，每件衣服都有不同的名称，现在我需要去给他们加上指定集合中的元素装饰品，
 * 每件衣服最多只能装饰3个。另外装饰后还会在衣服名称后面加上-装饰品的名称。
 */
public class Decortion {

    public static void main(String[] args) {
        new Decortion().execute();
    }

    public void execute() {
        // 装蝴蝶结和小河马的爱马仕
        ADecoration aDecoration = new ADecoration(new BDecoration(new Aclothing()));
        System.out.println(aDecoration.name);
    }

    public class Clothing {
        public String name;
    }

    public class Decoration extends Clothing {
        public Clothing clothing;
    }

    public class Aclothing extends Clothing {

        public Aclothing() {
            super.name = "爱马仕";
        }
    }

    public class Bclothing extends Clothing {

        public Bclothing() {
            super.name = "香奈儿";
        }
    }

    public class ADecoration extends Decoration {

        public ADecoration(Clothing clothing) {
            this.clothing = clothing;
            this.name = clothing.name + "-蝴蝶结";
        }
    }

    public class BDecoration extends Decoration {

        public BDecoration(Clothing clothing) {
            this.clothing = clothing;
            this.name = clothing.name + "-小河马";
        }
    }
}
