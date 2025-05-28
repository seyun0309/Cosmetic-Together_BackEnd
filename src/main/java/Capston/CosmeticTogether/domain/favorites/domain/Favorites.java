package Capston.CosmeticTogether.domain.favorites.domain;

import Capston.CosmeticTogether.domain.board.domain.Board;
import Capston.CosmeticTogether.domain.form.domain.Form;
import Capston.CosmeticTogether.domain.member.domain.Member;
import Capston.CosmeticTogether.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@SuperBuilder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "favorites")
public class Favorites extends BaseEntity {

    @JoinColumn(nullable = false)
    @ManyToOne
    private Member member;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Form form;

    @Column(nullable = false)
    @Setter
    private boolean isValid;
}
