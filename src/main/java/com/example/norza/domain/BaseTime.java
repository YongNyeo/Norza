package com.example.norza.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//생성,수정일을 모든 엔티티에 적용을 도와주는 클래스 생성
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class BaseTime {

    @LastModifiedDate
    private LocalDateTime modifiedTime;
    @CreatedDate
    private LocalDateTime createdTime;

}
