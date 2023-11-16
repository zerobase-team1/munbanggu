package com.zerobase.munbanggu.study.model.entity;

import com.zerobase.munbanggu.study.type.AccessType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long study_id; // 스터디아이디

    private Long user_id;

    @Builder.Default
    private boolean done = false; // 체s크리스트 완료 여부

    private String todo; //할일

    @Enumerated(EnumType.STRING)
    private AccessType accessType;  //타입 - 스터디/개인

}
