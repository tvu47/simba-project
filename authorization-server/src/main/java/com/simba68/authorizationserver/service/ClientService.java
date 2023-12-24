package com.simba68.authorizationserver.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

import com.simba68.authorizationserver.dto.CreateClientDto;
import com.simba68.authorizationserver.dto.MessageDto;
import com.simba68.authorizationserver.entity.Client;
import com.simba68.authorizationserver.repository.ClientRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService implements RegisteredClientRepository {
    private final ClientRepo clientRepo;
    private final PasswordEncoder passwordEncoder;

    private Client clientFromDto(CreateClientDto clientDto) {
        Client client = Client.builder()
                .clientId(clientDto.getClientId())
                .clientSecret(passwordEncoder.encode(clientDto.getClientSecret()))
                .authenticationMethods(clientDto.getAuthenticationMethods())
                .authorizationGrantTypes(clientDto.getAuthorizationGrantTypes())
                .redirectUris(clientDto.getRedirectUris())
                .postLogoutRedirectUris(clientDto.getPostLogoutRedirectUris())
                .scopes(clientDto.getScopes())
                .requireProofKey(clientDto.isRequireProofKey())
                .build();

        return client;

    }

    public MessageDto create(CreateClientDto clientDto) {
        Client client = clientFromDto(clientDto);
        clientRepo.save(client);
        return new MessageDto("client " + client.getClientId() + " saved");
    }

    @Override
    public void save(RegisteredClient registeredClient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public RegisteredClient findById(String id) {
        Client client = clientRepo.findByClientId(id).orElseThrow(() -> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Client client = clientRepo.findByClientId(clientId).orElseThrow(() -> new RuntimeException("Client not found"));
        return Client.toRegisteredClient(client);
    }
}
