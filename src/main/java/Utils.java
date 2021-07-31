import joinery.DataFrame;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import tech.tablesaw.api.DateColumn;
import tech.tablesaw.api.Table;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {
    public static DataFrame readDataJoinery() throws IOException {
        return DataFrame.readCsv("resources/train.csv");
    }

    public static String dataDescribe(DataFrame df){
        return df.describe().toString();
    }

    public static Table readDataTablesaw() throws IOException {
        return Table.read().csv("resources/train.csv");
    }

    public static String dataStructure(Table data){
        return data.structure().toString();
    }

    public static String dataSummary(Table data){
        return data.summary().toString();
    }

    public static String addDateColumnToData(Table data){
        int rowCount=data.rowCount ();
        List<LocalDate> dateList=new ArrayList<LocalDate>();
        for(int i=0;i<rowCount;i++) {
            dateList.add (LocalDate.of (2021, 3, i%31==0?1:i%31));
        }
        DateColumn dateColumn=DateColumn.create("Date",dateList);
        data.insertColumn (data.columnCount (),dateColumn);
        return data.toString();
    }


    public void graphPassengerClass(Table passengerList) {
    //filter to get a map of passenger class and total number of passengers in each class
        Map<String, Long> result =
                passengerList.stream ().collect (
                        Collectors.groupingBy (
                                TitanicPassenger::getPclass, Collectors.counting ()
                        )
                );
    // Create Chart
        PieChart chart = new PieChartBuilder().width (800).height (600).title (getClass ().getSimpleName ()).build ();
    // Customize Chart
        Color[] sliceColors = new Color[]{new Color (180, 68, 50), new Color (130, 105, 120), new Color (80, 143, 160)};
        chart.getStyler ().setSeriesColors (sliceColors);
    // Series
        chart.addSeries ("First Class", result.get ("1"));
        chart.addSeries ("Second Class", result.get ("2"));
        chart.addSeries ("Third Class", result.get ("3"));
    // Show it
        new SwingWrapper(chart).displayChart ();
    }




}
