package com.groupeisi.ms1_gestschool.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EntityExistsException extends RuntimeException{
    private String message;
}
