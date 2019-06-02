package pl.gjahn.miniapp.model.dto;

import lombok.Data;
import pl.gjahn.miniapp.model.entity.PostEntity;
import pl.gjahn.miniapp.model.entity.UserEntity;

@Data
public class PostDto {

    private String title;
    private String content;
    private int userId;

    public static PostEntity convertToEntity (PostDto postDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(postDto.getUserId());

        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity);

        postEntity.setPostText(postDto.content);
        postEntity.setPostName(postDto.title);

        return postEntity;
    }
}
