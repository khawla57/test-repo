package esprit.CatchTalent.candidatservice.service;

import esprit.CatchTalent.candidatservice.dto.UserDto;
import esprit.CatchTalent.candidatservice.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserResponseDto getUserById(Long userId);
    List<UserResponseDto> getAllUsers();
    void deleteUser(Long userId);
    UserDto updateUser(Long userId, UserDto userDto);

}