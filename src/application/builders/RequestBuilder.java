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
        String requestBody =requestParameters[2];
        String requestMethod = requestParameters[3];
        String requestContentType = requestParameters[4];

        StringBuilder response = new StringBuilder();

        try {
            URL myRequestUrl = new URL(requestProtocol+requestUrl);

            HttpURLConnection connection = (HttpURLConnection) myRequestUrl.openConnection();

            connection.setRequestMethod(requestMethod);
            connection.addRequestProperty("Content-Type", requestContentType);
            connection.addRequestProperty("Accept", requestContentType);

            if (requestMethod.equals("POST")) {
                connection.setDoOutput(true);
                try(OutputStream os = connection.getOutputStream()) {
                    byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                    os.write(input, 0, input.length);
                }
            }

            int codeResponse = connection.getResponseCode();
            PoseidonApplication.applicationMessages.appendSuccessToLogger("REQUEST","code response : " + codeResponse);

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String responseLine;

            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
        }
        catch (IOException ioException) {
            PoseidonApplication.applicationMessages.appendWarningToLogger("REQUEST", ioException.toString());
        }
        PoseidonApplication.dataFlow = response.toString();
    }
}
