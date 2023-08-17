package com.example.norza.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;

/**
 *  ShowDto를 안만드는 이유 : FreeBoard에서 가려야할 정보가 user id(@id)말고 없어서 비효율적이라 생각함
 *  UpdateDto를 안만드는 이유 : SaveDto와 사용 필드가 같아서 그대로 사용해도 됨
 *
 */

@Getter
@Setter
@NoArgsConstructor
public class FreeBoardSaveDto {

    @NotBlank
    private String title;

    @NotBlank
    @Lob
    private String content;
}
