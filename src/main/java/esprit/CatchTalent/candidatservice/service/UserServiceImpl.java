package esprit.CatchTalent.candidatservice.service;

import esprit.CatchTalent.candidatservice.dto.UserDto;
import esprit.CatchTalent.candidatservice.dto.UserResponseDto;
import esprit.CatchTalent.candidatservice.entities.ERole;
import esprit.CatchTalent.candidatservice.entities.Role;
import esprit.CatchTalent.candidatservice.entities.User;
import esprit.CatchTalent.candidatservice.repositories.RoleRepository;
import esprit.CatchTalent.candidatservice.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
        private RoleRepository roleRepository;


   /* public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        configureModelMapper();
    }
    private void configureModelMapper() {

        modelMapper.typeMap(User.class, UserResponseDto.class)
                .addMapping(src -> src.getFirstname(), UserResponseDto::setFirstname)
                .addMapping(src -> src.getLastname(), UserResponseDto::setLastname);
    }*/
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return modelMapper.map(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public UserDto updateUser(Long userId, UserDto userDto) {

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));


        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());


        User updatedUser = userRepository.save(existingUser);

        return modelMapper.map(updatedUser, UserDto.class);
    }


}