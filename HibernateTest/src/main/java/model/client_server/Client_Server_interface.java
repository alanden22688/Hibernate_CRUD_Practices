package model.client_server;

public interface Client_Server_interface {
	public void insert(String clientName,Integer... serverIds);
	public void update(Integer clientId,Integer... serverIds);
	public void delete(Integer clientId);
	public void findByClientId(Integer clientId);
	public void getAll();
}
