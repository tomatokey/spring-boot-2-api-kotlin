package com.prototype.architecture.layer_02_application.user.find;

import com.prototype.architecture.layer_03_domain.user.UserId;
import com.prototype.architecture.layer_03_domain.user.UserRepository;
import com.prototype.framework.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FindByIdUserUseCase implements Function<UserId, Optional<FindUserOutput>> {

    private final UserRepository userRepository;

    @Override
    public Optional<FindUserOutput> apply(UserId userId) {
        return userRepository.findById(userId).map(FindUserOutput::of);
    }

}
