package com.sixessense.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp
    @Column(updatable = false)  // 수정시 관여 안하겠다는 뜻
    private LocalDateTime createdTime;

    @UpdateTimestamp
    @Column(insertable = false) // insert시 관여 안하겠다는 뜻
    private LocalDateTime updatedTime;

}
