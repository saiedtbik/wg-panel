package com.panel.wg.restapi.api;

import com.panel.wg.client.applicationservice.ClientApplicationService;
import com.panel.wg.client.applicationservice.commands.CreateTrafficCommand;
import com.panel.wg.client.domain.dtoes.TrafficDto;
import com.panel.wg.common.domain.exceptions.BusinessRuleViolationException;
import com.panel.wg.restapi.BaseController;
import com.panel.wg.user.applicationservice.UserApplicationService;
import com.panel.wg.user.applicationservice.commands.CreateUserCommand;
import com.panel.wg.user.applicationservice.queries.GetUser;
import com.panel.wg.user.domain.dtoes.UserDto;
import com.panel.wg.user.domain.exceptions.UserError;
import com.panel.wg.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Tag(name = "User API")
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
@RestController
public class ApplicationController extends BaseController {

    private final UserApplicationService userApplicationService;
    private final ClientApplicationService clientApplicationService;
    private final UserRepository userRepository;

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserCommand command) {
        UserDto createdUserDto = userApplicationService.createUser(command);
        return success(createdUserDto);
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/user")
    public ResponseEntity<UserDto> findUser(@Valid GetUser query) {
        Optional<UserDto> userDto = userRepository.findClientUserInfo(query.username());
        return success(userDto.orElseThrow(() -> new BusinessRuleViolationException(UserError.USER_NOT_FOUND)));
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("user/all")
    public ResponseEntity<List<UserDto>> finalAllUser() {
        List<UserDto> userList = userRepository.findAllClientUsersInfo();
        return success(userList);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/{username}/disable-client")
    public ResponseEntity<List<UserDto>> disableClient(@PathVariable("username") String username) {
        clientApplicationService.disableClient(username);
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/{username}/enable-client")
    public ResponseEntity<List<UserDto>> enableClient(@PathVariable("username") String username) {
        clientApplicationService.enableClient(username);
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("clients/enable")
    public ResponseEntity<List<UserDto>> enableAllClient() {
        clientApplicationService.enableAllClients();
        return success();
    }



    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/{username}/client/rest-wg-transfer")
    public ResponseEntity<List<UserDto>> restTransferClient(@PathVariable("username") String username) {
        clientApplicationService.resetClientsWgTransfer(username);
        return success();
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("clients/rest-wg-transfer")
    public ResponseEntity<List<UserDto>> resetTransferClients() {
        clientApplicationService.resetAllClientsWgTransfer();
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/add-traffic")
    public ResponseEntity addTraffic(@RequestBody CreateTrafficCommand command) {
        clientApplicationService.addTraffic(command);
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("user/{username}/traffics")
    public ResponseEntity<List<TrafficDto>> getUserTraffic(@PathVariable String username) {
        List<TrafficDto> trafficDtoList = clientApplicationService.getAllTraffics(username);
        return success(trafficDtoList);
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/create-from-wg-clients")
    public ResponseEntity generateUserFromWgClients(){
        userApplicationService.creatAllUserFromWgClients();
        return success();
    }

    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("clients/stop")
    public ResponseEntity stop() {
        clientApplicationService.stop();
        return success();
    }



}
