package com.stussy.stussyclone20220929HDH.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Role {
   private int id;
   private String role;
   private String role_name;
}
