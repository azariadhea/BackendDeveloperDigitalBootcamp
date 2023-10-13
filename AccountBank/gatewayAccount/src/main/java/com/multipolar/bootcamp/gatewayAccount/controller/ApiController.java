package com.multipolar.bootcamp.gatewayAccount.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.multipolar.bootcamp.gatewayAccount.dto.AccountDTO;
import com.multipolar.bootcamp.gatewayAccount.dto.ErrorMessageDTO;
import com.multipolar.bootcamp.gatewayAccount.kafka.AccessLog;
import com.multipolar.bootcamp.gatewayAccount.service.AccessLogService;
import com.multipolar.bootcamp.gatewayAccount.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final String ACCOUNT_URL = "http://localhost:8084/account";
    private final RestTemplateUtil restTemplateUtil;
    private final ObjectMapper objectMapper;
    private final AccessLogService logService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    public ApiController(RestTemplateUtil restTemplateUtil, ObjectMapper objectMapper, AccessLogService logService) {
        this.restTemplateUtil = restTemplateUtil;
        this.objectMapper = objectMapper;
        this.logService = logService;
    }

    //http://localhost:8083/api/getAccount
    @GetMapping("/getAccount")
    public ResponseEntity<?> getAccount() throws JsonProcessingException {
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        try {
            ResponseEntity<?> response = restTemplateUtil.getList(ACCOUNT_URL, new ParameterizedTypeReference<>() {});
            AccessLog accessLog = new AccessLog("GET", ACCOUNT_URL,
                    response.getStatusCodeValue(), "successful", clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            List<ErrorMessageDTO> errorResponse =
                    objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            AccessLog accessLog = new AccessLog("GET", ACCOUNT_URL,
                    ex.getStatusCode().value(), "failed",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }

    //http:/localhost:8083/api/createAccount
    @PostMapping("/createAccount")
    public ResponseEntity<?> postAccount(@RequestBody AccountDTO accountDTO) throws JsonProcessingException {
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        try {
            ResponseEntity<?> response = restTemplateUtil.post(ACCOUNT_URL, accountDTO, AccountDTO.class);
            AccessLog accessLog = new AccessLog("CREATE", ACCOUNT_URL,
                    response.getStatusCodeValue(), "successful",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            List<ErrorMessageDTO> errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            AccessLog accessLog = new AccessLog("CREATE", ACCOUNT_URL,
                    ex.getStatusCode().value(), "failed",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable String id) throws JsonProcessingException {
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        try {
            ResponseEntity<?> response = restTemplateUtil.delete(ACCOUNT_URL + "/" + id);
            AccessLog accessLog = new AccessLog("DELETE", ACCOUNT_URL + "/" + id,
                    response.getStatusCodeValue(), "successful", clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.noContent().build();
        } catch (HttpClientErrorException ex) {
            AccessLog accessLog = new AccessLog("DELETE", ACCOUNT_URL + "/" + id,
                    ex.getStatusCode().value(), "failed", clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable String id, @RequestBody AccountDTO accountDTO) throws JsonProcessingException {
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        try {
            ResponseEntity<?> response = restTemplateUtil.put(ACCOUNT_URL + "/" + id, accountDTO, AccountDTO.class);
            AccessLog accessLog = new AccessLog("PUT", ACCOUNT_URL + "/" + id,
                    response.getStatusCodeValue(), "successful",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            AccessLog accessLog = new AccessLog("PUT", ACCOUNT_URL + "/" + id,
                    ex.getStatusCode().value(), "failed",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(ex.getStatusCode()).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAccountById(@PathVariable String id) throws JsonProcessingException {
        String clientIP = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        try {
            ResponseEntity<?> response = restTemplateUtil.getList(ACCOUNT_URL+ "/" + id, new ParameterizedTypeReference<>() {});
            AccessLog accessLog = new AccessLog("GET", ACCOUNT_URL + "/" + id,
                    response.getStatusCodeValue(), "successful",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        } catch (HttpClientErrorException ex) {
            AccessLog accessLog = new AccessLog("GET", ACCOUNT_URL + "/" + id,
                    ex.getRawStatusCode(), "failed",clientIP, userAgent, LocalDateTime.now());
            logService.logAccess(accessLog);
            List<ErrorMessageDTO> errorResponse = objectMapper.readValue(ex.getResponseBodyAsString(), List.class);
            return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
        }
    }
}