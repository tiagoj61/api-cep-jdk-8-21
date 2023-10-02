package api.functions;

import api.controller.dto.response.CepResponseDto;

@FunctionalInterface
public interface FilterCepsFunction {
    boolean containsValue(String cep, Integer value);
}
