package com.resumebuilder.detailsMS.response;

import com.resumebuilder.detailsMS.entity.CustomerDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private String message;
    private CustomerDetails customerDetails;
}
