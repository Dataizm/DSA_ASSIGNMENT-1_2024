package DSA_Assignement;

class ClientNode {
    private String clientId;

    public ClientNode(String id) {
        this.clientId = id;
    }

    public void send(String message, ServerNode server) {
        server.brokerMessage(message, this);
    }

    public void receiveMessage(String message, ClientNode sender) {
        System.out.println(clientId + " received: '" + message + "' from " + sender.clientId);
    }
}
