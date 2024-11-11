package com.example.newspringbootproject.pojo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatorPojo {
    @NotNull(message = "id不能为空")
    private Long id;

    @Future(message = "需要一个将来的日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;

    @NotNull
    @DecimalMax(value = "0.1")
    @DecimalMin(value = "10000.00")
    private Double doubleValue = null;

    @Min(value = 1, message = "最小值为1")
    @Max(value = 88, message = "最大值为88")
    @NotNull
    private Integer integer;

    @Range(min = 1, max = 888, message = "范围为1~888")
    private Long range;

    @Email(message = "邮箱格式错误")
    private String email;

    @Size(min = 20, max = 30, message = "字符串长度要求为20~30")
    private String size;
}
