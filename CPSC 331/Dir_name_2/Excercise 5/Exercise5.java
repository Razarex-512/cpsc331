public class Exercise5 {

    static class DynamicStack {
        private int[] asteroidData;
        private int count;

        public DynamicStack() {
            asteroidData = new int[10]; // Adjustable initial capacity
            count = 0;
        }

        public void add(int data) {
            if (count == asteroidData.length) {
                increaseSize();
            }
            asteroidData[count++] = data;
        }

        public int remove() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return asteroidData[--count];
        }

        public int top() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return asteroidData[count - 1];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        private void increaseSize() {
            int newSize = asteroidData.length * 2;
            int[] newData = new int[newSize];
            System.arraycopy(asteroidData, 0, newData, 0, count);
            asteroidData = newData;
        }
    }

    public static int[] processCollisions(int[] spaceRocks) {
        DynamicStack rockStack = new DynamicStack();
        int processStep = 1;

        for (int current : spaceRocks) {
            System.out.print("Process-" + processStep + ": [");

            for (int i = 0; i < rockStack.count; i++) {
                System.out.print(rockStack.asteroidData[i] + " ");
            }
            System.out.println("]");

            if (rockStack.isEmpty() || (current > 0 && rockStack.top() > 0) || (current < 0 && rockStack.top() < 0)) {
                rockStack.add(current);
            } else {
                while (!rockStack.isEmpty() && rockStack.top() > 0 && Math.abs(current) > rockStack.top()) {
                    rockStack.remove();
                }

                if (rockStack.isEmpty() || rockStack.top() < 0) {
                    rockStack.add(current);
                } else if (Math.abs(current) == rockStack.top()) {
                    rockStack.remove();
                }
            }

            processStep++;
        }

        System.out.print("Process-" + processStep + ": Final asteroid = [" + (rockStack.isEmpty() ? "" : rockStack.top()) + "]");

        int[] remainingRocks = new int[rockStack.count];
        for (int i = rockStack.count - 1; i >= 0; i--) {
            remainingRocks[i] = rockStack.remove();
        }

        return remainingRocks;
    }

    public static void main(String[] args) {
        int[] spaceRocks = {7, 16, -16, -7, 5, -5, -21, 2, 2, 34, -9};

        System.out.println("Initial asteroids = " + convertToString(spaceRocks));
        processCollisions(spaceRocks);
    }

    private static String convertToString(int[] data) {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < data.length; i++) {
            result.append(data[i]);
            if (i < data.length - 1) {
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }
}

