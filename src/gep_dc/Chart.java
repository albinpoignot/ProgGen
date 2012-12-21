/**
 * 
 */
package gep_dc;

import java.awt.Color;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.XYDataset;

/**
 * 
 */
public class Chart extends JFrame {

	private static final long serialVersionUID = 1L;
	public static XYDataset dataset;

	public Chart(String applicationTitle, String chartTitle, XYDataset dataset) {
        super(applicationTitle);
        // This will create the dataset 
        this.dataset = dataset;
        
        // based on the dataset we create the chart
        JFreeChart chart = createChart(chartTitle);
        
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        // add it to our application
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a chart
     */
    private JFreeChart createChart(String title) {
        
    	JFreeChart chart = ChartFactory.createXYLineChart
                (title,  // Title
                 "Iteration",           // X-Axis label
                 "Fitness",           // Y-Axis label
                 dataset,          // Dataset
                 PlotOrientation.VERTICAL,
                 true,                // Show legend
                 false,
                 false
                );

    	final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
    	
    	final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
        
    }
	
}
