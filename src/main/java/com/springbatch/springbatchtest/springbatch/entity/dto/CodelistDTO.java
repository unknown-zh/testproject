package com.springbatch.springbatchtest.springbatch.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CodelistDTO implements java.io.Serializable {
    private String name;
    private String value;
    private String typeCode;
    private String typeName;
}
