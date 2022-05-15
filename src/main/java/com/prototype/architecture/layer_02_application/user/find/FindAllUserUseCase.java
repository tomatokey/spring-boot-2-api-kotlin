package com.prototype.architecture.layer_02_application.user.find;

import com.prototype.architecture.layer_03_domain.user.UserRepository;
import com.prototype.framework.utils.CollectionUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FindAllUserUseCase implements Supplier<List<FindUserOutput>> {

    private final UserRepository userRepository;

    @Override
    public List<FindUserOutput> get() {
        return CollectionUtils.toList(userRepository.findAll()).stream()
                .map(FindUserOutput::of)
                .collect(Collectors.toList());
    }

}
