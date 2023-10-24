package api.service.impl;

import api.client.CepCliente;
import api.controller.dto.response.CepResponseDto;
import api.converter.ConvertJson;
import api.functions.FilterCepsFunction;
import api.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CepServiceImpl implements CepService {
    @Autowired
    CepCliente cepCliente;

    @Override
    public CepResponseDto getCepData(String cep) {
        //cepCliente.getDataCep(cep);
        return null;
    }

    @Override
    public CepResponseDto getAllCepsByName(String uf, String cidade, String name) {
        try {
            //HTTP Client
            HttpResponse<String> response = getCep(uf, cidade, name);

            List<CepResponseDto> cepResponseDto = (List<CepResponseDto>) ConvertJson.convert(response.body(), CepResponseDto.class);

            //Execução do codigo java NomeArquivo.java
            //Variaveis por inferencia

            var cepResponseDtoInferencia = (List<CepResponseDto>) ConvertJson.convert(response.body(), CepResponseDto.class);

            //Performace
            //G1 - reduziu em 60 o tempo de esperado cliente pelo GC

            //Local-Variable Syntax for Lambda
            var allCeps = cepResponseDtoInferencia.stream().map(cepResponseDto1 -> cepResponseDto1.getCep())
                    .map((@NonNull var x) -> x.toUpperCase())
                    .collect(Collectors.joining(", "));


        } catch (Exception e) {
        }
        return null;
    }

    private HttpResponse<String> getCep(String uf, String cidade, String name) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        URI uri = URI.create("https://viacep.com.br/ws" + "/" + uf + "/" + cidade + "/" + name + "/json");

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public void removeCeps(List<CepResponseDto> ceps, FilterCepsFunction function) {
        for (int i = ceps.size() - 1; i >= 0; i--) {
            if (function.containsValue(ceps.get(i).getCep(), 100))
                ceps.remove(i);
        }
    }
}
