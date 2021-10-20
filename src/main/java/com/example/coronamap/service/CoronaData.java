package com.example.coronamap.service;

import com.example.coronamap.Model.Header;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class CoronaData {
    public static String link ="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
    public static List<Header> list = new ArrayList<>();
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public static void getData() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .build();
       HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Reader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        for (CSVRecord record : records) {
           Header header = new Header();
            header.setState(record.get("Province/State"));
            header.setCountry(record.get("Country/Region"));
            header.setTotalcaseLatest(Long.parseLong(record.get(100)));
            list.add(header);
        }
    }
    public static List<Header> find_data(String data){
        List<Header> find_data = new ArrayList<>();
        for(Header s : list){
            if(s.getCountry().toLowerCase(Locale.ROOT).contains(data.toLowerCase(Locale.ROOT))){
                find_data.add(s);
            }
        }
        return find_data;
    }
}
