package HW9.service;

import HW9.entity.Item;
import HW9.repository.ItemRepository;

import java.sql.SQLException;

public class ItemService {
    ItemRepository itemRepository = new ItemRepository();
    public int add(Item item) throws SQLException {
        return itemRepository.add(item);
    }
}
