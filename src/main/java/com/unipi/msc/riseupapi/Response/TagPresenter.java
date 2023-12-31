package com.unipi.msc.riseupapi.Response;

import com.unipi.msc.riseupapi.Model.Tag;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagPresenter {
    private Long id;
    private String name;
    private String color;
    public static TagPresenter getPresenter(Tag tag){
        return TagPresenter.builder()
                .id(tag.getId())
                .name(tag.getName())
                .color(tag.getColor())
                .build();
    }
    public static List<TagPresenter> getPresenter(List<Tag> tags){
        if (tags == null) return null;
        List<TagPresenter> presenters = new ArrayList<>();
        tags.forEach(tag -> presenters.add(getPresenter(tag)));
        return presenters;
    }
}
