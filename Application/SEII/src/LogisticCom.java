// import jackson datatype from the external library


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.*;
public class LogisticCom {
    URL weightsUrl = new url('https://logistics.dh.dtr0cks.de/api/v1/vehicle/weights');
    URL dimensionsUrl = new url('https://logistics.dh.dtr0cks.de/api/v1/vehicle/dimensions');
    public LogisticCom(){

    }
        // make a post request to the weights url with following parameters in the body:
        // model: String
        // codes:"String"
        // buildDate: String
        // return the response as a json object
    public JSONObject getWeights(String model, String codes, String buildDate) throws IOException {
        JSONObject json = new JSONObject();
        json.put("model", model);
        json.put("codes", codes);
        json.put("buildDate", buildDate);
        String jsonString = json.toString();
        byte[] postData = jsonString.getBytes(StandardCharsets.UTF_8);
        int postDataLength = postData.length;
        URLConnection conn = weightsUrl.openConnection();
        conn.setDoOutput(true);
        conn.setInstanceFollowRedirects(false);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("charset", "utf-8");
        conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        conn.setUseCaches(false);
        try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream())) {
            wr.write(postData);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            JSONObject responseJson = new JSONObject(response.toString());
            return responseJson;
        }
    }










}