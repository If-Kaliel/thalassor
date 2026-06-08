package br.com.fiap.services;

import jakarta.enterprise.context.ApplicationScoped;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Locale;

@ApplicationScoped
public class NasaSatelliteService {

    public String gerarUrlImagem(Double latitude, Double longitude) {
        String dataBusca = LocalDate.now().minusDays(7) + "T00:00:00Z";

        double margem = 0.20;

        double latMin = latitude - margem;
        double lonMin = longitude - margem;
        double latMax = latitude + margem;
        double lonMax = longitude + margem;

        String bbox = String.format(
                Locale.US,
                "%.6f,%.6f,%.6f,%.6f",
                latMin,
                lonMin,
                latMax,
                lonMax
        );

        return "https://wvs.earthdata.nasa.gov/api/v1/snapshot?" +
                "REQUEST=GetSnapshot" +
                "&TIME=" + encode(dataBusca) +
                "&BBOX=" + encode(bbox) +
                "&CRS=" + encode("EPSG:4326") +
                "&LAYERS=" + encode("MODIS_Terra_CorrectedReflectance_TrueColor") +
                "&WRAP=day" +
                "&FORMAT=" + encode("image/jpeg") +
                "&WIDTH=1200" +
                "&HEIGHT=800";
    }

    private String encode(String valor) {
        return URLEncoder.encode(valor, StandardCharsets.UTF_8);
    }
}