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

    public void brokerMessage(String message, ClientNode sender) {
        for (ClientNode client : clients) {
            client.receiveMessage(message, sender);
        }
    }
}
