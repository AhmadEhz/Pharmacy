package HW9.service;

import HW9.entity.Item;
import HW9.repository.ItemRepository;

import java.sql.SQLException;

public class ItemService {
    ItemRepository itemRepository = new ItemRepository();

    public void add(Item item, int prescriptionId) throws SQLException {
        itemRepository.add(item, prescriptionId);
    }
    public void remove(int id) throws SQLException {
        itemRepository.remove(id);
    }
    public void edit(Item item) throws SQLException {
        itemRepository.edit(item);
    }
}
