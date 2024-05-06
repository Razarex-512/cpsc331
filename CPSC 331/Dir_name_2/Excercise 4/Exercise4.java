public class Exercise4 {
    public static class Footwear {
        private String make;
        private double originalPrice;
        private double reductionPercentage;

        Footwear(String make, double originalPrice, double reductionPercentage) {
            this.make = make;
            this.originalPrice = originalPrice;
            this.reductionPercentage = reductionPercentage;
        }

        double calculateDiscountPrice() {
            return originalPrice * (1 - reductionPercentage / 100);
        }

        double fetchDiscount() {
            return reductionPercentage;
        }

        @Override
        public String toString() {
            return "(" + make + "," + ((int)originalPrice) + "," + ((int)reductionPercentage) + ")";
        }
    }

    public static class CustomStack<T> {
        private Object[] dataStore;
        private int count = 0;

        public CustomStack(int capacity) {
            dataStore = new Object[capacity];
        }

        public void add(T element) {
            if (count == dataStore.length) {
                throw new StackOverflowError();
            }
            dataStore[count++] = element;
        }

        @SuppressWarnings("unchecked")
        public T remove() {
            if (count == 0) {
                throw new IllegalStateException("Empty Stack");
            }
            T element = (T) dataStore[--count];
            dataStore[count] = null; // Prevent memory leak
            return element;
        }

        @SuppressWarnings("unchecked")
        public T peekTop() {
            if (count == 0) {
                throw new IllegalStateException("Empty Stack");
            }
            return (T) dataStore[count - 1];
        }

        public boolean isEmpty() {
            return count == 0;
        }
    }

    public static CustomStack<Footwear> compileOptimalCart(Footwear[] inventory) {
        CustomStack<Footwear> optimalCart = new CustomStack<>(inventory.length);
        optimalCart.add(inventory[0]);
        double lastPrice = optimalCart.peekTop().calculateDiscountPrice();
        System.out.println("Initial Step: " + optimalCart.peekTop().toString());
        for (int i = 1; i < inventory.length; i++) {
            double currentPrice = inventory[i].calculateDiscountPrice();
            if (currentPrice < lastPrice) {
                optimalCart.remove();
                optimalCart.add(inventory[i]);
                lastPrice = optimalCart.peekTop().calculateDiscountPrice();
            } else if (currentPrice == lastPrice) {
                if (inventory[i].fetchDiscount() > optimalCart.peekTop().fetchDiscount()) {
                    optimalCart.remove();
                    optimalCart.add(inventory[i]);
                    lastPrice = optimalCart.peekTop().calculateDiscountPrice();
                }
            }
            System.out.print("Modification " + (i + 1) + ": ");
            System.out.println("[" + optimalCart.peekTop().toString() + "]");
        }
        return optimalCart;
    }

    public static void main(String[] args) {
        Footwear[] inventory = {
            new Footwear("Nike", 450, 15),
            new Footwear("Adidas", 400, 10),
            new Footwear("Puma", 600, 30),
            new Footwear("Sorel", 360, 0),
            new Footwear("Aldo", 680, 15),
            new Footwear("Skechers", 390, 0)
        };

        System.out.print("Footwear Selection: ");
        for (Footwear item : inventory) {
            System.out.print(item + ",");
        }
        System.out.println();

        CustomStack<Footwear> optimizedCart = compileOptimalCart(inventory);
    }
}

