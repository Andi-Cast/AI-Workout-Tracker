package backend.AIGymTracker.controller;

import backend.AIGymTracker.dto.RegisterRequest;
import backend.AIGymTracker.dto.UpdateUserRequest;
import backend.AIGymTracker.entity.User;
import backend.AIGymTracker.service.AuthorizationService;
import backend.AIGymTracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthorizationService authorizationService;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        authorizationService.validateUserAccess(id, "profile");
        return userService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<?> createUser(
            @Valid @RequestBody RegisterRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        var user = userService.saveUser(request);
        var uri = uriComponentsBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest request) {
        authorizationService.validateUserAccess(id, "profile");
        var updatedUser = userService.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        authorizationService.validateUserAccess(id, "account");
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
