package com.example.Hueta.repos;

import com.example.Hueta.Domain.Message;
import com.example.Hueta.Domain.User;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface UserRepo extends CrudRepository<User, Long> {
}
