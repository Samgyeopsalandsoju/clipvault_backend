package com.samso.linkjoa.fork.presentation.web.request;

import com.samso.linkjoa.clip.domain.entity.Clip;
import com.samso.linkjoa.domain.member.Member;
import com.samso.linkjoa.fork.domain.entity.Fork;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ReqFork {

    private long clipId;
    private String clipTitle;
    private String clipLink;
    private String categoryName;
    private int categoryColor;
    private long forkId;

    public Fork toEntity(Clip clip, Member member) {

        return Fork.builder()
                .clipTitle(clip.getTitle())
                .clipLink(clip.getLink())
                .categoryName(clip.getCategory().getName())
                .categoryColor(clip.getCategory().getColor())
                .member(member)
                .clipId(clip.getId())
                .build();
    }
}
