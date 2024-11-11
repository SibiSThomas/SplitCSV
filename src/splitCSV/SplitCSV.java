package splitCSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SplitCSV {
	public static void main(String[] args) {

		String inputFile = "D:\\Sibi 2023-01-04\\Srishti\\SpliteCSVFiles\\fo_contract_stream_info (1).csv"; // Replace with
																										// the actual
																										// path
		Map<String, BufferedWriter> writers = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			boolean isFirstLine = true;

			while ((line = br.readLine()) != null) {
				// Skip the first line (header)
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				// Split the line by tabs (adjust if necessary)
				String[] fields = line.split(",");
				if (fields.length < 2) {
					System.out.println("Skipping line due to insufficient fields: " + line);
					continue;
				}

				// Get the "C" value from the second column
				String cValue = fields[1];
//              String outputFileName = "D:\\Sibi 2023-01-04\\Srishti\\SpliteCSVFiles\\c" + cValue + "csv.csv";
				String outputFileName = cValue + ".csv";

				// Check if writer exists for this file or create a new one
				// Map<String, BufferedWriter> writers = new HashMap<>();
				BufferedWriter writer = writers.get(outputFileName);
				if (writer == null) {
					System.out.println("Creating file: " + outputFileName);
					writer = new BufferedWriter(new FileWriter(outputFileName));
					writers.put(outputFileName, writer);
				}

				// Write the line to the appropriate file
				writer.write(line);
				writer.newLine();
			}

			// Close all writers
			for (BufferedWriter writer : writers.values()) {
				writer.close();
			}
			System.out.println("File splitting completed successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
