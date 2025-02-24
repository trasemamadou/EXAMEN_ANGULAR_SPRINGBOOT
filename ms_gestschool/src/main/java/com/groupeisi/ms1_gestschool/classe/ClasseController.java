package com.groupeisi.ms1_gestschool.classe;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClasseController {
private ClasseService classeService;
@GetMapping
public ResponseEntity<Optional<List<ClasseDtoResponse>>> getAllClasses(){
    return ResponseEntity.ok(classeService.getAllClasses());
}
@GetMapping("/{id}")
public ResponseEntity<Optional<ClasseDtoResponse>> getClasseById(@PathVariable Long id){
    return ResponseEntity.ok(classeService.getClasseById(id));
}
@PostMapping
public ResponseEntity<Optional<ClasseDtoResponse>> addClasse(@RequestBody @Valid ClasseDtoRequest classeDtoRequest){
    return ResponseEntity.ok(classeService.addClasse(classeDtoRequest));
}
@PutMapping("/{id}")
    public ResponseEntity<Optional<ClasseDtoResponse>> updateClasse(@PathVariable Long id, @RequestBody ClasseDtoRequest classeDtoRequest){
    return ResponseEntity.ok(classeService.updateClasse(id, classeDtoRequest));
}
@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClasse(@PathVariable Long id){
    classeService.deleteClasse(id);
    return ResponseEntity.noContent().build();
}
}
