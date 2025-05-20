package com.muthamizh.blog.mappers;

import com.muthamizh.blog.domain.dtos.TagDto;
import com.muthamizh.blog.domain.entities.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-19T14:22:16+0530",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.5 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDto toTagResponse(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto.TagDtoBuilder tagDto = TagDto.builder();

        tagDto.postCount( calculatePostCount( tag.getPosts() ) );
        tagDto.id( tag.getId() );
        tagDto.name( tag.getName() );

        return tagDto.build();
    }

    @Override
    public List<TagDto> toTagResponseList(List<Tag> tags) {
        if ( tags == null ) {
            return null;
        }

        List<TagDto> list = new ArrayList<TagDto>( tags.size() );
        for ( Tag tag : tags ) {
            list.add( toTagResponse( tag ) );
        }

        return list;
    }
}
