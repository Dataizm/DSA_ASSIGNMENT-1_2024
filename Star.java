class Star {
    private List<ClientNode> clients;
    private ServerNode server;

    public Star() {
        this.clients = new ArrayList<>();
        this.server = new ServerNode("CentralServer");
    }

    public void insertNode(ClientNode client) {
        clients.add(client);
        server.addClient(client);
    }

    public void deleteNode(ClientNode client) {
        clients.remove(client);
    }

    public ServerNode getServer() {
        return server;
    }
}
