package com.multipolar.bootcamp.gatewaybank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multipolar.bootcamp.gatewaybank.dto.ErrorMessageDTO;
import com.multipolar.bootcamp.gatewaybank.dto.ProductDTO;
import com.multipolar.bootcamp.gatewaybank.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.multipolar.bootcamp.gatewaybank.util.RestTemplateUtil;
import org.springframework.web.client.HttpClientErrorException;
import com.multipolar.bootcamp.gatewaybank.kafka.AccessLog;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final String PRODUCT_URL = "http://localhost:8081/product";
    private final RestTemplateUtil restTemplateUtil;
    private final ObjectMapper objectMapper;
    private final AccessLogService logService;

    @Autowired
    public ApiController(RestTemplateUtil restTemplateUtil, ObjectMapper objectMapper, AccessLogService logService) {
        this.restTemplateUtil = restTemplateUtil;
        this.objectMapper = objectMapper;
        this.logService = logService;
    }

    //http://localhost:8080/api/getProduct
    @GetMapping("/getProduct")
    public ResponseEntity<?> getProduct() throws JsonProcessingException {
        try {
            ResponseEntity<?> response = restTemplateUtil.getList(PRODUCT_URL, new ParameterizedTypeReference<>() {});
            AccessLog accessLog = new AccessLog("GET", PRODUCT_URL,
                    response.getStatusCodeValue(), LocalDateTime.now(), "successful");
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            List<ErrorMessageDTO> errorResponse =
                    objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            AccessLog accessLog = new AccessLog("GET", PRODUCT_URL,
                    ex.getStatusCode().value(), LocalDateTime.now(), "failed");
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }

    //http:/localhost:8080/api/createProduct
    @PostMapping("/createProduct")
    public ResponseEntity<?> postProduct(@RequestBody ProductDTO productDTO) throws JsonProcessingException {
        try {
            ResponseEntity<?> response = restTemplateUtil.post(PRODUCT_URL, productDTO, ProductDTO.class);
            AccessLog accessLog = new AccessLog("POST", PRODUCT_URL,
                    response.getStatusCodeValue(), LocalDateTime.now(), "successful");
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            List<ErrorMessageDTO> errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            AccessLog accessLog = new AccessLog("POST", PRODUCT_URL,
                    ex.getStatusCode().value(), LocalDateTime.now(), "failed");
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductById(@PathVariable String id) throws JsonProcessingException {
        try {
            ResponseEntity<?> response = restTemplateUtil.delete(PRODUCT_URL + "/" + id);
            AccessLog accessLog = new AccessLog("DELETE", PRODUCT_URL + "/" + id,
                    response.getStatusCodeValue(), LocalDateTime.now(), "successful");
            logService.logAccess(accessLog);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException ex) {
            AccessLog accessLog = new AccessLog("DELETE", PRODUCT_URL + "/" + id,
                    ex.getStatusCode().value(), LocalDateTime.now(), "failed");
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable String id, @RequestBody ProductDTO productDTO) throws JsonProcessingException {
        try {
            ResponseEntity<?> response = restTemplateUtil.put(PRODUCT_URL + "/" + id, productDTO, ProductDTO.class);
            AccessLog accessLog = new AccessLog("PUT", PRODUCT_URL + "/" + id,
                    response.getStatusCodeValue(), LocalDateTime.now(), "successful");
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            AccessLog accessLog = new AccessLog("PUT", PRODUCT_URL + "/" + id,
                    ex.getStatusCode().value(), LocalDateTime.now(), "failed");
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) throws JsonProcessingException {
        try {
            ResponseEntity<?> response = restTemplateUtil.getList(PRODUCT_URL+ "/" + id, new ParameterizedTypeReference<>() {});
            AccessLog accessLog = new AccessLog("GET", PRODUCT_URL + "/" + id,
                    response.getStatusCodeValue(), LocalDateTime.now(), "successful");
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            AccessLog accessLog = new AccessLog("GET", PRODUCT_URL + "/" + id,
                    ex.getRawStatusCode(), LocalDateTime.now(), "failed");
            logService.logAccess(accessLog);
            List<ErrorMessageDTO> errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }
}
