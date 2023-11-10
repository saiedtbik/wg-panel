package com.panel.wg.restapi.api;

import com.panel.wg.restapi.BaseController;
import com.panel.wg.user.applicationservice.UserApplicationService;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.applicationservice.mapper.UserDataMapper;
import com.panel.wg.user.applicationservice.queries.GetUser;
import com.panel.wg.user.domain.dtoes.UserDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Tag(name = "User API")
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1/user")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
@RestController
public class UserController extends BaseController {

    private final UserApplicationService userApplicationService;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserCommand command) {
        UserDto createdUserDto = UserDataMapper.toUserDto(userApplicationService.createUser(command));
        return success(createdUserDto);
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("")
    public ResponseEntity<UserDto> findUser(@Valid GetUser query) {
        UserDto createdUserDto = UserDataMapper.toUserDto(userApplicationService.findUserByUserName(query.username()));
        return success(createdUserDto);
    }
}
