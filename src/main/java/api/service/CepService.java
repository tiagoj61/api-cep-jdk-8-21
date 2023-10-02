package api.service;

import api.controller.dto.response.CepResponseDto;

public interface CepService {
    CepResponseDto getCepData(String cep);
    CepResponseDto getAllCepsByName(String uf, String cidade, String name);
}
