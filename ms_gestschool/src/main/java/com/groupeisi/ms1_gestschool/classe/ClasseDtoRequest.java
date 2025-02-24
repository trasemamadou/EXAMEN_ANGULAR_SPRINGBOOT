package com.groupeisi.ms1_gestschool.classe;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClasseDtoRequest {
    private Long id;
    @NotBlank(message = "Le nom ne peux pas être vide")
    private String name;
    @NotBlank(message = "La description ne peut pas être vide")
    private String description;
    private boolean archive;
 
}
