import joinery.DataFrame;
import tech.tablesaw.api.Table;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws IOException {
        DataFrame df = Utils.readDataJoinery();
        System.out.println(Utils.dataDescribe(df));
        Table data = Utils.readDataTablesaw();
        System.out.println(Utils.dataStructure(data));
        System.out.println(Utils.dataSummary(data));

        System.out.println(Utils.addDateColumnToData(data));
    }
}
