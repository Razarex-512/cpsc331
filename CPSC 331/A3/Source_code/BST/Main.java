import java.util.Random;
import java.util.ArrayList;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
public class Main {
    private final static Random random = new Random();
    static double start;
    static double end;

    public static void main(String[] args) {
        BinarySearchTree<Integer> t0 = new BinarySearchTree<>();

        int n = 300;
        int m = 100; // Generate m between 100 and 200
        int count = 0;
        ArrayList<Double> hh = new ArrayList<>();
        ArrayList<Double> tt = new ArrayList<>();

        while (count < m) {
            int ri = g(n);
            if (!t0.contains(ri)) {
                start = System.nanoTime();
                t0.add(ri);
                end = System.nanoTime();
                hh.add((double)t0.height());
                tt.add(end - start);
                count++;
            }
        }

        int k = random.nextInt(101) + 50; // Generate m between 50 and 150
        count = 0;
        while (count < k) {
            int ri2 = g(n);
            if (!t0.contains(ri2)) {
                start = System.nanoTime();
                t0.add(ri2);
                end = System.nanoTime();
                hh.add((double)t0.height());
                tt.add(end - start);
                count++;
            }
        }

        double[] hhArray = hh.stream().mapToDouble(Double::doubleValue).toArray();
        double[] ttArray = tt.stream().mapToDouble(Double::doubleValue).toArray();

        XYChart chart = new XYChartBuilder().width(750).height(750).title("Time vs Height").xAxisTitle("Time (ns)").yAxisTitle("Height").build();
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
        chart.getStyler().setMarkerSize(5);
        chart.getStyler().setXAxisTickMarkSpacingHint(10);
        chart.getStyler().setYAxisTickMarkSpacingHint(25);

        chart.addSeries("Time vs Height", ttArray, hhArray);

        new SwingWrapper<>(chart).displayChart();
    }

    public static int g(int n) {
        return random.nextInt(n + 1);
    }
}
