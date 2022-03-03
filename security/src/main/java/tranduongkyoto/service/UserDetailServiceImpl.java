package tranduongkyoto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tranduongkyoto.User;
import tranduongkyoto.UserRepository;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null){
            return (UserDetails) user;
        }
        throw new UsernameNotFoundException("User "+ username +" not found");
    }

}
