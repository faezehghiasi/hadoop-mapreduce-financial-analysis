import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class InvoiceCountryMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private Text country = new Text();
    private final static IntWritable one = new IntWritable(1);

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        if (line.startsWith("InvoiceNo")) return;
        String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        if (fields.length >= 8) {
            String countryName = fields[7].trim();
            if (!countryName.isEmpty()) {
                country.set(countryName);
                context.write(country, one);
            }
        }
    }
}

