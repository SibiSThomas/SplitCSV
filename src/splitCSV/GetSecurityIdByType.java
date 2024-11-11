package splitCSV;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetSecurityIdByType {
    public static void main(String[] args) {

        String inputFile = "D:\\Sibi 2023-01-04\\Srishti\\SpliteCSVFiles\\fo_contract_stream_info.csv";
        
        String[] securityIds = getSecurityIdByType(inputFile, "10", 50);

        for (String id : securityIds) {
            System.out.println(id);
        }
    }

    public static String[] getSecurityIdByType(String csvFile, String type, int count) {
        List<String> completeSecurityIdList = new ArrayList<>();
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
                    completeSecurityIdList.add(fields[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert list to array
        String[] completeList = completeSecurityIdList.toArray(new String[0]);
     
        int completeListLength = completeList.length;
        for (int i = 0; i<count && i<completeListLength; i++) {
            requiredSecurityIds[i] = completeList[i];
        }

        return requiredSecurityIds;
    }
}
