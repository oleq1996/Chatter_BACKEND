package pwsz.toik.chatter;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pwsz.toik.chatter.appuser.AppUser;
import pwsz.toik.chatter.appuser.AppUserRepository;
import pwsz.toik.chatter.appuser.AppUserRole;
import pwsz.toik.chatter.appuser.AppUserService;

@SpringBootApplication
public class ChatterApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(ChatterApplication.class, args);

		AppUserService appUserService = (AppUserService) app.getBean("appUserService");

		AppUser devUser = new AppUser("dev","dev","dev", AppUserRole.ADMIN);
		AppUser testUser = new AppUser("testuser","testuser","testuser", AppUserRole.USER);

		appUserService.signUpUser(devUser);
		appUserService.enableAppUser(devUser.getEmail());

		appUserService.signUpUser(testUser);
		appUserService.enableAppUser(testUser.getEmail());

	}

}
