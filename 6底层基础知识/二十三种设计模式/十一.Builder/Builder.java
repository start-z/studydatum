import java.util.ArrayList;
import java.util.List;

/**
 * @author zhou
 * @since 2023/10/19
 * description: 设计模式-构造器模式
 * 需求： 一个典型的套餐可以是一个汉堡（Burger）和一杯冷饮（Cold drink）。汉堡（Burger）可以是素食汉堡（Veg Burger）或鸡肉汉堡（Chicken Burger），它们是包在纸盒中。冷饮（Cold drink）可以是可口可乐（coke）或百事可乐（pepsi），它们是装在瓶子中。
 */
public class Builder {

    /**
     * 使用构建者模式
     */
    public static class DesignBuilder {
        public void tester() {
            MealBuilder mealBuilder = new MealBuilder();

            Meal vegMeal = mealBuilder.prepareVegMeal();
            System.out.println("Veg Meal");
            vegMeal.showItems();
            System.out.println("Total Cost: " + vegMeal.getCost());

            Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
            System.out.println("\n\nNon-Veg Meal");
            nonVegMeal.showItems();
            System.out.println("Total Cost: " + nonVegMeal.getCost());
        }

        /**
         * 商品基类
         */
        public interface Item {
            String name();

            Pack pack();

            float price();
        }

        /**
         * 包装者基类
         */
        public interface Pack {
            String pack();
        }

        public class Wrapper implements Pack {

            @Override
            public String pack() {
                return "Wrapper";
            }
        }

        public class Bottle implements Pack {
            @Override
            public String pack() {
                return "Bottle";
            }
        }

        public abstract class Burger implements Item {

            @Override
            public Pack pack() {
                return new Wrapper();
            }

            @Override
            public abstract float price();
        }

        public abstract class ColdDrink implements Item {

            @Override
            public Pack pack() {
                return new Bottle();
            }

            @Override
            public abstract float price();
        }

        public class VegBurger extends Burger {

            @Override
            public float price() {
                return 25.0f;
            }

            @Override
            public String name() {
                return "Veg Burger";
            }
        }

        public class ChickenBurger extends Burger {

            @Override
            public float price() {
                return 50.5f;
            }

            @Override
            public String name() {
                return "Chicken Burger";
            }
        }

        public class Coke extends ColdDrink {

            @Override
            public float price() {
                return 30.0f;
            }

            @Override
            public String name() {
                return "Coke";
            }
        }

        public class Pepsi extends ColdDrink {

            @Override
            public float price() {
                return 35.0f;
            }

            @Override
            public String name() {
                return "Pepsi";
            }
        }

        public class Meal {
            private List<Item> items = new ArrayList<Item>();

            public void addItem(Item item) {
                items.add(item);
            }

            public float getCost() {
                float cost = 0.0f;
                for (Item item : items) {
                    cost += item.price();
                }
                return cost;
            }

            public void showItems() {
                for (Item item : items) {
                    System.out.print("Item : " + item.name());
                    System.out.print(", Packing : " + item.pack().pack());
                    System.out.println(", Price : " + item.price());
                }
            }
        }

        public class MealBuilder {

            public Meal prepareVegMeal() {
                Meal meal = new Meal();
                meal.addItem(new VegBurger());
                meal.addItem(new Coke());
                return meal;
            }

            public Meal prepareNonVegMeal() {
                Meal meal = new Meal();
                meal.addItem(new ChickenBurger());
                meal.addItem(new Pepsi());
                return meal;
            }
        }
    }


    /**
     * 不使用设计模式的代码编写  后续有些问题 不再修改
     */
    public class requireMentImp {
        public Burger burger = new Burger();
        private Drink drink = new Drink();
        private Pack pack = new Pack();

        public void packageKFC() {
            this.burger = new VegBurger();
            this.drink = new Coffice();
            this.pack = new Pack();
        }

        /**
         * 素食汉堡
         */
        public class VegBurger extends Burger {
        }

        /**
         * 鸡肉汉堡
         */
        public class ChickenBurger extends Burger {
        }

        /**
         * 汉堡
         */
        public class Burger extends BaseCommoodity {
        }

        /**
         * 咖啡
         */
        public class Coffice extends Drink {
        }

        /**
         * 可口可乐
         */
        public class Coke extends Drink {
        }

        /**
         * 饮品
         */
        public class Drink extends BaseCommoodity {
        }

        /**
         * 塑料袋
         */
        public class PlasticBag extends Pack {
        }

        /**
         * 纸盒
         */
        public class Carton extends Pack {
        }

        /**
         * 包装盒
         */
        public class Pack extends BaseCommoodity {
        }

        /**
         * 商品基类
         */
        public class BaseCommoodity {
            private String name;
            private String price;

            private void setName() {
            }

            ;

            private void setprice() {
            }

            ;

        }
    }
}
