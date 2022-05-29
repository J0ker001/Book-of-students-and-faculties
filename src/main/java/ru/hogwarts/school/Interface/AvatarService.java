package ru.hogwarts.school.Interface;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {
    void uploadAvatar(Long avatarId, MultipartFile file) throws IOException;

    Avatar findAvatar(Long avatarId);

    List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);
}
