/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 *
 * @author Guslarz
 */
@Named(value = "chartBean")
@RequestScoped
public class ChartBean {
    
    private final LineChartModel chartModel;

    /**
     * Creates a new instance of ChartBean
     */
    public ChartBean() {
        chartModel = new LineChartModel();
        chartModel.setLegendPosition("w");
        chartModel.getAxis(AxisType.X).setLabel("Angle");
        chartModel.getAxis(AxisType.Y).setLabel("Value");
        chartModel.setZoom(true);
        
        ChartSeries sine = new ChartSeries();
        sine.setLabel("sin(x)");
        ChartSeries cosine = new ChartSeries();
        cosine.setLabel("cos(x)");
        
        for (int deg = 0; deg <= 360; deg += 10) {
            double rad = Math.toRadians(deg);
            sine.set(deg, Math.sin(rad));
            cosine.set(deg, Math.cos(rad));
        }
        
        chartModel.addSeries(sine);
        chartModel.addSeries(cosine);
    }

    /**
     * @return the chartModel
     */
    public LineChartModel getChartModel() {
        return chartModel;
    }
}
