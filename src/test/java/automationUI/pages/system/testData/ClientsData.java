package automationUI.pages.system.testData;

import automationUI.pages.system.config.TestConfig;

import java.util.Optional;

public class ClientsData {
    public Client returnClient(String clientRole) {
        String filePath = TestConfig.getInstance().getClientsPath();
        JsonData<Clients> jsonData = new JsonData(new Clients());
        Clients clients = jsonData.getData(filePath);
        Optional<Client> client = clients.clients.stream().
                filter(it -> it.getClientRole().equals(clientRole)).findFirst();
        if (!client.isEmpty()) {
            return client.get();
        }
        throw new RuntimeException("Не найден клиент " + clientRole);
    }
}
