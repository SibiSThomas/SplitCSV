package splitCSV;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContractStreamIdExtractor {

	 public static void main(String[] args) {
	        // Ensure the correct number of arguments is provided
	        if (args.length < 3) {
	            System.out.println("Usage: java -jar GetSecurityIdByType.jar <inputFile> <type> <count>");
	            return;
	        }

	        String inputFile = args[0]; // File path from command line
	        String type = args[1]; // Type from command line
	        int count = Integer.parseInt(args[2]); // Count from command line

	        String[] securityIds = getSecurityIdByType(inputFile, type, count);

	        for (String id : securityIds) {
	            System.out.println(id);
	        }
	    }

	    public static String[] getSecurityIdByType(String csvFile, String type, int count) {
	        List<String> completeSecurityIds = new ArrayList<>();
	        String[] requiredSecurityIds = new String[count];

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
	            String line;
	            boolean isFirstLine = true;

	            while ((line = br.readLine()) != null) {
	                // Skip the first line (header)
	                if (isFirstLine) {
	                    isFirstLine = false;
	                    continue;
	                }

	                // Split the line by commas
	                String[] fields = line.split(",");
	                if (fields.length < 3) {
	                    System.out.println("Skipping line due to insufficient fields: " + line);
	                    continue;
	                }

	                // Get the "C" value from the second column
	                String cValue = fields[1];
	                if (type.equals(cValue)) {
	                    completeSecurityIds.add(fields[2]);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        // Convert list to array
	        String[] completeList = completeSecurityIds.toArray(new String[0]);

	        int completeListLength = completeList.length;
	        for (int i = 0; i < count && i < completeListLength; i++) {
	            requiredSecurityIds[i] = completeList[i];
	        }

	        return requiredSecurityIds;
	    }

}
