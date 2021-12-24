package model.server;

import java.util.List;

public interface ServerDAO_interface {
	public void insert(ServerVO serverVO);
	public void update(ServerVO serverVO);
	public void delete(Integer id);
	public void findByPrimaryKey(Integer id);
	public List<ServerVO> getAll();
}
