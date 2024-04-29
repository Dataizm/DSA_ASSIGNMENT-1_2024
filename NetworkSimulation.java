import java.util.ArrayList;
import java.util.List;

    // PrimaryServer class
    class ServerNode {
        private List<ClientNode> clients;
        private String serverName;

        public ServerNode(String name) {
            this.serverName = name;
            this.clients = new ArrayList<>();
        }

        public void addClient(ClientNode client) {
            clients.add(client);
        }
            public void processMessage(String message, ClientNode sender) {
                for (ClientNode client : clients) {
                    client.receiveMessage(message, sender);
                }
            }
        }


        // ClientDevice class
        class ClientNode {
            private String clientId;

            public ClientNode(String id) {
                this.clientId = id;

            }

            public void sendMessage(String message, ServerNode server) {
                server.processMessage(message, this);
            }

            public void receiveMessage(String message, ClientNode sender) {
                System.out.println(clientId + " received: '" + message + "' from " + sender.clientId);
            }
        }
// StarNetwork class
class StarNetwork {
    private List<ClientNode> clients;
    private ServerNode server;

    public StarNetwork() {
        this.clients = new ArrayList<>();
        this.server = new ServerNode("CentralServer");
    }

    public void addNode(ClientNode client) {
        clients.add(client);
        server.addClient(client);
    }

    public void removeNode(ClientNode client) {
        clients.remove(client);
    }
}
public class NetworkSimulation {
    public static void main(String[] args) {
        // Creating instances of ClientDevice and PrimaryServer
        ClientNode device1 = new ClientNode("Device1");
        ClientNode device2 = new ClientNode("Device2");
        ClientNode device3 = new ClientNode("Device3");

        StarNetwork starNet = new StarNetwork();

        // Inserting nodes into the star network
        starNet.addNode(device1);
        starNet.addNode(device2);
        starNet.addNode(device3);

        // Sending messages between clients
        device1.sendMessage("Hello from Device1", starNet.server);
        device2.sendMessage("Hi from Device2", starNet.server);
        device3.sendMessage("Greetings from Device3", starNet.server);
    }
}
    }
