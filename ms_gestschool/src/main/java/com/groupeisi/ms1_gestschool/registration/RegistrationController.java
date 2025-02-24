package com.groupeisi.ms1_gestschool.registration;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/registrations")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RegistrationController {
    private final RegistrationService registrationService;
    @GetMapping
    public ResponseEntity<List<RegistrationDtoResponse>> getAllRegistrations() {
        Optional<List<RegistrationDtoResponse>> registrations = registrationService.getAllRegistrations();
        return registrations.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    @PostMapping
    public ResponseEntity<RegistrationDtoResponse> addRegistration(@RequestBody @Valid  RegistrationDtoRequest registrationDto) {
        Optional<RegistrationDtoResponse> savedRegistration = registrationService.addRegistration(registrationDto);
        return savedRegistration.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<RegistrationDtoResponse> updateRegistration(@PathVariable Long id, @RequestBody @Valid RegistrationDtoRequest registrationDto) {
        Optional<RegistrationDtoResponse> updatedRegistration = registrationService.updateRegistration(id, registrationDto);
        return updatedRegistration.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteRegistration(@PathVariable Long id) {
        Optional<?> response = registrationService.deleteRegistration(id);

        if (response.isPresent()) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Registration successfully deleted.");
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Registration not found"));
        }
    }

}