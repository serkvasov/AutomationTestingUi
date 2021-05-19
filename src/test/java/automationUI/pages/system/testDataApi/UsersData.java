package automationUI.pages.system.testDataApi;

import automationUI.pages.system.config.TestConfig;

import java.util.Optional;

public class UsersData {
    public User returnUser(String role) {
        String filePath = TestConfig.getInstance().getUsersPath();
        JsonData<Users> jsonData = new JsonData(new Users());
        Users users = jsonData.getData(filePath);
        Optional<User> user = users.users.stream().filter(it -> it.getUserRole().equals(role)).findFirst();
        if (!user.isEmpty()) {
            return user.get();
        }
        throw new RuntimeException("Не найден клиент " + role);
    }
}

