package com.groupeisi.ms1_gestschool.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SessionDtoRequest{
        private  Long id;
        private String name;
        private String description;
        private boolean archive;
        private Long courseId;
        //        LocalDateTime beginDate,
//LocalDateTime endDate,
}

