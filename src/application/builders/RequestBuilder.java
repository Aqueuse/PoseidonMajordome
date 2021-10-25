package application.builders;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

import application.PoseidonApplication;

public class RequestBuilder {
    public RequestBuilder(String[] requestParameters) {
        String requestProtocol = requestParameters[0];
        String requestUrl =requestParameters[1];
        String requestBody =requestParameters[2];
        String requestMethod = requestParameters[3];
        String requestContentType = requestParameters[4];
        String filePath = requestParameters[5];

        StringBuilder response = new StringBuilder();

        try {
            URL myRequestUrl = new URL(requestProtocol+requestUrl);

            HttpURLConnection connection = (HttpURLConnection) myRequestUrl.openConnection();

            connection.setRequestMethod(requestMethod);
            connection.addRequestProperty("Content-Type", requestContentType);
            connection.addRequestProperty("Accept", requestContentType);

            if (requestMethod.equals("POST")) {
                connection.setDoOutput(true);
                try(OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
                    outputStream.write(input, 0, input.length);
                }
            }

            int codeResponse = connection.getResponseCode();
            PoseidonApplication.applicationMessages.appendSuccessToLogger("REQUEST","code response : " + codeResponse);

            ReadableByteChannel readableByteChannel = Channels.newChannel(myRequestUrl.openStream());

            if (!isValidFilepath(filePath)) {
                PoseidonApplication.applicationMessages.appendWarningToLogger("REQUEST", "filepath is invalid, correct it to save the response");
            }

            else {
                FileOutputStream fileOutputStream = new FileOutputStream(Paths.get(filePath).toFile());
                FileChannel fileChannel = fileOutputStream.getChannel();
                fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                PoseidonApplication.applicationMessages.appendSuccessToLogger("REQUEST", "response saved as " + filePath);
            }
        }
        catch (IOException ioException) {
            PoseidonApplication.applicationMessages.appendWarningToLogger("REQUEST", ioException.toString());
        }
    }

    public static boolean isValidFilepath(String filename) {
        Paths.get(filename);
        return true;
    }
}
