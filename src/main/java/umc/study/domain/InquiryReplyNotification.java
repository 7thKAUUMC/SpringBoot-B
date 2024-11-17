package umc.study.domain;

import lombok.*;
import jakarta.persistence.*;
import umc.study.domain.common.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class InquiryReplyNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_reply_id", nullable = false)
    private InquiryReply inquiryReply;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(name = "is_confirmed", nullable = false)
    private Boolean isConfirmed;
}
