package com.gungor.exchange.integrations.ratesapi;

import com.gungor.exchange.integrations.ExchangeServiceProvider;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class RatesApiImpl implements ExchangeServiceProvider {

    private final RestTemplate restTemplate;
    private final String apiUrl = "https://api.ratesapi.io/api/latest";
    private final String currenciesUrl = apiUrl;
    private final String rateUrl = apiUrl + "?base={base}&symbols={target}";
    private final HttpEntity httpEntity;

    public RatesApiImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        httpEntity = new HttpEntity(headers);
    }

    @Override
    public List<String> getSupportedCurrencies() {
        CurrenciesResponse response = getRequest(currenciesUrl, CurrenciesResponse.class, Collections.EMPTY_MAP);
        if (Objects.nonNull(response)) {
            return new ArrayList<>(response.getRates().keySet());
        } else {
            return null;
        }
    }

    @Override
    public Double exchangeRate(String baseCurrency, String targetCurrency) {
        Map<String, String> uriParameters = new HashMap<>();
        uriParameters.put("base", baseCurrency);
        uriParameters.put("target", targetCurrency);

        CurrenciesResponse response = getRequest(rateUrl, CurrenciesResponse.class, uriParameters);
        if (Objects.nonNull(response)) {
            return response.getRates().get(targetCurrency);
        } else {
            return null;
        }
    }


    private <T> T getRequest(String url, Class<T> responseType, Map<String, String> uriParamaters) {
        ResponseEntity<T> response = this.restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType, uriParamaters);
        switch (response.getStatusCode()) {
            case OK:
            case BAD_REQUEST:
                return response.getBody();
            default:
                return null;
        }
    }
}
