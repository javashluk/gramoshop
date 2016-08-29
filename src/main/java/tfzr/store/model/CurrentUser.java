package tfzr.store.model;

import org.springframework.security.core.authority.AuthorityUtils;

import lombok.Getter;

@Getter
public class CurrentUser extends org.springframework.security.core.userdetails.User {
	
	private static final long serialVersionUID = 6547379243155815370L;
	private User user;
	
	public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }	
}