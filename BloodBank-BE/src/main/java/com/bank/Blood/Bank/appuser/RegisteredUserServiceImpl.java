package com.bank.Blood.Bank.appuser;

import com.bank.Blood.Bank.appuser.token.ConfirmationToken;
import com.bank.Blood.Bank.appuser.token.ConfirmationTokenServiceImpl;
import com.bank.Blood.Bank.email.EmailSender;
import com.bank.Blood.Bank.model.LoyaltyCard;
import com.bank.Blood.Bank.model.RegisteredUser;
import com.bank.Blood.Bank.model.Role;
import com.bank.Blood.Bank.repository.AddressRepository;
import com.bank.Blood.Bank.repository.LoyaltyCardRepository;
import com.bank.Blood.Bank.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegisteredUserServiceImpl implements RegisteredUserService {


    private LoyaltyCardRepository loyaltyCardRepository;
    private AddressRepository addressRepository;
    private RegisteredUserRepository registeredUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenServiceImpl confirmationTokenService;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;
    private final RoleRepository roleRepository;


    @Autowired
    public RegisteredUserServiceImpl(EmailSender emailSender, EmailValidator emailValidator, ConfirmationTokenServiceImpl confirmationTokenService, BCryptPasswordEncoder bCryptPasswordEncoder, LoyaltyCardRepository loyaltyCardRepository, RegisteredUserRepository registeredUserRepository, AddressRepository addressRepository,
                                     RoleRepository roleRepository) {
        this.loyaltyCardRepository = loyaltyCardRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.registeredUserRepository = registeredUserRepository;
        this.addressRepository = addressRepository;
        this.confirmationTokenService = confirmationTokenService;
        this.emailValidator = emailValidator;
        this.emailSender = emailSender;
        this.roleRepository = roleRepository;
    }


    @Override
    public List<RegisteredUser> findAll() {
        return registeredUserRepository.findAll();
    }

    @Override
    public RegisteredUser findOne(int id) {
        Optional<RegisteredUser> user = registeredUserRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    @Override
    public RegisteredUser save(RegisteredUser registeredUser) throws IllegalAccessException {
        boolean isValidEmail = emailValidator.test(registeredUser.getUsername());
        if(!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        boolean userExists = registeredUserRepository.findByUsername(registeredUser.getUsername())
                .isPresent();
        if (userExists){
            throw new IllegalAccessException("Email already taken!");
        }
        String encodedPassword = bCryptPasswordEncoder.encode((registeredUser.getPassword()));
        registeredUser.setPassword(encodedPassword);
        registeredUser.setPoints(0);
        Optional<LoyaltyCard> loyaltyCard = loyaltyCardRepository.findById(1);
        registeredUser.setLoyaltyCard(loyaltyCard.get());
        List<Role> roles = roleRepository.findByName("USER");
        registeredUser.setRoles(roles);
        addressRepository.save(registeredUser.getAddress());

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationTokenoken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                registeredUser
        );
        RegisteredUser registeredUser1 = registeredUserRepository.save(registeredUser);
        confirmationTokenService.saveConfirmationToken(confirmationTokenoken);

        String link = "http://localhost:8082/api/registeredUsers/confirm?token=" + token;
        emailSender.send(registeredUser.getUsername(),buildEmail(registeredUser.getFirstName(), link));
        return registeredUser1;
    }


    @Override
    public RegisteredUser update(RegisteredUser registeredUser, Integer id){
        Optional<RegisteredUser> optExistingUser = registeredUserRepository.findById(id);
        RegisteredUser existingUser = optExistingUser.get();
        existingUser.setFirstName(registeredUser.getFirstName());
        existingUser.setLastName(registeredUser.getLastName());
        existingUser.setGender(registeredUser.getGender());
        existingUser.setUmcn(registeredUser.getUmcn());
        existingUser.setPhoneNumber(registeredUser.getPhoneNumber());
        existingUser.setInstitution(registeredUser.getInstitution());
        existingUser.setUsername(registeredUser.getUsername());
        existingUser.setAddress(registeredUser.getAddress());
        //existingUser.setPassword(registeredUser.getPassword());
        RegisteredUser editedUser = registeredUserRepository.save(existingUser);
        return editedUser;
    }

    @Override
    public RegisteredUser updatePassword(RegisteredUser registeredUser, Integer id) {
        Optional<RegisteredUser> optExistingUser = registeredUserRepository.findById(id);
        RegisteredUser existingUser = optExistingUser.get();
        //if(registeredUser.getPassword()==)
        existingUser.setPassword(registeredUser.getPassword());
        RegisteredUser editedUser = registeredUserRepository.save(existingUser);
        return editedUser;
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableRegisteredUser(
                confirmationToken.getAppUser().getUsername());
        return "confirmed";
    }

    public int enableRegisteredUser(String email) {
        return registeredUserRepository.enableRegisteredUser(email);
    }
}
