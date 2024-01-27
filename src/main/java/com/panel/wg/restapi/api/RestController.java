package com.panel.wg.restapi.api;

import com.panel.wg.client.applicationservice.ClientApplicationService;
import com.panel.wg.client.applicationservice.commands.CreateTrafficCommand;
import com.panel.wg.client.applicationservice.data.ClientRepository;
import com.panel.wg.client.dataaccess.entities.ConfigEntity;
import com.panel.wg.client.dataaccess.repositories.ConfigJpaRepository;
import com.panel.wg.client.domain.dtoes.TrafficDto;
import com.panel.wg.client.domain.entities.Client;
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
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Tag(name = "User API")
@Validated
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1")
@CrossOrigin
@ResponseStatus(HttpStatus.OK)
@org.springframework.web.bind.annotation.RestController
public class RestController extends BaseController {

    private final UserApplicationService userApplicationService;
    private final ClientApplicationService clientApplicationService;
    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final ConfigJpaRepository configJpaRepository;

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
    @PostMapping("user/{clientId}/disable-client")
    public ResponseEntity<List<UserDto>> disableClient(@PathVariable("clientId") String clientId) {
        clientApplicationService.disableClient(clientId);
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/{clientId}/enable-client")
    public ResponseEntity<List<UserDto>> enableClient(@PathVariable("clientId") String clientId) {
        clientApplicationService.enableClient(clientId);
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("clients/enable")
    public ResponseEntity<List<UserDto>> enableAllClient() {
        clientApplicationService.enableAllClients();
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("user/{clientId}/client/rest-wg-transfer")
    public ResponseEntity<List<UserDto>> restTransferClient(@PathVariable("clientId") String clientId) {
        clientApplicationService.resetClientsWgTransfer(clientId);
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
    public ResponseEntity generateUserFromWgClients() {
        userApplicationService.creatAllUserFromWgClients();
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/add-endpoint")
    public ResponseEntity addConfig(@RequestBody String config) {
        ConfigEntity configEntity = new ConfigEntity();
        configEntity.setId(1L);
        configEntity.setUrl(config);
        configJpaRepository.save(configEntity);
        return success();
    }

    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/endpoint")
    public ResponseEntity getConfig() {
        String url = configJpaRepository.findById(1L).map(c -> c.getUrl()).get();
        return success(url);
    }


    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("clients/stop")
    public ResponseEntity stop() {
        clientApplicationService.stop();
        return success();
    }


    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("client/{clientId}/configs")
    @ResponseBody
    public ResponseEntity<byte[]> getFiles(@PathVariable("clientId") String clientId) throws IOException {

        Optional<Client> client = clientRepository.find(clientId);
        String clientName = client.get().getClientName();

        byte[] qrCodeResponse = clientApplicationService.getQRCodeFile(clientId);
        byte[] configResponse = clientApplicationService.getConfigFile(clientId);

        if (configJpaRepository.findById(1L).map(co -> co.getUrl()).isPresent()) {
            configResponse = replaceLastLine(configResponse, configJpaRepository.findById(1L).map(co -> co.getUrl()).get());
        }

        byte[] zipFile = createZipFile(qrCodeResponse, configResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("attachment").filename(clientName + ".zip").build());

        return new ResponseEntity<>(zipFile, headers, HttpStatus.OK);
    }


    private byte[] createZipFile(byte[] svgContent, byte[] textContent) throws IOException {
        // Create a ByteArrayOutputStream to hold the zip file content
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(baos)) {

            // Add SVG file to the zip file
            zipOutputStream.putNextEntry(new ZipEntry("QRCode.svg"));
            zipOutputStream.write(svgContent);
            zipOutputStream.closeEntry();

            // Add modified text file to the zip file
            zipOutputStream.putNextEntry(new ZipEntry("Config.conf"));
            zipOutputStream.write(textContent);
            zipOutputStream.closeEntry();

            // Finish and close the zip file
            zipOutputStream.finish();

            return baos.toByteArray();
        }
    }


    public static byte[] replaceLastLine(byte[] fileContent, String newLine) {
        try {
            // Convert the new line string to a byte array
            byte[] newLineBytes = newLine.getBytes();

            // Find the position of the last line
            int lastLinePosition = findLastLinePosition(fileContent);

            // Create a new byte array to store the modified content
            byte[] modifiedContent = new byte[lastLinePosition + newLineBytes.length];

            // Copy the original content up to the last line
            System.arraycopy(fileContent, 0, modifiedContent, 0, lastLinePosition);

            // Copy the new line to the modified content
            System.arraycopy(newLineBytes, 0, modifiedContent, lastLinePosition, newLineBytes.length);

            return modifiedContent;
        } catch (Exception e) {
            e.printStackTrace();
            return fileContent; // Return the original content in case of an error
        }
    }

    private static int findLastLinePosition(byte[] fileContent) {
        for (int i = fileContent.length - 1; i >= 0; i--) {
            if (fileContent[i] == '\n') {
                // Found the end of the last line
                return i + 1;
            }
        }

        // If there's only one line or an empty file, return 0
        return 0;
    }
}



