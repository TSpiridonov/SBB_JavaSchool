package ru.javaschool.sbb.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
    public class RoleDTO {

        private int id;
        private String roleTitle;
}
