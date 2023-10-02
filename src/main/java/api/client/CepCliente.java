package api.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;

@FeignClient(url="https://viacep.com.br/ws", name = "cep-cliente")
public interface CepCliente {
    @GetMapping("/{UF}/{CIDADE}/{BUSCA}/json")
    String getDataCep(@PathParam("UF") String uf, @PathParam("CIDADE") String cidade,@PathParam("BUSCA") String busca);
}
