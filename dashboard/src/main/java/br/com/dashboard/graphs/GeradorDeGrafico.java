package br.com.dashboard.graphs;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.IntervalCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Gera os gráficos de total de mensagens de informe diárias por operação
 * @author contrui
 *
 */
public class GeradorDeGrafico {
	public String getImage(HashMap<Date, Integer> map, String nomeOperacao, String uriActTask) {
		String yAxisLabel = "Qtd Msg Informe";

		DefaultCategoryDataset dataset = createDataset(map);

		JFreeChart chart = ChartFactory.createLineChart(nomeOperacao, uriActTask, yAxisLabel, dataset, PlotOrientation.VERTICAL, true, true, false);

		final CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		// customise the range axis...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setAutoRangeIncludesZero(true);

		final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

		// label the points
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMaximumFractionDigits(2);
        IntervalCategoryItemLabelGenerator intervalCategoryItemLabelGenerator = new IntervalCategoryItemLabelGenerator(StandardXYItemLabelGenerator.DEFAULT_ITEM_LABEL_FORMAT, format);
		renderer.setBaseItemLabelGenerator( intervalCategoryItemLabelGenerator );
        renderer.setBaseItemLabelsVisible(true);
        
        // set the renderer's stroke
        Stroke stroke = new BasicStroke( 3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL );
        renderer.setBaseOutlineStroke(stroke);
		
		CategoryPlot catPlot = chart.getCategoryPlot();

        CategoryAxis domainAxis = catPlot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		int width = 640;
		int height = 480;

		String b64 = printBase64Binary( chart, width, height );

		return b64;
	}

	/**
	 * @param chart
	 * @param width
	 * @param height
	 * @param b64
	 * @return
	 */
	private String printBase64Binary(JFreeChart chart, int width, int height ) {
		byte[] imageInByteArray;
		String b64 = "";
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(chart.createBufferedImage(width, height), "png", baos);
			baos.flush();
			imageInByteArray = baos.toByteArray();
			baos.close();
			b64 = DatatypeConverter.printBase64Binary(imageInByteArray);
		} catch (IOException e) {
			Logger.getLogger(GeradorDeGrafico.class.getName()).log(Level.WARNING, null, e);
		}
		return b64;
	}

	/**
	 * Cria o database
	 * @param map
	 * @return
	 */
	private DefaultCategoryDataset createDataset(HashMap<Date, Integer> map) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		for (Date data : map.keySet()) {
			dataset.addValue(map.get(data), "Progressão da Qtd de Msgs", sdf.format(data));
		}

		return dataset;
	}
}