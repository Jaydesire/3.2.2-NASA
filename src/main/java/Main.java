import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;


public class Main {
    public static void main(String[] args) {

        //https://api.nasa.gov/planetary/apod?api_key=XjX5zYrtneMUkJobhBxOGaOC8paVlxpc6xsLHN2c

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=XjX5zYrtneMUkJobhBxOGaOC8paVlxpc6xsLHN2c");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            ObjectMapper objectMapper = new ObjectMapper();

            ResponseNasa responseNasa = objectMapper.readValue(
                    response.getEntity()
                            .getContent(),
                    new TypeReference<ResponseNasa>() {
                    }
            );

            String hdUrl = responseNasa.getHdurl();

            request = new HttpGet(hdUrl);
            downloader(httpClient, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void downloader(CloseableHttpClient httpClient, HttpGet request) {
        String hdUrl = request.getURI().toString();
        String[] addressFileName = hdUrl.split("/");
        String fileName = addressFileName[addressFileName.length - 1];
        System.out.println(fileName);

        try (
                CloseableHttpResponse fileResponse = httpClient.execute(request);
                BufferedInputStream in = new BufferedInputStream(fileResponse.getEntity().getContent());
                FileOutputStream fout = new FileOutputStream(fileName)
        ) {
            byte[] data = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1) {
                fout.write(data, 0, count);
                fout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
