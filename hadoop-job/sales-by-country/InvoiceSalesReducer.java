import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class InvoiceSalesReducer extends Reducer<Text, DoubleWritable, Text, DoubleWritable> {
    public void reduce(Text key, Iterable<DoubleWritable> values, Context context) throws IOException, InterruptedException {
        double total = 0;
        for (DoubleWritable val : values) {
            total += val.get();
        }
        context.write(key, new DoubleWritable(total));
    }
}

