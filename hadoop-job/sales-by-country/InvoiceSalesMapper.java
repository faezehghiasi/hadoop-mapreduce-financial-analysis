import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class InvoiceSalesMapper extends Mapper<LongWritable, Text, Text, DoubleWritable> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if (key.get() == 0 && value.toString().contains("InvoiceNo")) return;

        String[] fields = value.toString().split(",");
        if (fields.length >= 8) {
            try {
                String country = fields[7];
                int quantity = Integer.parseInt(fields[3]);
                double unitPrice = Double.parseDouble(fields[5]);
                context.write(new Text(country), new DoubleWritable(quantity * unitPrice));
            } catch (NumberFormatException e) {}
        }
    }
}

