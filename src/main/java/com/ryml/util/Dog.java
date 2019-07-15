package com.ryml.util;

import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description:
 *
 * @author 刘一博
 * @version V1.0
 * @date 2019/7/14
 */
@Builder
public class Dog {
    @NotNull(message = "不能爲空")
    private Integer id;
    @NotBlank(message = "name不能爲空")
    private String name;
    @Max(value = 250,message = "金額不能超過250")
    private Double ammount;
    @NotBlank(message = "手機號不能爲空")
    @Length(max = 11,message = "手机号不能大于11位")
    private String phoneNum;
    @AssertFalse(message = "性別不能為true")
    private Boolean gender;
    @NotEmpty(message = "intlist不能爲空")
    private List<Integer> integers;
    @IsEmpty(message = "哈哈哈,集合不能为空")
    private List<Long> longs;

}
