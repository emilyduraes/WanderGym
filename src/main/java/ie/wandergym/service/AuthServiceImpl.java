package ie.wandergym.service;

import ie.wandergym.domain.dto.AuthDto;
import ie.wandergym.domain.dto.LoginDto;
import ie.wandergym.domain.entity.Auth;
import ie.wandergym.domain.enums.MsgLoginResponse;
import ie.wandergym.domain.request.AuthRequest;
import ie.wandergym.domain.request.LoginRequest;
import ie.wandergym.mapper.AuthenticationMapper;
import ie.wandergym.repository.AuthenticationRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final AuthenticationRepository repository;

    private static final AuthenticationMapper MAPPER = Mappers.getMapper(AuthenticationMapper.class);

    public AuthServiceImpl(AuthenticationRepository repository){
        this.repository = repository;
    }

    @Override
    public void register(AuthRequest request) {
        request.setPassword(passwordEncoder(request.getPassword()));
        Auth user = MAPPER.requestToEntity(request);
        repository.save(user);
    }

    @Override
    public LoginDto login(LoginRequest request) throws Exception {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new
                UsernamePasswordAuthenticationToken(request.getEmail(),
                request.getPassword());

        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            Auth user = (Auth) authentication.getPrincipal();
            AuthDto authDto = new AuthDto();
            authDto.setAuthorities(user.getAuthorities());
            authDto.setBusiness(user.getBusiness());
            authDto.setUser(user.getUser());
            authDto.setBasicAuthorization("Basic " +
                    Base64Utils.encodeToString(
                            String.format("%s:%s", request.getEmail(), request.getPassword())
                                    .getBytes()));

            return new LoginDto(HttpStatus.OK, MsgLoginResponse.OK, authDto);
        } catch (BadCredentialsException ex) {
            return new LoginDto(HttpStatus.BAD_REQUEST, MsgLoginResponse.NO_USER_WITH_USERNAME);
        }
    }

    @Override
    public LoginDto logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            HttpSession session = httpServletRequest.getSession();
            session.invalidate();
            new SecurityContextLogoutHandler().logout(
                    httpServletRequest,
                    httpServletResponse,
                    authentication);
        }
        return new LoginDto(HttpStatus.OK, MsgLoginResponse.OK);
    }

    public String passwordEncoder(String password) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder.encode(password);
    }
}
