package epam.jmp.muha.service.inter;

/**
 * Service for Security.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

public interface ISecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
