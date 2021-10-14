package application.builders;

import application.PoseidonApplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class RequestBuilder {
    public RequestBuilder(String[] requestParameters) {
        String requestProtocol = requestParameters[0];
        String requestUrl =requestParameters[1];
        String requestMethod = requestParameters[2];
        String requestContentType = requestParameters[3];

        StringBuilder response = new StringBuilder();
        try {
            URL myRequestUrl = new URL(requestUrl);

            HttpURLConnection connection = (HttpURLConnection) myRequestUrl.openConnection();

            connection.setRequestMethod(requestMethod);
            connection.addRequestProperty("Content-Type", requestContentType);
            connection.addRequestProperty("Accept", requestContentType);
            connection.setDoOutput(true);
            int codeResponse = connection.getResponseCode();
            System.out.println("code response : " + codeResponse);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
            PoseidonApplication.applicationMessagesTextArea.setText(ioException.toString());
        }
        PoseidonApplication.dataFlow = response.toString();
        PoseidonApplication.paneLogger.writeInLogger(PoseidonApplication.dataFlow);
    }
}
