package iuniversity.model.user;

import java.time.LocalDate;

public class AdminImpl extends AbstractUser implements Admin {

    public AdminImpl() {
        super("admin", "admin", "admin", LocalDate.now(), Gender.UNSPECIFIED, "", 0);
    }

}
