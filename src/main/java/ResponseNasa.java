import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseNasa {
    /* Server response example:
"copyright":"Maxime Oudoux",
"date":"2022-06-03",
"explanation":"by about 10,000 kilometers.",
"hdurl":"https://apod.nasa.gov/apod/image/2206/Oudoux-and-Jeff-Graphy-with10000km.jpg",
"media_type":"image",
"service_version":"v1",
"title":"A 10,000 Kilometer Galactic Bridge",
"url":"https://apod.nasa.gov/apod/image/2206/Oudoux-and-Jeff-Graphy-with10000km_c.jpg"
    */

    private String copyright;
    private String date;
    private String explanation;
    private String hdurl;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public ResponseNasa(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getHdurl() {
        return hdurl;
    }

    @Override
    public String toString() {
        return "ResponseNasa{" +
                "copyright='" + copyright + '\'' +
                ", date='" + date + '\'' +
                ", explanation='" + explanation + '\'' +
                ", hdurl='" + hdurl + '\'' +
                ", media_type='" + media_type + '\'' +
                ", service_version='" + service_version + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
