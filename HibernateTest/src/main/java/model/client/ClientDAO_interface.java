package model.client;

import java.util.List;

public interface ClientDAO_interface {
	public void insert(ClientVO clientVO);
	public void update(ClientVO clientVO);
	public void delete(Integer id);
	public void findByPrimaryKey(Integer id);
	public List<ClientVO> getAll();
}
