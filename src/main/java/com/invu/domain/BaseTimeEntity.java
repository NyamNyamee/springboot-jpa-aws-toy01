package com.invu.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass  // 다른 엔티티클래스들이 해당 클래스를 상속받으면 아래 필드들도 컬럼으로 인식하게 됨
@EntityListeners(AuditingEntityListener.class)  // 이 엔티티에 Auditing기능 추가
public class BaseTimeEntity {

    @CreatedDate  // 생성일시
    private LocalDateTime createdDate;

    @LastModifiedDate  // 변경일시
    private LocalDateTime modifiedDate;
}
