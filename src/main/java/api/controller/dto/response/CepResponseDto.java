package api.controller.dto.response;

import api.controller.dto.SearchTime;

import java.time.LocalDateTime;

public class CepResponseDto implements SearchTime {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String ddd;
    private String siafi;
    private String encodedFormat;
    private String decodedFormat;
    private String searchMoment;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getSiafi() {
        return siafi;
    }

    public void setSiafi(String siafi) {
        this.siafi = siafi;
    }

    public String getEncodedFormat() {
        return encodedFormat;
    }

    public void setEncodedFormat(String encodedFormat) {
        this.encodedFormat = encodedFormat;
    }

    public String getSearchMoment() {
        return searchMoment;
    }

    @Override
    public void searchMoment(String searchMoment) {
        this.searchMoment = searchMoment;
    }

    public String getDecodedFormat() {
        return decodedFormat;
    }

    public void setDecodedFormat(String decodedFormat) {
        this.decodedFormat = decodedFormat;
    }
}
