package ru.hogwarts.school.Interface;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;

import java.io.IOException;

public interface AvatarService {
    void uploadAvatar(Long avatarId, MultipartFile file) throws IOException;

    Avatar findAvatar(Long avatarId);
}
