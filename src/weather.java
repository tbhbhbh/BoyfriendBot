import java.net.URL;
import java.net.HttpURLConnection;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class weather {

    public String callWebAPI(String datasetName, String keyref) throws Exception {
        // Step 1: Construct URL
        String url = "http://api.nea.gov.sg/api/WebAPI/?dataset=24hrs_forecast&keyref=781CF461BB6606ADA12D573149525F69F9E98B66D1269B12";

        // Step 2: Call API Url
        URL obj = new URL(url);
        String str;
        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : "+ url);
        System.out.println("Response Code : "+ responseCode);
        // Step 3: Check the response status
        if(responseCode == 200) {
            // Step 3a: If response status == 200
            // print the received xml
            str = readStream(con.getInputStream());
        } else {
            // Step 3b: If response status != 200
            // print the error received from server
            str = "Error in accessing API - " +
                    readStream(con.getErrorStream());
        }

        return str;
    }
    // Read the responded result
    private String readStream(InputStream inputStream) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }
}

