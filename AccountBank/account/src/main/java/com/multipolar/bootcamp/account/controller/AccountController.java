package com.multipolar.bootcamp.account.controller;

import com.multipolar.bootcamp.account.domain.Account;
import com.multipolar.bootcamp.account.dto.ErrorMessage;
import com.multipolar.bootcamp.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody Account account, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ErrorMessage> validationErrors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                ErrorMessage errorMessage = new ErrorMessage();
                errorMessage.setCode("VALIDATION_ERROR");
                errorMessage.setMessage(error.getDefaultMessage());
                validationErrors.add(errorMessage);
            }
            return ResponseEntity.badRequest().body(validationErrors);
        }
        Account createdAccount = accountService.createOrUpdateAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping
    public List<Account> getAllAccount() {
        return accountService.getAllAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Optional<Account> account = accountService.getAccountById(id);
        return account.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable String id, @RequestBody Account account) {
        account.setId(id);
        return accountService.createOrUpdateAccount(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable String id) {
        accountService.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}
